package it.softwareInside.WizardinWorld.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

@Entity
public class Wizard {

	
		
		
		@Id
		private String id;
	  
		
		private String name;
		private String incantation;
		private String effect;
		private boolean canBeVerbal;
		private String type;
		private String light;
		private String creator;
		
		
		
		public Wizard(String name, String incatation, String effect, boolean canBeVerbal, String type, String light, String creator) {
			
			setName(name);
			setIncantation(incatation);
			setEffect(effect);
			setCanBeVerbal(canBeVerbal);
			setType(type);
			setLight(light);
			setCreator(creator);
		
		
		
		
	    	
	    }
	    
	    
	    
	    
	
	
}
