package com.example.sweet.service;

import com.example.sweet.database.respository.TaiKhoan.QuyenHanRespository;
import com.example.sweet.database.schema.TaiKhoan.QuyenHan;
import com.example.sweet.util.error.DuplicateResourceException;
import com.example.sweet.util.error.IdInvalidException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuyenHanService {

    private final QuyenHanRespository quyenHanRespository;
    public QuyenHanService(QuyenHanRespository quyenHanRespository) {
        this.quyenHanRespository = quyenHanRespository;
    }

    public boolean checkDuplicated(QuyenHan quyenHan) {
        if (quyenHan.getId() == null) {
            return quyenHanRespository.existsByApiPathAndMethodAndModule(

                    quyenHan.getApiPath(),
                    quyenHan.getMethod(),
                    quyenHan.getModule()
            );
        } else {
            return quyenHanRespository.existsByApiPathAndMethodAndModuleAndIdNot(

                    quyenHan.getApiPath(),
                    quyenHan.getMethod(),
                    quyenHan.getModule(),
                    quyenHan.getId()
            );
        }
    }

    public QuyenHan createQuyenHan(QuyenHan quyenHan) {
        if(checkDuplicated(quyenHan)) {
            throw new DuplicateResourceException("QuyenHan with the same apiPath, method, and module already exists.");
        }
        return quyenHanRespository.save(quyenHan);
    }

    public List<QuyenHan> getAllQuyenHan(Specification<QuyenHan> specification) {
        return quyenHanRespository.findAll(specification);
    }

    public QuyenHan getQuyenHanById(Long id) {
        return quyenHanRespository.findById(id)
                .orElseThrow(() -> new IdInvalidException("QuyenHan with id " + id + " not found."));
    }

    public QuyenHan updateQuyenHan(QuyenHan quyenHan) {
        QuyenHan existingQuyenHan = quyenHanRespository.findById(quyenHan.getId())
                .orElseThrow(() -> new IdInvalidException("QuyenHan with id " + quyenHan.getId() + " not found."));
        if(checkDuplicated(quyenHan)) {
            throw new DuplicateResourceException("QuyenHan with the same apiPath, method, and module already exists.");
        }

        // Update the existing entity with new values
        existingQuyenHan.setApiPath(quyenHan.getApiPath());
        existingQuyenHan.setMethod(quyenHan.getMethod());
        existingQuyenHan.setModule(quyenHan.getModule());
        existingQuyenHan.setName(quyenHan.getName());

        // Save the updated entity
        return quyenHanRespository.save(existingQuyenHan);

    }

    public void deleteQuyenHan(Long id) {
        QuyenHan quyenHan = quyenHanRespository.findById(id)
                .orElseThrow(() -> new IdInvalidException("QuyenHan with id " + id + " not found."));
        quyenHan.getVaiTros().forEach(vaiTro -> vaiTro.getQuyenHans().remove(quyenHan));
        this.quyenHanRespository.delete(quyenHan);
    }


}
