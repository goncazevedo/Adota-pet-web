package com.trab.trabarq.servico;

import com.trab.trabarq.modelos.Usuario;
import com.trab.trabarq.repositorio.RepositorioUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * ServicoUsuario
 */
@Service
public class ServicoUsuario {
    @Autowired //Spring se carrega de criar e injetar dentro da classe
    private RepositorioUsuario repositorioUsuario;

    @Autowired //Todo autowired é um objeto bean q o spring sabe criar(ou foi ensinado em configuration)
    private BCryptPasswordEncoder passwordEncoder;

    public Usuario encontrarPorCPF(String email){
        return repositorioUsuario.findByCpf(email);
    }

    public Usuario encontrarPorEmail(String email){
        return repositorioUsuario.findByEmail(email);
    }

    public void salvar(Usuario usuario) {
        //Criptografar a senha com um hash criptografia unidirecional
        //impossivel de ser desfeita assim a senha do usuario n é exposta
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        repositorioUsuario.save(usuario);
}
    
}