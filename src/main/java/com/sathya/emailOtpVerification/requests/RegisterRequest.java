package com.sathya.emailOtpVerification.requests;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class RegisterRequest {

    private String username;
    private String email;
    private String password;


}
