package com.example.sweet.domain.response;

import com.example.sweet.database.schema.Loai.ChiTietQuyDinhLaiSuat;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class QuyDinhLaiSuatResponseDTO {
    private Long quyDinhLaiSuatID;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private String moTa;
    private NhanVienResponseDTO nguoiLapQuyDinh;
    private float laiSuatKhongKyHan;
    private int soTienGuiToiThieu;
    private List<ChiTietQuyDinhLaiSuatResponseDTO> chiTietQuyDinhLaiSuats;
}
