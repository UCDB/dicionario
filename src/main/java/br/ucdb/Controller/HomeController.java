package br.ucdb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String carregarIndex(){
        return "index";
    }
    @RequestMapping("/login")
    public String carregarLogin(){
        return "login";
    }

}
