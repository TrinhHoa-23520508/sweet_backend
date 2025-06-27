package com.example.sweet.services;

import com.example.sweet.database.repository.Loai.LoaiTrangThaiRepository;
import com.example.sweet.database.repository.TaiKhoan.KhachHangRepository;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.repository.TaiKhoan.VerificationEmailRepository;
import com.example.sweet.database.repository.TrangThaiRepository;
import com.example.sweet.database.schema.Loai.LoaiTrangThai;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.database.schema.TaiKhoan.VerificationEmail;
import com.example.sweet.database.schema.TrangThai;
import com.example.sweet.domain.request.ResetPasswordDTO;
import com.example.sweet.domain.request.VerificationDTO;
import com.example.sweet.domain.response.ResLoginDTO;
import com.example.sweet.util.constant.StatusEnum;
import com.example.sweet.util.constant.TypeStatusEnum;
import com.example.sweet.util.constant.TypeUserEnum;
import com.example.sweet.util.error.IdInvalidException;
import com.example.sweet.util.mapper.KhachHangMapper;
import com.example.sweet.util.mapper.NhanVienMapper;
import com.example.sweet.util.mapper.UserLoginMapper;
import jakarta.mail.*;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.awt.dnd.InvalidDnDOperationException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;


@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String sender;
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final MessageSource messageSource;
    private final VerificationEmailRepository verificationEmailRepository;
    private final KhachHangRepository khachHangRepository;
    private final NhanVienRepository nhanVienRepository;
    private final LoaiTrangThaiRepository loaiTrangThaiRepository;
    private final TrangThaiRepository trangThaiRepository;
    private final UserLoginMapper userLoginMapper;
    private final KhachHangMapper khachHangMapper;
    private final NhanVienMapper nhanVienMapper;
    private final PasswordEncoder passwordEncoder;

    private static final String USER = "user";
    private static final int OTP_EXPIRY_MINUTES = 5;


    public EmailService(JavaMailSender javaMailSender,
                        TemplateEngine templateEngine,
                        MessageSource messageSource,
                        VerificationEmailRepository verificationEmailRepository,
                        KhachHangRepository khachHangRepository,
                        NhanVienRepository nhanVienRepository,
                        LoaiTrangThaiRepository loaiTrangThaiRepository,
                        TrangThaiRepository trangThaiRepository,
                        UserLoginMapper userLoginMapper,
                        KhachHangMapper khachHangMapper,
                        NhanVienMapper nhanVienMapper,
                        PasswordEncoder passwordEncoder) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.messageSource = messageSource;
        this.verificationEmailRepository = verificationEmailRepository;
        this.khachHangRepository = khachHangRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.loaiTrangThaiRepository = loaiTrangThaiRepository;
        this.trangThaiRepository = trangThaiRepository;
        this.userLoginMapper = userLoginMapper;
        this.khachHangMapper = khachHangMapper;
        this.nhanVienMapper = nhanVienMapper;
        this.passwordEncoder = passwordEncoder;
    }

    private String generateOTP() {
        int otp = 100000 + new Random().nextInt(900000);
        return String.valueOf(otp);
    }

    private String createValidOTP(ResLoginDTO.UserGetAccount user) {
        List<VerificationEmail> oldOtp = this.verificationEmailRepository.findByUserIdAndUserTypeAndUsed(user.getId(), user.getType(), false);
        if (!oldOtp.isEmpty()) {
            oldOtp.forEach(verificationEmail -> {
                verificationEmail.setUsed(true);
            });
            this.verificationEmailRepository.saveAll(oldOtp);
        }

        VerificationEmail verificationEmail = new VerificationEmail();
        String newOtp;
        do {
            newOtp = this.generateOTP();
        } while (this.verificationEmailRepository.existsByOtpAndUsedFalseAndExpiredAtAfter(newOtp, LocalDateTime.now()));

        Optional<VerificationEmail> existsOtpOptional = this.verificationEmailRepository.findByUserIdAndOtpAndUserType(user.getId(), newOtp, user.getType());
        if (existsOtpOptional.isPresent()) {
            verificationEmail = existsOtpOptional.get();
            verificationEmail.setUsed(false);
            verificationEmail.setCreatedAt(LocalDateTime.now());
            verificationEmail.setExpiredAt(LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES));


        } else {

            verificationEmail.setUserId(user.getId());
            verificationEmail.setUserType(user.getType());
            verificationEmail.setUsed(false);
            verificationEmail.setOtp(newOtp);
            verificationEmail.setCreatedAt(LocalDateTime.now());
            verificationEmail.setExpiredAt(LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES));
        }

        this.verificationEmailRepository.save(verificationEmail);
        return newOtp;
    }

    @Async
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        sendEmailSync(to, subject, content, isMultipart, isHtml);
    }

    private void sendEmailSync(String to, String subject, String content, boolean isMultipart, boolean isHtml) {

        // Prepare message using a Spring helper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
            message.setTo(to);
            message.setFrom(this.sender);
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
        } catch (MailException | MessagingException e) {
            System.out.println(e);
        }
    }

    @Async
    public void sendEmailFromTemplate(ResLoginDTO.UserGetAccount user, String templateName, String titleKey) {
        sendEmailFromTemplateSync(user, templateName, titleKey);
    }

    private void sendEmailFromTemplateSync(ResLoginDTO.UserGetAccount user, String templateName, String titleKey) {
        if (user.getEmail() == null) {
            return;
        }
        Locale locale = Locale.forLanguageTag("vi");
        Context context = new Context(locale);
        context.setVariable(USER, user);
        String content = templateEngine.process(templateName, context);
        String subject = messageSource.getMessage(titleKey, null, locale);
        sendEmailSync(user.getEmail(), subject, content, false, true);
    }

    @Async
    public void sendActivationEmail(ResLoginDTO.UserGetAccount user) {

        String newOtp = this.createValidOTP(user);
        this.sendVerificationMailWithTemplate(user, newOtp, "mail/activationEmail", "email.activation.title");

    }


    public void verifyActivationOtp(VerificationDTO verificationDTO) throws IdInvalidException {
        KhachHang kh = null;
        NhanVien nv = null;
        if (verificationDTO.getUserType().equals(TypeUserEnum.KHACHHANG)) {
            kh = this.khachHangRepository.findByEmail(verificationDTO.getEmail()).orElseThrow(
                    () -> new IdInvalidException("email không hợp lệ!")
            );
        } else if (verificationDTO.getUserType().equals(TypeUserEnum.NHANVIEN)) {
            nv = this.nhanVienRepository.findByEmail(verificationDTO.getEmail()).orElseThrow(
                    () -> new IdInvalidException("email không hợp lệ!")
            );
        } else {
            throw new IdInvalidException("type không hợp lệ");
        }
        VerificationEmail verificationEmail = this.verificationEmailRepository.findByUserIdAndOtpAndUserType(
                (kh != null) ? kh.getKhachHangID() : nv.getNhanVienID(),
                verificationDTO.getOtp(),
                verificationDTO.getUserType()
        ).orElseThrow(
                () -> new IdInvalidException("OTP không hợp lệ")
        );

        if (verificationEmail.isUsed()) {
            throw new IdInvalidException("OTP không hợp lệ");
        }
        if (verificationEmail.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new IdInvalidException("OTP không hợp lệ");
        }

        verificationEmail.setUsed(true);
        LoaiTrangThai loaiTrangThai_TK = this.loaiTrangThaiRepository.findByMaLoaiTrangThai(TypeStatusEnum.login_account.toString())
                .orElseThrow(() -> new IllegalArgumentException("Loại trạng thái không tồn tại"));
        TrangThai activeTrangThai = this.trangThaiRepository.findByMaTrangThaiAndLoaiTrangThai(StatusEnum.active.toString(), loaiTrangThai_TK)
                .orElseThrow(() -> new IllegalArgumentException("Trạng thái không tồn tại cho loại trạng thái tài khoản"));

        //update user and create information user
        ResLoginDTO.UserGetAccount user = new ResLoginDTO.UserGetAccount();
        if (kh != null) {
            kh.setTrangThaiTaiKhoan(activeTrangThai);
            user = this.userLoginMapper.KhachHangResToUserGetAccount(
                    this.khachHangMapper.toKhachHangResponseDTO(
                            this.khachHangRepository.save(kh)
                    )
            );
        } else {
            nv.setTrangThaiTaiKhoan(activeTrangThai);
            user = this.userLoginMapper.NhanVienResToUserGetAccount(
                    this.nhanVienMapper.toNhanVienResponseDTO(
                            this.nhanVienRepository.save(nv)
                    )
            );
        }

        //save verification email
        this.verificationEmailRepository.save(verificationEmail);

        //send email
        this.sendVerifiedMail(user);


    }

    public void resetPassword(ResetPasswordDTO resetPasswordDTO) throws IdInvalidException {
        KhachHang kh = null;
        NhanVien nv = null;
        if (resetPasswordDTO.getUserType().equals(TypeUserEnum.KHACHHANG)) {
            kh = this.khachHangRepository.findByEmail(resetPasswordDTO.getEmail()).orElseThrow(
                    () -> new IdInvalidException("email không hợp lệ!")
            );
        } else if (resetPasswordDTO.getUserType().equals(TypeUserEnum.NHANVIEN)) {
            nv = this.nhanVienRepository.findByEmail(resetPasswordDTO.getEmail()).orElseThrow(
                    () -> new IdInvalidException("email không hợp lệ!")
            );
        } else {
            throw new IdInvalidException("type không hợp lệ");
        }
        VerificationEmail verificationEmail = this.verificationEmailRepository.findByUserIdAndOtpAndUserType(
                (kh != null) ? kh.getKhachHangID() : nv.getNhanVienID(),
                resetPasswordDTO.getOtp(),
                resetPasswordDTO.getUserType()
        ).orElseThrow(
                () -> new IdInvalidException("OTP không hợp lệ")
        );

        if (verificationEmail.isUsed()) {
            throw new IdInvalidException("OTP không hợp lệ");
        }
        if (verificationEmail.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new IdInvalidException("OTP không hợp lệ");
        }

        verificationEmail.setUsed(true);

        //save verification email
        this.verificationEmailRepository.save(verificationEmail);

        //change password
        if (kh != null) {
            kh.setMatKhau(passwordEncoder.encode(resetPasswordDTO.getNewPassword()));
            this.khachHangRepository.save(kh);
        } else {
            nv.setMatKhau(passwordEncoder.encode(resetPasswordDTO.getNewPassword()));
            this.nhanVienRepository.save(nv);
        }


    }

    @Async
    public void sendVerifiedMail(ResLoginDTO.UserGetAccount user) {
        sendEmailFromTemplateSync(user, "mail/verifiedMail", "email.verified.title");
    }

    @Async
    public void sendPasswordResetMail(ResLoginDTO.UserGetAccount user) {

        String newOtp = this.createValidOTP(user);
        sendVerificationMailWithTemplate(user, newOtp, "mail/passwordResetMail", "email.reset.title");
    }

    @Async
    public void sendVerificationMailWithTemplate(ResLoginDTO.UserGetAccount user, String otp, String template, String titleKey) {
        if (user.getEmail() == null) {
            return;
        }

        Locale locale = Locale.forLanguageTag("vi");
        Context context = new Context(locale);
        context.setVariable(USER, user);
        context.setVariable("otp", otp);
        String content = templateEngine.process(template, context);
        String subject = messageSource.getMessage(titleKey, null, locale);
        sendEmailSync(user.getEmail(), subject, content, false, true);
    }
}
