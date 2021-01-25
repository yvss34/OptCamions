package modele;

import java.time.LocalTime;

public class ContratDeTravail {
	
	//Attributs
	
	private LocalTime nbrHeureJour;
	private LocalTime nbrHeureNuit;
	private float coutTrajetVide;
	
	
	//Constructeurs
	public ContratDeTravail() {
		
	}
	
	public ContratDeTravail(LocalTime nbrHeureJour, LocalTime nbrHeureNuit, float coutTrajetVide) {
		super();
		this.nbrHeureJour = nbrHeureJour;
		this.nbrHeureNuit = nbrHeureNuit;
		this.coutTrajetVide = coutTrajetVide;
	}
	
	//Getters & Setters
	public LocalTime getNbrHeureJour() {
		return nbrHeureJour;
	}
	public void setNbrHeureJour(LocalTime nbrHeureJour) {
		this.nbrHeureJour = nbrHeureJour;
	}
	public LocalTime getNbrHeureNuit() {
		return nbrHeureNuit;
	}
	public void setNbrHeureNuit(LocalTime nbrHeureNuit) {
		this.nbrHeureNuit = nbrHeureNuit;
	}
	public float getCoutTrajetVide() {
		return coutTrajetVide;
	}
	public void setCoutTrajetVide(float coutTrajetVide) {
		this.coutTrajetVide = coutTrajetVide;
	}
	
}
