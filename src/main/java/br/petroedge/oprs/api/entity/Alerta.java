package br.petroedge.oprs.api.entity;

import br.petroedge.oprs.api.utils.AlertaStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "tb_alerta")
@Data
public class Alerta {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, comment = "ID do alerta")
    private String id;

    @Column(name = "assunto", nullable = false, comment = "Assunto do alerta")
    private String assunto;

    @Column(name = "mensagem", nullable = false, comment = "Mensagem do alerta")
    private String mensagem;

    @Column(name = "dt_criacao", nullable = false, comment = "Data de criação do alerta")
    private LocalDateTime dtCriacao;

    @Column(name = "dt_envio", nullable = true, comment = "Data de envio do alerta")
    private LocalDateTime dtEnvio;

    @Column(name = "destinatario", nullable = false, comment = "Destinatário do alerta")
    private String destinatario;

    @Column(name = "status", nullable = false, comment = "Status do alerta")
    @Enumerated(EnumType.STRING)
    private AlertaStatusEnum status;

    @Column(name = "fk_diagnostico_id", nullable = false, comment = "ID do diagnóstico associado ao alerta")
    private String diagnosticoId;
}
