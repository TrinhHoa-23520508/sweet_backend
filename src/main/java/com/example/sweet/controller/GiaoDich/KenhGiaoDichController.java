package com.example.sweet.controller.GiaoDich;

import com.example.sweet.database.repository.GiaoDich.KenhGiaoDichRepository;
import com.example.sweet.database.schema.GiaoDich.KenhGiaoDich;
import com.example.sweet.util.annotation.ApiMessage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/giao-dich/kenh")
@AllArgsConstructor
public class KenhGiaoDichController {
    private KenhGiaoDichRepository respository;

    @GetMapping("/")
    @ApiMessage("Mah balls")
    public @ResponseBody Iterable<KenhGiaoDich> getAllKenhGiaoDich() {
        return respository.findAll();
    }

    @PostMapping("/")
    public @ResponseBody KenhGiaoDich insertKenhGiaoDich(@RequestBody KenhGiaoDich kenhGiaoDich) {
        respository.save(kenhGiaoDich);
        return kenhGiaoDich;
    }

    @DeleteMapping("/{id}")
    public void deleteKenhGiaoDich(@PathVariable int id) {
        respository.deleteById(id);
    }
}
