package br.petroedge.oprs.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.petroedge.oprs.api.controller.RecursoModificadoResponse;
import br.petroedge.oprs.api.controller.incidente.IncidenteDto.AdicionarIncidenteRequest;
import br.petroedge.oprs.api.controller.incidente.IncidenteDto.BuscaIncidenteDetalhadoResponse;
import br.petroedge.oprs.api.controller.incidente.IncidenteDto.BuscaIncidenteRequest;
import br.petroedge.oprs.api.controller.incidente.IncidenteDto.BuscaIncidenteResponse;
import br.petroedge.oprs.api.controller.incidente.IncidenteDto.EditarIncidenteRequest;
import br.petroedge.oprs.api.controller.incidente.IncidenteMapper;
import br.petroedge.oprs.api.repository.DiagnosticoRepository;
import br.petroedge.oprs.api.repository.IncidenteRepository;
import br.petroedge.oprs.api.repository.ManutencaoRepository;
import br.petroedge.oprs.api.service.spec.IncidenteSpecification;
import br.petroedge.oprs.api.utils.IncidenteStatusEnum;
import br.petroedge.oprs.api.utils.ManutencaoStatusEnum;
import br.petroedge.oprs.api.utils.OperacaoEnum;
import jakarta.transaction.Transactional;

@Service 
public class IncidenteService {
    private final IncidenteRepository incidenteRepository;
    private final DiagnosticoRepository diagnosticoRepository;
    private final ManutencaoRepository manutencaoRepository;

    public IncidenteService(IncidenteRepository incidenteRepository, DiagnosticoRepository diagnosticoRepository, ManutencaoRepository manutencaoRepository) {
        this.incidenteRepository = incidenteRepository;
        this.diagnosticoRepository = diagnosticoRepository;
        this.manutencaoRepository = manutencaoRepository;
    }

    public Page<BuscaIncidenteResponse> buscarIncidentes(Pageable pageable, BuscaIncidenteRequest request) {
        var specification = Specification.allOf(
            IncidenteSpecification.containsTermoBusca(request.termoBusca()),
            IncidenteSpecification.hasStatus(request.status()),
            IncidenteSpecification.hasImportancia(request.importancia()),
            IncidenteSpecification.creationBetween(request.dtCriacaoInicio(), request.dtCriacaoFim()),
            IncidenteSpecification.updateBetween(request.dtAtualizacaoInicio(), request.dtAtualizacaoFim())
        );

        var incidentes = incidenteRepository.findAll(specification, pageable);

        return incidentes.map(IncidenteMapper::toResponse);
    }

    public BuscaIncidenteDetalhadoResponse buscarIncidentePorId(String idIncidente) {
        var incidente = incidenteRepository.findById(idIncidente)
                .orElseThrow(() -> new RuntimeException("Incidente não encontrado com o ID: " + idIncidente));

        return IncidenteMapper.toDetalhadoResponse(incidente);
    }

    public RecursoModificadoResponse<String> adicionarIncidentes(AdicionarIncidenteRequest request) {
        var diagnostico = diagnosticoRepository.findById(request.diagnosticoId())
                .orElseThrow(() -> new RuntimeException("Diagnóstico não encontrado com o ID: " + request.diagnosticoId()));

        var incidente = incidenteRepository.save(IncidenteMapper.toEntity(request, diagnostico));
        return new RecursoModificadoResponse<String>("Incidente criado com sucesso", OperacaoEnum.CRIACAO, incidente.getId());
    }

    public RecursoModificadoResponse<String> atualizarIncidente(String idIncidente, EditarIncidenteRequest request) {
        var incidente = incidenteRepository.findById(idIncidente)
                .orElseThrow(() -> new RuntimeException("Incidente não encontrado com o ID: " + idIncidente));

        incidente.setTitulo(request.titulo());
        incidente.setDescricao(request.descricao());
        incidente.setImportancia(request.importancia());
        
        incidenteRepository.save(incidente);

        return new RecursoModificadoResponse<String>("Incidente atualizado com sucesso", OperacaoEnum.ATUALIZACAO, incidente.getId());
    }

    @Transactional
    public RecursoModificadoResponse<String> fecharIncidente(String idIncidente) {
        var incidente = incidenteRepository.findById(idIncidente)
                .orElseThrow(() -> new RuntimeException("Incidente não encontrado com o ID: " + idIncidente));

        incidente.setStatus(IncidenteStatusEnum.FECHADO);

        var manutencoes = manutencaoRepository.findAllByIncidenteId(idIncidente);

        for (var manutencao : manutencoes) {
            if (manutencao.getStatus() != ManutencaoStatusEnum.CONCLUIDA) {
                manutencao.setStatus(ManutencaoStatusEnum.CANCELADA);
            }
        }

        return new RecursoModificadoResponse<String>("Incidente fechado com sucesso", OperacaoEnum.ATUALIZACAO, incidente.getId());
    }
}
