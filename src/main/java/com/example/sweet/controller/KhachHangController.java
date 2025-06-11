package com.example.sweet.controller;

import com.example.sweet.database.repository.TaiKhoan.KhachHangRepository;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class KhachHangController {

    private KhachHangRepository respository;

    @GetMapping("/khach-hang")
    public @ResponseBody Iterable<KhachHang> getAllKhachHang() {
        return respository.findAll();
    }

    @PostMapping("/khach-hang")
    public String createKhachHang() {
        // Logic to create a new Khach Hang (Customer)
        return "Khach Hang created successfully";
    }
}
