package com.zaqksdev.el_meyloud_RE.models.dtos.auth;

import com.zaqksdev.el_meyloud_RE.models.entities.Client;
import com.zaqksdev.el_meyloud_RE.services.repos.ClientRepo;

import jakarta.validation.constraints.Size;

public class ClientKeyDTO {
    // @Autowired
    private ClientRepo repo;

    @Size(min = 12, message = "Invalid email")
    private String email;

    @Size(min = 8, message = "Invalid password")
    private String password;

    public ClientKeyDTO() {
    }

    public ClientKeyDTO(ClientRepo repo, String email, String password) {
        this.repo = repo;
        this.email = email;
        this.password = password;
    }

    public Boolean checkAuth() {
        Client rslt = repo.findByEmail(email);
        if (rslt != null) {
            return rslt.getPassword().equals(password);
        }

        return false;
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
