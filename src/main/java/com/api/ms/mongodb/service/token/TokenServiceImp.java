package com.api.ms.mongodb.service.token;

import com.api.ms.mongodb.domain.dto.EmpresaDTO;
import com.api.ms.mongodb.domain.dto.TokenDTO;
import com.api.ms.mongodb.domain.dto.util.CreateOrConvertDTO;
import com.api.ms.mongodb.domain.model.Empresa;
import com.api.ms.mongodb.domain.model.Token;
import com.api.ms.mongodb.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

//@Service

//A anotação @Service é usada em sua camada de serviço e anota classes que realizam tarefas de serviço, muitas vezes
//você não a usa, mas em muitos casos você usa essa anotação para representar uma prática recomendada. Por exemplo,
//você poderia chamar diretamente uma classe DAO para persistir um objeto em seu banco de dados, mas isso é horrível.
//É muito bom chamar uma classe de serviço que chama um DAO. Isso é uma boa coisa para executar o padrão de separação
//de interesses.

@Service

//@Transactional
//https://www.devmedia.com.br/conheca-o-spring-transactional-annotations/32472
//"A boa prática é sempre colocar o @Transactional nos métodos que precisam de transação, por exemplo: salvar, alterar,
//excluir, etc., pois assim você garante que eles vão ser executados dentro um contexto transacional e o rollback
//será feito caso ocorra algum erro."

@Transactional

//@RequiredArgsConstructor
//Gera um construtor com argumentos necessários. Os argumentos obrigatórios são campos finais e campos com restrições como @NonNull.

@RequiredArgsConstructor


public class TokenServiceImp implements TokenService{

    private final TokenRepository tokenRepository;
    private final MongoTemplate mongoTemplate;

    @Override
    public TokenDTO gerarToken(Map<String, String> headers, Map<String, String> grants){

        if (grants.containsKey("grant_type") && grants.containsValue("client_credentials")) {


            Token token = Token.builder()
                    .accessToken(UUID.randomUUID().toString())
                    .expiresIn(Long.toString(LocalDateTime.now().plusMinutes(2).toInstant(ZoneOffset.UTC).toEpochMilli()))
                    .dataCriacao(LocalDateTime.now())
                    .tokenType("bearer")
                    .build();

            return (TokenDTO) CreateOrConvertDTO.create(tokenRepository.save(token));

        }

        return null;

    }

    public boolean verifyToken(String token) {


        Query query = new Query();
        query.addCriteria(Criteria.where("acessToken").is(token));

        try{
            return (boolean) CreateOrConvertDTO.create(mongoTemplate.exists(query, Empresa.class));
        }catch (Exception e){
            return false;
        }

    }


}
