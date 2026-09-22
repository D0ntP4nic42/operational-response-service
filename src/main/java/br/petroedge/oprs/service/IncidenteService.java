package br.petroedge.oprs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.petroedge.oprs.controller.incidente.IncidenteDto;
import br.petroedge.oprs.entity.Incidente;
import br.petroedge.oprs.repository.IncidenteRepository;
import br.petroedge.oprs.service.spec.IncidenteSpecification;

@Service 
public class IncidenteService {
    private final IncidenteRepository incidenteRepository;

    public IncidenteService(IncidenteRepository incidenteRepository) {
        this.incidenteRepository = incidenteRepository;
    }

    public Page<Incidente> buscarIncidentes(Pageable pageable, IncidenteDto.BuscaIncidenteRequest request) {
        var specification = Specification.allOf(
            IncidenteSpecification.containsTermoBusca(request.termoBusca()),
            IncidenteSpecification.hasStatus(request.status()),
            IncidenteSpecification.hasImportancia(request.importancia()),
            IncidenteSpecification.creationBetween(request.dtCriacaoInicio(), request.dtCriacaoFim()),
            IncidenteSpecification.updateBetween(request.dtAtualizacaoInicio(), request.dtAtualizacaoFim())
        );

        return incidenteRepository.findAll(specification, pageable);
    }
}
