package modele;

public class Ville {
	
	//Attributs
	private int identifiant;
	private String nom;
	
	//Constructeurs
	public Ville() {
		
	}
	
	public Ville(int identifiant, String nom) {
		super();
		this.identifiant = identifiant;
		this.nom = nom;
	}
	
	
	//Getters & Setters
	public int getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}
