package br.petroedge.oprs.api.service.spec;

import br.petroedge.oprs.api.entity.Incidente;
import br.petroedge.oprs.api.utils.IncidenteImportanciaEnum;
import br.petroedge.oprs.api.utils.IncidenteStatusEnum;
import java.time.LocalDateTime;
import org.springframework.data.jpa.domain.Specification;

public class IncidenteSpecification {
    public static Specification<Incidente> containsTermoBusca(String termoBusca) {
        return (root, query, builder) -> {
            if (termoBusca == null || termoBusca.isEmpty()) {
                return builder.conjunction();
            }
            String likeTermoBusca = "%" + termoBusca.toLowerCase() + "%";
            return builder.or(
                    builder.like(builder.lower(root.get("descricao")), likeTermoBusca),
                    builder.like(builder.lower(root.get("titulo")), likeTermoBusca));
        };
    }

    public static Specification<Incidente> hasStatus(IncidenteStatusEnum status) {
        return (root, query, builder) -> {
            if (status == null) {
                return builder.conjunction();
            }
            return builder.equal(root.get("status"), status);
        };
    }

    public static Specification<Incidente> hasImportancia(IncidenteImportanciaEnum importancia) {
        return (root, query, builder) -> {
            if (importancia == null) {
                return builder.conjunction();
            }
            return builder.equal(root.get("importancia"), importancia);
        };
    }

    public static Specification<Incidente> creationBetween(LocalDateTime inicio, LocalDateTime fim) {
        return (root, query, builder) -> {
            if (inicio == null || fim == null) {
                return builder.conjunction();
            }
            return builder.between(root.get("dtCriacao"), inicio, fim);
        };
    }

    public static Specification<Incidente> updateBetween(LocalDateTime inicio, LocalDateTime fim) {
        return (root, query, builder) -> {
            if (inicio == null || fim == null) {
                return builder.conjunction();
            }
            return builder.between(root.get("dtAtualizacao"), inicio, fim);
        };
    }
}
