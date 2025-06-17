package com.example.sweet.database.schema.TaiKhoan;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaChiID;

    @Column(length = 100)
    private String soNha;

    @Column(length = 100)
    private String tenDuong;

    @Column(length = 100)
    private String phuongXa;

    @Column(length = 100)
    private String quanHuyen;

    @Column(length = 100)
    private String tinhTP;




}
