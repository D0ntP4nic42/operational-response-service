package br.petroedge.oprs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.petroedge.oprs.api.entity.Diagnostico;

@Repository
public interface DiagnosticoRepository extends JpaRepository<Diagnostico, String>, JpaSpecificationExecutor<Diagnostico> {

}
