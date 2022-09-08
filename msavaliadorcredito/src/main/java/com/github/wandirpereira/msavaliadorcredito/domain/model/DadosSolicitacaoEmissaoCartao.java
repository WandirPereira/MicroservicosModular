package com.github.wandirpereira.msavaliadorcredito.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.function.BiConsumer;

@Data
public class DadosSolicitacaoEmissaoCartao {
    private Long idCartao;
    private String cpf;
    private String endereco;
    private BigDecimal limiteLiberado;
}
