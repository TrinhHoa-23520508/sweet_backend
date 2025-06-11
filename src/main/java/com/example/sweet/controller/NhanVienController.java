package com.example.sweet.controller;

import com.example.sweet.database.respository.TaiKhoan.KhachHangRespository;
import com.example.sweet.database.respository.TaiKhoan.NhanVienRespository;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class NhanVienController {

    private NhanVienRespository respository;

    @GetMapping("/nhan-vien")
    public @ResponseBody Iterable<NhanVien> getAllNhanVien() {
        return respository.findAll();
    }
}
