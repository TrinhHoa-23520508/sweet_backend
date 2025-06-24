package com.example.sweet.database.repository.TaiKhoan;

import com.example.sweet.database.schema.TaiKhoan.VerificationEmail;
import com.example.sweet.util.constant.TypeUserEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VerificationEmailRepository extends JpaRepository<VerificationEmail, Long> {

    List<VerificationEmail> findByUserIdAndUserTypeAndUsed(Long userId, TypeUserEnum type, boolean used);

    Optional<VerificationEmail> findByUserIdAndOtpAndUserType(Long userId, String otp, TypeUserEnum userType);

    boolean existsByOtpAndUsedFalseAndExpiredAtAfter(String otp, LocalDateTime now);
}
