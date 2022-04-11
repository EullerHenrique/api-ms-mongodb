package com.api.ms.mongodb.service.token;

import com.api.ms.mongodb.domain.dto.TokenDTO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Validated

//@Transactional

//https://www.devmedia.com.br/conheca-o-spring-transactional-annotations/32472
//"A boa prática é sempre colocar o @Transactional nos métodos que precisam de transação, por exemplo: salvar, alterar,
//excluir, etc., pois assim você garante que eles vão ser executados dentro um contexto transacional e o rollback
//será feito caso ocorra algum erro

@Transactional

public interface TokenService {

    TokenDTO gerarToken(Map<String, String> headers, Map<String, String> grants) throws Exception;
    boolean verifyToken(String token);

}
