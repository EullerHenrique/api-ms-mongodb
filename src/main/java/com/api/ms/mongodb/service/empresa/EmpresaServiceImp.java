package com.api.ms.mongodb.service.empresa;


import com.api.ms.mongodb.domain.dto.util.CreateOrConvertDTO;
import com.api.ms.mongodb.repository.EmpresaRepository;
import com.api.ms.mongodb.domain.dto.EmpresaDTO;
import com.api.ms.mongodb.domain.model.Empresa;
import lombok.RequiredArgsConstructor;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

public class EmpresaServiceImp implements EmpresaService{

    private final MongoTemplate mongoTemplate;
    private final EmpresaRepository empresaRepository;

    @Override
    public EmpresaDTO findById(String id) {
        return (EmpresaDTO) empresaRepository.findById(id).map(CreateOrConvertDTO::create).orElse(null);
    }


    @Override
    public EmpresaDTO findByIdAndPassword(String id, String password){

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        query.addCriteria(Criteria.where("password").is(password));

        try {
            return (EmpresaDTO) CreateOrConvertDTO.create(Objects.requireNonNull(mongoTemplate.findOne(query, Empresa.class)));
        }catch (NullPointerException e){
            return null;
        }

    }



    @Override
    public List<Object> findAll() {
        return empresaRepository.findAll().stream().map(CreateOrConvertDTO::create).collect(Collectors.toList());
    }


    @Override
    public void save(Empresa empresa){
        empresaRepository.save(empresa);
    }


}

//criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//criteria.createAlias("contratos", "c", JoinType.LEFT_OUTER_JOIN);