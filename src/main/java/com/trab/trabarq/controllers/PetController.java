package com.trab.trabarq.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.trab.trabarq.modelos.Pet;
import com.trab.trabarq.modelos.Uf;
import com.trab.trabarq.modelos.Usuario;
import com.trab.trabarq.repositorio.RepositorioPet;
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
 * petController
 */
@Controller
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private RepositorioPet repositoriopet;

    @Autowired
    private ServicoUsuario servicoUsuario;

    @ModelAttribute("ufs")
    public Uf[] getUf(){
        return Uf.values();
    }

    @GetMapping("/pets")
    public ModelAndView list(HttpServletRequest request) {
        String emailUsuario = request.getUserPrincipal().getName(); //pega o username do usuario
        Usuario usuarioLogado = servicoUsuario.encontrarPorEmail(emailUsuario);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pet/pets");
        mv.addObject("pets", repositoriopet.findAll());
        mv.addObject("usuarioLogado", usuarioLogado);
        return mv;
    }

    @GetMapping("/cadastro")
    public ModelAndView insere() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pet/cadastro");
        mv.addObject("pet", new Pet());
        return mv;
    }


    @PostMapping("/cadastro")
    public ModelAndView inserir(@Valid Pet pet, BindingResult result, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        if(result.hasErrors()){
            mv.setViewName("pet/cadastro");
            mv.addObject(pet);
        }else{
            String emailUsuario = request.getUserPrincipal().getName(); //pega o username do usuario
            Usuario usuarioLogado = servicoUsuario.encontrarPorEmail(emailUsuario);
            pet.setDono(usuarioLogado);
            repositoriopet.save(pet);
            mv.setViewName("redirect:/pet/pets");
        }
        return mv;
    }

    @GetMapping("perfil/{id}")
    public ModelAndView show(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pet/perfil");
        Pet pet = repositoriopet.getOne(id);
        mv.addObject("pet", pet);
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pet/editar");
        Pet pet = repositoriopet.getOne(id);
        String emailUsuario = request.getUserPrincipal().getName(); //pega o username do usuario
        Usuario usuarioLogado = servicoUsuario.encontrarPorEmail(emailUsuario);
        if (pet.getDono() != usuarioLogado ) mv.setViewName("redirect:/usuario/meuperfil");
        mv.addObject("pet",pet);
        return mv;
    }


    @PostMapping("/editar")
    public ModelAndView editar(@Valid Pet pet, BindingResult result, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String emailUsuario = request.getUserPrincipal().getName(); //pega o username do usuario
        Usuario usuarioLogado = servicoUsuario.encontrarPorEmail(emailUsuario);
        pet.setDono(usuarioLogado);
        if(result.hasErrors()){
            mv.setViewName("pet/editar");
            mv.addObject(pet);
        }else{
            mv.setViewName("redirect:/pet/pets");
            repositoriopet.save(pet);
        }
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, HttpServletRequest request) {
        String emailUsuario = request.getUserPrincipal().getName(); //pega o username do usuario
        Usuario usuarioLogado = servicoUsuario.encontrarPorEmail(emailUsuario);
        if( repositoriopet.getOne(id).getDono() == usuarioLogado) repositoriopet.deleteById(id);
        return "redirect:/usuario/meuperfil";
    }

}