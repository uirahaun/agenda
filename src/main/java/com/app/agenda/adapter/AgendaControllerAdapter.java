package com.app.agenda.adapter;

import com.app.agenda.domain.ContatoDTO;
import com.app.agenda.domain.ContatoLista;
import com.app.agenda.exception.ContatoNaoEncontradoException;
import com.app.agenda.port.impl.AgendaServicePortAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AgendaControllerAdapter {

    @Autowired
    private AgendaServicePortAdapter agendaServicePortAdapter;

    @RequestMapping(value = "/contato", method = RequestMethod.POST)
    public ResponseEntity<ContatoDTO> adicionarContato(@Valid @RequestBody ContatoDTO contatoDTO) {
        agendaServicePortAdapter.addContact(contatoDTO);

        return new ResponseEntity<ContatoDTO>(contatoDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/contato", method = RequestMethod.GET)
    public ResponseEntity<ContatoLista> findAll() {

        List<ContatoDTO> lista = agendaServicePortAdapter.findAll();

        ContatoLista retorno = new ContatoLista(lista.size(), lista);

        return new ResponseEntity<ContatoLista>(retorno, HttpStatus.OK);
    }

    @RequestMapping(value = "/contato/{id}", method = RequestMethod.GET)
    public ResponseEntity<ContatoDTO> findById(@PathVariable(value = "id") long id) throws ContatoNaoEncontradoException {
        ContatoDTO contatoExiste = null;

        try {
            contatoExiste = agendaServicePortAdapter.findContact(id);
        }catch (Exception e) {
            throw new ContatoNaoEncontradoException("Contato não encontrado com id: "+id);
        }

        return new ResponseEntity<ContatoDTO>(contatoExiste, HttpStatus.OK);
    }

    @RequestMapping(value = "/contato/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ContatoDTO> alterarContato(@PathVariable(value = "id") long id,
                                                  @Valid @RequestBody ContatoDTO contato) throws ContatoNaoEncontradoException {
        try {
            final ContatoDTO contatoExiste = agendaServicePortAdapter.findContact(id);
        }catch (Exception e) {
            throw new ContatoNaoEncontradoException("Contato não encontrado com id: "+id);
        }

        contato.setId(id);

        final ContatoDTO contatoDTO = agendaServicePortAdapter.editContact(contato);

        return new ResponseEntity<ContatoDTO>(contatoDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/contato/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> removerContato(@PathVariable(value = "id") long id) throws ContatoNaoEncontradoException {
        try {
            final ContatoDTO contatoExiste = agendaServicePortAdapter.findContact(id);

            if(agendaServicePortAdapter.deleteContact(id)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }catch (Exception e) {
            throw new ContatoNaoEncontradoException("Contato não encontrado com id: "+id);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
