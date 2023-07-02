package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.example.validator.IpAddress;

@Entity
public class Author extends BaseEntity<Long> {

//    @NotBlank
    @Email
    private String email;
//    @NotNull
    @IpAddress(message = "Not Valid IP Address")
    private String ipAddress;
    public Author() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
