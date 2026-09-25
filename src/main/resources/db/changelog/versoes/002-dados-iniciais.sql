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
    2,
    'Vibração elevada detectada no equipamento.',
    NOW(),
    3
),
(
    UUID(),
    3,
    'Pressão hidráulica abaixo do nível esperado.',
    NOW(),
    4
),
(
    UUID(),
    4,
    'Nível de óleo abaixo do recomendado.',
    NOW(),
    2
),
(
    UUID(),
    5,
    'Falha crítica detectada no sistema de acionamento.',
    NOW(),
    5
);