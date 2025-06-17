package com.example.sweet.util.mapper;

import com.example.sweet.database.repository.Loai.LoaiTrangThaiRepository;
import com.example.sweet.database.schema.Loai.LoaiTrangThai;
import com.example.sweet.database.schema.TrangThai;
import com.example.sweet.domain.request.TrangThaiDTO;
import com.example.sweet.util.error.NotFoundException;
import org.springframework.stereotype.Component;

@Component
public class TrangThaiMapper {

    private LoaiTrangThaiRepository loaiTrangThaiRepository;
    public TrangThaiMapper(LoaiTrangThaiRepository loaiTrangThaiRepository) {
        this.loaiTrangThaiRepository = loaiTrangThaiRepository;
    }

    public TrangThaiDTO toTrangThaiDTO(TrangThai trangThai) {
        if (trangThai == null) {
            return null;
        }

        TrangThaiDTO trangThaiDTO = new TrangThaiDTO();
        trangThaiDTO.setTrangThaiID(trangThai.getTrangThaiID());
        trangThaiDTO.setMaTrangThai(trangThai.getMaTrangThai());
        trangThaiDTO.setTenTrangThai(trangThai.getTenTrangThai());
        trangThaiDTO.setLoaiTrangThaiID(trangThai.getLoaiTrangThai()!= null ? trangThai.getLoaiTrangThai().getLoaiTrangThaiID() : null);

        return trangThaiDTO;
    }

    public TrangThai toTrangThaiEnTiTy(TrangThaiDTO trangThaiDTO) {
        if (trangThaiDTO == null) {
            return null;
        }

        TrangThai trangThai = new TrangThai();
        trangThai.setTrangThaiID(trangThaiDTO.getTrangThaiID());
        trangThai.setMaTrangThai(trangThaiDTO.getMaTrangThai());
        trangThai.setTenTrangThai(trangThaiDTO.getTenTrangThai());
        if(trangThaiDTO.getLoaiTrangThaiID() != null){
            LoaiTrangThai LoaiTrangThai = loaiTrangThaiRepository.findById(trangThaiDTO.getLoaiTrangThaiID())
                    .orElseThrow(() -> new NotFoundException("LoaiTrangThai with id " + trangThaiDTO.getLoaiTrangThaiID() + " not found."));
            trangThai.setLoaiTrangThai(LoaiTrangThai);
        }


        return trangThai;
    }
}
