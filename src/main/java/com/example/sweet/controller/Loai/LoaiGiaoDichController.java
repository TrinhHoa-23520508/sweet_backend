package com.example.sweet.controller.Loai;

import com.example.sweet.database.repository.Loai.LoaiGiaoDichRepository;
import com.example.sweet.database.schema.Loai.LoaiGiaoDich;
import com.example.sweet.util.annotation.ApiMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loai-giao-dich")
@AllArgsConstructor
public class LoaiGiaoDichController {
    private LoaiGiaoDichRepository respository;

    @GetMapping("/")
    @ApiMessage("Mah balls")
    public ResponseEntity<Iterable<LoaiGiaoDich>> getAllLoaiGiaoDich() {
        return ResponseEntity.ok(respository.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<LoaiGiaoDich> insertLoaiGiaoDich(@RequestBody LoaiGiaoDich loaiGiaoDich) {
        return ResponseEntity.ok(respository.save(loaiGiaoDich));
    }

    @DeleteMapping("/{id}")
    public void deleteLoaiGiaoDich(@PathVariable Long id) {
        respository.deleteById(id);
    }
}
