package br.petroedge.oprs.api.controller;

import java.util.List;

public record PaginacaoResponse<T>(List<T> content, int page, int size, long totalElements, int totalPages) {}
