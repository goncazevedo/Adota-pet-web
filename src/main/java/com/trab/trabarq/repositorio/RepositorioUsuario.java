package com.trab.trabarq.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trab.trabarq.modelos.*;

public interface RepositorioUsuario extends JpaRepository<Usuario, Long>{

    
}