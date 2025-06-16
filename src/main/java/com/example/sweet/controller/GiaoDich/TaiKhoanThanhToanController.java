package com.example.sweet.controller.GiaoDich;

import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
import com.example.sweet.database.repository.TaiKhoan.TaiKhoanThanhToanRepository;
import com.example.sweet.database.schema.ThamSo;
import com.example.sweet.services.GiaoDich.TaiKhoanThanhToanService;
import com.example.sweet.util.annotation.ApiMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/giao-dich/tktt")
@AllArgsConstructor
public class TaiKhoanThanhToanController {
    private TaiKhoanThanhToanService service;

    @GetMapping("")
    @ApiMessage("Mah balls")
    public ResponseEntity<Iterable<TaiKhoanThanhToan>> getAllTaiKhoanThanhToan() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("")
    public ResponseEntity<TaiKhoanThanhToan> insertTaiKhoanThanhToan(@RequestBody TaiKhoanThanhToan taiKhoan) {
        return ResponseEntity.ok(service.save(taiKhoan));
    }

    @GetMapping("/loai/{id}")
    @ApiMessage("Mah balls")
    public ResponseEntity<Optional<TaiKhoanThanhToan>> getTaiKhoanThanhToanByLoai(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteTaiKhoanThanhToan(@PathVariable Long id) {
        service.deleteById(id);
    }
}
