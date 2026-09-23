package br.petroedge.oprs.api.controller.incidente;

import java.time.LocalDateTime;

import br.petroedge.oprs.api.utils.IncidenteImportanciaEnum;
import br.petroedge.oprs.api.utils.IncidenteStatusEnum;

public class IncidenteDto {
    public record BuscaIncidenteRequest(
        String termoBusca,
        IncidenteStatusEnum status,
        IncidenteImportanciaEnum importancia,
        LocalDateTime dtCriacaoInicio,
        LocalDateTime dtCriacaoFim,
        LocalDateTime dtAtualizacaoInicio,
        LocalDateTime dtAtualizacaoFim
    ) {}
}
