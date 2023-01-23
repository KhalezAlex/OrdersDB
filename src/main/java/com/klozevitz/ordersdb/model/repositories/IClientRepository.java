package com.klozevitz.ordersdb.model.repositories;

import com.klozevitz.ordersdb.model.entities.client.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IClientRepository extends CrudRepository<Client, Integer> {
    @Override
    Optional<Client> findById(Integer id);
}
