package com.example.sweet.controller.Loai;

import com.example.sweet.database.repository.Loai.LoaiTaiKhoanRepository;
import com.example.sweet.database.schema.Loai.LoaiGiaoDich;
import com.example.sweet.database.schema.Loai.LoaiTaiKhoan;
import com.example.sweet.util.annotation.ApiMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/loai-tai-khoan")
@AllArgsConstructor
public class LoaiTaiKhoanController {
    private LoaiTaiKhoanRepository respository;

    @GetMapping("")
    @ApiMessage("Lấy danh sách loại tài khoản")
    public ResponseEntity<Iterable<LoaiTaiKhoan>> getAllLoaiTaiKhoan() {
        return ResponseEntity.ok(respository.findAll());
    }

    @GetMapping("/{id}")
    @ApiMessage("Lấy danh sách loại tài khoản")
    public ResponseEntity<Optional<LoaiTaiKhoan>> getLoaiTaiKhoanByID(@PathVariable Long id) {
        return ResponseEntity.ok(respository.findById(id));
    }
//
//    @PostMapping("")
//    @ApiMessage("Thêm mới loại tài khoản")
//    public ResponseEntity<LoaiTaiKhoan> insertLoaiTaiKhoan(@RequestBody LoaiTaiKhoan loaiTK) {
//        return ResponseEntity.ok(respository.save(loaiTK));
//    }
//
//    @DeleteMapping("/{id}")
//    @ApiMessage("Xoá loại tài khoản theo ID")
//    public void deleteLoaiTaiKhoan(@PathVariable Long id) {
//        respository.deleteById(id);
//    }
}
