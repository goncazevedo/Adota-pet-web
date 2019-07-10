package com.trab.trabarq.repositorio;

import java.util.List;

import com.trab.trabarq.modelos.Pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * RepositorioPet
 */
public interface RepositorioPet extends JpaRepository<Pet, Long>{

    @Query("SELECT t FROM Pet t WHERE t.usuario.email = :emailUsuario")
    List<Pet> carregarPetsPorUsuario(@Param("emailUsuario") String email);
}