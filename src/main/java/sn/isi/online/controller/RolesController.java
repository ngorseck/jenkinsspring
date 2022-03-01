package sn.isi.online.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RolesController {

    @RequestMapping(value = "/online/roles")
    public String list() {
        return "";
    }
}
