package com.trab.trabarq.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import com.trab.trabarq.modelos.Usuario;
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
    private ServicoUsuario servicoUsuario;

    @GetMapping("/")
    public ModelAndView home(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal(); //pega o username do usuario
        ModelAndView mv = new  ModelAndView();
        if(principal == null){
            mv.addObject("usuarioLogado", null);    
        }
        else{
            Usuario usuarioLogado = servicoUsuario.encontrarPorEmail(principal.getName());
            mv.addObject("usuarioLogado", usuarioLogado);
        }
        mv.setViewName("/home");
        return mv;
    }
}