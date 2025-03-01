package com.sathya.emailOtpVerification.service.IMPL;


import com.sathya.emailOtpVerification.model.Users;
import com.sathya.emailOtpVerification.repository.UsersRepository;
import com.sathya.emailOtpVerification.requests.RegisterRequest;
import com.sathya.emailOtpVerification.responses.RegisterResponse;
import com.sathya.emailOtpVerification.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;

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

        usersRepository.save(users);

        RegisterResponse response = RegisterResponse.builder()
                .username(users.getUsername())
                .email(users.getEmail())
                .build();

        return response;
    }
}


