package com.henrique.eventineos.controller;

import com.henrique.eventineos.model.Evento;
import com.henrique.eventineos.model.Participante;
import com.henrique.eventineos.repository.EventoRepository;
import com.henrique.eventineos.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ParticipanteRepository participanteRepository;

    @RequestMapping(value = "/cadastrarEvento", method = GET)
    public String form() {
        return "evento/formEvento";
    }

    @RequestMapping(value = "/cadastrarEvento", method = POST)
    public String form(@Valid Evento evento, BindingResult bindingResult, RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");

        } else {
            eventoRepository.save(evento);
            attributes.addFlashAttribute("mensagem", "Cadastrado com sucesso.");

        }
        return "redirect:/cadastrarEvento";
    }

    @RequestMapping("/eventos")
    public ModelAndView listagemEventos() {
        ModelAndView mav = new ModelAndView("index");
        List<Evento> eventos = (List<Evento>) eventoRepository.findAll();
        mav.addObject("eventos", eventos);
        return mav;
    }

    @RequestMapping(value = "/{codigo}", method = GET)
    public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
        Evento event = eventoRepository.findById(codigo);

        ModelAndView mav = new ModelAndView("evento/detalhesEvento");
        mav.addObject("evento", event);

        Iterable<Participante> participantes = participanteRepository.findByEvento(event);
        mav.addObject("participantes", participantes);
        return mav;
    }

    @RequestMapping(value = "/{codigo}", method = POST)
    public String detalharEvento(@PathVariable("codigo") long codigo, @Valid Participante participante,
                                 BindingResult bindingResult, RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {

            attributes.addFlashAttribute("mensagem", "Verifique os campos!\n"+
                    bindingResult.getAllErrors().toString());

        } else {

            Evento event = eventoRepository.findById(codigo);
            participante.setEvento(event);
            participanteRepository.save(participante);
            attributes.addFlashAttribute("mensagem", "Adicionado com sucesso");

        }
        return "redirect:/{codigo}";
    }

    @RequestMapping("/deletarEvento")
    public String deletarEvento(long codigo){

        Evento evento = eventoRepository.findById(codigo);
        eventoRepository.delete(evento);

        return "redirect:/eventos";
    }
    @RequestMapping("/deletarParticipante")
    public String deletarParticipante(String rg){

        Participante participante = participanteRepository.findByRg(rg);
        participanteRepository.delete(participante);

        Evento evento = participante.getEvento();

        long codigoL = evento.getId();
        String codigo = String.valueOf(codigoL);
        return "redirect:/"+codigo;
    }
}