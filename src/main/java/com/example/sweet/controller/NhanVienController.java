package com.example.sweet.controller;

import com.example.sweet.database.repository.TaiKhoan.KhachHangRepository;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class NhanVienController {

    private NhanVienRepository respository;

    @GetMapping("/nhan-vien")
    public @ResponseBody Iterable<NhanVien> getAllNhanVien() {
        return respository.findAll();
    }
}
