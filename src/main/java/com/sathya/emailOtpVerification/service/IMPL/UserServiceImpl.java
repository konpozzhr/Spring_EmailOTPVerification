package com.sathya.emailOtpVerification.service.IMPL;


import com.sathya.emailOtpVerification.model.Users;
import com.sathya.emailOtpVerification.repository.UsersRepository;
import com.sathya.emailOtpVerification.requests.RegisterRequest;
import com.sathya.emailOtpVerification.responses.RegisterResponse;
import com.sathya.emailOtpVerification.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final EmailService emailService;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        Users existingUser = usersRepository.findByEmail(registerRequest.getEmail());
        if(existingUser != null && existingUser.isVerified()){
            throw new RuntimeException("User Already Registered");
        }

        Users users = Users.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .build();

        String otp = generateOTP();
        users.setOtp(otp);

        Users savedUser = usersRepository.save(users);
        sendVerificationEmail(savedUser.getEmail(), otp);

        RegisterResponse response = RegisterResponse.builder()
                .username(users.getUsername())
                .email(users.getEmail())
                .build();

        return response;
    }

    @Override
    public void verify(String email, String otp) {
        Users users = usersRepository.findByEmail(email);
        if(users == null){
            throw new RuntimeException("User not found");
        }else if(users.isVerified()){
            throw new RuntimeException("User already verified");
        }else if(otp.equals(users.getOtp())){
            users.setVerified(true);
            usersRepository.save(users);
        }else{
            throw new RuntimeException("Internal Server Error");
        }
    }

    @Override
    public Users login(String email, String password) {
        Users byEmail = usersRepository.findByEmail(email);
        if(byEmail != null && byEmail.isVerified() && byEmail.getPassword().equals(password)){
            return byEmail;
        }else {
            throw new RuntimeException("Internal Server Error");
        }

    }

    private String generateOTP(){
        Random random = new Random();
        int otpValue = 100000 + random.nextInt(900000);
        return String.valueOf(otpValue);
    }


    private void sendVerificationEmail(String email, String otp){
        String subject = "Email Verification SpringBoot Application";
        String body = "Your verification OTP is : " + otp;
        emailService.sendEmail(email, subject, body);
    }

}


