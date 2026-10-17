package br.petroedge.oprs.api.service;

import br.petroedge.oprs.api.controller.RecursoModificadoResponse;
import br.petroedge.oprs.api.controller.manutencao.ManutencaoDTO.AdicionarManutencaoRequest;
import br.petroedge.oprs.api.controller.manutencao.ManutencaoDTO.BuscaManutencaoDetalhadoResponse;
import br.petroedge.oprs.api.controller.manutencao.ManutencaoDTO.BuscaManutencaoRequest;
import br.petroedge.oprs.api.controller.manutencao.ManutencaoDTO.BuscaManutencaoResponse;
import br.petroedge.oprs.api.controller.manutencao.ManutencaoDTO.EditarManutencaoRequest;
import br.petroedge.oprs.api.mapper.ManutencaoMapper;
import br.petroedge.oprs.api.repository.IncidenteRepository;
import br.petroedge.oprs.api.repository.ManutencaoRepository;
import br.petroedge.oprs.api.service.spec.ManutencaoSpecification;
import br.petroedge.oprs.api.utils.ManutencaoStatusEnum;
import br.petroedge.oprs.api.utils.OperacaoEnum;
import java.time.Instant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManutencaoService {

    private final ManutencaoRepository manutencaoRepository;
    private final IncidenteRepository incidenteRepository;

    public ManutencaoService(ManutencaoRepository manutencaoRepository, IncidenteRepository incidenteRepository) {
        this.manutencaoRepository = manutencaoRepository;
        this.incidenteRepository = incidenteRepository;
    }

    public Page<BuscaManutencaoResponse> buscarManutencoes(Pageable pageable, BuscaManutencaoRequest request) {
        var specification = Specification.allOf(
                ManutencaoSpecification.containsTermoBusca(request.termoBusca()),
                ManutencaoSpecification.hasStatus(request.status()),
                ManutencaoSpecification.hasIncidenteId(request.incidenteId()),
                ManutencaoSpecification.dtPrevistaBetween(request.dtPrevistaInicio(), request.dtPrevistaFim()),
                ManutencaoSpecification.dtCriacaoBetween(request.dtCriacaoInicio(), request.dtCriacaoFim()));

        var manutencoes = manutencaoRepository.findAll(specification, pageable);

        return manutencoes.map(ManutencaoMapper::toResponse);
    }

    public BuscaManutencaoDetalhadoResponse buscarManutencaoPorId(String idManutencao) {
        var manutencao = manutencaoRepository
                .findById(idManutencao)
                .orElseThrow(() -> new RuntimeException("Manutenção não encontrada com o ID: " + idManutencao));

        return ManutencaoMapper.toDetalhadoResponse(manutencao);
    }

    public RecursoModificadoResponse<String> adicionarManutencao(AdicionarManutencaoRequest request) {
        var incidente = incidenteRepository
                .findById(request.incidenteId())
                .orElseThrow(() -> new RuntimeException("Incidente não encontrado com o ID: " + request.incidenteId()));

        var manutencao = manutencaoRepository.save(ManutencaoMapper.toEntity(request, incidente));

        return new RecursoModificadoResponse<String>(
                "Manutenção criada com sucesso", OperacaoEnum.CRIACAO, manutencao.getId());
    }

    public RecursoModificadoResponse<String> atualizarManutencao(String idManutencao, EditarManutencaoRequest request) {
        var manutencao = manutencaoRepository
                .findById(idManutencao)
                .orElseThrow(() -> new RuntimeException("Manutenção não encontrada com o ID: " + idManutencao));

        manutencao.setTitulo(request.titulo());
        manutencao.setObservacao(request.observacao());
        manutencao.setResponsavel(request.responsavel());
        manutencao.setDtAtualizacao(Instant.now());

        manutencaoRepository.save(manutencao);

        return new RecursoModificadoResponse<String>(
                "Manutenção atualizada com sucesso", OperacaoEnum.ATUALIZACAO, manutencao.getId());
    }

    @Transactional
    public RecursoModificadoResponse<String> concluirManutencao(String idManutencao) {
        var manutencao = manutencaoRepository
                .findById(idManutencao)
                .orElseThrow(() -> new RuntimeException("Manutenção não encontrada com o ID: " + idManutencao));

        manutencao.setStatus(ManutencaoStatusEnum.CONCLUIDA);
        manutencao.setDtExecucao(Instant.now());
        manutencao.setDtAtualizacao(Instant.now());

        manutencaoRepository.save(manutencao);

        return new RecursoModificadoResponse<String>(
                "Manutenção concluída com sucesso", OperacaoEnum.ATUALIZACAO, manutencao.getId());
    }

    @Transactional
    public RecursoModificadoResponse<String> cancelarManutencao(String idManutencao) {
        var manutencao = manutencaoRepository
                .findById(idManutencao)
                .orElseThrow(() -> new RuntimeException("Manutenção não encontrada com o ID: " + idManutencao));

        manutencao.setStatus(ManutencaoStatusEnum.CANCELADA);
        manutencao.setDtAtualizacao(Instant.now());

        manutencaoRepository.save(manutencao);

        return new RecursoModificadoResponse<String>(
                "Manutenção cancelada com sucesso", OperacaoEnum.ATUALIZACAO, manutencao.getId());
    }
}
