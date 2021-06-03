package ar.edu.unju.edm.controller;

import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@Autowired
    Main unMain;

    @GetMapping({ "/", "/login", "/home", "/index", "/login?error=true" })
    public String cargarHome(Model model) {
        return "home";
    }
}