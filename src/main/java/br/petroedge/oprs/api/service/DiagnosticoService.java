package br.petroedge.oprs.api.service;

import br.petroedge.oprs.api.entity.Diagnostico;
import br.petroedge.oprs.api.repository.DiagnosticoRepository;
import org.springframework.stereotype.Service;

@Service
public class DiagnosticoService {
    private final DiagnosticoRepository diagnosticoRepository;

    public DiagnosticoService(DiagnosticoRepository diagnosticoRepository) {
        this.diagnosticoRepository = diagnosticoRepository;
    }

    public String salvarDiagnostico(Diagnostico diagnostico) {
        return diagnosticoRepository.save(diagnostico).getId();
    }
}
