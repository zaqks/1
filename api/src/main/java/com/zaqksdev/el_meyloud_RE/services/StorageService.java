package com.zaqksdev.el_meyloud_RE.services;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zaqksdev.el_meyloud_RE.models.Property;

import java.util.Date;

@Service
public class StorageService {
    public String saveImage(MultipartFile image) {
        // Date createdAt = new Date();
        String dst = "/images/properties/" + new Date() + "_" + image.getOriginalFilename();
        dst = dst.replace(" ", "_");
        try {
            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get("public" + dst), StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (Exception e) {

        }

        return dst;
    }

    public void saveAllImages(Property property, ArrayList<MultipartFile> imgs) {
        MultipartFile current;

        for (int i = 0; i < imgs.size(); i++) {
            current = imgs.get(i);
            if (current.isEmpty())
                break;

            property.getImgs().add(saveImage(current));

        }

    }

}
