package it.progettosiw.siwcalcio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReactController {

    @GetMapping({
            "/react/tornei",
            "/react/squadre"
    })
    public String reactApp() {
        return "forward:/react/index.html";
    }
}