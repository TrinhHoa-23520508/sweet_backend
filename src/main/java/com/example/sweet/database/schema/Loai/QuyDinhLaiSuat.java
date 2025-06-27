package com.example.sweet.database.schema.Loai;

import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuyDinhLaiSuat {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long quyDinhLaiSuatID;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private String moTa;

    @ManyToOne
    @JoinColumn(name = "nguoi_lap_quy_dinh", nullable = false)
    private NhanVien nguoiLapQuyDinh;

    private float laiSuatKhongKyHan;
    private int soTienGuiToiThieu;
    @ColumnDefault("true")
    private boolean active;

    @OneToMany(mappedBy = "quyDinhLaiSuat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ChiTietQuyDinhLaiSuat> chiTietQuyDinhLaiSuats;
}
