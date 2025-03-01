package com.sathya.emailOtpVerification.repository;

import com.sathya.emailOtpVerification.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);
}
