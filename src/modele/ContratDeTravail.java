package modele;

public class ContratDeTravail {
	
	//Attributs
	
	private int nbrHeureJour;
	private int nbrHeureNuit;
	private int coutTrajetVide;
	
	
	//Constructeurs
	public ContratDeTravail() {
		
	}
	
	public ContratDeTravail(int nbrHeureJour, int nbrHeureNuit, int coutTrajetVide) {
		super();
		this.nbrHeureJour = nbrHeureJour;
		this.nbrHeureNuit = nbrHeureNuit;
		this.coutTrajetVide = coutTrajetVide;
	}
	
	//Getters & Setters
	public int getNbrHeureJour() {
		return nbrHeureJour;
	}
	public void setNbrHeureJour(int nbrHeureJour) {
		this.nbrHeureJour = nbrHeureJour;
	}
	public int getNbrHeureNuit() {
		return nbrHeureNuit;
	}
	public void setNbrHeureNuit(int nbrHeureNuit) {
		this.nbrHeureNuit = nbrHeureNuit;
	}
	public int getCoutTrajetVide() {
		return coutTrajetVide;
	}
	public void setCoutTrajetVide(int coutTrajetVide) {
		this.coutTrajetVide = coutTrajetVide;
	}
	
}
