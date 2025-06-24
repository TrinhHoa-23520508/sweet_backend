package com.example.sweet.controller;

import com.example.sweet.database.repository.TaiKhoan.KhachHangRepository;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.domain.request.*;
import com.example.sweet.domain.response.KhachHangResponseDTO;
import com.example.sweet.domain.response.ResLoginDTO;
import com.example.sweet.services.EmailService;
import com.example.sweet.services.KhachHangService;
import com.example.sweet.services.NhanVienService;
import com.example.sweet.util.SecurityUtil;
import com.example.sweet.util.annotation.ApiMessage;
import com.example.sweet.util.constant.StatusEnum;
import com.example.sweet.util.constant.TypeUserEnum;
import com.example.sweet.util.error.IdInvalidException;
import com.example.sweet.util.error.NotFoundException;
import com.example.sweet.util.mapper.KhachHangMapper;
import com.example.sweet.util.mapper.NhanVienMapper;
import com.example.sweet.util.mapper.UserLoginMapper;
import jakarta.mail.MessagingException;
import jakarta.persistence.Id;
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
    private final EmailService emailService;
    private final KhachHangRepository khachHangRepository;
    private final NhanVienRepository nhanVienRepository;
    private final KhachHangMapper khachHangMapper;
    private final NhanVienMapper nhanVienMapper;

    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder,
                          SecurityUtil securityUtil,
                          KhachHangService khachHangService,
                          NhanVienService nhanVienService,
                          UserLoginMapper userLoginMapper,
                          EmailService emailService,
                          KhachHangRepository khachHangRepository,
                          NhanVienRepository nhanVienRepository,
                          KhachHangMapper khachHangMapper,
                          NhanVienMapper nhanVienMapper) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.securityUtil = securityUtil;
        this.khachHangService = khachHangService;
        this.nhanVienService = nhanVienService;
        this.userLoginMapper = userLoginMapper;
        this.emailService = emailService;
        this.khachHangRepository = khachHangRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.khachHangMapper = khachHangMapper;
        this.nhanVienMapper = nhanVienMapper;
    }

    @Value("${jwt.refresh-token.expiration}")
    private int refreshTokenExpiration;

    @PostMapping("/auth/login")
    @ApiMessage("Login account")
    public ResponseEntity<ResLoginDTO> login(@Valid @RequestBody ReqLoginDTO reqLoginDTO) {
        String prefix = "";
        if (reqLoginDTO.getType().equals(TypeUserEnum.KHACHHANG)) {
            prefix = "kh.";
            KhachHang kh = this.khachHangRepository.findByEmail(reqLoginDTO.getUsername()).orElseThrow(
                    () -> new NotFoundException("Tài khoản không tồn tại")
            );
            if (!kh.getTrangThaiTaiKhoan().getMaTrangThai().equals(StatusEnum.active.toString())) {
                throw new NotFoundException("Tài khoản chưa được kích hoạt");
            }
        } else if (reqLoginDTO.getType().equals(TypeUserEnum.NHANVIEN)) {
            prefix = "nv.";
            NhanVien nv = this.nhanVienRepository.findByEmail(reqLoginDTO.getUsername()).orElseThrow(
                    () -> new NotFoundException("Tài khoản không tồn tại")
            );
            if (!nv.getTrangThaiTaiKhoan().getMaTrangThai().equals(StatusEnum.active.toString())) {
                throw new NotFoundException("Tài khoản chưa được kích hoạt");
            }
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

    @GetMapping("/auth/account")
    @ApiMessage("Get account information")
    public ResponseEntity<ResLoginDTO.UserGetAccount> getAccount() {

        String emailHasPrefix = SecurityUtil.getCurrentUserLogin()
                .isPresent() ?
                SecurityUtil.getCurrentUserLogin().get()
                : "";
        if (emailHasPrefix.equals("")) {
            throw new NotFoundException("Token truyền lên không hợp lệ");
        }
        ResLoginDTO.UserGetAccount userGetAccount = new ResLoginDTO.UserGetAccount();
        if (emailHasPrefix.startsWith("kh.")) {
            String email = emailHasPrefix.substring(3);
            userGetAccount = this.khachHangService.getUserAccountByEmail(email);

        } else if (emailHasPrefix.startsWith("nv.")) {
            String email = emailHasPrefix.substring(3);
            userGetAccount = this.nhanVienService.getUserAccountByEmail(email);
        }
        return ResponseEntity.ok().body(userGetAccount);
    }

    @PostMapping("/auth/register")
    @ApiMessage("Register account")
    public ResponseEntity<RegisterDTO<KhachHangResponseDTO>> register(@Valid @RequestBody KhachHangRequestDTO khachHangRequestDTO) {
        KhachHangResponseDTO responseDTO = this.khachHangService.createKhachHang(khachHangRequestDTO);
        RegisterDTO<KhachHangResponseDTO> registerDTO = new RegisterDTO<>();
        registerDTO.setData(responseDTO);
        registerDTO.setType(TypeUserEnum.KHACHHANG);
        return ResponseEntity.ok().body(registerDTO);
    }

    @PostMapping("/auth/send-verification")
    @ApiMessage("Email xác thực đã được gửi đi")
    public ResponseEntity<Void> sendVerification(
            @RequestParam String email,
            @RequestParam String userType
    ) throws IdInvalidException {
        ResLoginDTO.UserGetAccount user = new ResLoginDTO.UserGetAccount();
        if (userType.equals(TypeUserEnum.KHACHHANG.toString())) {
            KhachHang khachHang = this.khachHangRepository.findByEmail(email).orElseThrow(
                    () -> new NotFoundException("user not found!")
            );
            user = this.userLoginMapper.KhachHangResToUserGetAccount(
                    this.khachHangMapper.toKhachHangResponseDTO(khachHang)
            );
        } else if (userType.equals(TypeUserEnum.NHANVIEN.toString())) {
            NhanVien nhanVien = this.nhanVienRepository.findByEmail(email).orElseThrow(
                    () -> new NotFoundException("user not found!")
            );
            user = this.userLoginMapper.NhanVienResToUserGetAccount(
                    this.nhanVienMapper.toNhanVienResponseDTO(nhanVien)
            );
        } else {
            throw new IdInvalidException("type user is invalide!");
        }
        this.emailService.sendActivationEmail(user);

        return ResponseEntity.ok(null);

    }

    @PostMapping("/auth/verify")
    @ApiMessage("Xác thực email thành công")
    public ResponseEntity<Void> verifyEmail(
            @RequestBody VerificationDTO verificationDTO
    ) throws IdInvalidException {
        this.emailService.verifyActivationOtp(verificationDTO);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/auth/forgot-password")
    @ApiMessage("gửi email xác nhận đặt lại mật khẩu")
    public ResponseEntity<Void> forgotPassword(
            @RequestBody ForgotPasswordDTO forgotPasswordDTO
    ) throws IdInvalidException {
        ResLoginDTO.UserGetAccount user = new ResLoginDTO.UserGetAccount();
        if (forgotPasswordDTO.getUserType().equals(TypeUserEnum.KHACHHANG)) {
            KhachHang khachHang = this.khachHangRepository.findByEmail(forgotPasswordDTO.getEmail()).orElseThrow(
                    () -> new NotFoundException("user not found!")
            );
            user = this.userLoginMapper.KhachHangResToUserGetAccount(
                    this.khachHangMapper.toKhachHangResponseDTO(khachHang)
            );
        } else if (forgotPasswordDTO.getUserType().equals(TypeUserEnum.NHANVIEN)) {
            NhanVien nhanVien = this.nhanVienRepository.findByEmail(forgotPasswordDTO.getEmail()).orElseThrow(
                    () -> new NotFoundException("user not found!")
            );
            user = this.userLoginMapper.NhanVienResToUserGetAccount(
                    this.nhanVienMapper.toNhanVienResponseDTO(nhanVien)
            );
        } else {
            throw new IdInvalidException("type user is invalide!");
        }
        this.emailService.sendPasswordResetMail(user);

        return ResponseEntity.ok(null);
    }

    @PostMapping("/auth/reset-password")
    @ApiMessage("Đặt lại mật khẩu")
    public ResponseEntity<Void> resetPassword(
            @RequestBody ResetPasswordDTO resetPasswordDTO
    ) throws IdInvalidException {
        this.emailService.resetPassword(resetPasswordDTO);
        return ResponseEntity.ok(null);
    }


}
