package com.zaqksdev.el_meyloud_RE.models.dtos.property;

import org.springframework.web.multipart.MultipartFile;
import com.zaqksdev.el_meyloud_RE.models.entities.Property;

public class PropertyCreateDTO extends Property {
    private MultipartFile img1;
    private MultipartFile img2;
    private MultipartFile img3;
    private MultipartFile img4;
    private MultipartFile img5;

    public Property convertToEntity() {
        Property rslt = new Property();

        rslt.setAddr(this.getAddr());
        rslt.setX(this.getX());
        rslt.setY(this.getY());
        rslt.setSurf(this.getSurf());
        rslt.setFloors(this.getFloors());
        rslt.setGrgs(this.getGrgs());
        rslt.setPools(this.getPools());
        rslt.setRooms(this.getRooms());
        
        return rslt;
    }

    public MultipartFile getImg1() {
        return img1;
    }

    public void setImg1(MultipartFile img1) {
        this.img1 = img1;
    }

    public MultipartFile getImg2() {
        return img2;
    }

    public void setImg2(MultipartFile img2) {
        this.img2 = img2;
    }

    public MultipartFile getImg3() {
        return img3;
    }

    public void setImg3(MultipartFile img3) {
        this.img3 = img3;
    }

    public MultipartFile getImg4() {
        return img4;
    }

    public void setImg4(MultipartFile img4) {
        this.img4 = img4;
    }

    public MultipartFile getImg5() {
        return img5;
    }

    public void setImg5(MultipartFile img5) {
        this.img5 = img5;
    }

}
