--liquibase formatted sql

--changeset luiz0451:003-descricao-diagnostico-text
ALTER TABLE tb_diagnostico
    MODIFY descricao TEXT NOT NULL COMMENT 'Descrição do diagnóstico';
--rollback ALTER TABLE tb_diagnostico MODIFY descricao VARCHAR(255) NOT NULL COMMENT 'Descrição do diagnóstico';
