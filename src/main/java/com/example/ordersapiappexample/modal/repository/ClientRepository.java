package com.example.ordersapiappexample.modal.repository;

import com.example.ordersapiappexample.modal.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository  extends CrudRepository<Client, Integer> {
}
