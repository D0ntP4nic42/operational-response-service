package br.petroedge.oprs.api.controller.incidente;

import br.petroedge.oprs.api.controller.incidente.IncidenteDto.AdicionarIncidenteRequest;
import br.petroedge.oprs.api.controller.incidente.IncidenteDto.BuscaIncidenteDetalhadoResponse;
import br.petroedge.oprs.api.controller.incidente.IncidenteDto.BuscaIncidenteResponse;
import br.petroedge.oprs.api.entity.Diagnostico;
import br.petroedge.oprs.api.entity.Incidente;
import java.time.LocalDateTime;

public class IncidenteMapper {
    public static BuscaIncidenteResponse toResponse(Incidente incidente) {
        return new BuscaIncidenteResponse(
                incidente.getId(),
                incidente.getTitulo(),
                incidente.getStatus(),
                incidente.getImportancia(),
                incidente.getDtCriacao(),
                incidente.getDiagnostico().getId());
    }

    public static BuscaIncidenteDetalhadoResponse toDetalhadoResponse(Incidente incidente) {
        return new BuscaIncidenteDetalhadoResponse(
                incidente.getId(),
                incidente.getTitulo(),
                incidente.getDescricao(),
                incidente.getStatus(),
                incidente.getImportancia(),
                incidente.getDtCriacao(),
                incidente.getDtAtualizacao(),
                incidente.getDiagnostico().getId());
    }

    public static Incidente toEntity(AdicionarIncidenteRequest request, Diagnostico diagnostico) {
        Incidente incidente = new Incidente();
        incidente.setTitulo(request.titulo());
        incidente.setDescricao(request.descricao());
        incidente.setImportancia(request.importancia());
        incidente.setDtCriacao(LocalDateTime.now());
        incidente.setDtAtualizacao(null);
        incidente.setDiagnostico(diagnostico);
        return incidente;
    }
}
