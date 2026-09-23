package br.petroedge.oprs.api.entity;

import br.petroedge.oprs.api.utils.IncidenteImportanciaEnum;
import br.petroedge.oprs.api.utils.IncidenteStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "tb_incidente")
@Data
public class Incidente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, comment = "ID do incidente")
    private String id;

    @ManyToOne
    @JoinColumn(name = "fk_diagnostico_id", nullable = false)
    private Diagnostico diagnostico;

    @Column(name = "titulo", nullable = false, comment = "Título do incidente")
    private String titulo;

    @Column(name = "descricao", nullable = false, comment = "Descrição do incidente")
    private String descricao;

    @Column(name = "dt_criacao", nullable = false, comment = "Data de criação do incidente")
    private LocalDateTime dtCriacao;

    @Column(name = "dt_atualizacao", nullable = true, comment = "Data de atualização do incidente")
    private LocalDateTime dtAtualizacao;

    @Column(name = "status", nullable = false, comment = "Status do incidente")
    @Enumerated(EnumType.STRING)
    private IncidenteStatusEnum status;

    @Column(name = "importancia", nullable = false, comment = "Importância do incidente")
    @Enumerated(EnumType.STRING)
    private IncidenteImportanciaEnum importancia;
}
