package com.example.sweet.database.repository;

import com.example.sweet.database.schema.ThamSo;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThamSoRepository extends CrudRepository<ThamSo, Integer> {
}
