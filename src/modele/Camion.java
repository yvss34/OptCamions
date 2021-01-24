package modele;

public class Camion {
	
	//Attribut
	private int identifiant;

	//Constructeurs
	public Camion() {
		
	}
	
	public Camion(int identifiant) {
		super();
		this.identifiant = identifiant;
	}

	//Getters & Setters
	public int getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}
	
	
}
