package br.petroedge.oprs.common.repository;

import br.petroedge.oprs.common.entity.Alerta;
import br.petroedge.oprs.common.utils.AlertaStatusEnum;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, String> {
    List<Alerta> findAllByStatusOrderByDtCriacaoAsc(AlertaStatusEnum status);
}
