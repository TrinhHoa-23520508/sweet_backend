package com.example.sweet.database.respository;

import com.example.sweet.database.schema.ThamSo;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.repository.CrudRepository;

public interface ThamSoRespository extends CrudRepository<ThamSo, Integer> {}
