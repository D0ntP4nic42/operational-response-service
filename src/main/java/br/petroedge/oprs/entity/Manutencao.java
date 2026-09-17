package br.petroedge.oprs.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import br.petroedge.oprs.utils.ManutencaoStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;

@Entity(name = "tb_manutencao")
public class Manutencao {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, comment = "ID da manutenção")
    private String id;
    @Column(name = "observacao", nullable = false, comment = "Observação da manutenção")
    private String observacao;
    @Column(name = "dt_criacao", nullable = false, comment = "Data de criação da manutenção")
    private LocalDateTime dtCriacao;
    @Column(name = "dt_atualizacao", nullable = false, comment = "Data de atualização da manutenção")
    private LocalDateTime dtAtualizacao;
    @Column(name = "responsavel", nullable = false, comment = "Responsável pela manutenção")
    private String responsavel;
    @Column(name = "dt_prevista", nullable = false, comment = "Data prevista para a manutenção")
    private LocalDateTime dtPrevista;
    @Column (name = "dt_execucao", nullable = true, comment = "Data de execução da manutenção")
    private LocalDateTime dtExecucao;
    @Column(name = "status", nullable = false, comment = "Status da manutenção")
    private ManutencaoStatusEnum status;
    @Column(name = "incidente_id", nullable = false, comment = "ID do incidente associado")
    @JoinColumn(name = "fk_incidente_id", referencedColumnName = "id")
    private String incidenteId;
}
