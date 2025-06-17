package com.example.sweet.controller;

import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.domain.request.KhachHangRequestDTO;
import com.example.sweet.domain.response.KhachHangResponseDTO;
import com.example.sweet.services.KhachHangService;
import com.example.sweet.util.annotation.ApiMessage;
import com.turkraft.springfilter.boot.Filter;
import jakarta.validation.Valid;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class KhachHangController {

    private final KhachHangService khachHangService;

    public KhachHangController(KhachHangService khachHangService) {
        this.khachHangService = khachHangService;
    }

    //create a new Khach Hang (Customer)
    @PostMapping("/khach-hang")
    @ApiMessage("Create a new Khach Hang (Customer)")
    public ResponseEntity<KhachHang> createKhachHang(@Valid @RequestBody KhachHangRequestDTO requestDTO) {
        KhachHang newKhachHang = khachHangService.createKhachHang(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newKhachHang);
    }

    //Get Khach Hang by ID
    @GetMapping("/khach-hang/{id}")
    @ApiMessage("Get Khach Hang by ID")
    public ResponseEntity<KhachHangResponseDTO> getKhachHangById(@PathVariable Long id) {
        KhachHangResponseDTO khachHang = khachHangService.getKhachHangById(id);
        return ResponseEntity.status(HttpStatus.OK).body(khachHang);
    }

    //Get all Khach Hang
    @GetMapping("/khach-hang")
    @ApiMessage("Get all Khach Hang")
    public ResponseEntity<List<KhachHangResponseDTO>> getAllKhachHang(@Filter Specification<KhachHang> specification) {
        List<KhachHangResponseDTO> khachHangs = khachHangService.getAllKhachHang(specification);
        return ResponseEntity.status(HttpStatus.OK).body(khachHangs);
    }

    //update a khach hang
    @PutMapping("/khach-hang/{id}")
    @ApiMessage("Update a Khach Hang (Customer)")
    public ResponseEntity<KhachHangResponseDTO> updateKhachHang(
            @PathVariable Long id,
            @Valid @RequestBody KhachHangRequestDTO requestDTO) {
        KhachHangResponseDTO updatedKhachHang = khachHangService.updateKhachHang(id, requestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedKhachHang);
    }

    //Delete a khach hang
    @DeleteMapping("/khach-hang/{id}")
    @ApiMessage("Delete a Khach Hang (Customer)")
    public ResponseEntity<Void> deleteKhachHang(@PathVariable Long id) {
        khachHangService.deleteKhachHang(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
