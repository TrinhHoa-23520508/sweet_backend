package com.example.sweet.controller.GiaoDich;

import com.example.sweet.database.respository.GiaoDich.GiaoDichRespository;
import com.example.sweet.database.schema.GiaoDich.GiaoDich;
import com.example.sweet.util.annotation.ApiMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/giao-dich")
@AllArgsConstructor
public class GiaoDichController {
    private GiaoDichRespository respository;

    @GetMapping("/")
    @ApiMessage("Mah balls")
    public @ResponseBody Iterable<GiaoDich> getAllGiaoDich() {
        return respository.findAll();
    }

    @PostMapping("/")
    public @ResponseBody GiaoDich insertGiaoDich(@RequestBody GiaoDich giaoDich) {
        respository.save(giaoDich);
        return giaoDich;
    }

    @DeleteMapping("/{id}")
    public void deleteGiaoDich(@PathVariable int id) {
        respository.deleteById(id);
    }
}
