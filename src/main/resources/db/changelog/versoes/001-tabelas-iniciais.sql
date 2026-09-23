--liquibase formatted sql

--changeset leonardo.gomes:001-tabelas-iniciais
CREATE TABLE tb_diagnostico (
    id VARCHAR(36) NOT NULL COMMENT 'ID do diagnóstico',
    fonte_id BIGINT NOT NULL COMMENT 'ID da fonte do diagnóstico',
    descricao VARCHAR(255) NOT NULL COMMENT 'Descrição do diagnóstico',
    dt_criacao DATETIME NOT NULL COMMENT 'Data de criação do diagnóstico',
    severidade INT NOT NULL COMMENT 'Severidade do diagnóstico',

    CONSTRAINT pk_tb_diagnostico
        PRIMARY KEY (id)
);

CREATE TABLE tb_incidente (
    id VARCHAR(36) NOT NULL COMMENT 'ID do incidente',
    fk_diagnostico_id VARCHAR(36) NOT NULL COMMENT 'ID do diagnóstico associado',
    titulo VARCHAR(255) NOT NULL COMMENT 'Título do incidente',
    descricao VARCHAR(255) NOT NULL COMMENT 'Descrição do incidente',
    dt_criacao DATETIME NOT NULL COMMENT 'Data de criação do incidente',
    dt_atualizacao DATETIME NULL COMMENT 'Data de atualização do incidente',
    status VARCHAR(50) NOT NULL COMMENT 'Status do incidente',
    importancia VARCHAR(50) NOT NULL COMMENT 'Importância do incidente',

    CONSTRAINT pk_tb_incidente
        PRIMARY KEY (id),

    CONSTRAINT fk_tb_incidente_diagnostico
        FOREIGN KEY (fk_diagnostico_id)
        REFERENCES tb_diagnostico (id)
);

CREATE TABLE tb_manutencao (
    id VARCHAR(36) NOT NULL COMMENT 'ID da manutenção',
    titulo VARCHAR(255) NOT NULL COMMENT 'Título da manutenção',
    observacao TEXT NOT NULL COMMENT 'Observação da manutenção',
    dt_criacao DATETIME NOT NULL COMMENT 'Data de criação da manutenção',
    dt_atualizacao DATETIME NULL COMMENT 'Data de atualização da manutenção',
    responsavel VARCHAR(255) NOT NULL COMMENT 'Responsável pela manutenção',
    dt_prevista DATETIME NOT NULL COMMENT 'Data prevista para a manutenção',
    dt_execucao DATETIME NULL COMMENT 'Data de execução da manutenção',
    status VARCHAR(50) NOT NULL COMMENT 'Status da manutenção',
    fk_incidente_id VARCHAR(36) NOT NULL COMMENT 'ID do incidente associado',

    CONSTRAINT pk_tb_manutencao
        PRIMARY KEY (id),

    CONSTRAINT fk_tb_manutencao_incidente
        FOREIGN KEY (fk_incidente_id)
        REFERENCES tb_incidente (id)
);

CREATE TABLE tb_alerta (
    id VARCHAR(36) NOT NULL COMMENT 'ID do alerta',
    assunto VARCHAR(255) NOT NULL COMMENT 'Assunto do alerta',
    mensagem TEXT NOT NULL COMMENT 'Mensagem do alerta',
    dt_criacao DATETIME NOT NULL COMMENT 'Data de criação do alerta',
    dt_envio DATETIME NULL COMMENT 'Data de envio do alerta',
    destinatario VARCHAR(255) NOT NULL COMMENT 'Destinatário do alerta',
    status VARCHAR(50) NOT NULL COMMENT 'Status do alerta',
    fk_diagnostico_id VARCHAR(36) NOT NULL COMMENT 'ID do diagnóstico associado ao alerta',

    CONSTRAINT pk_tb_alerta
        PRIMARY KEY (id),

    CONSTRAINT fk_tb_alerta_diagnostico
        FOREIGN KEY (fk_diagnostico_id)
        REFERENCES tb_diagnostico (id)
);

CREATE TABLE tb_audit (
    id VARCHAR(36) NOT NULL COMMENT 'ID do registro de auditoria',
    entidade VARCHAR(255) NOT NULL COMMENT 'Nome da entidade auditada',
    entidade_id VARCHAR(36) NOT NULL COMMENT 'ID da entidade auditada',
    acao VARCHAR(50) NOT NULL COMMENT 'Ação realizada',
    valor_novo TEXT NULL COMMENT 'Novo valor',
    valor_antigo TEXT NULL COMMENT 'Valor antigo',
    dt_criacao DATETIME NOT NULL COMMENT 'Data de criação do registro de auditoria',
    usuario VARCHAR(255) NOT NULL COMMENT 'Usuário que realizou a ação',

    CONSTRAINT pk_tb_audit
        PRIMARY KEY (id)
);