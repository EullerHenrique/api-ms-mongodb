package com.api.ms.mongodb.domain.model;

import com.api.ms.mongodb.config.cascade.CascadeSave;
import com.api.ms.mongodb.domain.enumeration.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

//@Data = @Data é uma anotação que gera o código padronizado para classes Java: getters para todos os campos,
//setters para todos os campos não-finais e o toString apropriado, equals e implementações hashCode
//que envolvem os campos da classe.

@Data

//@Builder = Builder é um padrão de projeto de software criacional que permite a separação da construção de
//um objeto complexo da sua representação, de forma que o mesmo processo de construção possa criar diferentes representações.

@Builder

//@NoArgsConstructor = essa anotação é responsável por gerar um construtor sem parâmetros,
//vale ressaltar que se tiver campos final na sua classe deverá usar um atributo force = true em sua anotação.

@NoArgsConstructor

//@AllArgsConstructor = essa anotação é responsável por gerar um construtor com um parâmetro para cada atributo de sua classe.

@AllArgsConstructor

//Obs: A especificação do JPA diz: "A especificação JPA requer que todas as classes persistentes tenham um construtor no-arg.
//Este construtor pode ser público ou protegido. Como o compilador cria automaticamente um construtor no-arg padrão
//quando nenhum outro construtor é definido, apenas as classes que definem os construtores também deve incluir um construtor sem argumentos."

@Document("contratos")
public class Contrato {

    @Id
    public String id;

    private Long numero;

    private StatusEnum status;

    //https://spring.io/blog/2021/11/29/spring-data-mongodb-relation-modelling
    //https://www.mongodb.com/docs/manual/reference/database-references/#manual-references
    @DocumentReference
    @CascadeSave()
    public Cliente cliente;

    @DocumentReference
    @CascadeSave()
    public Empresa empresa;

    private LocalDateTime dataVencimento;

    private LocalDateTime dataCriacao;

    private LocalDateTime  dataAlteracao;

}
