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
		Ville nantes = new Ville(6, "Nantes");
		Ville nice = new Ville(7, "Nice");
		Ville strasbourg = new Ville(8, "Strasbourg");
		Ville montpellier = new Ville(9, "Montpellier");
		Ville lille = new Ville(10, "Lille");
		Ville rennes = new Ville(11, "Rennes");
		Ville angers = new Ville(12, "Angers");
		Ville dijon = new Ville(13, "Dijon");
		Ville grenoble = new Ville(14, "Grenoble");
		Ville lehavre = new Ville(15, "Le Havre");
		Ville tours = new Ville(16, "Tours");
		Ville orleans = new Ville(17, "Orleans");
		Ville clermont = new Ville(18, "Clermont-Ferrand");
		Ville perpignan = new Ville(19, "Perpignan");





		villes.add(paris);
		villes.add(marseille);
		villes.add(lyon);
		villes.add(toulouse);
		villes.add(bordeaux);
		villes.add(nantes);
		villes.add(nice);
		villes.add(strasbourg);
		villes.add(montpellier);
		villes.add(lille);
		villes.add(rennes);
		villes.add(angers);
		villes.add(dijon);
		villes.add(grenoble);
		villes.add(lehavre);
		villes.add(tours);
		villes.add(orleans);
		villes.add(clermont);
		villes.add(perpignan);

		return villes;
	}
}
