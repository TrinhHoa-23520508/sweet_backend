package com.example.sweet.domain.request;

import com.example.sweet.util.constant.TypeUserEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDTO {

    @NotBlank(message = "Old password không được để trống")
    private String oldPassword;

    @NotBlank(message = "New password không được để trống")
    @Size(min = 6, message = "Password phải có độ dài trên 6 kí tự")
    private String newPassword;

    @NotNull(message = "Loại người (userType) dùng không được để trống.")
    private TypeUserEnum userType;
}
