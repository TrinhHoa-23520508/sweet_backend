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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class HinhThucDaoHan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hinhThucDaoHangID;
    private String tenHinhThucDaoHang;
    private int maHinhThucDaoHang;
    private String moTa;
}
