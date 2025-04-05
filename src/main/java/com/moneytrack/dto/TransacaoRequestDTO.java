package com.moneytrack.dto;

import com.moneytrack.model.TipoTransacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransacaoRequestDTO {
    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotNull(message = "O valor é obrigatório")
    @Positive(message = "O valor deve ser positivo")
    private BigDecimal valor;

    @NotNull(message = "A data é obrigatória")
    private LocalDateTime data;

    @NotNull(message = "A categoria é obrigatória")
    private Long categoriaId;

    @NotNull(message = "O tipo de transação é obrigatório")
    private TipoTransacao tipo;
} 