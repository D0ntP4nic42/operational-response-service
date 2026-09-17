package br.petroedge.oprs.entity;

import java.time.LocalDateTime;

import br.petroedge.oprs.utils.ManutencaoStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity 
@Table(name = "tb_manutencao")
@Data 
public class Manutencao {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, comment = "ID da manutenção")
    private String id;
    @Column(name = "observacao", nullable = false, comment = "Observação da manutenção")
    private String observacao;
    @Column(name = "dt_criacao", nullable = false, comment = "Data de criação da manutenção")
    private LocalDateTime dtCriacao;
    @Column(name = "dt_atualizacao", nullable = true, comment = "Data de atualização da manutenção")
    private LocalDateTime dtAtualizacao;
    @Column(name = "responsavel", nullable = false, comment = "Responsável pela manutenção")
    private String responsavel;
    @Column(name = "dt_prevista", nullable = false, comment = "Data prevista para a manutenção")
    private LocalDateTime dtPrevista;
    @Column (name = "dt_execucao", nullable = true, comment = "Data de execução da manutenção")
    private LocalDateTime dtExecucao;
    @Column(name = "status", nullable = false, comment = "Status da manutenção")
    @Enumerated(EnumType.STRING)
    private ManutencaoStatusEnum status;
    @Column(name = "incidente_id", nullable = false, comment = "ID do incidente associado")
    private String incidenteId;
}
