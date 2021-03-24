package modele;

import java.util.ArrayList;

/**
 * Classe represente un jour de la semaine
 */
public class Jour {

	/**
	 * Attributes
	 */
	private int identifiant;
	private String nom;

	/**
	 * Constructors
	 */
	public Jour() {

	}

	public Jour(int identifiant, String nom) {
		super();
		this.identifiant = identifiant;
		this.nom = nom;
	}

	/**
	 * Getters & Setters
	 */
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
	 *
	 * @return une liste des jours de la semaine
	 */
	public static ArrayList<Jour> jourDeLaSemaine(){
		ArrayList<Jour> jours = new ArrayList<Jour>();

		Jour lundi = new Jour(0,"Lundi");
		Jour mardi = new Jour(1,"Mardi");
		Jour mercredi = new Jour(2,"Mercredi");
		Jour jeudi = new Jour(3,"Jeudi");
		Jour vendredi = new Jour(4,"Vendredi");
		Jour samedi = new Jour(5,"Samedi");
		Jour dimanche = new Jour(6,"Dimanche");

		jours.add(lundi);
		jours.add(mardi);
		jours.add(mercredi);
		jours.add(jeudi);
		jours.add(vendredi);
		jours.add(samedi);
		jours.add(dimanche);

		return jours;
	}

	/**
	 * Renvoi le jour de la semaine
	 * @param identifiant du jour de la semaine
	 */
	public static Jour getJourById(int identifiant){
			switch (identifiant){
				case 0:
					return new Jour(0,"Lundi");
				case 1:
					return new Jour(1,"Mardi");
				case 2:
					return new Jour(2,"Mercredi");
				case 3:
					return new Jour(3,"Jeudi");
				case 4:
					return new Jour(4,"Vendredi");
				case 5:
					return new Jour(5,"Samedi");
				case 6:
					return new Jour(6,"Dimanche");
			}
			return null;
	}
}
