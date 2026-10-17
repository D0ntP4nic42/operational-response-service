package br.petroedge.oprs.worker;

import br.petroedge.oprs.common.entity.Alerta;
import br.petroedge.oprs.common.repository.AlertaRepository;
import br.petroedge.oprs.common.utils.AlertaStatusEnum;
import jakarta.transaction.Transactional;
import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailWorker {

    private static final int MAX_TENTATIVAS = 3;

    private final AlertaRepository emailPendenteRepository;
    private final JavaMailSender mailSender;

    public EmailWorker(AlertaRepository emailPendenteRepository, JavaMailSender mailSender) {
        this.emailPendenteRepository = emailPendenteRepository;
        this.mailSender = mailSender;
    }

    @Scheduled(fixedDelayString = "${worker.email.intervalo-ms:30000}")
    @Transactional
    public void processarFila() {
        var pendentes = emailPendenteRepository.findAllByStatusOrderByDtCriacaoAsc(AlertaStatusEnum.PENDENTE);

        for (var email : pendentes) {
            enviar(email);
        }
    }

    private void enviar(Alerta email) {
        try {
            var message = new SimpleMailMessage();
            message.setFrom("oprs-nao-responda@gmail.com");
            message.setTo(email.getDestinatario());
            message.setSubject(email.getAssunto());
            message.setText(email.getMensagem());

            mailSender.send(message);

            email.setStatus(AlertaStatusEnum.ENVIADO);
            email.setDtEnvio(Instant.now());
            email.setDescricaoErro(null);

        } catch (Exception e) {
            log.error("Falha ao enviar e-mail para {}", email.getDestinatario(), e);

            email.setNumeroDeTentativas(email.getNumeroDeTentativas() + 1);
            email.setDescricaoErro(e.getMessage());

            if (email.getNumeroDeTentativas() >= MAX_TENTATIVAS) {
                email.setStatus(AlertaStatusEnum.ERRO);
            }
        }

        emailPendenteRepository.save(email);
    }
}
