package br.petroedge.oprs.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import br.petroedge.oprs.utils.AuditAcoesEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity(name = "tb_audit")
@Data 
public class Audit {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "entidade", nullable = false)
    private String entidade;
    @Column(name = "entidade_id", nullable = false)
    private String entidadeId;
    @Column(name = "acao", nullable = false)
    private AuditAcoesEnum acao;
    @Column(name = "valor_novo", nullable = true)
    private String valorNovo;
    @Column(name = "valor_antigo", nullable = true)
    private String valorAntigo;
    @Column(name = "dt_criacao", nullable = false)
    private LocalDateTime dtCriacao;
    @Column(name = "usuario", nullable = false)
    private String usuario;
}
