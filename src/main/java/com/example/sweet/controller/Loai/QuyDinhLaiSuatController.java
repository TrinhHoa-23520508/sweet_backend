package com.example.sweet.controller.Loai;

import com.example.sweet.database.schema.Loai.QuyDinhLaiSuat;
import com.example.sweet.domain.request.QuyDinhLaiSuatReqDTO;
import com.example.sweet.domain.response.QuyDinhLaiSuatResDTO;
import com.example.sweet.services.QuyDinhLaiSuatService;
import com.example.sweet.util.annotation.ApiMessage;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/quy-dinh-lai-suat")
@AllArgsConstructor
public class QuyDinhLaiSuatController {
    private QuyDinhLaiSuatService service;

    @GetMapping("")
    @ApiMessage("Lấy danh sách quy định lãi suất")
    public ResponseEntity<Iterable<QuyDinhLaiSuatResDTO>> getAllQuyDinhLaiSuat() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("")
    @ApiMessage("Thêm mới quy định lãi suất")
    /*
        * @param quyDinhLaiSuat Quy định lãi suất cần thêm mới/cập nhật
        * bây giờ ấy là quyDinhLaiSuat sẽ lấy vào mấy cái quy định lãi suất như bình thường
        * và thêm một list chi tiết quy định lãi suất
        * nếu mà cái list trỗng thì không thêm gì
        * nếu có thêm mà nó là null thì thêm mới
        * nếu nó có thêm mà nó không null thì cập nhật lại
     */
    public ResponseEntity<QuyDinhLaiSuatResDTO> insertQuyDinhLaiSuat(@RequestBody QuyDinhLaiSuatReqDTO quyDinhLaiSuat) {
        return ResponseEntity.ok(service.save(quyDinhLaiSuat));
    }

//
//    @PostMapping("/{id}")
//    @ApiMessage("Thêm mới/cập nhật quy định lãi suất")
//    /*
//     * @param quyDinhLaiSuat Quy định lãi suất cần thêm mới/cập nhật
//     * bây giờ ấy là quyDinhLaiSuat sẽ lấy vào mấy cái quy định lãi suất như bình thường
//     * và thêm một list chi tiết quy định lãi suất
//     * nếu mà cái list trỗng thì không thêm gì
//     * nếu có thêm mà nó là null thì thêm mới
//     * nếu nó có thêm mà nó không null thì cập nhật lại
//     */
//    public ResponseEntity<QuyDinhLaiSuatResDTO> insertQuyDinhLaiSuat(@PathVariable Long id, @RequestBody QuyDinhLaiSuatReqDTO quyDinhLaiSuat) {
//        return ResponseEntity.ok(service.save(quyDinhLaiSuat));
//    }

    @GetMapping("/{id}")
    @ApiMessage("Lấy chi tiết quy định lãi suất theo ID")
    public Optional<QuyDinhLaiSuatResDTO> getChiTietQuyDinhLaiSuat(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ApiMessage("Deactivate quy định lãi suất theo ID")
    public void deleteQuyDinhLaiSuat(@PathVariable Long id) {
        service.deleteById(id);
    }

    @GetMapping("/current")
    public ResponseEntity<QuyDinhLaiSuatResDTO> getCurrentQuyDinhLaiSuat() {
        return service.findCurrentQuyDinhLaiSuat()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
