package com.example.sweet.database.schema.TaiKhoan;

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
public class DiaChi {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer diaChiID;
    private Integer soNha;
    private String tenDuong;
    private String phuongXa;
    private String quanHuyen;
    private String tinhTP;




}
