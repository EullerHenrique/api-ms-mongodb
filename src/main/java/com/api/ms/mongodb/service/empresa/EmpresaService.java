package com.api.ms.mongodb.service.empresa;

import com.api.ms.mongodb.domain.dto.EmpresaDTO;
import com.api.ms.mongodb.domain.model.Empresa;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated

//@Transactional

//https://www.devmedia.com.br/conheca-o-spring-transactional-annotations/32472
//"A boa prática é sempre colocar o @Transactional nos métodos que precisam de transação, por exemplo: salvar, alterar,
//excluir, etc., pois assim você garante que eles vão ser executados dentro um contexto transacional e o rollback
//será feito caso ocorra algum erro

@Transactional
public interface EmpresaService {

    EmpresaDTO findById(String id);
    EmpresaDTO findByIdAndPassword(String id, String password);
    List<Object> findAll();
    void save(Empresa empresa);


}
