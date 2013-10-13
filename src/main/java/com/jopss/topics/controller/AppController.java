package com.jopss.topics.controller;

import com.jopss.topics.exception.TopicsException;
import com.jopss.topics.util.ConnectionFactory;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controlador para acessos a URL genericas do sistema.
 */
@Controller
public class AppController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpSession session) throws TopicsException {
        ConnectionFactory.create();
        return "../../login";
    }
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(HttpSession session) {
        return "home";
    }
    
}
