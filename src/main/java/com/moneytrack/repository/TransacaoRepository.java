package com.moneytrack.repository;

import com.moneytrack.model.Transacao;
import com.moneytrack.model.TipoTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByDataBetween(LocalDateTime inicio, LocalDateTime fim);
    
    List<Transacao> findByTipoAndDataBetween(TipoTransacao tipo, LocalDateTime inicio, LocalDateTime fim);
    
    @Query("SELECT SUM(t.valor) FROM Transacao t WHERE t.tipo = :tipo AND t.data BETWEEN :inicio AND :fim")
    Double calcularTotalPorPeriodo(@Param("tipo") TipoTransacao tipo, 
                                  @Param("inicio") LocalDateTime inicio, 
                                  @Param("fim") LocalDateTime fim);
} 