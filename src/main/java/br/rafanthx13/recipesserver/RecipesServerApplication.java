package br.rafanthx13.recipesserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipesServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipesServerApplication.class, args);
	}

}

/*
`mvn install`

`mvn spring-boot:run`

https://www.websparrow.org/spring/spring-boot-display-image-from-database-and-classpath






====================
	Outro caso
====================
// REPOSITORY

package com.javasampleapproach.saveimage2mysql.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javasampleapproach.saveimage2mysql.model.ImageModel;

public interface ImageRepository extends JpaRepository<ImageModel, Long>{
}

====================
	CONTROLLER
====================

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import com.javasampleapproach.saveimage2mysql.jpa.ImageRepository;
import com.javasampleapproach.saveimage2mysql.model.ImageModel;


@SpringBootApplication
public class SpringJpaSaveImage2MySqlApplication implements CommandLineRunner{

	@Autowired
	ImageRepository imageRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJpaSaveImage2MySqlApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// image 1
		ClassPathResource backImgFile = new ClassPathResource("image/jsa_about_img_black_background.png");
		byte[] arrayPic = new byte[(int) backImgFile.contentLength()];
		backImgFile.getInputStream().read(arrayPic);
		ImageModel blackImage = new ImageModel(1, "JSA-ABOUT-IMAGE-BLACK-BACKGROUND", "png", arrayPic);
		
		// image 2
		ClassPathResource blueImgFile = new ClassPathResource("image/jsa_about_img_blue_background.png");
		arrayPic = new byte[(int) blueImgFile.contentLength()];
		blueImgFile.getInputStream().read(arrayPic);
		ImageModel blueImage = new ImageModel(2, "JSA-ABOUT-IMAGE-BLUE-BACKGROUND", "png", arrayPic);
		
		// store image to MySQL via SpringJPA
		imageRepository.save(blackImage);
		imageRepository.save(blueImage);
		
		// retrieve image from MySQL via SpringJPA
		for(ImageModel imageModel : imageRepository.findAll()){
			Files.write(Paths.get("retrieve-dir/" + imageModel.getName() + "." + imageModel.getType()), imageModel.getPic());
		}
	}
}
*/
