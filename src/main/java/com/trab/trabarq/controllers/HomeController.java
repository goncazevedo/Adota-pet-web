package com.trab.trabarq.controllers;

import javax.servlet.http.HttpServletRequest;

import com.trab.trabarq.modelos.Usuario;
import com.trab.trabarq.repositorio.RepositorioPet;
import com.trab.trabarq.servico.ServicoUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * HomeController
 */

@Controller
public class HomeController {

    @Autowired
    private RepositorioPet repositoriopet;

    @Autowired
    private ServicoUsuario servicoUsuario;

    @GetMapping("/")
    public ModelAndView home(HttpServletRequest request) {
        String emailUsuario = request.getUserPrincipal().getName(); //pega o username do usuario
        Usuario usuarioLogado = servicoUsuario.encontrarPorEmail(emailUsuario);
        ModelAndView mv = new  ModelAndView();
        mv.addObject("usuarioLogado", usuarioLogado);
        mv.setViewName("/home");
        return mv;
    }
}