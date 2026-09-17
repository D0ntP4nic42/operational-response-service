package br.petroedge.oprs.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity(name = "tb_diagnostico")
@Data
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "fonte_id", nullable = false)
    private Long fonteId;
    @Column(name = "descricao", nullable = false)
    private String descricao;
    @Column(name = "dt_criacao", nullable = false)
    private LocalDateTime dtCriacao;
    @Column(name = "severidade", nullable = false)
    private Integer severidade;
}