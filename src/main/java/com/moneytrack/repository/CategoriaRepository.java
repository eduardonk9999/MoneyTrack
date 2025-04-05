package com.moneytrack.repository;

import com.moneytrack.model.Categoria;
import com.moneytrack.model.TipoTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByTipo(TipoTransacao tipo);
} 