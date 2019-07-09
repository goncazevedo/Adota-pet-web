package com.trab.trabarq.controllers;

import javax.validation.Valid;

import com.trab.trabarq.modelos.Pet;
import com.trab.trabarq.repositorio.RepositorioPet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/pets")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pet/pets");
        mv.addObject("pets", repositoriopet.findAll());
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
    public ModelAndView inserir(@Valid Pet pet, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if(result.hasErrors()){
            mv.setViewName("pet/cadastro");
            mv.addObject(pet);
        }else{
            mv.setViewName("redirect:/pet/pets");
            repositoriopet.save(pet);
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
    public ModelAndView editar(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("pet/editar");
        Pet pet = repositoriopet.getOne(id);
        mv.addObject("pet",pet);
        return mv;
    }


    @PostMapping("/editar")
    public ModelAndView editar(@Valid Pet pet, BindingResult result) {
        ModelAndView mv = new ModelAndView();
        if(result.hasErrors()){
            mv.setViewName("pet/editar");
            mv.addObject(pet);
        }else{
            mv.setViewName("redirect:/pet/pets");
            repositoriopet.save(pet);
        }
        return mv;
}
}