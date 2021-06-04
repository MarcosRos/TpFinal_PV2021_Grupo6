<<<<<<< HEAD
package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ar.edu.unju.edm.model.Main;

@Controller
public class MainController {

    @Autowired
    Main unMain;

    @GetMapping({ "/", "/login", "/home", "/index", "/login?error=true" })
    public String cargarHome(Model model) {
        return "home";
    }
}
=======

package ar.edu.unju.edm.controller;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ar.edu.unju.edm.model.Main;

@Controller
public class MainController {
	@Autowired
    Main unMain;
	
	@GetMapping({ "/", "/login", "/home", "/index", "/login?error=true" })
	public String cargarHome(Model model) {
		return "home";
	}
}

}

>>>>>>> branch 'master' of https://github.com/MarcosRos/TpFinal_PV2021_Grupo6.git
