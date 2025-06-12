package com.example.sweet.controller.GiaoDich;

import com.example.sweet.database.repository.GiaoDich.GiaoDichRepository;
import com.example.sweet.database.schema.GiaoDich.GiaoDich;
import com.example.sweet.services.GiaoDich.GiaoDichService;
import com.example.sweet.util.annotation.ApiMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/giao-dich")
@AllArgsConstructor
public class GiaoDichController {
    private GiaoDichService service;

    @GetMapping("/")
    @ApiMessage("Mah balls")
    public ResponseEntity<Iterable<GiaoDich>> getAllGiaoDich() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<GiaoDich> insertGiaoDich(@RequestBody GiaoDich giaoDich) {
        if (giaoDich.getGiaoDichID() != 0)
            throw new IllegalStateException("Không thể update được giao dịch");
        return ResponseEntity.ok(service.createGiaoDich(giaoDich));
    }

    @DeleteMapping("/{id}")
    public void deleteGiaoDich(@PathVariable Long id) {
        service.cancelGiaoDich(id);
    }
}
