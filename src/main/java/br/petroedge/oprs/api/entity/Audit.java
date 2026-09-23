package br.petroedge.oprs.api.entity;

import java.time.LocalDateTime;

import br.petroedge.oprs.api.utils.AuditAcoesEnum;
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
@Table(name = "tb_audit")
@Data
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, comment = "ID do registro de auditoria")
    private String id;

    @Column(name = "entidade", nullable = false, comment = "Nome da entidade auditada")
    private String entidade;

    @Column(name = "entidade_id", nullable = false, comment = "ID da entidade auditada")
    private String entidadeId;

    @Column(name = "acao", nullable = false, comment = "Ação realizada")
    @Enumerated(EnumType.STRING)
    private AuditAcoesEnum acao;

    @Column(name = "valor_novo", nullable = true, comment = "Novo valor")
    private String valorNovo;

    @Column(name = "valor_antigo", nullable = true, comment = "Valor antigo")
    private String valorAntigo;

    @Column(name = "dt_criacao", nullable = false, comment = "Data de criação do registro de auditoria")
    private LocalDateTime dtCriacao;

    @Column(name = "usuario", nullable = false, comment = "Usuário que realizou a ação")
    private String usuario;
}
