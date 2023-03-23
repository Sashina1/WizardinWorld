package it.softwareInside.WizardinWorld.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import it.softwareInside.WizardinWorld.models.Wizard;
import it.softwareInside.WizardinWorld.repository.WizardRepository;

@Service
public class WizardService {

	@Autowired
	WizardRepository wr;
	
	

	
	public Wizard findW(String id) {
		
		try {
			return wr.findById(id).get();
			
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
	public void deleteW(String id) {
		
		
		 wr.deleteById(id);
			 
	}
	
	
	
	
	public void deleteAll(String id) {
		
		wr.deleteAll();

	}
	
	
	
	
	
	public Iterable<Wizard> findAllW(){
		 
		return wr.findAll();
	}
	
	
	
	
	public void deleteAllW() {
		
		wr.deleteAll();
	}
	
	
	
	
	/**
	 * il metodo restituisce un incantesimo preso dall Array
	 * che viene restituito all Api
	 * @return
	 */
	public Wizard[] generate() {
	
		
		RestTemplate rest = new RestTemplate();
		ResponseEntity<Wizard[]> incantesimo = rest.getForEntity("https://wizard-world-api.herokuapp.com/Spells/", Wizard[].class);
		
		return incantesimo.getBody();
				
	}

	
	/**
	 * il metodo restituisce il numero di incantesimi
	 * presenti nell Api
	 * 
	 *  lo useremo nel metodo add per aggiungere un incantesimo dell'API al nostro database,
     * senza dover passare un id al metodo
	 * @return
	 */
	public int quantitaIncantesimi() {
		
		try {
			int quantitaIncantesimi = generate().length;
			return quantitaIncantesimi;
		} catch (Exception e) {
			return -1;
		}
	}
	
	
	
	
	
	
	/**
	 * metodo controlla se nel Database esiste già quell incantesimo
	 * con lo stesso Id
	 * @param id
	 * @return
	 */
	public boolean isIdHere(String id){
        for (Wizard elemento : findAllW()) {
            if(elemento.getId().equals(id))
                return true;
        }
        return false;
}
	
	
	
	
	
	
	/**
	 * con questo metodo aggiugniamo un incantesimo al nostro
	 * Database.
	 * @return
	 */
	public String addW(){
        try {
            Random casuale = new Random();
            Wizard nuovo = (generate()[casuale.nextInt(1, quantitaIncantesimi())]);
            if(!isIdHere(nuovo.getId())){
                wr.save(nuovo);
                return "incantesimo aggiunto";
            }
            return "non è stato possibile aggiungere l'incantesimo" + nuovo.getId();
        } catch (Exception e) {
            System.out.println("errore: " + e);
            return "non è stato possibile aggiungere l'incantesimo";
        }
    } 
	
	
	
	
	
	
	
	
	
	
	
	
	
}
