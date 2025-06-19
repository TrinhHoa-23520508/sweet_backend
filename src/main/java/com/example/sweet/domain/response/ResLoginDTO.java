package com.example.sweet.domain.response;

import com.example.sweet.database.schema.TaiKhoan.VaiTro;
import com.example.sweet.domain.request.TrangThaiDTO;
import com.example.sweet.domain.request.VaiTroDTO;
import com.example.sweet.util.constant.TypeUserEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResLoginDTO {
    @JsonProperty("access_token")
    private String accessToken;
    private UserLogin user;
    private TypeUserEnum type;


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserLogin {
        private Long id;
        private String hoTen;
        private String email;
        private VaiTroDTO vaiTro;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserInsideToken {
        private long id;
        private String hoTen;
        private String email;
        private TypeUserEnum type;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserGetAccount {
        private Long id;
        private String hoTen;
        private LocalDate ngaySinh;
        private int tuoi;
        private TypeUserEnum type;
        private String email;
        private String cccd;
        private String soDienThoai;
        private Long diaChiThuongTruId;
        private Long diaChiLienLacId;
        private VaiTroDTO vaiTro;
        private TrangThaiDTO trangThaiTaiKhoan;

    }

}
