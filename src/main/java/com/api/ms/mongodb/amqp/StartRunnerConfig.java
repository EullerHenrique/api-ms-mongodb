package com.api.ms.mongodb.amqp;

import com.api.ms.mongodb.domain.enumeration.StatusEnum;
import com.api.ms.mongodb.domain.model.Cliente;
import com.api.ms.mongodb.domain.model.Contrato;
import com.api.ms.mongodb.domain.model.Empresa;
import com.api.ms.mongodb.domain.model.Token;
import com.api.ms.mongodb.repository.ClienteRepository;
import com.api.ms.mongodb.repository.ContratoRepository;
import com.api.ms.mongodb.repository.EmpresaRepository;
import com.api.ms.mongodb.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

//@Configuration

// Indica que uma classe declara um ou mais métodos @Bean e pode ser processada pelo contêiner Spring para gerar
// definições de bean e solicitações de serviço para esses beans em tempo de execução

@Configuration

//@RequiredArgsConstructor
//Gera um construtor com argumentos necessários. Os argumentos obrigatórios são campos finais e campos com restrições como @NonNull.

@RequiredArgsConstructor

public class StartRunnerConfig {

    private final ClienteRepository clienteRepository;
    private final ContratoRepository contratoRepository;
    private final EmpresaRepository empresaRepository;
    private final TokenRepository tokenRepository;

    private List<Cliente> clientes;
    private List<Token> tokens;
    private List<Contrato> contratos;

    @Bean
    public ApplicationRunner initializer() {
        return args -> {
            clienteRepository
                    .saveAll(
                            clientes =
                                    Arrays.asList(

                                            criarCliente("Euller_1"),
                                            criarCliente("Euller_2"),
                                            criarCliente("Euller_3")


                                    )
                    );
            contratoRepository
                    .saveAll(
                            contratos = criarContratos()
                    );
            tokenRepository
                    .saveAll(
                            tokens =
                                 Arrays.asList(
                                         criarToken(),
                                         criarToken(),
                                         criarToken()

                                 )
                    );
            empresaRepository
                   .save(
                     criarEmpresa()
                   );
        };

    }


    public Cliente criarCliente(String nome) {
        return Cliente.builder()
                .nome(nome)
                .dataCriacao(LocalDateTime.now())
                .build();
    }

    public Contrato criarContrato(Long numero, Cliente c){
        return Contrato.builder()
                .numero(numero)
                .status(StatusEnum.ATIVO)
                .cliente(c)
                .dataCriacao(LocalDateTime.now())
                .build();
    }

    public Token criarToken(){
        return Token.builder()
                .accessToken(UUID.randomUUID().toString())
                .expiresIn(Long.toString(LocalDateTime.now().plusMinutes(2).toInstant(ZoneOffset.UTC).toEpochMilli()))
                .dataCriacao(LocalDateTime.now())
                .build();

    }

    public Empresa criarEmpresa(){
        return Empresa.builder()
                .password("fd9047a1-1364-4bbe-a629-2970e9387328")
                .tokens(tokens)
                .dataCriacao(LocalDateTime.now())
                .dataAlteracao(LocalDateTime.now())
                .contratos(contratos)
                .build();
    }

    public List<Contrato> criarContratos(){

        List<Contrato> contratos = new ArrayList<>();

        clientes.stream().forEach(c -> {
            for(int i = 0; i < 3; i++) {
                contratos.add(criarContrato(new Random().nextLong(), c));
            }
        });

        return contratos;
    }



}