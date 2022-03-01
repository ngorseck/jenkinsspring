package sn.isi.online.utils;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import sn.isi.online.entities.Professeur;


public class ExcelProfesseurListReportView extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment; filename=\"prof_list.xls\"");
		
		@SuppressWarnings("unused")
		List<Professeur> list = (List<Professeur>) model.get("professeursList");
		Sheet sheet = workbook.createSheet("liste des professeurs");
		//Entete de ligne
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("NOM");
		header.createCell(1).setCellValue("PRENOM");
		header.createCell(2).setCellValue("EMAIL");
		header.createCell(3).setCellValue("PASSWORD");
		header.createCell(4).setCellValue("ETAT COMPTE");
		int rowNumber = 1;
		for(Professeur professeur : list) {
			Row row = sheet.createRow(rowNumber++);
			row.createCell(0).setCellValue(professeur.getNom());
			row.createCell(1).setCellValue(professeur.getPrenom());
			row.createCell(2).setCellValue(professeur.getEmail());
			row.createCell(3).setCellValue(professeur.getPassword());
			row.createCell(4).setCellValue(professeur.getEtat());
		}
	}

}
