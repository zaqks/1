package com.zaqksdev.el_meyloud_RE.controllers.client.utils.storage;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.zaqksdev.el_meyloud_RE.models.dtos.property.PropertyCreateDTO;
import com.zaqksdev.el_meyloud_RE.models.entities.Property;
import com.zaqksdev.el_meyloud_RE.services.repos.PropertyRepo;

import java.util.Date;
import java.util.List;

public class Storage {
    public String saveImage(MultipartFile image) {
        // Date createdAt = new Date();
        String dst = "/images/properties/" + new Date() + "_" + image.getOriginalFilename();

        try {
            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get("public" + dst), StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (Exception e) {
        }

        return dst;
    }

    public void saveAllImages(Property property,  ArrayList<MultipartFile> imgs) {
        MultipartFile current;

        for (int i = 0; i < imgs.size(); i++) {
            current = imgs.get(i);
            if (current.isEmpty())
                break;

            property.getImgs().add(saveImage(current));

        }

    }

    public Boolean existingProperty(PropertyRepo propertyRepo, BindingResult result, PropertyCreateDTO property) {
        // check the addr
        if (propertyRepo.findByAddr(property.getAddr()) != null) {
            result.rejectValue("addr", null, "already existing");
            return true;
        }
        // check the coords
        float x = property.getX();
        float y = property.getY();
        int i;

        List<Property> xlst = propertyRepo.findByX(x);
        List<Property> ylst = propertyRepo.findByX(y);

        for (i = 0; i < xlst.size(); i++) {
            if (xlst.get(i).getY() == y) {
                result.rejectValue("x", null, "already existing");
                result.rejectValue("y", null, "already existing");
                return true;
            }
        }

        for (i = 0; i < ylst.size(); i++) {
            if (ylst.get(i).getX() == x) {
                result.rejectValue("x", null, "already existing");
                result.rejectValue("y", null, "already existing");
                return true;
            }
        }

        return false;
    }


}
