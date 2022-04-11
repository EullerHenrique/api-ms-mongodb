package com.api.ms.mongodb.repository;

import com.api.ms.mongodb.domain.model.Empresa;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface EmpresaRepository extends MongoRepository<Empresa, String> {

}
