package br.dev.diego.medic.api.repositories;

import br.dev.diego.medic.api.entities.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllMedicoByAtivo(Pageable pageable, Boolean status);

}
