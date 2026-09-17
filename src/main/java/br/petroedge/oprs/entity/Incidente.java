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
    @Column(name = "id", nullable = false, comment = "ID do incidente")
    private String id;
    @Column(name = "fk_diagnostico_id", nullable = false, comment = "ID do diagnóstico associado")
    @JoinColumn(name = "fk_diagnostico_id", referencedColumnName = "id")
    private String diagnosticoId;
    @Column(name = "descricao", nullable = false, comment = "Descrição do incidente")
    private String descricao;
    @Column(name = "dt_criacao", nullable = false, comment = "Data de criação do incidente")
    private LocalDateTime dtCriacao;
    @Column(name = "dt_atualizacao", nullable = false, comment = "Data de atualização do incidente")
    private LocalDateTime dtAtualizacao;
    @Column(name = "status", nullable = false, comment = "Status do incidente")
    private IncidenteStatusEnum status;
}
