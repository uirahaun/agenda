package com.app.agenda.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author Uirá Haun
 */
public class ContatoLista implements Serializable {

    private static final long serialVersionUID = 1L;

    private int total;
    private List<ContatoDTO> lista;

    public ContatoLista() {
    }

    public ContatoLista(int total, List<ContatoDTO> lista) {
        this.total = total;
        this.lista = lista;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ContatoDTO> getLista() {
        return lista;
    }

    public void setLista(List<ContatoDTO> lista) {
        this.lista = lista;
    }
}
