package br.petroedge.oprs.api.controller.incidente;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.petroedge.oprs.api.controller.ApiResponse;
import br.petroedge.oprs.api.controller.PaginacaoResponse;
import br.petroedge.oprs.api.controller.RecursoModificadoResponse;
import br.petroedge.oprs.api.controller.incidente.IncidenteDto.AdicionarIncidenteRequest;
import br.petroedge.oprs.api.controller.incidente.IncidenteDto.BuscaIncidenteDetalhadoResponse;
import br.petroedge.oprs.api.controller.incidente.IncidenteDto.BuscaIncidenteRequest;
import br.petroedge.oprs.api.controller.incidente.IncidenteDto.BuscaIncidenteResponse;
import br.petroedge.oprs.api.controller.incidente.IncidenteDto.EditarIncidenteRequest;
import br.petroedge.oprs.api.service.IncidenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/incidentes")
@Tag(name = "Incidentes", description = "Endpoints relacionados a incidentes")
public class IncidenteController {
    private final IncidenteService incidenteService;

    public IncidenteController(IncidenteService incidenteService) {
        this.incidenteService = incidenteService;
    }

    @GetMapping
    @Operation(summary = "Buscar incidentes", description = "Endpoint para buscar incidentes com base em critérios de pesquisa.")
    public ResponseEntity<ApiResponse<PaginacaoResponse<BuscaIncidenteResponse>>> buscarIncidentes(@ParameterObject BuscaIncidenteRequest filtros,
            @ParameterObject @PageableDefault(size = 10, sort = "dtCriacao", direction = Sort.Direction.DESC, page = 0) Pageable pageable) {
            
        var incidentes = incidenteService.buscarIncidentes(pageable, filtros);
        var response = new PaginacaoResponse<>(incidentes.getContent(), incidentes.getNumber(), incidentes.getSize(), incidentes.getTotalElements(), incidentes.getTotalPages());
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/{idIncidente}")
    @Operation(summary = "Buscar incidente por ID", description = "Endpoint para buscar um incidente específico pelo seu ID.")
    public ResponseEntity<ApiResponse<BuscaIncidenteDetalhadoResponse>> buscarIncidentePorId(String idIncidente) {
        var incidente = incidenteService.buscarIncidentePorId(idIncidente);
        return ResponseEntity.ok(ApiResponse.success(incidente));
    }

    @PostMapping
    @Operation(summary = "Adicionar incidentes", description = "Endpoint para adicionar incidentes com base em um diagnóstico.")
    public ResponseEntity<ApiResponse<RecursoModificadoResponse<String>>> adicionarIncidentes(@RequestBody AdicionarIncidenteRequest request) {
        return ResponseEntity.ok(ApiResponse.success(incidenteService.adicionarIncidentes(request)));
    }

    @PutMapping("/{idIncidente}")
    @Operation(summary = "Atualizar incidente", description = "Endpoint para atualizar um incidente existente.")
    public ResponseEntity<ApiResponse<RecursoModificadoResponse<String>>> atualizarIncidente(String idIncidente, @RequestBody EditarIncidenteRequest request) {
        return ResponseEntity.ok(ApiResponse.success(incidenteService.atualizarIncidente(idIncidente, request)));
    }

    @DeleteMapping("/{idIncidente}")
    @Operation(summary = "Fechar o incidente", description = "Endpoint para fechar um incidente existente.")
    public ResponseEntity<ApiResponse<RecursoModificadoResponse<String>>> excluirIncidente(String idIncidente) {
        return ResponseEntity.ok(ApiResponse.success(incidenteService.fecharIncidente(idIncidente)));
    }
}
