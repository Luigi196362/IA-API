package com.ia.api.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file, @RequestParam("username") String username) {
        try {
            imageService.saveImage(file, username);
            return ResponseEntity.status(HttpStatus.CREATED).body("{\"message\": \"Image uploaded and classified successfully\"}");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image: " + e.getMessage());
        } catch (com.ia.api.Service.AiServiceException e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("AI Service Error: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Image>> getAllImages(@RequestParam("username") String username) {
        List<Image> images = imageService.getImagesByUser(username);
        return ResponseEntity.ok(images);
    }
}
