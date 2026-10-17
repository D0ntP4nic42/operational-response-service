package br.petroedge.oprs.api.mapper;

import java.time.Instant;
import java.time.ZoneId;

import br.petroedge.oprs.api.controller.manutencao.ManutencaoDTO.AdicionarManutencaoRequest;
import br.petroedge.oprs.api.controller.manutencao.ManutencaoDTO.BuscaManutencaoDetalhadoResponse;
import br.petroedge.oprs.api.controller.manutencao.ManutencaoDTO.BuscaManutencaoResponse;
import br.petroedge.oprs.api.entity.Incidente;
import br.petroedge.oprs.api.entity.Manutencao;
import br.petroedge.oprs.api.utils.ManutencaoStatusEnum;

public class ManutencaoMapper {

    private ManutencaoMapper() {}

    public static Manutencao toEntity(AdicionarManutencaoRequest request, Incidente incidente) {
        var manutencao = new Manutencao();
        manutencao.setTitulo(request.titulo());
        manutencao.setObservacao(request.observacao());
        manutencao.setResponsavel(request.responsavel());
        manutencao.setDtPrevista(request.dtPrevista().atZone(ZoneId.systemDefault()).toLocalDateTime());
        manutencao.setDtCriacao(Instant.now());
        manutencao.setStatus(ManutencaoStatusEnum.ABERTA);
        manutencao.setIncidente(incidente);
        return manutencao;
    }

    public static BuscaManutencaoResponse toResponse(Manutencao manutencao) {
        return new BuscaManutencaoResponse(
                manutencao.getId(),
                manutencao.getTitulo(),
                manutencao.getStatus(),
                manutencao.getResponsavel(),
                manutencao.getDtPrevista(),
                manutencao.getDtCriacao(),
                manutencao.getIncidente().getId());
    }

    public static BuscaManutencaoDetalhadoResponse toDetalhadoResponse(Manutencao manutencao) {
        return new BuscaManutencaoDetalhadoResponse(
                manutencao.getId(),
                manutencao.getTitulo(),
                manutencao.getObservacao(),
                manutencao.getStatus(),
                manutencao.getResponsavel(),
                manutencao.getDtPrevista(),
                manutencao.getDtExecucao(),
                manutencao.getDtCriacao(),
                manutencao.getDtAtualizacao(),
                manutencao.getIncidente().getId());
    }
}