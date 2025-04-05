package com.moneytrack.service;

import com.moneytrack.dto.TransacaoDTO;
import com.moneytrack.dto.TransacaoRequestDTO;
import com.moneytrack.model.Categoria;
import com.moneytrack.model.Transacao;
import com.moneytrack.model.TipoTransacao;
import com.moneytrack.repository.CategoriaRepository;
import com.moneytrack.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransacaoService {
    private final TransacaoRepository transacaoRepository;
    private final CategoriaRepository categoriaRepository;

    @Transactional
    public TransacaoDTO criar(TransacaoRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Transacao transacao = new Transacao();
        transacao.setDescricao(dto.getDescricao());
        transacao.setValor(dto.getValor());
        transacao.setData(dto.getData());
        transacao.setCategoria(categoria);
        transacao.setTipo(dto.getTipo());

        transacao = transacaoRepository.save(transacao);
        return converterParaDTO(transacao);
    }

    public List<TransacaoDTO> listarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return transacaoRepository.findByDataBetween(inicio, fim)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<TransacaoDTO> listarPorTipoEPeriodo(TipoTransacao tipo, LocalDateTime inicio, LocalDateTime fim) {
        return transacaoRepository.findByTipoAndDataBetween(tipo, inicio, fim)
                .stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public Double calcularSaldoPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        Double receitas = transacaoRepository.calcularTotalPorPeriodo(TipoTransacao.RECEITA, inicio, fim);
        Double despesas = transacaoRepository.calcularTotalPorPeriodo(TipoTransacao.DESPESA, inicio, fim);

        receitas = receitas != null ? receitas : 0.0;
        despesas = despesas != null ? despesas : 0.0;

        return receitas - despesas;
    }

    private TransacaoDTO converterParaDTO(Transacao transacao) {
        TransacaoDTO dto = new TransacaoDTO();
        dto.setId(transacao.getId());
        dto.setDescricao(transacao.getDescricao());
        dto.setValor(transacao.getValor());
        dto.setData(transacao.getData());
        dto.setCategoriaId(transacao.getCategoria().getId());
        dto.setCategoriaNome(transacao.getCategoria().getNome());
        dto.setTipo(transacao.getTipo());
        return dto;
    }
} 