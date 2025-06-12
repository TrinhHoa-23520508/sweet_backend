package com.example.sweet.controller.Loai;

import com.example.sweet.database.repository.Loai.LoaiTaiKhoanRepository;
import com.example.sweet.database.schema.Loai.LoaiTaiKhoan;
import com.example.sweet.util.annotation.ApiMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loai-tai-khoan")
@AllArgsConstructor
public class LoaiTaiKhoanController {
    private LoaiTaiKhoanRepository respository;

    @GetMapping("/")
    @ApiMessage("Mah balls")
    public ResponseEntity<Iterable<LoaiTaiKhoan>> getAllLoaiTaiKhoan() {
        return ResponseEntity.ok(respository.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<LoaiTaiKhoan> insertLoaiTaiKhoan(@RequestBody LoaiTaiKhoan loaiTK) {
        return ResponseEntity.ok(respository.save(loaiTK));
    }

    @DeleteMapping("/{id}")
    public void deleteLoaiTaiKhoan(@PathVariable int id) {
        respository.deleteById(id);
    }
}
