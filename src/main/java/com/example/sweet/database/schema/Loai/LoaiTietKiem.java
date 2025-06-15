package com.example.sweet.database.schema.Loai;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoaiTietKiem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loaiTietKiemID;
    private String tenLoaiTietKiem;
    private int maLoaiTietKiem;
    private String moTa;
    private boolean duocPhepRutTruocHan;
    private boolean duocPhepRutMotPhan;
    private boolean coHoatDong;
}
