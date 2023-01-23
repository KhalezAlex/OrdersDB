package com.klozevitz.ordersdb.model.dao;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IDaoDB<E> {
    List<E> findAll();
    E findById(Integer id);
    E save(E entity);
    @Transactional
    E update(E entity);
    E delete(Integer id);
}
