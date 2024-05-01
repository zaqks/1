package com.zaqksdev.el_meyloud_RE.models.dtos.auth;


import jakarta.validation.constraints.Size;

public class ClientKeyDTO {
    
    @Size(min = 12, message = "Invalid email")
    private String email;

    @Size(min = 8, message = "Invalid password")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
