package com.app.agenda.port.impl;

import com.app.agenda.domain.ContatoDTO;
import com.app.agenda.port.AgendaServicePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Uirá Haun
 */
@Service
public class AgendaServicePortAdapter implements AgendaServicePort {

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    @Override
    @Transactional
    public ContatoDTO addContact(ContatoDTO contatoDTO) {
        entityManager.persist(contatoDTO);

        return contatoDTO;
    }

    @Override
    @Transactional
    public boolean deleteContact(Long idContato) {
        ContatoDTO contatoDTO = findContact(idContato);

        if(contatoDTO != null) {
            entityManager.remove(contatoDTO);

            return true;
        }

        return false;
    }

    @Override
    @Transactional
    public ContatoDTO editContact(ContatoDTO contatoDTO) {

        entityManager.merge(contatoDTO);

        return contatoDTO;
    }

    @Override
    public ContatoDTO findContact(Long idContato) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<ContatoDTO> criteria = cb.createQuery(ContatoDTO.class);

        Root<ContatoDTO> root = criteria.from(ContatoDTO.class);

        criteria.where(cb.equal(root.get("id"), idContato));

        TypedQuery<ContatoDTO> q = entityManager.createQuery(criteria);

        return q.getSingleResult();
    }

    @Override
    public List<ContatoDTO> findAll() {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<ContatoDTO> criteria = cb.createQuery(ContatoDTO.class);

        Root<ContatoDTO> root = criteria.from(ContatoDTO.class);

        criteria.orderBy(cb.asc(root.get("id")));

        return entityManager.createQuery(criteria).getResultList();
    }
}
