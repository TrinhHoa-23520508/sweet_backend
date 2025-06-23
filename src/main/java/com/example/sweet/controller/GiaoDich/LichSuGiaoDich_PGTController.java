package com.example.sweet.controller.GiaoDich;

import com.example.sweet.database.repository.GiaoDich.LichSuGiaoDich_PhieuGuiTienRepository;
import com.example.sweet.database.repository.GiaoDich.PhieuGuiTienRepository;
import com.example.sweet.database.schema.GiaoDich.LichSuGiaoDich_PhieuGuiTien;
import com.example.sweet.database.schema.GiaoDich.PhieuGuiTien;
import com.example.sweet.domain.response.GiaoDich.LSGD_PGTDTO;
import com.example.sweet.util.annotation.ApiMessage;
import com.example.sweet.util.mapper.LSGD_PGTMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/giao-dich/pgt/lich-su")
@AllArgsConstructor
public class LichSuGiaoDich_PGTController {
    private final LichSuGiaoDich_PhieuGuiTienRepository repository;
    private final PhieuGuiTienRepository phieuGuiTienRepository;
    private final LSGD_PGTMapper lsgdPgtMapper;

    @GetMapping("")
    @ApiMessage("Lấy danh sách lịch sử giao dịch phiếu gửi tiền")
    public ResponseEntity<List<LSGD_PGTDTO>> getAllLichSuGiaoDich_PGT() {
        return ResponseEntity.ok(
                repository.findAll().stream().map(lsgdPgtMapper::toDTO).toList());
    }

    @GetMapping("/{id}")
    @ApiMessage("Lấy lịch sử giao dịch phiếu gửi tiền theo ID")
    public ResponseEntity<Optional<LSGD_PGTDTO>> getLichSuGiaoDich_PGTById(@PathVariable Long id) {
        return ResponseEntity.ok(
                repository.findById(id).map(lsgdPgtMapper::toDTO));
    }

    @GetMapping("by/{id}")
    @ApiMessage("Lấy lịch sử giao dịch phiếu gửi tiền theo ID phiếu gửi tiền")
    public ResponseEntity<List<LSGD_PGTDTO>> getLichSuGiaoDichByPhieuGuiTien(@PathVariable Long id) {
        PhieuGuiTien phieuGuiTien = phieuGuiTienRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Không tìm thấy phiếu gửi tiền với ID: " + id));
        return ResponseEntity.ok(
                repository.findByPhieuGuiTien(phieuGuiTien).stream().map(lsgdPgtMapper::toDTO).toList());
    }

    @DeleteMapping("/{id}")
    @ApiMessage("Xoá lịch sử giao dịch phiếu gửi tiền theo ID")
    public void deleteLichSuGiaoDich_PGT(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
