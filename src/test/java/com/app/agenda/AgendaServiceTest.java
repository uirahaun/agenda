package com.app.agenda;

import com.app.agenda.domain.ContatoDTO;
import com.app.agenda.port.AgendaServicePort;
import com.app.agenda.port.impl.AgendaServicePortAdapter;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:test.properties")
public class AgendaServiceTest {

    @Autowired
    ContatoRepository contatoRepository;

    @Test
    public void whenFindByNome_thenReturnContato() {
        final ContatoDTO contato = new ContatoDTO();
        contato.setNome("Thiago Cruz");

        ContatoDTO contatoEncontrado = contatoRepository.findByNome("Thiago Cruz");

        assertThat(contatoEncontrado.getNome())
                .isEqualTo(contato.getNome());
    }
}
