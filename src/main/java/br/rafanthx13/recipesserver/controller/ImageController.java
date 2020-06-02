package br.rafanthx13.recipesserver.controller;

import br.rafanthx13.recipesserver.model.dto.ResponseImageDTO;
import br.rafanthx13.recipesserver.model.entity.Image;
import br.rafanthx13.recipesserver.model.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


@RestController // Controlador Rest
@RequestMapping("/images") // Base URL
@RequiredArgsConstructor // Gera e injeta os objetos privados
public class ImageController {

    @Autowired
    ImageRepository imageRepository;

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    public ResponseImageDTO uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        Image img = new Image(
                file.getOriginalFilename(),
                file.getContentType(),
                compressBytes(file.getBytes()));
        Optional<Image> repeatImage = imageRepository.findByFileName(file.getOriginalFilename());
        ResponseImageDTO responseImageDTO = new ResponseImageDTO();
        if(repeatImage.isPresent()){
            responseImageDTO.setImageId(repeatImage.get().getId());
            responseImageDTO.setRepeat(true);
            return responseImageDTO;
        }
        Image postedImage = imageRepository.save(img);
        responseImageDTO.setImageId(postedImage.getId());
        responseImageDTO.setRepeat(false);
        return responseImageDTO;
        // Deve retornar Id para ser usado depois, se já tiver a imagem, entoa so volta o ID
    }

    @GetMapping(path = { "/get/id/{id}" })
    public Image getStorageImageById(@PathVariable("id") Long id) {
        Image retrievedImage = imageRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Busca de Imagem por ID nao encontrada"));

        Image img = new Image(
                retrievedImage.getFileName(),
                retrievedImage.getFileType(),
                decompressBytes(retrievedImage.getData())
        );
        img.setId(retrievedImage.getId());
        return img;
    }


    @GetMapping(path = { "/get/{imageName}" })
    public Image getStorageImage(@PathVariable("imageName") String imageName) {
        Image retrievedImage = imageRepository.findByFileName(imageName)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));

        Image img = new Image(
                retrievedImage.getFileName(),
                retrievedImage.getFileType(),
                decompressBytes(retrievedImage.getData())
        );
        img.setId(retrievedImage.getId());
        return img;
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro IO");
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error IOException");
        } catch (DataFormatException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error DataFormatException");
        }
        return outputStream.toByteArray();
    }
}
