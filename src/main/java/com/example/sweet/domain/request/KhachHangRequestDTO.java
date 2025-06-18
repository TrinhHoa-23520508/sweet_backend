package com.example.sweet.domain.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KhachHangRequestDTO {
    @NotBlank(message = "Họ tên không được để trống")
    private String hoTen;

    @NotNull(message = "Ngày sinh không được null")
    @Past(message = "Ngày sinh phải là quá khứ")
    private LocalDate ngaySinh;

    @NotBlank(message = "CCCD/CMND không được để trống")
    @Size(min = 9, max = 12, message = "CCCD/CMND phải từ 9 đến 12 số")
    private String cccd;

    @Email(message = "Email không hợp lệ")
    private String email;

    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại phải gồm 10 số và bắt đầu bằng 0")
    private String soDienThoai;

    private Long diaChiThuongTruId;
    private Long diaChiLienLacId;
    private Long trangThaiKhachHangId;
    private String matKhau;
    private Long vaiTroId;
    private Long trangThaiTaiKhoanId;
}
