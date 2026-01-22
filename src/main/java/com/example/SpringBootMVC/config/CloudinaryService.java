package com.example.SpringBootMVC.config;


import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadImage(MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), Map.of());
            return uploadResult.get("public_id").toString();
        } catch (IOException e) {
            throw new RuntimeException("Image upload failed");
        }
    }

    public String buildImageUrl(String publicId) {
        return cloudinary.url()
                .secure(true)
                .generate(publicId);
    }

}
