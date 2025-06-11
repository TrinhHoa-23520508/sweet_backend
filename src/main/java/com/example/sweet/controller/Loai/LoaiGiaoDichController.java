package com.example.sweet.controller.Loai;

import com.example.sweet.database.repository.Loai.LoaiGiaoDichRepository;
import com.example.sweet.database.schema.Loai.LoaiGiaoDich;
import com.example.sweet.util.annotation.ApiMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loai-giao-dich")
@AllArgsConstructor
public class LoaiGiaoDichController {
    private LoaiGiaoDichRepository respository;

    @GetMapping("/")
    @ApiMessage("Mah balls")
    public @ResponseBody Iterable<LoaiGiaoDich> getAllLoaiGiaoDich() {
        return respository.findAll();
    }

    @PostMapping("/")
    public @ResponseBody LoaiGiaoDich insertLoaiGiaoDich(@RequestBody LoaiGiaoDich loaiGiaoDich) {
        respository.save(loaiGiaoDich);
        return loaiGiaoDich;
    }

    @DeleteMapping("/{id}")
    public void deleteLoaiGiaoDich(@PathVariable int id) {
        respository.deleteById(id);
    }
}
