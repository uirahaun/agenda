package com.app.agenda;

import com.app.agenda.domain.ContatoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Uirá Haun
 */
@Repository
public interface ContatoRepository extends JpaRepository<ContatoDTO, Long> {
 
    public ContatoDTO findByNome(String nome);
 
}