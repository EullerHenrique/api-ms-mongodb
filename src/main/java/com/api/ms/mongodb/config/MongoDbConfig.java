package com.api.ms.mongodb.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

//@Configuration

// Indica que uma classe declara um ou mais métodos @Bean e pode ser processada pelo contêiner Spring para gerar
// definições de bean e solicitações de serviço para esses beans em tempo de execução

@Configuration

//@RequiredArgsConstructor
//Gera um construtor com argumentos necessários. Os argumentos obrigatórios são campos finais e campos com restrições como @NonNull.

@RequiredArgsConstructor

public class MongoDbConfig {

    private final MongoTemplate mongoTemplate;

    @Bean
    public void dropDataBase(){
        mongoTemplate.getDb().drop();
    }
}
