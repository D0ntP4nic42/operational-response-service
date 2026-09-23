package br.petroedge.oprs.api.controller;

import br.petroedge.oprs.api.utils.OperacaoEnum;

public record RecursoModificadoResponse<T>(
    String mensagem,
    OperacaoEnum operacao,
    T idRecurso
) {}