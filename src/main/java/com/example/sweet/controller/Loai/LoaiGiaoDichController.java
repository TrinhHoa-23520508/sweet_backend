package com.example.sweet.controller.Loai;

import com.example.sweet.database.repository.Loai.LoaiGiaoDichRepository;
import com.example.sweet.database.schema.GiaoDich.KenhGiaoDich;
import com.example.sweet.database.schema.Loai.LoaiGiaoDich;
import com.example.sweet.util.annotation.ApiMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/loai-giao-dich")
@AllArgsConstructor
public class LoaiGiaoDichController {
    private LoaiGiaoDichRepository respository;

    @GetMapping("")
    @ApiMessage("Lấy danh sách loại giao dịch")
    public ResponseEntity<Iterable<LoaiGiaoDich>> getAllLoaiGiaoDich() {
        return ResponseEntity.ok(respository.findAll());
    }
    @GetMapping("/{id}")
    @ApiMessage("Lấy danh sách loại giao dịch")
    public ResponseEntity<Optional<LoaiGiaoDich>> getLoaiGiaoDichByID(@PathVariable Long id) {
        return ResponseEntity.ok(respository.findById(id));
    }

//    @PostMapping("")
//    @ApiMessage("Thêm mới loại giao dịch")
//    public ResponseEntity<LoaiGiaoDich> insertLoaiGiaoDich(@RequestBody LoaiGiaoDich loaiGiaoDich) {
//        return ResponseEntity.ok(respository.save(loaiGiaoDich));
//    }
//
//    @DeleteMapping("/{id}")
//    @ApiMessage("Xoá loại giao dịch theo ID")
//    public void deleteLoaiGiaoDich(@PathVariable Long id) {
//        respository.deleteById(id);
//    }
}
