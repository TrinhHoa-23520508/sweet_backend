package com.example.sweet.util.mapper;

import com.example.sweet.database.repository.Loai.ChiTietQuyDinhLaiSuatRepository;
import com.example.sweet.database.repository.Loai.QuyDinhLaiSuatRepository;
import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.schema.Loai.ChiTietQuyDinhLaiSuat;
import com.example.sweet.database.schema.Loai.QuyDinhLaiSuat;
import com.example.sweet.domain.request.QuyDinhLaiSuatReqDTO;
import com.example.sweet.domain.response.QuyDinhLaiSuatResDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuyDinhLaiSuatMapper {
    private final NhanVienMapper nhanVienMapper;
    private final ChiTietQuyDinhLaiSuatMapper chiTietQuyDinhLaiSuatMapper;
    private final ChiTietQuyDinhLaiSuatRepository chiTietQuyDinhLaiSuatRepository;
    private final NhanVienRepository nhanVien;
    private final QuyDinhLaiSuatRepository quyDinhLaiSuatRepository;

    public QuyDinhLaiSuatResDTO toQuyDinhLaiSuatResponseDTO(QuyDinhLaiSuat quyDinhLaiSuat) {
        if (quyDinhLaiSuat == null) {
            return null;
        }

        QuyDinhLaiSuatResDTO responseDTO = new QuyDinhLaiSuatResDTO();
        responseDTO.setQuyDinhLaiSuatID(quyDinhLaiSuat.getQuyDinhLaiSuatID());
        responseDTO.setNgayBatDau(quyDinhLaiSuat.getNgayBatDau());
        responseDTO.setNgayKetThuc(quyDinhLaiSuat.getNgayKetThuc());
        responseDTO.setMoTa(quyDinhLaiSuat.getMoTa());
        responseDTO
                .setNguoiLapQuyDinh(nhanVienMapper.toNhanVienNoVaiTroResponseDTO(quyDinhLaiSuat.getNguoiLapQuyDinh()));
        responseDTO.setLaiSuatKhongKyHan(quyDinhLaiSuat.getLaiSuatKhongKyHan());
        responseDTO.setSoTienGuiToiThieu(quyDinhLaiSuat.getSoTienGuiToiThieu());
        responseDTO.setActive(quyDinhLaiSuat.getActive());
        responseDTO.setChiTietQuyDinhLaiSuats(
                chiTietQuyDinhLaiSuatRepository.findAllByQuyDinhLaiSuat(quyDinhLaiSuat)
                        .stream()
                        .map(chiTietQuyDinhLaiSuatMapper::toChiTietQuyDinhLaiSuatResponseDTO).toList());

        return responseDTO;
    }

    public QuyDinhLaiSuat toQuyDinhLaiSuat(QuyDinhLaiSuatReqDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }

        QuyDinhLaiSuat quyDinhLaiSuat = toQuyDinhLaiSuatGoc(requestDTO);

        var chiTiets = requestDTO.getChiTietQuyDinhLaiSuats().stream()
                .map((value) -> {
                    ChiTietQuyDinhLaiSuat result = chiTietQuyDinhLaiSuatMapper.toChiTietQuyDinhLaiSuat(value);
                    result.setQuyDinhLaiSuat(quyDinhLaiSuat);
                    return result;
                }).toList();
        quyDinhLaiSuat.setChiTietQuyDinhLaiSuats(chiTiets);

        return quyDinhLaiSuat;
    }

    public QuyDinhLaiSuat toQuyDinhLaiSuatGoc(QuyDinhLaiSuatReqDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }

        QuyDinhLaiSuat quyDinhLaiSuat = new QuyDinhLaiSuat();

        if (requestDTO.getQuyDinhLaiSuatID() != null) {
            quyDinhLaiSuat.setQuyDinhLaiSuatID(requestDTO.getQuyDinhLaiSuatID());
        }

        quyDinhLaiSuat.setNgayBatDau(requestDTO.getNgayBatDau());
        quyDinhLaiSuat.setNgayKetThuc(requestDTO.getNgayKetThuc());
        quyDinhLaiSuat.setMoTa(requestDTO.getMoTa());
        quyDinhLaiSuat.setLaiSuatKhongKyHan(requestDTO.getLaiSuatKhongKyHan());
        quyDinhLaiSuat.setSoTienGuiToiThieu(requestDTO.getSoTienGuiToiThieu());
        quyDinhLaiSuat.setActive(requestDTO.getActive());

        if (requestDTO.getNgayBatDau() != null) {
            var existingQuyDinh = quyDinhLaiSuatRepository.findByNgayBatDau(requestDTO.getNgayBatDau());
            if (existingQuyDinh.isPresent()) {
                throw new IllegalArgumentException(
                        String.format("Ngày áp dụng %s đã trùng với quy định có ID: %d",
                                requestDTO.getNgayBatDau(),
                                existingQuyDinh.get().getQuyDinhLaiSuatID()));
            }
            quyDinhLaiSuat.setNgayBatDau(requestDTO.getNgayBatDau());
        }

        if (requestDTO.getNguoiLapQuyDinhID() != null) {
            quyDinhLaiSuat.setNguoiLapQuyDinh(nhanVien.findById(requestDTO.getNguoiLapQuyDinhID())
                    .orElseThrow(() -> new NullPointerException("Người lập quy định không tồn tại")));
        } else {
            throw new NullPointerException("Nhân viên lập quy định không tồn tại");
        }
        if (requestDTO.getActive() == null) {
            quyDinhLaiSuat.setActive(true);
        } else {
            quyDinhLaiSuat.setActive(requestDTO.getActive());
        }

        return quyDinhLaiSuat;
    }

}
