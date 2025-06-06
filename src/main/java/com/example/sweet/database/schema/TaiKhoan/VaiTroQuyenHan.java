package com.example.sweet.database.schema.TaiKhoan;

import com.example.sweet.database.schema.EmbeddableIds.VaiTroQuyenHanID;
import jakarta.persistence.*;


//@Entity
public class VaiTroQuyenHan {
    @EmbeddedId
    private VaiTroQuyenHanID id;

    @ManyToOne
    @MapsId("vaiTroID")
    @JoinColumn(name = "vai_tro_id")
    private VaiTro vaiTro;

    @ManyToOne
    @MapsId("quyenHanID")
    @JoinColumn(name = "quyen_han_id")
    private QuyenHan quyenHan;
}

