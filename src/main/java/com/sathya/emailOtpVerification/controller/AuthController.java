package com.sathya.emailOtpVerification.controller;


import com.sathya.emailOtpVerification.model.Users;
import com.sathya.emailOtpVerification.requests.RegisterRequest;
import com.sathya.emailOtpVerification.responses.RegisterResponse;
import com.sathya.emailOtpVerification.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest){
        RegisterResponse registerResponse = userService.register(registerRequest);

        return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
    }


    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestParam String email, @RequestParam String otp){
        try{
            userService.verify(email, otp);
            return new ResponseEntity<>("User verified successfully", HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password){
        Users users = userService.login(email, password);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
