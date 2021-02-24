package modele;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Cette classe represente une ville
 */
public class Ville {

	/**Attributes**/
	private int identifiant;
	private String nom;
	
	/**Constructors**/
	public Ville() {
		
	}
	
	public Ville(int identifiant, String nom) {
		super();
		this.identifiant = identifiant;
		this.nom = nom;
	}
	
	
	/**Getters & Setters**/
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

	@Override
	public String toString() {
		return nom;
	}

	/**
	 * @return une ArrayList des villes de France (non exhaustive)
	 */
	public static ArrayList<Ville> getVilles(){
		ArrayList<Ville> villes = new ArrayList<Ville>();

		Ville paris = new Ville(1, "Paris");
		Ville marseille = new Ville(2, "Marseille");
		Ville lyon = new Ville(3, "Lyon");
		Ville toulouse = new Ville(4, "Toulouse");
		Ville bordeaux = new Ville(5, "Bordeaux");

		villes.add(paris);
		villes.add(marseille);
		villes.add(lyon);
		villes.add(toulouse);
		villes.add(bordeaux);

		return villes;
	}
}
