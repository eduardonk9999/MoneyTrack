package com.moneytrack.service;

import com.moneytrack.dto.CategoriaDTO;
import com.moneytrack.model.Categoria;
import com.moneytrack.model.TipoTransacao;
import com.moneytrack.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    @Transactional
    public CategoriaDTO criar(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        categoria.setTipo(dto.getTipo());
        categoria = categoriaRepository.save(categoria);
        return converterParaDTO(categoria);
    }

    public List<CategoriaDTO> listarPorTipo(TipoTransacao tipo) {
        return categoriaRepository.findByTipo(tipo)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    private CategoriaDTO converterParaDTO(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNome(categoria.getNome());
        dto.setTipo(categoria.getTipo());
        return dto;
    }
} 