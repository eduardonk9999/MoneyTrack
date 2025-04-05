package com.moneytrack.dto;

import com.moneytrack.model.TipoTransacao;
import lombok.Data;

@Data
public class CategoriaDTO {
    private Long id;
    private String nome;
    private TipoTransacao tipo;
} 