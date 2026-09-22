package br.petroedge.oprs.controller.incidente;

import java.time.LocalDateTime;

import br.petroedge.oprs.utils.IncidenteImportanciaEnum;
import br.petroedge.oprs.utils.IncidenteStatusEnum;

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
