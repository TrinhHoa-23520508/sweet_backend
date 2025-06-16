package com.example.sweet.util.mapper;

import com.example.sweet.database.respository.TaiKhoan.QuyenHanRespository;
import com.example.sweet.database.schema.TaiKhoan.QuyenHan;
import com.example.sweet.database.schema.TaiKhoan.VaiTro;
import com.example.sweet.domain.request.VaiTroDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VaiTroMapper {
    private final QuyenHanRespository quyenHanRespository;

    public VaiTroMapper(QuyenHanRespository quyenHanRespository) {
        this.quyenHanRespository = quyenHanRespository;
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

        List<QuyenHan> quyenHans = this.quyenHanRespository.findAllByIdIn(vaiTroDTO.getQuyenHanIds());

        vaiTro.setQuyenHans(quyenHans);

        return vaiTro;
    }
}
