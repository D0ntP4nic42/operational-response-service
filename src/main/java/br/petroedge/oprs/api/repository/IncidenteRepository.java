package br.petroedge.oprs.api.repository;

import br.petroedge.oprs.api.entity.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenteRepository extends JpaRepository<Incidente, String>, JpaSpecificationExecutor<Incidente> {}
