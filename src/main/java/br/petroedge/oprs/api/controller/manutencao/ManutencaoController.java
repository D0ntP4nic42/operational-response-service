package br.petroedge.oprs.api.controller.manutencao;

import br.petroedge.oprs.api.controller.ApiResponse;
import br.petroedge.oprs.api.controller.PaginacaoResponse;
import br.petroedge.oprs.api.controller.RecursoModificadoResponse;
import br.petroedge.oprs.api.controller.manutencao.ManutencaoDTO.AdicionarManutencaoRequest;
import br.petroedge.oprs.api.controller.manutencao.ManutencaoDTO.BuscaManutencaoDetalhadoResponse;
import br.petroedge.oprs.api.controller.manutencao.ManutencaoDTO.BuscaManutencaoRequest;
import br.petroedge.oprs.api.controller.manutencao.ManutencaoDTO.BuscaManutencaoResponse;
import br.petroedge.oprs.api.controller.manutencao.ManutencaoDTO.EditarManutencaoRequest;
import br.petroedge.oprs.api.service.ManutencaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
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

@RestController
@RequestMapping("/v1/manutencoes")
@Tag(name = "Manutenções", description = "Endpoints relacionados a manutenções")
public class ManutencaoController {

    private final ManutencaoService manutencaoService;

    public ManutencaoController(ManutencaoService manutencaoService) {
        this.manutencaoService = manutencaoService;
    }

    @GetMapping
    @Operation(
            summary = "Buscar manutenções",
            description = "Endpoint para buscar manutenções com base em critérios de pesquisa.")
    public ResponseEntity<ApiResponse<PaginacaoResponse<BuscaManutencaoResponse>>> buscarManutencoes(
            @ParameterObject BuscaManutencaoRequest filtros,
            @ParameterObject @PageableDefault(size = 10, sort = "dtCriacao", direction = Sort.Direction.DESC, page = 0)
                    Pageable pageable) {

        var manutencoes = manutencaoService.buscarManutencoes(pageable, filtros);
        var response = new PaginacaoResponse<>(
                manutencoes.getContent(),
                manutencoes.getNumber(),
                manutencoes.getSize(),
                manutencoes.getTotalElements(),
                manutencoes.getTotalPages());
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/{idManutencao}")
    @Operation(
            summary = "Buscar manutenção por ID",
            description = "Endpoint para buscar uma manutenção específica pelo seu ID.")
    public ResponseEntity<ApiResponse<BuscaManutencaoDetalhadoResponse>> buscarManutencaoPorId(String idManutencao) {
        var manutencao = manutencaoService.buscarManutencaoPorId(idManutencao);
        return ResponseEntity.ok(ApiResponse.success(manutencao));
    }

    @PostMapping
    @Operation(
            summary = "Adicionar manutenção",
            description = "Endpoint para adicionar uma manutenção com base em um incidente.")
    public ResponseEntity<ApiResponse<RecursoModificadoResponse<String>>> adicionarManutencao(
            @RequestBody AdicionarManutencaoRequest request) {
        return ResponseEntity.ok(ApiResponse.success(manutencaoService.adicionarManutencao(request)));
    }

    @PutMapping("/{idManutencao}")
    @Operation(summary = "Atualizar manutenção", description = "Endpoint para atualizar uma manutenção existente.")
    public ResponseEntity<ApiResponse<RecursoModificadoResponse<String>>> atualizarManutencao(
            String idManutencao, @RequestBody EditarManutencaoRequest request) {
        return ResponseEntity.ok(ApiResponse.success(manutencaoService.atualizarManutencao(idManutencao, request)));
    }

    @PatchMapping("/{idManutencao}/concluir")
    @Operation(summary = "Concluir manutenção", description = "Endpoint para marcar uma manutenção como concluída.")
    public ResponseEntity<ApiResponse<RecursoModificadoResponse<String>>> concluirManutencao(String idManutencao) {
        return ResponseEntity.ok(ApiResponse.success(manutencaoService.concluirManutencao(idManutencao)));
    }

    @DeleteMapping("/{idManutencao}")
    @Operation(summary = "Cancelar manutenção", description = "Endpoint para cancelar uma manutenção existente.")
    public ResponseEntity<ApiResponse<RecursoModificadoResponse<String>>> cancelarManutencao(String idManutencao) {
        return ResponseEntity.ok(ApiResponse.success(manutencaoService.cancelarManutencao(idManutencao)));
    }
}
