package com.example.sweet.controller;

import com.example.sweet.domain.request.ReqLoginDTO;
import com.example.sweet.domain.response.ResLoginDTO;
import com.example.sweet.services.KhachHangService;
import com.example.sweet.services.NhanVienService;
import com.example.sweet.util.SecurityUtil;
import com.example.sweet.util.annotation.ApiMessage;
import com.example.sweet.util.constant.TypeUserEnum;
import com.example.sweet.util.error.IdInvalidException;
import com.example.sweet.util.mapper.UserLoginMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final SecurityUtil securityUtil;
    private final NhanVienService nhanVienService;
    private final KhachHangService khachHangService;
    private final UserLoginMapper userLoginMapper;

    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder,
                          SecurityUtil securityUtil,
                          KhachHangService khachHangService,
                          NhanVienService nhanVienService,
                          UserLoginMapper userLoginMapper) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.securityUtil = securityUtil;
        this.khachHangService = khachHangService;
        this.nhanVienService = nhanVienService;
        this.userLoginMapper = userLoginMapper;
    }

    @Value("${jwt.refresh-token.expiration}")
    private int refreshTokenExpiration;

    @PostMapping("/auth/login")
    @ApiMessage("Login account")
    public ResponseEntity<ResLoginDTO> login(@Valid @RequestBody ReqLoginDTO reqLoginDTO) {
        String prefix = "";
        if (reqLoginDTO.getType().equals(TypeUserEnum.KHACHHANG)) {
            prefix = "kh.";
        } else if (reqLoginDTO.getType().equals(TypeUserEnum.NHANVIEN)) {
            prefix = "nv.";
        }
        reqLoginDTO.setUsername(prefix + reqLoginDTO.getUsername());


        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                reqLoginDTO.getUsername(),
                reqLoginDTO.getPassword()
        );
        //xác thực người dùng cần viết hàm loadUserByName()
        Authentication authentication = this.authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        //nạp thông tin người đăng nhập vào security context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        reqLoginDTO.setUsername(reqLoginDTO.getUsername().substring(3));
        ResLoginDTO resLoginDTO = this.userLoginMapper.reqLoginToResLogin(reqLoginDTO);

        //Create token
        String accessToken = this.securityUtil.createAccessToken(authentication.getName(), resLoginDTO);
        resLoginDTO.setAccessToken(accessToken);

        return ResponseEntity.ok()
                .body(resLoginDTO);
    }


    @PostMapping("/auth/logout")
    @ApiMessage("Logout account")
    public ResponseEntity<Void> logout() throws IdInvalidException {
        String email = SecurityUtil.getCurrentUserLogin()
                .orElseThrow(() -> new IdInvalidException("Token truyền lên không hợp lệ"));

        if (email.equals("")) {
            throw new IdInvalidException("Token truyền lên không hợp lệ");
        }

        ResponseCookie cookie = ResponseCookie.from("refresh_token", null)
                .httpOnly(true)
                .path("/")
                .maxAge(0)
                .build();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(null);

    }

}
