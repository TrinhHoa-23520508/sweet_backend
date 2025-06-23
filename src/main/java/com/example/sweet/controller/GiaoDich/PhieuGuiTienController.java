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

import com.example.sweet.database.repository.dto.PhieuTraLaiDTO;
import com.example.sweet.database.repository.dto.PhieuRutTien.PhieuRutTienDTO_inp;
import com.example.sweet.database.repository.dto.PhieuRutTien.PhieuRutTienDTO_out;
import com.example.sweet.domain.response.GiaoDich.PhieuGuiTienDTO;
import com.example.sweet.services.GiaoDich.PhieuGuiTienService;
import com.example.sweet.services.GiaoDich.PhieuRutTienService;
import com.example.sweet.services.GiaoDich.PhieuTraLaiService;
import com.example.sweet.util.annotation.ApiMessage;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/phieu-gui-tien")
public class PhieuGuiTienController {
    private final PhieuGuiTienService phieuGuiTienService;
    private final PhieuTraLaiService phieuTraLaiService;
    private final PhieuRutTienService phieuRutTienService;

    public PhieuGuiTienController(PhieuGuiTienService phieuGuiTienService, PhieuTraLaiService phieuTraLaiService,
            PhieuRutTienService phieuRutTienService) {
        this.phieuGuiTienService = phieuGuiTienService;
        this.phieuTraLaiService = phieuTraLaiService;
        this.phieuRutTienService = phieuRutTienService;
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

    @GetMapping("/{khachHangId}")
    @ApiMessage("Lấy danh sách phiếu gửi tiền theo ID khách hàng")
    public ResponseEntity<List<PhieuGuiTienDTO>> getPhieuGuiTienByKhachHangId(@PathVariable Long khachHangId) {
        List<PhieuGuiTienDTO> phieuGuiTiens = phieuGuiTienService.getPhieuGuiTienByKhachHangId(khachHangId);
        return ResponseEntity.ok(phieuGuiTiens);
    }

    @GetMapping("/{id}/phieu-tra-lai")
    @ApiMessage("Lấy danh sách phiếu trả lại theo ID phiếu gửi tiền")
    public ResponseEntity<?> getPhieuTraLaiByPhieuGuiTienId(@PathVariable Long id) {
        try {
            List<PhieuTraLaiDTO> phieuTraLais = phieuTraLaiService.getPhieuTraLaiByPhieuGuiTienId(id);
            return ResponseEntity.ok(
                    phieuTraLais);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // @GetMapping("/{id}/phieu-rut-tien")
    // public ResponseEntity<?> getPhieuRutTienByPhieuGuiTienId(@PathVariable Long
    // id) {
    // try {
    // List<PhieuRutTienDTO_out> phieuRutTiens =
    // phieuRutTienService.getPhieuRutTienByPhieuGuiTienId(id);
    // return ResponseEntity.ok(
    // phieuRutTiens);
    // } catch (Exception e) {
    // return ResponseEntity.badRequest().body(null);
    // }
    // }
}
