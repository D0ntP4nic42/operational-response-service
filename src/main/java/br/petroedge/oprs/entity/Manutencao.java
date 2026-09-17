package br.petroedge.oprs.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;

@Entity(name = "tb_manutencao")
public class Manutencao {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "observacao", nullable = false)
    private String observacao;
    @Column(name = "dt_criacao", nullable = false)
    private LocalDateTime dtCriacao;
    @Column(name = "dt_atualizacao", nullable = false)
    private LocalDateTime dtAtualizacao;
    @Column(name = "responsavel", nullable = false)
    private String responsavel;
    @Column(name = "dt_prevista", nullable = false)
    private LocalDateTime dtPrevista;
    @Column (name = "dt_execucao", nullable = true)
    private LocalDateTime dtExecucao;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "incidente_id", nullable = false)
    @JoinColumn(name = "fk_incidente_id", referencedColumnName = "id")
    private String incidenteId;
}
