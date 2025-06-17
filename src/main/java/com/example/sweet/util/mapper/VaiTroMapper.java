package com.example.sweet.util.mapper;

import com.example.sweet.database.repository.TaiKhoan.QuyenHanRepository;
import com.example.sweet.database.schema.TaiKhoan.QuyenHan;
import com.example.sweet.database.schema.TaiKhoan.VaiTro;
import com.example.sweet.domain.request.VaiTroDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VaiTroMapper {
    private final QuyenHanRepository quyenHanRepository;

    public VaiTroMapper(QuyenHanRepository quyenHanRepository) {
        this.quyenHanRepository = quyenHanRepository;
    }

    public VaiTro toVaiTroEntity(VaiTroDTO vaiTroDTO) {
        if (vaiTroDTO == null) {
            return null;
        }

        VaiTro vaiTro = new VaiTro();
        vaiTro.setId(vaiTroDTO.getId());
        vaiTro.setName(vaiTroDTO.getName());
        vaiTro.setDescription(vaiTroDTO.getDescription());
        vaiTro.setActive(vaiTroDTO.getActive());

        List<QuyenHan> quyenHans = this.quyenHanRepository.findAllByIdIn(vaiTroDTO.getQuyenHanIds());

        vaiTro.setQuyenHans(quyenHans);

        return vaiTro;
    }
}
