package com.trab.trabarq.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trab.trabarq.modelos.*;

public interface RepositorioUsuario extends JpaRepository<Usuario, Long>{
    Usuario findByEmail(String Email);
    Usuario findByCpf(String cpf);  
    //Classe finfByAtributo(TipoDoAtributo Atributo)
    //Basta seguir esse formato para buscar por qlq atributo
    //Spring ja gera o método automaticamente

    
}