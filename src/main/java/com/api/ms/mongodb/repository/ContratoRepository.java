package com.api.ms.mongodb.repository;

import com.api.ms.mongodb.domain.model.Contrato;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContratoRepository extends MongoRepository<Contrato, String> { }
