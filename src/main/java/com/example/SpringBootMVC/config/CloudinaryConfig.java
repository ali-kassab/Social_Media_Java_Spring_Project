package com.example.SpringBootMVC.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(Map.of(
                "cloud_name", "dml6pjxs7",
                "api_key", "745332867494689",
                "api_secret", "FA-jAcqaooleuKAoMo-tqsfN_lk"
        ));
    }
}
