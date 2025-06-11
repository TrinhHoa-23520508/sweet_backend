package com.example.sweet.controller.GiaoDich;

import com.example.sweet.database.repository.GiaoDich.KenhGiaoDichRepository;
import com.example.sweet.database.schema.GiaoDich.KenhGiaoDich;
import com.example.sweet.util.annotation.ApiMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/giao-dich/kenh")
@AllArgsConstructor
public class KenhGiaoDichController {
    private KenhGiaoDichRepository respository;

    @GetMapping("/")
    @ApiMessage("Mah balls")
    public ResponseEntity<Iterable<KenhGiaoDich>> getAllKenhGiaoDich() {
        return ResponseEntity.ok(respository.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<KenhGiaoDich> insertKenhGiaoDich(@RequestBody KenhGiaoDich kenhGiaoDich) {
        return ResponseEntity.ok(respository.save(kenhGiaoDich));
    }

    @DeleteMapping("/{id}")
    public void deleteKenhGiaoDich(@PathVariable int id) {
        respository.deleteById(id);
    }
}
