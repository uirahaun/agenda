package com.app.agenda.port;

import com.app.agenda.domain.ContatoDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Uirá Haun
 */
public interface AgendaServicePort {
    ContatoDTO addContact(ContatoDTO contatoDTO);

    boolean deleteContact(Long idContato);

    ContatoDTO editContact(ContatoDTO contatoDTO);

    ContatoDTO findContact(Long idContato);

    List<ContatoDTO> findAll();
}
