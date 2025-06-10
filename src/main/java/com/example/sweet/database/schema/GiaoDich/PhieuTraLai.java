package com.example.sweet.database.schema.GiaoDich;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class PhieuTraLai {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter
    private int phieuTraLaiID;
    @ManyToOne
    @JoinColumn(name = "giao_dich", nullable = false)
    private GiaoDich giaoDich;
    @ManyToOne
    @JoinColumn(name = "phieu_gui_tien", nullable = false)
    private PhieuGuiTien phieuGuiTien;

    private LocalDateTime ngayTraLai;

}
