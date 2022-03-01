package sn.isi.online.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sn.isi.online.dao.IProfesseur;
import sn.isi.online.entities.Professeur;
import sn.isi.online.utils.ExcelProfesseurListReportView;
import sn.isi.online.utils.PdfProfesseurListReportView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private IProfesseur professeurdao;

    @RequestMapping(value = "/online/excel")
    public ModelAndView createExcel(HttpServletRequest request, HttpServletResponse response) {
        //create data
        List<Professeur> list = professeurdao.findAll();

        return new ModelAndView(new ExcelProfesseurListReportView(), "professeursList", list);
    }

    @RequestMapping(value = "/online/pdf")
    public ModelAndView createPdf(HttpServletRequest request, HttpServletResponse response) {
        String typeReport = request.getParameter("type");

        //create data
        List<Professeur> list = professeurdao.findAll();

        return new ModelAndView(new PdfProfesseurListReportView(), "professeursList", list);
    }
}
