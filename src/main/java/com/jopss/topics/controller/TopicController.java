package com.jopss.topics.controller;

import com.jopss.logico.CaptchaLogico;
import com.jopss.topics.model.Topic;
import com.jopss.topics.model.form.TopicForm;
import com.jopss.topics.util.Paginator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private Paginator paginator;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String show(Model model, HttpSession session, HttpServletRequest request) {
        return this.showPage(model, 1, session, request);
    }
    
    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public String showPage(Model model, @PathVariable Integer page, HttpSession session, HttpServletRequest request) {
        
        paginator.setCurrentPage(page);
        
        model.addAttribute("topics", Topic.findAll(paginator));
        model.addAttribute("topicForm", new TopicForm());
        model.addAttribute("pageCount", paginator.getPageCount());
        
        CaptchaLogico.carregar(session, request).criarNovaPerguntaRespostas();
        return "topic";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid TopicForm topicForm, BindingResult result, Model model, HttpSession session, HttpServletRequest request) {

        try {
            CaptchaLogico.carregar(session, request).validarRespostaUsuario( topicForm.getResponseUser() );
            
            topicForm.getTopic().save();
            model.addAttribute("msgSuccess", "form.save.success");
            return show(model, session, request);
            
        } catch (Exception e) {
            model.addAttribute("msgError", e.getMessage());
        }
        
        return this.showPage(model, 1, session, request);
    }
}
