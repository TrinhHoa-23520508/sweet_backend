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
@NoArgsConstructor
@AllArgsConstructor
public class LoaiTaiKhoan {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long loaiTaiKhoanID;
    private int maLoaiTaiKhoan;
    private String tenLoaiTaiKhoan;
    private String moTa;
}
