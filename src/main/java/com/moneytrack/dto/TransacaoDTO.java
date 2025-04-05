package com.moneytrack.dto;

import com.moneytrack.model.TipoTransacao;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransacaoDTO {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDateTime data;
    private Long categoriaId;
    private String categoriaNome;
    private TipoTransacao tipo;
} 