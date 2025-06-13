package com.example.sweet.database.repository.dto;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;

public class PhieuDaoHanDTO {
    String maPhieuDaoHan; // khoa chinh, auto generated

    // input
    String maPhieuGuiTien; // khoa ngoai, tham chieu toi phieu gui tien
    String maKhachHang; // khoa ngoai, tham chieu toi khach hang

    // Thong tin khach hang
    String cccdKhachHang;
    String hoTenKhachHang;

    // Thong tin phieu gui tien
    LocalDate ngayGuiTien;
    Long soTienGui;

}
