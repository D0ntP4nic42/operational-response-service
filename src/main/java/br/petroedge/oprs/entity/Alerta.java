package br.petroedge.oprs.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Entity(name = "tb_alerta")
@Data 
public class Alerta {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "assunto", nullable = false)
    private String assunto;
    @Column(name = "mensagem", nullable = false)
    private String mensagem;
    @Column (name = "dt_criacao", nullable = false)
    private LocalDateTime dtCriacao;
    @Column (name = "dt_envio", nullable = true)
    private LocalDateTime dtEnvio;
    @Column (name = "destinatario", nullable = false)
    private String destinatario;
    @Column (name = "status", nullable = false)
    private String status;
    @Column(name = "fk_diagnostico_id", nullable = false)
    @JoinColumn(name = "fk_diagnostico_id", referencedColumnName = "id")
    private String diagnosticoId;
}
