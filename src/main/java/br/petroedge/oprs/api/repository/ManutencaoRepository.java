package br.petroedge.oprs.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.petroedge.oprs.api.entity.Manutencao;

@Repository
public interface ManutencaoRepository extends JpaRepository<Manutencao, String>, JpaSpecificationExecutor<Manutencao> {
    List<Manutencao> findAllByIncidenteId(String incidenteId);
}
