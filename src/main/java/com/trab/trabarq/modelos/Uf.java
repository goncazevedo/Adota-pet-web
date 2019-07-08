package com.trab.trabarq.modelos;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Estados
 */
@Entity
@Table(name = "Uf")
public class Uf {

    @Id //identifica a chave principal
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uf_id")
    private long id;

    @Column(name = "uf_uf", length = 60, nullable = false)
    private String uf;

    @OneToMany(mappedBy = "us_usuario")
    private List<Usuario> usuarios;
}