package com.trab.trabarq.controllers;

import javax.validation.Valid;

import com.trab.trabarq.modelos.Uf;
import com.trab.trabarq.modelos.Usuario;
import com.trab.trabarq.repositorio.RepositorioUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * UsuarioController
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @GetMapping("/usuarios")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("usuario/usuarios");
        mv.addObject("usuarios", repositorioUsuario.findAll());
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView insere() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("usuario/cadastro");
        mv.addObject("usuario", new Usuario());
        return mv;
    }


    @PostMapping("/cadastro")
    public ModelAndView inserir(@Valid Usuario usuario, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if(result.hasErrors()){
            mv.setViewName("usuario/cadastro");
            mv.addObject(usuario);
        }else{
            mv.setViewName("redirect:/usuario/usuarios");
            repositorioUsuario.save(usuario);
        }
        return mv;
    }

    @GetMapping("perfil/{id}")
    public ModelAndView show(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("usuario/perfil");
        Usuario usuario = repositorioUsuario.getOne(id);
        mv.addObject("usuario", usuario);
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("usuario/editar");
        Usuario usuario = repositorioUsuario.getOne(id);
        mv.addObject("usuario",usuario);
        return mv;
    }


    @PostMapping("/editar")
    public ModelAndView editar(@Valid Usuario usuario, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if(result.hasErrors()){
            mv.setViewName("usuario/editar");
            mv.addObject(usuario);
        }else{
            mv.setViewName("redirect:/usuario/usuarios");
            repositorioUsuario.save(usuario);
        }
        return mv;
    }

    @ModelAttribute("ufs")
    public Uf[] getUf(){
        return Uf.values();
    }
}