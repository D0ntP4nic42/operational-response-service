package br.petroedge.oprs.common.service;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import br.petroedge.oprs.common.entity.Alerta;
import br.petroedge.oprs.common.repository.AlertaRepository;
import br.petroedge.oprs.common.utils.AlertaStatusEnum;

@Service
public class EmailQueueService {

    private final AlertaRepository alertaRepository;

    public EmailQueueService(AlertaRepository alertaRepository) {
        this.alertaRepository = alertaRepository;
    }

    public void enfileirar(String destinatario, String assunto, String corpo, String diagnosticoId) {
        var email = new Alerta();
        email.setDestinatario(destinatario);
        email.setAssunto(assunto);
        email.setMensagem(corpo);
        email.setStatus(AlertaStatusEnum.PENDENTE);
        email.setDtCriacao(Instant.now());
        email.setDiagnosticoId(diagnosticoId);

        alertaRepository.save(email);
    }

    public void enfileirarParaVarios(List<String> destinatarios, String assunto, String corpo, String diagnosticoId) {
        destinatarios.forEach(destinatario -> enfileirar(destinatario, assunto, corpo, diagnosticoId));
    }
}
