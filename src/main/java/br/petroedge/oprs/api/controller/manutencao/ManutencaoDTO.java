package br.petroedge.oprs.api.controller.manutencao;

import java.time.Instant;
import java.time.LocalDateTime;

import br.petroedge.oprs.api.utils.ManutencaoStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;

public class ManutencaoDTO {

    public record BuscaManutencaoRequest(
            @Schema(
                            description = "Termo utilizado para buscar pelo título ou observação da manutenção",
                            example = "troca de óleo")
                    String termoBusca,
            @Schema(description = "Status atual da manutenção", example = "ABERTA") ManutencaoStatusEnum status,
            @Schema(
                            description = "Identificador do incidente associado",
                            example = "550e8400-e29b-41d4-a716-446655440002")
                    String incidenteId,
            @Schema(description = "Data inicial do período previsto", example = "2026-09-01T00:00:00")
                    LocalDateTime dtPrevistaInicio,
            @Schema(description = "Data final do período previsto") LocalDateTime dtPrevistaFim,
            @Schema(description = "Data inicial do período de criação") Instant dtCriacaoInicio,
            @Schema(description = "Data final do período de criação") Instant dtCriacaoFim) {}

    public record AdicionarManutencaoRequest(
            @Schema(
                            description = "Identificador do incidente que originou a manutenção",
                            example = "550e8400-e29b-41d4-a716-446655440002")
                    String incidenteId,
            @Schema(description = "Título da manutenção", example = "Troca de óleo do motor") String titulo,
            @Schema(
                            description = "Observação detalhada da manutenção",
                            example = "Troca programada após identificação de desgaste no óleo.")
                    String observacao,
            @Schema(description = "Responsável pela execução da manutenção", example = "João Silva")
                    String responsavel,
            @Schema(description = "Data prevista para a manutenção", example = "2026-10-01T09:00:00")
                    LocalDateTime dtPrevista) {}

    public record EditarManutencaoRequest(
            @Schema(description = "Título da manutenção", example = "Troca de óleo do motor") String titulo,
            @Schema(
                            description = "Observação detalhada da manutenção",
                            example = "Troca programada após identificação de desgaste no óleo.")
                    String observacao,
            @Schema(description = "Responsável pela execução da manutenção", example = "João Silva")
                    String responsavel,
            @Schema(description = "Data prevista para a manutenção", example = "2026-10-01T09:00:00")
                    LocalDateTime dtPrevista) {}

    public record BuscaManutencaoResponse(
            @Schema(description = "Identificador único da manutenção", example = "550e8400-e29b-41d4-a716-446655440003")
                    String id,
            @Schema(description = "Título da manutenção", example = "Troca de óleo do motor") String titulo,
            @Schema(description = "Status atual da manutenção", example = "ABERTA") ManutencaoStatusEnum status,
            @Schema(description = "Responsável pela execução da manutenção", example = "João Silva")
                    String responsavel,
            @Schema(description = "Data prevista para a manutenção", example = "2026-10-01T09:00:00")
                    LocalDateTime dtPrevista,
            @Schema(description = "Data de criação da manutenção", example = "2026-09-23T10:30:00") Instant dtCriacao,
            @Schema(
                            description = "Identificador do incidente associado",
                            example = "550e8400-e29b-41d4-a716-446655440002")
                    String incidenteId) {}

    public record BuscaManutencaoDetalhadoResponse(
            @Schema(description = "Identificador único da manutenção", example = "550e8400-e29b-41d4-a716-446655440003")
                    String id,
            @Schema(description = "Título da manutenção", example = "Troca de óleo do motor") String titulo,
            @Schema(
                            description = "Observação detalhada da manutenção",
                            example = "Troca programada após identificação de desgaste no óleo.")
                    String observacao,
            @Schema(description = "Status atual da manutenção", example = "ABERTA") ManutencaoStatusEnum status,
            @Schema(description = "Responsável pela execução da manutenção", example = "João Silva")
                    String responsavel,
            @Schema(description = "Data prevista para a manutenção", example = "2026-10-01T09:00:00")
                    LocalDateTime dtPrevista,
            @Schema(description = "Data de execução da manutenção", example = "2026-10-01T11:20:00")
                    Instant dtExecucao,
            @Schema(description = "Data de criação da manutenção", example = "2026-09-23T10:30:00") Instant dtCriacao,
            @Schema(description = "Data da última atualização da manutenção", example = "2026-09-23T14:30:00")
                    Instant dtAtualizacao,
            @Schema(
                            description = "Identificador do incidente associado",
                            example = "550e8400-e29b-41d4-a716-446655440002")
                    String incidenteId) {}
}