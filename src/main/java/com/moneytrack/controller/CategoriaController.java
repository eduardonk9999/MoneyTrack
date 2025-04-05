package com.moneytrack.controller;

import com.moneytrack.dto.CategoriaDTO;
import com.moneytrack.model.TipoTransacao;
import com.moneytrack.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaDTO> criar(@RequestBody CategoriaDTO dto) {
        return ResponseEntity.ok(categoriaService.criar(dto));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<CategoriaDTO>> listarPorTipo(@PathVariable TipoTransacao tipo) {
        return ResponseEntity.ok(categoriaService.listarPorTipo(tipo));
    }
} 