package com.api.ms.mongodb.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
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


@Document("tokens")
public class Token {

    //@Id = Id da tabela

    //GeneratedValue(strategy = GenerationType.IDENTITY)

    // A anotação @GeneratedValue é utilizada para informar que a geração do valor do identificador único da entidade
    //será gerenciada pelo provedor de persistência. Essa anotação deve ser adicionada logo após a anotação @Id.
    //Quando não anotamos o campo com essa opção, significa que a responsabilidade de gerar e gerenciar as chaves
    //primárias será da aplicação, em outras palavras, do nosso código.

    //Opções:

    //GenerationType.AUTO = Valor padrão, deixa com o provedor de persistência a escolha da estratégia
    //mais adequada de acordo com o banco de dados.

    //GenerationType.IDENTITY = Informamos ao provedor de persistência que os valores a serem atribuídos ao
    //identificador único serão gerados pela coluna de auto incremento do banco de dados. Assim, um valor para
    //o identificador é gerado para cada registro inserido no banco. Alguns bancos de dados podem não suportar
    //essa opção.

    //GenerationType.SEQUENCE = Informamos ao provedor de persistência que os valores serão gerados a partir
    //de uma sequence. Caso não seja especificado um nome para a sequence, será utilizada uma sequence padrão,
    //a qual será global, para todas as entidades. Caso uma sequence seja especificada, o provedor passará a
    //adotar essa sequence para criação das chaves primárias. Alguns bancos de dados podem não suportar essa opção.

    //GenerationType.TABLE = Com a opção TABLE é necessário criar uma tabela para gerenciar as chaves primárias.
    //Por causa da sobrecarga de consultas necessárias para manter a tabela atualizada, essa opção é pouco recomendada.

    @Id
    @JsonIgnore
    private String id;

    private String accessToken;
    private String expiresIn;
    private String tokenType;
    private LocalDateTime dataCriacao;



}
