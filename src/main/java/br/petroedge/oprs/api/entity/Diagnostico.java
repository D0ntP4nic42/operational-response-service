package br.petroedge.oprs.api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_diagnostico")
@Data
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, comment = "ID do diagnóstico")
    private String id;

    @Column(name = "fonte_id", nullable = false, comment = "ID da fonte do diagnóstico")
    private Long fonteId;

    @Column(name = "descricao", nullable = false, comment = "Descrição do diagnóstico")
    private String descricao;

    @Column(name = "dt_criacao", nullable = false, comment = "Data de criação do diagnóstico")
    private LocalDateTime dtCriacao;

    @Column(name = "severidade", nullable = false, comment = "Severidade do diagnóstico")
    private Integer severidade;
}
