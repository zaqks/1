package com.zaqksdev.el_meyloud_RE.dtos.auth;

import com.zaqksdev.el_meyloud_RE.repos.ClientRepo;

import jakarta.validation.constraints.Size;

public class ClientKeyDTO {
    @Size(min = 12, message = "Invalid email")
    private String email;

    @Size(min = 8, message = "Invalid password")
    private String password;

    public ClientKeyDTO() {
    }

    public ClientKeyDTO(ClientRepo repo, String email, String password) {
        this.email = email;
        this.password = password;
    }



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
