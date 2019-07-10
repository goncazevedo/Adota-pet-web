package com.trab.trabarq.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.trab.trabarq.modelos.Uf;
import com.trab.trabarq.modelos.Usuario;
import com.trab.trabarq.repositorio.RepositorioUsuario;
import com.trab.trabarq.servico.ServicoUsuario;

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
    
    
    @ModelAttribute("ufs")
    public Uf[] getUf(){
        return Uf.values();
    }

    @Autowired
    private ServicoUsuario ex;

    @GetMapping("/usuarios")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("usuario/usuarios");
        mv.addObject("usuarios", repositorioUsuario.findAll());
        return mv;
    }
/* 
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
    } */


    @GetMapping("/meuperfil")
    public ModelAndView show(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("usuario/perfil");
        String emailUsuario = request.getUserPrincipal().getName(); //pega o username do usuario
        Usuario usuarioLogado = ex.encontrarPorEmail(emailUsuario);
        mv.addObject("usuario", usuarioLogado);
        mv.addObject("usuarioLogado", usuarioLogado);
        return mv;
    }


    @GetMapping("/perfil/{id}")
    public ModelAndView show(@PathVariable("id") Long id, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("usuario/perfil");
        Usuario usuario = repositorioUsuario.getOne(id);
        mv.addObject("usuario", usuario);
        String emailUsuario = request.getUserPrincipal().getName(); //pega o username do usuario
        Usuario usuarioLogado = ex.encontrarPorEmail(emailUsuario);
        mv.addObject("usuarioLogado", usuarioLogado);
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

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, HttpServletRequest request) {
        String emailUsuario = request.getUserPrincipal().getName(); //pega o username do usuario
        Usuario usuarioLogado = ex.encontrarPorEmail(emailUsuario);
        if( repositorioUsuario.getOne(id) == usuarioLogado) repositorioUsuario.deleteById(id);
        return "redirect:/logout";
    }

    @GetMapping("/login")
    public String login() {
        return "usuario/login";
    }

    @GetMapping("/registration")
    public ModelAndView registrar(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("usuario/cadastro");
        mv.addObject("usuario", new Usuario());
        return mv;
    }

    @PostMapping("/registration")
    public ModelAndView registrar(@Valid Usuario usuario, BindingResult result){
        ModelAndView mv = new ModelAndView();
        Usuario usr = ex.encontrarPorEmail(usuario.getEmail());
        if(usr!=null){
            result.rejectValue("email", "", "Email já cadastrado");
        }
        usr = ex.encontrarPorCPF(usuario.getCpf());
        if(usr!=null){
            result.rejectValue("cpf", "", "cpf já cadastrado");
        }
        if(result.hasErrors()){
            mv.setViewName("usuario/cadastro");
            mv.addObject("usuarios", usuario);
        }
        else{
            ex.salvar(usuario);
            mv.setViewName("redirect:/usuario/login");
        }
        return mv;
    }
}