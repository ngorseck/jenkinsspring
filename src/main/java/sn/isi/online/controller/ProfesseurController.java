package sn.isi.online.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sn.isi.online.dao.IProfesseur;
import sn.isi.online.entities.Professeur;
import sn.isi.online.utils.EmailService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ProfesseurController {

    @Autowired
    private IProfesseur professeurdao;

    @Autowired
    private EmailService emailService;

    /**
     * CRUD
     */
    @RequestMapping(value = "/online/professeurs")
    public String listProf(ModelMap map) {

        map.addAttribute("professeursList", professeurdao.findAll());//Pour la liste
        map.addAttribute("professeur", new Professeur());//Pour le formulaire

        return "prof/list";
    }

    @RequestMapping(value = "/online/professeurs/edit", method = RequestMethod.GET)
    public String editProf(ModelMap map, String email) {

        map.addAttribute("professeursList", professeurdao.findAll());//Pour la liste
        map.addAttribute("professeur", professeurdao.getProfesseurByEmail(email));//Pour le formulaire
        return "prof/list";
    }
    @RequestMapping(value = "/online/professeurs/delete", method = RequestMethod.GET)
    public String deleteProf(String email) {

        professeurdao.delete(professeurdao.getProfesseurByEmail(email));
        return "redirect:/online/professeurs";
    }

    @RequestMapping(value = "/online/professeurs/add", method = RequestMethod.POST)
    public String addProf(Professeur professeur) {
        //Crypt du mot de passe
        BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
        String hashedPwd = pwdEncoder.encode(professeur.getPassword());
        professeur.setPassword(hashedPwd);

        professeurdao.save(professeur);
        /**
         * Emailing
         */
        String sujet = "Validation inscription";
        String text = "Votre insciption a été validée, pour retourner vers votre interface " +
                "merci de cliquer sur ce lien http://localhost:8080/online/professeurs";
        emailService.sendSimpleMessage(professeur.getEmail(), sujet, text);
        return "redirect:/online/professeurs";
    }

    /**
     * Spring security
     */
    @RequestMapping(value = "/online/login")
    public String login() {

        return "login";
    }

    @RequestMapping(value = "")
    public String home() {

        return "redirect:/online/logon";
    }

    @RequestMapping(value = "/")
    public String index() {

        return "redirect:/online/logon";
    }

    @RequestMapping(value = "/online/logon")
    public String logon(HttpServletRequest req, HttpServletResponse resp) {

        /*String email = req.getRemoteUser();
        Professeur professeur = professeurdao.getProfesseurByEmail(email);
        System.out.println(professeur.getPrenom() + "  " + professeur.getNom());*/

        return "redirect:/online/professeurs";
    }

    @RequestMapping(value="/online/logout", method = RequestMethod.GET)
    public String logout (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/online/login?logout";
    }

    @RequestMapping(value="/online/403")
    public String accessDenied(){
        return "403";
    }









    /*
    @Autowired
    private IProfesseur professeurdao;
    /**
     * Spring security
     * "" ou / correspond a la connexion a l'application cest a dire home
      */
    /*@RequestMapping(value="/")
    public String home(){
        return "redirect:/online/logon";
    }

    @RequestMapping(value="")
    public String homes(){
        return "redirect:/online/logon";
    }

    @RequestMapping(value="/online/logon")
	public String index(ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		//Recuperation du login de l'utilisateur connecte
		String user_email = request.getRemoteUser();

        System.out.println(user_email);
		return "redirect:/online/professeurs";
	}

    @RequestMapping(value="/online/login")
    public String login(){

        return "prof/login";
    }
    @RequestMapping(value="/online/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //if (auth != null){
            //new SecurityContextLogoutHandler().logout(request, response, auth);
        //}
        return "redirect:/online/login?logout";
    }
    @RequestMapping(value="/online/403")
    public String accessDenied(){
        return "prof/403";
    }

    /**
     * Securite simple
     * Pour le fichier login1.html
    @RequestMapping(value = "/", name = "login")
    public String login() {

        return "prof/login1";
    }

    //Ceci marche bien
    @RequestMapping(value = "/logon", name = "logon", method= RequestMethod.POST)
    public String login(ModelMap map, String username, String password) {
        System.out.println(username + "     " + password);
        Professeur professeur = professeurdao.chercher(username, password);
        //System.out.println(professeur.getPrenom() + "  " + professeur.getNom());

        if(professeur != null) {
            return "redirect:/online/professeurs";
        } else {
            map.addAttribute("loginParam", "Login ou mot de passe incorrect !!! ");
            return "prof/login1";
            //return "redirect:/";
        }
    }
    */
}
