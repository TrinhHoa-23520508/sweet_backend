package com.example.sweet.controller.GiaoDich;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sweet.database.repository.dto.PhieuGuiTienDTO;
import com.example.sweet.database.repository.dto.PhieuTraLaiDTO;
import com.example.sweet.services.GiaoDich.PhieuGuiTienService;
import com.example.sweet.services.GiaoDich.PhieuTraLaiService;
import com.example.sweet.util.annotation.ApiMessage;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/phieu-gui-tien")
public class PhieuGuiTienController {
    private final PhieuGuiTienService phieuGuiTienService;
    private final PhieuTraLaiService phieuTraLaiService;

    public PhieuGuiTienController(PhieuGuiTienService phieuGuiTienService, PhieuTraLaiService phieuTraLaiService) {
        this.phieuGuiTienService = phieuGuiTienService;
        this.phieuTraLaiService = phieuTraLaiService;
    }

    @GetMapping
    @ApiMessage("Lấy tất cả phiếu gửi tiền")
    public ResponseEntity<List<PhieuGuiTienDTO>> getAllPhieuGuiTien() {
        return ResponseEntity.ok(this.phieuGuiTienService.getAllPhieuGuiTien());
    }

    @PostMapping
    @ApiMessage("Tạo mới phiếu gửi tiền")
    public ResponseEntity<PhieuGuiTienDTO> createPhieuGuiTien(
            @Valid @RequestBody PhieuGuiTienDTO phieuGuiTienDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.phieuGuiTienService.createPhieuGuiTien(phieuGuiTienDTO));
    }

    // @GetMapping("/phieu-gui-tien/{id}")
    // @ApiMessage("Lấy phiếu gửi tiền theo ID")
    // public ResponseEntity<PhieuGuiTienDTO> getPhieuGuiTienById(@PathVariable
    // Integer id) {
    // return ResponseEntity.ok(this.phieuGuiTienService.getPhieuGuiTienById(id));
    // }

    @DeleteMapping("/{id}")
    @ApiMessage("Xóa phiếu gửi tiền")
    public ResponseEntity<Void> deletePhieuGuiTien(@PathVariable Long id) {
        phieuGuiTienService.deletePhieuGuiTien(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{khachHangId}")
    public ResponseEntity<List<PhieuGuiTienDTO>> getPhieuGuiTienByKhachHangId(@PathVariable Long khachHangId) {
        List<PhieuGuiTienDTO> phieuGuiTiens = phieuGuiTienService.getPhieuGuiTienByKhachHangId(khachHangId);
        return ResponseEntity.ok(phieuGuiTiens);
    }

    @GetMapping("/{id}/phieu-tra-lai")
    public ResponseEntity<?> getPhieuTraLaiByPhieuGuiTienId(@PathVariable Long id) {
        try {
            List<PhieuTraLaiDTO> phieuTraLais = phieuTraLaiService.getPhieuTraLaiByPhieuGuiTienId(id);
            return ResponseEntity.ok(
                    phieuTraLais);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
