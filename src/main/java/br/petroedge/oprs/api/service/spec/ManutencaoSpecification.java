package br.petroedge.oprs.api.service.spec;

import java.time.Instant;
import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import br.petroedge.oprs.api.entity.Manutencao;
import br.petroedge.oprs.api.utils.ManutencaoStatusEnum;

public class ManutencaoSpecification {

    private ManutencaoSpecification() {
    }

    public static Specification<Manutencao> containsTermoBusca(String termoBusca) {
        return (root, query, cb) -> {
            if (!StringUtils.hasText(termoBusca)) {
                return cb.conjunction();
            }
            var like = "%" + termoBusca.toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(root.get("titulo")), like),
                    cb.like(cb.lower(root.get("observacao")), like));
        };
    }

    public static Specification<Manutencao> hasStatus(ManutencaoStatusEnum status) {
        return (root, query, cb) -> {
            if (status == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("status"), status);
        };
    }

    public static Specification<Manutencao> hasIncidenteId(String incidenteId) {
        return (root, query, cb) -> {
            if (!StringUtils.hasText(incidenteId)) {
                return cb.conjunction();
            }
            return cb.equal(root.get("incidente").get("id"), incidenteId);
        };
    }

    public static Specification<Manutencao> dtPrevistaBetween(LocalDateTime inicio, LocalDateTime fim) {
        return (root, query, cb) -> {
            if (inicio == null && fim == null) {
                return cb.conjunction();
            }
            if (inicio != null && fim != null) {
                return cb.between(root.get("dtPrevista"), inicio, fim);
            }
            if (inicio != null) {
                return cb.greaterThanOrEqualTo(root.get("dtPrevista"), inicio);
            }
            return cb.lessThanOrEqualTo(root.get("dtPrevista"), fim);
        };
    }

    public static Specification<Manutencao> dtCriacaoBetween(Instant inicio, Instant fim) {
        return (root, query, cb) -> {
            if (inicio == null && fim == null) {
                return cb.conjunction();
            }
            if (inicio != null && fim != null) {
                return cb.between(root.get("dtCriacao"), inicio, fim);
            }
            if (inicio != null) {
                return cb.greaterThanOrEqualTo(root.get("dtCriacao"), inicio);
            }
            return cb.lessThanOrEqualTo(root.get("dtCriacao"), fim);
        };
    }
}