package br.dev.diego.medic.api.domain.repositories;

import br.dev.diego.medic.api.domain.entities.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select p.ativo from Paciente p 
            where p.id = :idPaciente
            """)
    Boolean findAtivoById(Long idPaciente);
}
