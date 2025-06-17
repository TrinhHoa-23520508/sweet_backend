package com.example.sweet.database.repository;

import com.example.sweet.database.schema.ThamSo;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThamSoRepository extends JpaRepository<ThamSo, Long>, JpaSpecificationExecutor<ThamSo> {
    boolean existsByMaThamSo(String maThamSo);

    boolean existsByMaThamSoAndThamSoIDNot(String maThamSo, Long thamSoID);

    Optional<ThamSo> findByMaThamSo(String maThamSo);
}
