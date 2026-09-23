--liquibase formatted sql

--changeset leonardo.gomes:002-dados-iniciais
INSERT INTO tb_diagnostico (
    id,
    fonte_id,
    descricao,
    dt_criacao,
    severidade
) VALUES
(
    UUID(),
    1,
    'Temperatura do motor acima do limite operacional.',
    NOW(),
    4
),
(
    UUID(),
    1,
    'Vibração elevada detectada no equipamento.',
    NOW(),
    3
),
(
    UUID(),
    2,
    'Pressão hidráulica abaixo do nível esperado.',
    NOW(),
    4
),
(
    UUID(),
    2,
    'Nível de óleo abaixo do recomendado.',
    NOW(),
    2
),
(
    UUID(),
    3,
    'Falha crítica detectada no sistema de acionamento.',
    NOW(),
    5
);