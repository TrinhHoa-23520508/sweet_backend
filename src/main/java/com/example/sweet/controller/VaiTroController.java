package com.example.sweet.controller;

import com.example.sweet.database.schema.TaiKhoan.VaiTro;
import com.example.sweet.domain.request.ModuleDTO;
import com.example.sweet.domain.request.VaiTroDTO;
import com.example.sweet.services.VaiTroService;
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
public class VaiTroController {

    private final VaiTroService vaiTroService;

    public VaiTroController(VaiTroService vaiTroService) {
        this.vaiTroService = vaiTroService;
    }

    //create a new vai tro
    @PostMapping("/vai-tro")
    @ApiMessage("Create a new Vai Tro")
    public ResponseEntity<VaiTro> createVaiTro(@Valid @RequestBody VaiTroDTO vaiTroDTO) {
        VaiTro createdVaiTro = vaiTroService.createVaiTro(vaiTroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVaiTro);
    }

    //Get all vai tro
    @GetMapping("/vai-tro")
    @ApiMessage("Get all Vai Tro")
    public ResponseEntity<List<VaiTro>> getAllVaiTro(@Filter Specification<VaiTro> specification) {
        List<VaiTro> vaiTroList = vaiTroService.getAllVaiTro(specification);
        return ResponseEntity.ok(vaiTroList);
    }

    //Get vai tro by ID
    @GetMapping("/vai-tro/{id}")
    @ApiMessage("Get Vai Tro by ID")
    public ResponseEntity<VaiTro> getVaiTroById(@PathVariable("id") Long id) {
        VaiTro vaiTro = vaiTroService.getVaiTroById(id);
        return ResponseEntity.ok(vaiTro);
    }

    //Update vai tro
    @PutMapping("/vai-tro/{id}")
    @ApiMessage("Update Vai Tro by ID")
    public ResponseEntity<VaiTro> updateVaiTro(@PathVariable("id") Long id, @Valid @RequestBody VaiTroDTO vaiTroDTO) {
        vaiTroDTO.setId(id);
        VaiTro updatedVaiTro = vaiTroService.updateVaiTro(vaiTroDTO);
        return ResponseEntity.ok(updatedVaiTro);
    }

    //Delete vai tro
    @DeleteMapping("/vai-tro/{id}")
    @ApiMessage("Delete Vai Tro by ID")
    public ResponseEntity<Void> deleteVaiTro(@PathVariable("id") Long id) {
        vaiTroService.deleteVaiTro(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    //add full api of module to Vai Tro
    @PutMapping("/vai-tro/{id}/cap-quyen-module")
    @ApiMessage("Cap Quyen Module to Vai Tro")
    public ResponseEntity<VaiTro> capQuyenModuleToVaiTro(
            @PathVariable("id") Long id,
            @RequestBody ModuleDTO moduleDTO) {
        VaiTro updatedVaiTro = vaiTroService.capQuyenModuleToVaiTro(id, moduleDTO);
        return ResponseEntity.ok(updatedVaiTro);
    }

    //remove full api of module from Vai Tro
    @PutMapping("/vai-tro/{id}/xoa-quyen-module")
    @ApiMessage("Xoa Quyen Module from Vai Tro")
    public ResponseEntity<VaiTro> xoaQuyenModuleFromVaiTro(
            @PathVariable("id") Long id,
            @RequestBody ModuleDTO moduleDTO) {
        VaiTro updatedVaiTro = vaiTroService.xoaQuyenModuleFromVaiTro(id, moduleDTO);
        return ResponseEntity.ok(updatedVaiTro);
    }

}
