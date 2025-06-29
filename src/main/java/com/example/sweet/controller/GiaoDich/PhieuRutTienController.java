package com.example.sweet.controller.GiaoDich;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sweet.database.repository.dto.PhieuRutTien.PhieuRutTienDTO_inp;
import com.example.sweet.database.repository.dto.PhieuRutTien.PhieuRutTienDTO_out;
import com.example.sweet.database.schema.GiaoDich.PhieuRutTien;
import com.example.sweet.domain.request.PhieuRutTienreqDTO;
import com.example.sweet.services.GiaoDich.PhieuRutTienService;
import com.example.sweet.util.annotation.ApiMessage;

import java.util.List;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1")
public class PhieuRutTienController {
    private final PhieuRutTienService phieuRutTienService;

    public PhieuRutTienController(PhieuRutTienService phieuRutTienService) {
        this.phieuRutTienService = phieuRutTienService;
    }

    @GetMapping("/phieu-rut-tien")
    @ApiMessage("Lấy danh sách các phiếu rút tiền")
    public ResponseEntity<List<PhieuRutTien>> getPhieuRutTien() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(phieuRutTienService.handleGetAllPhieuRutTien());
    }

    @PostMapping("/phieu-rut-tien")
    @ApiMessage("Tạo mới một phiếu rút tiền")
    public ResponseEntity<PhieuRutTien> createPhieuRutTien(@RequestBody PhieuRutTienreqDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(phieuRutTienService.handleCreatePhieuRutTien(dto));
    }

}
