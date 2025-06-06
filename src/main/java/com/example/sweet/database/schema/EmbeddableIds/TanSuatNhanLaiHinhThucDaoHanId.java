package com.example.sweet.database.schema.EmbeddableIds;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class TanSuatNhanLaiHinhThucDaoHanId implements Serializable {
    private Integer tanSuatNhanLaiID;
    private Integer hinhThucDaoHanID;

    // Default constructor
    public TanSuatNhanLaiHinhThucDaoHanId() {}

    public TanSuatNhanLaiHinhThucDaoHanId(Integer tanSuatnhanLaiID, Integer hinhThucDaoHanID) {
        this.tanSuatNhanLaiID = tanSuatnhanLaiID;
        this.hinhThucDaoHanID = hinhThucDaoHanID;
    }

    // Getters and setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TanSuatNhanLaiHinhThucDaoHanId)) return false;
        TanSuatNhanLaiHinhThucDaoHanId that = (TanSuatNhanLaiHinhThucDaoHanId) o;
        return tanSuatNhanLaiID.equals(that.tanSuatNhanLaiID) &&
                hinhThucDaoHanID.equals(that.hinhThucDaoHanID);
    }

    @Override
    public int hashCode() {
        return tanSuatNhanLaiID.hashCode() + hinhThucDaoHanID.hashCode();
    }
}
