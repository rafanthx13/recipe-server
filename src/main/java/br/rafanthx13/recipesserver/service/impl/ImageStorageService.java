package br.rafanthx13.recipesserver.service.impl;

import org.springframework.stereotype.Service;

@Service
public class ImageStorageService {

//    @Autowired
//    private ImageRepository dbFileRepository;
//
//    public Image storeFile(MultipartFile file) {
//        // Normalize file name
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//        try {
//            // Check if the file's name contains invalid characters
//            if(fileName.contains("..")) {
//                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//            }
//
//            Image dbFile = new Image(fileName, file.getContentType(), file.getBytes());
//
//            return dbFileRepository.save(dbFile);
//        } catch (IOException ex) {
//            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
//        }
//    }
//
//    public Image getFile(String fileId) {
//        return dbFileRepository.findById(fileId)
//                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
//    }
}