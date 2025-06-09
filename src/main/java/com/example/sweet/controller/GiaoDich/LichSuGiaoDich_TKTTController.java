package com.example.sweet.controller.GiaoDich;

import com.example.sweet.database.respository.GiaoDich.LichSuGiaoDich_TKTTRespository;
import com.example.sweet.database.schema.GiaoDich.LichSuGiaoDich_TKTT;
import com.example.sweet.database.schema.TaiKhoan.TaiKhoanThanhToan;
import com.example.sweet.util.annotation.ApiMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/giao-dich/tktt/lich-su")
@AllArgsConstructor
public class LichSuGiaoDich_TKTTController {
    private LichSuGiaoDich_TKTTRespository respository;

    @GetMapping("/")
    @ApiMessage("Mah balls")
    public @ResponseBody Iterable<LichSuGiaoDich_TKTT> getAllLichSuGiaoDich_TKTT() {
        return respository.findAll();
    }

    @PostMapping("/")
    public @ResponseBody LichSuGiaoDich_TKTT insertLichSuGiaoDich_TKTT(@RequestBody LichSuGiaoDich_TKTT lichSuGiaoDich) {
        respository.save(lichSuGiaoDich);
        return lichSuGiaoDich;
    }

    @DeleteMapping("/{id}")
    public void deleteLichSuGiaoDich_TKTT(@PathVariable int id) {
        respository.deleteById(id);
    }
}
