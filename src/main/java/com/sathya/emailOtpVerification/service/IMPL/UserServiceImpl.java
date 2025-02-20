package com.sathya.emailOtpVerification.service.IMPL;


import com.sathya.emailOtpVerification.repository.UsersRepository;
import com.sathya.emailOtpVerification.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
}
