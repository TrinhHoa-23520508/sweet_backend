package com.example.sweet.util.mapper;

import com.example.sweet.database.repository.TaiKhoan.NhanVienRepository;
import com.example.sweet.database.schema.Loai.QuyDinhLaiSuat;
import com.example.sweet.database.schema.TaiKhoan.NhanVien;
import com.example.sweet.domain.request.QuyDinhLaiSuatReqDTO;
import com.example.sweet.domain.response.QuyDinhLaiSuatResDTO;
import com.example.sweet.services.NhanVienService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuyDinhLaiSuatMapper {
    private final NhanVienMapper nhanVienMapper;
    private final ChiTietQuyDinhLaiSuatMapper chiTietQuyDinhLaiSuatMapper;
    private final NhanVienRepository nhanVien;

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
        responseDTO.setActive(quyDinhLaiSuat.isActive());
        responseDTO.setChiTietQuyDinhLaiSuats(
                quyDinhLaiSuat
                        .getChiTietQuyDinhLaiSuats()
                        .stream()
                        .map(chiTietQuyDinhLaiSuatMapper::toChiTietQuyDinhLaiSuatResponseDTO).toList());

        return responseDTO;
    }

    public QuyDinhLaiSuat toQuyDinhLaiSuat(QuyDinhLaiSuatReqDTO requestDTO) {
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
        quyDinhLaiSuat.setActive(requestDTO.isActive());

        if (requestDTO.getNguoiLapQuyDinhID() != null) {
            quyDinhLaiSuat.setNguoiLapQuyDinh(nhanVien.findById(requestDTO.getNguoiLapQuyDinhID())
                    .orElseThrow(() -> new NullPointerException("Người lập quy định không tồn tại")));
        } else {
            throw new NullPointerException("Nhân viên lập quy định không tồn tại");
        }
        var test = requestDTO.getChiTietQuyDinhLaiSuats().stream()
                .map(chiTietQuyDinhLaiSuatMapper::toChiTietQuyDinhLaiSuat).toList();
        quyDinhLaiSuat.setChiTietQuyDinhLaiSuats(test);

        return quyDinhLaiSuat;
    }

}
