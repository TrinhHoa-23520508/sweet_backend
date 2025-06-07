package com.example.sweet.controller;

import com.example.sweet.database.respository.TaiKhoan.NhanVienRespository;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class NhanVienController {
    @Autowired
    private NhanVienRespository nhanVienRespository;

    @GetMapping("/nhanvien")
    public @ResponseBody Iterable<NhanVien> getAllNhanVien() {
        return nhanVienRespository.findAll();
    }

    @PostMapping("/nhanvien")
    public NhanVien addNhanVien(@RequestBody NhanVien nhanVien) {
        return nhanVienRespository.save(nhanVien);
    }

    @DeleteMapping("/nhanvien/{id}")
    void deleteEmployee(@PathVariable int id) {
        nhanVienRespository.deleteById(id);
    }
}
