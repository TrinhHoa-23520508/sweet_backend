package com.example.sweet.controller.GiaoDich;

import com.example.sweet.database.repository.GiaoDich.LichSuGiaoDich_TKTTRepository;
import com.example.sweet.database.schema.GiaoDich.LichSuGiaoDich_TKTT;
import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
import com.example.sweet.domain.response.GiaoDich.LSGD_TKTTResponseDTO;
import com.example.sweet.util.annotation.ApiMessage;
import com.example.sweet.util.mapper.GiaoDich.LSGD_TKTTMapper;
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
    private LSGD_TKTTMapper lsgdTkttMapper;

    @GetMapping("")
    public ResponseEntity<Iterable<LSGD_TKTTResponseDTO>> getAllLichSuGiaoDich_TKTT() {
        return ResponseEntity.ok(respository.findAll().stream().map(lsgdTkttMapper::toLSGD_TKTTResponseDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<LSGD_TKTTResponseDTO>> getLichSuGiaoDich_TKTTID(@PathVariable Long id) {
        return ResponseEntity.ok(respository.findById(id).map(lsgdTkttMapper::toLSGD_TKTTResponseDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteLichSuGiaoDich_TKTT(@PathVariable Long id) {
        respository.deleteById(id);
    }
}
