package com.example.sweet.services;

import com.example.sweet.database.repository.TaiKhoan.QuyenHanRepository;
import com.example.sweet.database.schema.TaiKhoan.QuyenHan;
import com.example.sweet.util.error.DuplicateResourceException;
import com.example.sweet.util.error.NotFoundException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuyenHanService {

    private final QuyenHanRepository quyenHanRepository;

    public QuyenHanService(QuyenHanRepository quyenHanRepository) {
        this.quyenHanRepository = quyenHanRepository;
    }

    public boolean checkDuplicated(QuyenHan quyenHan) {
        if (quyenHan.getId() == null) {
            return quyenHanRepository.existsByApiPathAndMethodAndModule(

                    quyenHan.getApiPath(),
                    quyenHan.getMethod(),
                    quyenHan.getModule()
            );
        } else {
            return quyenHanRepository.existsByApiPathAndMethodAndModuleAndIdNot(

                    quyenHan.getApiPath(),
                    quyenHan.getMethod(),
                    quyenHan.getModule(),
                    quyenHan.getId()
            );
        }
    }

    public QuyenHan createQuyenHan(QuyenHan quyenHan) {
        quyenHan.setId(null);
        if (checkDuplicated(quyenHan)) {
            throw new DuplicateResourceException("QuyenHan with the same apiPath, method, and module already exists.");
        }
        return quyenHanRepository.save(quyenHan);
    }

    public List<QuyenHan> getAllQuyenHan(Specification<QuyenHan> specification) {
        return quyenHanRepository.findAll(specification);
    }

    public QuyenHan getQuyenHanById(Long id) {
        return quyenHanRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("QuyenHan with id " + id + " not found."));
    }

    public QuyenHan updateQuyenHan(QuyenHan quyenHan) {
        QuyenHan existingQuyenHan = quyenHanRepository.findById(quyenHan.getId())
                .orElseThrow(() -> new NotFoundException("QuyenHan with id " + quyenHan.getId() + " not found."));
        if (checkDuplicated(quyenHan)) {
            throw new DuplicateResourceException("QuyenHan with the same apiPath, method, and module already exists.");
        }

        // Update the existing entity with new values
        existingQuyenHan.setApiPath(quyenHan.getApiPath());
        existingQuyenHan.setMethod(quyenHan.getMethod());
        existingQuyenHan.setModule(quyenHan.getModule());
        existingQuyenHan.setName(quyenHan.getName());

        // Save the updated entity
        return quyenHanRepository.save(existingQuyenHan);

    }

    public void deleteQuyenHan(Long id) {
        QuyenHan quyenHan = quyenHanRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("QuyenHan with id " + id + " not found."));
        quyenHan.getVaiTros().forEach(vaiTro -> vaiTro.getQuyenHans().remove(quyenHan));
        this.quyenHanRepository.delete(quyenHan);
    }

    public List<QuyenHan> createListQuyenHan(List<QuyenHan> quyenHanList) {
        List<QuyenHan> savedQuyenHanList = new ArrayList<>();
        for (QuyenHan quyenHan : quyenHanList) {
            if (checkDuplicated(quyenHan)) {
                continue;
            }
            savedQuyenHanList.add(quyenHanRepository.save(quyenHan));
        }
        return savedQuyenHanList;
    }
}
