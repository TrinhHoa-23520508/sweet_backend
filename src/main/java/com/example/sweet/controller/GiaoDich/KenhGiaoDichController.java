package com.example.sweet.controller.GiaoDich;

import com.example.sweet.database.repository.GiaoDich.KenhGiaoDichRepository;
import com.example.sweet.database.schema.GiaoDich.KenhGiaoDich;
import com.example.sweet.util.annotation.ApiMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/giao-dich/kenh")
@AllArgsConstructor
public class KenhGiaoDichController {
    private KenhGiaoDichRepository respository;

    @GetMapping("")
    @ApiMessage("Lấy danh sách kênh giao dịch")
    public ResponseEntity<Iterable<KenhGiaoDich>> getAllKenhGiaoDich() {
        return ResponseEntity.ok(respository.findAll());
    }

    @GetMapping("/{id}")
    @ApiMessage("Lấy danh sách kênh giao dịch")
    public ResponseEntity<Optional<KenhGiaoDich>> getKenhGiaoDichByID(@PathVariable Long id) {
        return ResponseEntity.ok(respository.findById(id));
    }

//    @PostMapping("")
//    @ApiMessage("Thêm mới kênh giao dịch")
//    public ResponseEntity<KenhGiaoDich> insertKenhGiaoDich(@RequestBody KenhGiaoDich kenhGiaoDich) {
//        return ResponseEntity.ok(respository.save(kenhGiaoDich));
//    }
//
//    @DeleteMapping("/{id}")
//    @ApiMessage("Xoá kênh giao dịch theo ID")
//    public void deleteKenhGiaoDich(@PathVariable Long id) {
//        respository.deleteById(id);
//    }
}
