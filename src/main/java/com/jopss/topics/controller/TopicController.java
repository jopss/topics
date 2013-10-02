package com.jopss.topics.controller;

import com.jopss.topics.model.Topic;
import com.jopss.topics.util.Paginator;
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
    public String show(Model model) {
        return this.showPage(model, 1);
    }
    
    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public String showPage(Model model, @PathVariable Integer page) {
        
        paginator.setCurrentPage(page);
        
        model.addAttribute("topics", Topic.findAll(paginator));
        model.addAttribute("topic", new Topic());
        model.addAttribute("pageCount", paginator.getPageCount());
        return "topic";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid Topic topic, BindingResult result, Model model) {

        try {
            
            topic.save();
            model.addAttribute("msgSuccess", "form.save.success");
            return show(model);
            
        } catch (Exception e) {
            model.addAttribute("msgError", e.getMessage());
        }
        
        return this.showPage(model, 1);
    }
}
