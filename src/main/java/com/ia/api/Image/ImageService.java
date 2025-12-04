package com.ia.api.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import com.ia.api.Service.AiService;

@Service
@Transactional
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private AiService aiService;

    @Autowired
    private com.ia.api.User.UserRepository userRepository;

    public Image saveImage(MultipartFile file, String username) throws IOException {
        String classification = aiService.classifyImage(file);
        
        com.ia.api.User.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Image image = Image.builder()
                .name(file.getOriginalFilename())
                .classification(classification)
                .contentType(file.getContentType())
                .data(file.getBytes())
                .user(user)
                .build();
        return imageRepository.save(image);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public List<Image> getImagesByUser(String username) {
        return imageRepository.findByUserUsername(username);
    }
}
