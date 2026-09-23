package br.petroedge.oprs.api.controller.incidente;

import br.petroedge.oprs.api.utils.IncidenteImportanciaEnum;
import br.petroedge.oprs.api.utils.IncidenteStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

public class IncidenteDto {

    public record BuscaIncidenteRequest(
            @Schema(
                            description = "Termo utilizado para buscar pelo título ou descrição do incidente",
                            example = "motor")
                    String termoBusca,
            @Schema(description = "Status atual do incidente", example = "ABERTO") IncidenteStatusEnum status,
            @Schema(description = "Importância atribuída ao incidente", example = "ALTA")
                    IncidenteImportanciaEnum importancia,
            @Schema(description = "Data inicial do período de criação", example = "2026-09-01T00:00:00")
                    LocalDateTime dtCriacaoInicio,
            @Schema(description = "Data final do período de criação") LocalDateTime dtCriacaoFim,
            @Schema(description = "Data inicial do período de atualização") LocalDateTime dtAtualizacaoInicio,
            @Schema(description = "Data final do período de atualização") LocalDateTime dtAtualizacaoFim) {}

    public record AdicionarIncidenteRequest(
            @Schema(description = "Título do incidente", example = "Temperatura elevada no motor") String titulo,
            @Schema(
                            description = "Descrição detalhada do incidente",
                            example = "Foi identificada temperatura acima do limite operacional.")
                    String descricao,
            @Schema(description = "Importância atribuída ao incidente", example = "ALTA")
                    IncidenteImportanciaEnum importancia,
            @Schema(
                            description = "Identificador do diagnóstico que originou o incidente",
                            example = "550e8400-e29b-41d4-a716-446655440001")
                    String diagnosticoId,
            @Schema(description = "Indica se um e-mail deve ser enviado ao destinatário", example = "true")
                    boolean enviarEmail,
            @Schema(description = "Endereço de e-mail do destinatário", example = "destinatario@gmail.com")
                    List<String> emailDestinatario) {}

    public record EditarIncidenteRequest(
            @Schema(description = "Título do incidente", example = "Temperatura elevada no motor") String titulo,
            @Schema(
                            description = "Descrição detalhada do incidente",
                            example = "Foi identificada temperatura acima do limite operacional.")
                    String descricao,
            @Schema(description = "Importância atribuída ao incidente", example = "ALTA")
                    IncidenteImportanciaEnum importancia) {}

    public record BuscaIncidenteResponse(
            @Schema(description = "Identificador único do incidente", example = "550e8400-e29b-41d4-a716-446655440000")
                    String id,
            @Schema(description = "Título do incidente", example = "Temperatura elevada no motor") String titulo,
            @Schema(description = "Status atual do incidente", example = "ABERTO") IncidenteStatusEnum status,
            @Schema(description = "Importância do incidente", example = "ALTA") IncidenteImportanciaEnum importancia,
            @Schema(description = "Data de criação do incidente", example = "2026-09-23T10:30:00")
                    LocalDateTime dtCriacao,
            @Schema(
                            description = "Identificador do diagnóstico que originou o incidente",
                            example = "550e8400-e29b-41d4-a716-446655440001")
                    String diagnosticoId) {}

    public record BuscaIncidenteDetalhadoResponse(
            @Schema(description = "Identificador único do incidente", example = "550e8400-e29b-41d4-a716-446655440000")
                    String id,
            @Schema(description = "Título do incidente", example = "Temperatura elevada no motor") String titulo,
            @Schema(
                            description = "Descrição detalhada do incidente",
                            example = "Foi identificada temperatura acima do limite operacional.")
                    String descricao,
            @Schema(description = "Status atual do incidente", example = "ABERTO") IncidenteStatusEnum status,
            @Schema(description = "Importância do incidente", example = "ALTA") IncidenteImportanciaEnum importancia,
            @Schema(description = "Data de criação do incidente", example = "2026-09-23T10:30:00")
                    LocalDateTime dtCriacao,
            @Schema(description = "Data da última atualização do incidente", example = "2026-09-23T14:30:00")
                    LocalDateTime dtAtualizacao,
            @Schema(
                            description = "Identificador do diagnóstico que originou o incidente",
                            example = "550e8400-e29b-41d4-a716-446655440001")
                    String diagnosticoId) {}
}
