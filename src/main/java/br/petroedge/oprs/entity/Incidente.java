package br.petroedge.oprs.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import br.petroedge.oprs.utils.IncidenteStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Entity(name = "tb_incidente")
@Data
public class Incidente {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "fk_diagnostico_id", nullable = false)
    @JoinColumn(name = "fk_diagnostico_id", referencedColumnName = "id")
    private String diagnosticoId;
    @Column(name = "descricao", nullable = false)
    private String descricao;
    @Column(name = "dt_criacao", nullable = false)
    private LocalDateTime dtCriacao;
    @Column(name = "dt_atualizacao", nullable = false)
    private LocalDateTime dtAtualizacao;
    @Column(name = "status", nullable = false)
    private IncidenteStatusEnum status;
}
