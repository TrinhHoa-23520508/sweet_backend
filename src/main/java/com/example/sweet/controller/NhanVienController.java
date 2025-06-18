package com.example.sweet.controller;

import com.example.sweet.database.repository.TaiKhoan.KhachHangRepository;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.schema.TaiKhoan.KhachHang;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.domain.request.NhanVienRequestDTO;
import com.example.sweet.domain.response.NhanVienResponseDTO;
import com.example.sweet.services.NhanVienService;
import com.example.sweet.util.annotation.ApiMessage;
import com.turkraft.springfilter.boot.Filter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class NhanVienController {

    private final NhanVienService nhanVienService;

    public NhanVienController(NhanVienService nhanVienService) {
        this.nhanVienService = nhanVienService;
    }

    //create a new NhanVien
    @PostMapping("/nhan-vien")
    @ApiMessage("Create a new NhanVien")
    public ResponseEntity<NhanVienResponseDTO> createNhanVien(@Valid @RequestBody NhanVienRequestDTO nhanVienRequestDTO) {
        NhanVienResponseDTO response = nhanVienService.createNhanVien(nhanVienRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //get NhanVien by ID
    @GetMapping("/nhan-vien/{id}")
    @ApiMessage("Get NhanVien by ID")
    public ResponseEntity<NhanVienResponseDTO> getNhanVienById(@PathVariable Long id) {
        NhanVienResponseDTO response = nhanVienService.getNhanVienById(id);
        return ResponseEntity.ok(response);
    }

    //Get all NhanVien with filter
    @GetMapping("/nhan-vien")
    @ApiMessage("Get all NhanVien")
    public ResponseEntity<List<NhanVienResponseDTO>> getAllNhanVien(@Filter Specification<NhanVien> specification) {
        List<NhanVienResponseDTO> response = nhanVienService.getAllNhanVien(specification);
        return ResponseEntity.ok(response);
    }

    //update NhanVien
    @PutMapping("/nhan-vien/{id}")
    @ApiMessage("Update NhanVien")
    public ResponseEntity<NhanVienResponseDTO> updateNhanVien(@PathVariable Long id, @Valid @RequestBody NhanVienRequestDTO nhanVienRequestDTO) {
        NhanVienResponseDTO response = nhanVienService.updateNhanVien(id, nhanVienRequestDTO);
        return ResponseEntity.ok(response);
    }

    //deactivate NhanVien
    @PutMapping("/nhan-vien/{id}/vo-hieu-hoa")
    @ApiMessage("Deactivate NhanVien")
    public ResponseEntity<Void> deactivateNhanVien(@PathVariable Long id) {
        nhanVienService.deactivateNhanVien(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //activate NhanVien
    @PutMapping("/nhan-vien/{id}/kich-hoat")
    @ApiMessage("Activate NhanVien")
    public ResponseEntity<Void> activateNhanVien(@PathVariable Long id) {
        nhanVienService.activateNhanVien(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
