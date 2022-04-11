package com.api.ms.mongodb.repository;

import com.api.ms.mongodb.domain.model.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente, String> { }