package com.trab.trabarq.repositorio;

import com.trab.trabarq.modelos.Pet;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RepositorioPet
 */
public interface RepositorioPet extends JpaRepository<Pet, Long>{

    
}