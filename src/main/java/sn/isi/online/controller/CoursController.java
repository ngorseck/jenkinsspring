package sn.isi.online.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CoursController {


    @RequestMapping(value = "/online/cours")
    public String listCours() {

        return "cours/list";

    }

}
