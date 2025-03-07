package com.sathya.emailOtpVerification.service;

import com.sathya.emailOtpVerification.model.Users;
import com.sathya.emailOtpVerification.requests.RegisterRequest;
import com.sathya.emailOtpVerification.responses.RegisterResponse;

public interface UserService {

    RegisterResponse register (RegisterRequest registerRequest);

    void verify(String email, String otp);

    Users login(String email, String password);
}
