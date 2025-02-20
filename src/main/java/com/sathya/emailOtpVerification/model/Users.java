package com.sathya.emailOtpVerification.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "USERS")
public class Users {

    private Long userId;
    private String username;
    private String email;
    private String password;
    private boolean verified;
}
