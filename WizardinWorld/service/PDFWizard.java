package it.softwareInside.WizardinWorld.service;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PDFWizard {

	@Autowired
	WizardService ws;
	
	 
	
	
	 /**
	  * metodo per stampare un incantesimo PDF tramite id in input
	  * un incantesimo
	  * @param id
	  * @return
	  */
	 public  ByteArrayInputStream generaIncantesimo(String id) {

	        Document document = new Document();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();

	        try {
	            
	            Paragraph contenuto = new Paragraph();
	         
	            contenuto.add(ws.findW(id).toString());
	            
	            
	            
	            
	            PdfWriter.getInstance(document, out);
	            document.open();
	            document.add(contenuto);
	            document.close();

	        } catch (DocumentException ex) {

	           return null;
	        }

	        return new ByteArrayInputStream(out.toByteArray());
	    }
	
	
	 
	 
	 
	 
	 
	 /**
	  * metodo per stampare PDF  tutto il Database di incantesimi
	  * @return
	  */
	 public  ByteArrayInputStream stampaDatabase() {

	        Document document = new Document();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();

	        try {
	            
	            Paragraph contenuto = new Paragraph();
	         
	            contenuto.add(ws.findAllW().toString());
	            
	            
	            
	            
	            PdfWriter.getInstance(document, out);
	            document.open();
	            document.add(contenuto);
	            document.close();

	        } catch (DocumentException ex) {

	           return null;
	        }

	        return new ByteArrayInputStream(out.toByteArray());
	    }
	
	 
	 
	 
	 
	 
	 
	 
	 
}


