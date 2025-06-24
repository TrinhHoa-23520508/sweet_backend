package com.example.sweet.domain.request;

import com.example.sweet.util.constant.TypeUserEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordDTO {

    @NotBlank(message = "email is not blank")
    private String email;

    @NotBlank(message = "OTP is not blank")
    private String otp;

    @NotNull(message = "user type is not null")
    private TypeUserEnum userType;

    @NotBlank(message = "newPassword is not null")
    private String newPassword;
}
