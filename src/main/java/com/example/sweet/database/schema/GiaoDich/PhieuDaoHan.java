package com.example.sweet.database.schema.GiaoDich;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhieuDaoHan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long phieuDaoHanID;
    @ManyToOne
    @JoinColumn(name = "phieu_gui_tien_ky_truoc", nullable = false)
    private PhieuGuiTien phieuGuiTienKyTruoc;
    @ManyToOne
    @JoinColumn(name = "phieu_gui_tien_tiep_theo", nullable = false)
    private PhieuGuiTien phieuGuiTienTiepTheo;

    private LocalDateTime ngayDaoHan;

}
