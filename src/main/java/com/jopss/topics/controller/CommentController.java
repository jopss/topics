package com.jopss.topics.controller;

import com.jopss.topics.model.Comment;
import com.jopss.topics.model.Topic;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @RequestMapping(value = "/topic/{topicId}/", method = RequestMethod.GET)
    public String show(@PathVariable Long topicId, Model model) {
        this.addAttributesRequest( Topic.findById(topicId), model);
        return "comment";
    }
    
    @RequestMapping(value = "/topic/{topicId}/save/", method = RequestMethod.POST)
    public String saveCommentTopic(@Valid Comment comment, BindingResult result, Model model, @PathVariable Long topicId) {

        comment.prepareToSaveTopic(topicId);
        
        if (!result.hasErrors()) {
            try {

                comment = comment.save();
                model.addAttribute("msgSuccess", "form.save.success");

            } catch (Exception e) {
                model.addAttribute("msgError", e.getMessage());
            }
        }
        
        this.addAttributesRequest(comment.getTopic(),model);
        return "comment";
    }
    
    @RequestMapping(value = "/reference/{referenceId}/save/", method = RequestMethod.POST)
    public String saveCommentReference(@Valid Comment comment, BindingResult result, Model model, @PathVariable Long referenceId) {

        comment.prepareToSaveReference(referenceId);
        
        if (!result.hasErrors()) {
            try {

                comment = comment.save();
                model.addAttribute("msgSuccess", "form.save.success");

            } catch (Exception e) {
                model.addAttribute("msgError", e.getMessage());
            }
        }
        
        this.addAttributesRequest(comment.getTopic(),model);
        return "comment";
    }
    
    private void addAttributesRequest(Topic topic, Model model){
        model.addAttribute("topic", topic);
        model.addAttribute("comment", new Comment());
        model.addAttribute("comments", topic.searchHierarchy());
    }
    
}
