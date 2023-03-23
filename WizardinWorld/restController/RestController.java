package it.softwareInside.WizardinWorld.restController;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.softwareInside.WizardinWorld.models.Wizard;
import it.softwareInside.WizardinWorld.service.PDFWizard;
import it.softwareInside.WizardinWorld.service.WizardService;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	
	@Autowired
	WizardService ws;
	
	@Autowired
	PDFWizard pdfw;

	
	
	@GetMapping("/findW")
	public Wizard findW(@RequestParam("id") String id) {
		
		return ws.findW(id);
		
	}
	
	
	
	@DeleteMapping("/deleteW")
	public void deleteW(String id) {
		
		 ws.deleteW(id);
	}
	
	
	
	
	@DeleteMapping("/deleteAll")
	public void deleteAll() {
		
		ws.deleteAllW();
	}
	
	
	@GetMapping("/findAll")
	public Iterable<Wizard> findAll(){
		
		return ws.findAllW();	
	}
	
	
	
	@GetMapping("/addW")
    public String add(){
        return ws.addW();
    }
	
	
	 @RequestMapping(value = "/pdf", method = RequestMethod.GET,
	            produces = MediaType.APPLICATION_PDF_VALUE)
	    public ResponseEntity<InputStreamResource> generaIncantesimo(@RequestParam("id") String id) {

	    	
	    	try {
	    		
	            ByteArrayInputStream bis = pdfw.generaIncantesimo(id); 

	            var headers = new HttpHeaders();
	            headers.add("Content-Disposition", "inline; filename=incantesimi.pdf");
	    		
	            
	            
	            return ResponseEntity
	                    .ok()
	                    .headers(headers)
	                    .contentType(MediaType.APPLICATION_PDF)
	                    .body(new InputStreamResource(bis));
	    		
	    	}catch (Exception e) {
				return null;
	    	
	    
	    	}
		
	    }
	
	
	 
	 
	 
	 
	 
	 
	 
	 
	 @RequestMapping(value = "/pdfDataBase", method = RequestMethod.GET,
	            produces = MediaType.APPLICATION_PDF_VALUE)
	    public ResponseEntity<InputStreamResource> stampaDataBase() {

	    	
	    	try {
	    		
	            ByteArrayInputStream bis = pdfw.stampaDatabase(); 

	            var headers = new HttpHeaders();
	            headers.add("Content-Disposition", "inline; filename=incantesimiTutti.pdf");
	    		
	            
	            
	            return ResponseEntity
	                    .ok()
	                    .headers(headers)
	                    .contentType(MediaType.APPLICATION_PDF)
	                    .body(new InputStreamResource(bis));
	    		
	    	}catch (Exception e) {
				return null;
	    	
	    
	    	}
		
	    }
	
	
}
