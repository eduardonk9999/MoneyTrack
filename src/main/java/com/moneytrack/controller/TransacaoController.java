package com.moneytrack.controller;

import com.moneytrack.dto.TransacaoDTO;
import com.moneytrack.dto.TransacaoRequestDTO;
import com.moneytrack.model.TipoTransacao;
import com.moneytrack.service.TransacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/transacoes")
@RequiredArgsConstructor
public class TransacaoController {
    private final TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<TransacaoDTO> criar(@Valid @RequestBody TransacaoRequestDTO dto) {
        return ResponseEntity.ok(transacaoService.criar(dto));
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<TransacaoDTO>> listarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return ResponseEntity.ok(transacaoService.listarPorPeriodo(inicio, fim));
    }

    @GetMapping("/tipo/{tipo}/periodo")
    public ResponseEntity<List<TransacaoDTO>> listarPorTipoEPeriodo(
            @PathVariable TipoTransacao tipo,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return ResponseEntity.ok(transacaoService.listarPorTipoEPeriodo(tipo, inicio, fim));
    }

    @GetMapping("/saldo/periodo")
    public ResponseEntity<Double> calcularSaldoPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim) {
        return ResponseEntity.ok(transacaoService.calcularSaldoPorPeriodo(inicio, fim));
    }
} 