package sn.isi.online.utils;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import sn.isi.online.entities.Professeur;


public class PdfProfesseurListReportView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.addHeader("Content-Dispostion", "attachment; filename=\"prof_list.pdf\"");
		
		@SuppressWarnings("unused")
		List<Professeur> list = (List<Professeur>) model.get("professeursList");
		
		Table table = new Table(5);//5 colonnes
		table.addCell("NOM");
		table.addCell("PRENOM");
		table.addCell("EMAIL");
		table.addCell("PASSWORD");
		table.addCell("ETAT COMPTE");
		for(Professeur professeur : list) {
			table.addCell(professeur.getNom());
			table.addCell(professeur.getPrenom());
			table.addCell(professeur.getEmail());
			table.addCell(professeur.getPassword());
			table.addCell(String.valueOf(professeur.getEtat()));
		}
		
		document.add(table);
	}

}
