package com.example.sweet.controller.GiaoDich;

import com.example.sweet.database.respository.GiaoDich.KenhGiaoDichRespository;
import com.example.sweet.database.schema.GiaoDich.KenhGiaoDich;
import com.example.sweet.util.annotation.ApiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/giao-dich/kenh")
public class KenhGiaoDichController {
    @Autowired private KenhGiaoDichRespository respository;

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
