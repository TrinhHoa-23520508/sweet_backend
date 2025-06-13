package com.example.sweet.controller.GiaoDich;

import com.example.sweet.database.repository.GiaoDich.LichSuGiaoDich_TKTTRepository;
import com.example.sweet.database.schema.GiaoDich.LichSuGiaoDich_TKTT;
import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
import com.example.sweet.util.annotation.ApiMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/giao-dich/tktt/lich-su")
@AllArgsConstructor
public class LichSuGiaoDich_TKTTController {
    private LichSuGiaoDich_TKTTRepository respository;

    @GetMapping("")
    @ApiMessage("Mah balls")
    public ResponseEntity<Iterable<LichSuGiaoDich_TKTT>> getAllLichSuGiaoDich_TKTT() {
        return ResponseEntity.ok(respository.findAll());
    }

    @PostMapping("")
    public ResponseEntity<LichSuGiaoDich_TKTT> insertLichSuGiaoDich_TKTT(@RequestBody LichSuGiaoDich_TKTT lichSuGiaoDich) {
        return ResponseEntity.ok(respository.save(lichSuGiaoDich));
    }

    @GetMapping("/{id}")
    @ApiMessage("Mah balls")
    public ResponseEntity<Optional<LichSuGiaoDich_TKTT>> getLichSuGiaoDich_TKTTID(@PathVariable int id) {
        return ResponseEntity.ok(respository.findById(id));
    }


    @DeleteMapping("/{id}")
    public void deleteLichSuGiaoDich_TKTT(@PathVariable int id) {
        respository.deleteById(id);
    }
}
