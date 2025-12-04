package com.ia.api.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import com.ia.api.Service.AiService;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private AiService aiService;

    public Image saveImage(MultipartFile file) throws IOException {
        String classification = aiService.classifyImage(file);
        Image image = Image.builder()
                .name(file.getOriginalFilename())
                .classification(classification)
                .contentType(file.getContentType())
                .data(file.getBytes())
                .build();
        return imageRepository.save(image);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }
}
