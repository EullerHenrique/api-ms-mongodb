package com.api.ms.mongodb.repository;
import com.api.ms.mongodb.domain.model.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRepository extends MongoRepository<Token, String> {

}
