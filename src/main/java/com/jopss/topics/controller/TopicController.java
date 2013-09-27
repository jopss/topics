package com.jopss.topics.controller;

import com.jopss.topics.model.Topic;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/topic")
public class TopicController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String show(Model model) {
        model.addAttribute("topics", Topic.findAll());
        model.addAttribute("topic", new Topic());
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
        
        model.addAttribute("topics", Topic.findAll());
        return "topic";
    }
}
