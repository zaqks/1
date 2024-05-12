package com.zaqksdev.el_meyloud_RE.dtos.offer;

import com.zaqksdev.el_meyloud_RE.models.Client;
import com.zaqksdev.el_meyloud_RE.models.Offer;
import com.zaqksdev.el_meyloud_RE.models.Property;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class OfferCreateDTO {
    @Min(value = 10000, message = "too small")
    private int price;
    private boolean rent;

    public Offer convertToEntity(Client owner, Property property) {
        Offer rslt = new Offer();

        rslt.setPrice(price);
        rslt.setRent(rent);
        rslt.setDescription(description);
        rslt.setProperty(property);

        rslt.setChecked(false);
        rslt.setAvlbl(false);

        return rslt;
    }

    @Size(min = 16, max = 256, message = "invalid")
    private String description;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isRent() {
        return rent;
    }

    public void setRent(boolean rent) {
        this.rent = rent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
