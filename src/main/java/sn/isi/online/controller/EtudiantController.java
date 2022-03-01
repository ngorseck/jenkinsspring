package sn.isi.online.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EtudiantController {

    @RequestMapping(value = "/online/etudiants")
    public String listEtudiant() {

       return "etudiant/list";

    }
}
