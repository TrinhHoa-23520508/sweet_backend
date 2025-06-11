package com.example.sweet.controller.GiaoDich;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.sweet.database.schema.dto.PhieuGuiTienDTO;
import com.example.sweet.services.GiaoDich.PhieuGuiTienService;
import com.example.sweet.util.annotation.ApiMessage;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PhieuGuiTienController {

    private final PhieuGuiTienService phieuGuiTienService;

    public PhieuGuiTienController(PhieuGuiTienService phieuGuiTienService) {
        this.phieuGuiTienService = phieuGuiTienService;
    }

    @GetMapping("/phieu-gui-tien")
    @ApiMessage("Lấy tất cả phiếu gửi tiền")
    public ResponseEntity<List<PhieuGuiTienDTO>> getAllPhieuGuiTien() {
        return ResponseEntity.ok(this.phieuGuiTienService.getAllPhieuGuiTien());
    }

    @PostMapping("/phieu-gui-tien")
    @ApiMessage("Tạo mới phiếu gửi tiền")
    public ResponseEntity<PhieuGuiTienDTO> createPhieuGuiTien(
            @Valid @RequestBody PhieuGuiTienDTO phieuGuiTienDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.phieuGuiTienService.createPhieuGuiTien(phieuGuiTienDTO));
    }

    @GetMapping("/phieu-gui-tien/{id}")
    @ApiMessage("Lấy phiếu gửi tiền theo ID")
    public ResponseEntity<PhieuGuiTienDTO> getPhieuGuiTienById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.phieuGuiTienService.getPhieuGuiTienById(id));
    }

    @DeleteMapping("/phieu-gui-tien/{id}")
    @ApiMessage("Xóa phiếu gửi tiền")
    public ResponseEntity<Void> deletePhieuGuiTien(@PathVariable Integer id) {
        phieuGuiTienService.deletePhieuGuiTien(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/phieu-gui-tien/{id}")
    @ApiMessage("Cập nhật phiếu gửi tiền")
    public ResponseEntity<PhieuGuiTienDTO> updatePhieuGuiTien(
            @PathVariable Integer id,
            @Valid @RequestBody PhieuGuiTienDTO phieuGuiTienDTO) {
        return ResponseEntity.ok(
                this.phieuGuiTienService.updatePhieuGuiTien(id, phieuGuiTienDTO));
    }
}
