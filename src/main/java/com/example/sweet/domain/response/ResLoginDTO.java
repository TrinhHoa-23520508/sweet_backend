package com.example.sweet.domain.response;

import com.example.sweet.database.schema.TaiKhoan.VaiTro;
import com.example.sweet.domain.request.VaiTroDTO;
import com.example.sweet.util.constant.TypeUserEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
