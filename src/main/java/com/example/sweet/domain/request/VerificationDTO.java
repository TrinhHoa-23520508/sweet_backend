package com.example.sweet.domain.request;

import com.example.sweet.util.constant.TypeUserEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerificationDTO {

    @NotBlank(message = "email không được trống!")
    private String email;

    @NotBlank(message = "OTP không được để trống!")
    @Length(min = 0, max = 6)
    private String otp;
    private TypeUserEnum userType;
}
