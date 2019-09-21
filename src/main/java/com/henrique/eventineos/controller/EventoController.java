package com.henrique.eventineos.controller;

import com.henrique.eventineos.model.Evento;
import com.henrique.eventineos.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @RequestMapping(value = "/cadastrarEvento", method = GET)
    public String form()
    {
        return "evento/formEvento";
    }

    @RequestMapping(value = "/cadastrarEvento", method = POST)
    public String form(Evento evento)
    {
        if (evento !=  null) eventoRepository.save(evento);
        return "redirect:/cadastrarEvento";
    }

    @RequestMapping("/eventos")
    public ModelAndView listagemEventos()
    {
        ModelAndView mav = new ModelAndView("index");
        List<Evento> eventos = (List<Evento>) eventoRepository.findAll();
        mav.addObject("eventos", eventos);
        return mav;
    }

    @RequestMapping("/{codigo}")
    public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo){
        Evento event = eventoRepository.findById(codigo);
        ModelAndView mav = new ModelAndView("evento/detalhesEvento");
        mav.addObject("evento", event);
        return mav;
    }
}
