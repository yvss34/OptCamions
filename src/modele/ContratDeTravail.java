package modele;

import java.time.LocalTime;

/**
 * Classe represente un contrat de travail d'un chauffeur
 */
public class ContratDeTravail {

	/**
	 * Attributes
	 */
	private double nbrHeureJour;
	private double nbrHeureNuit;
	private double coutTrajetVide;


	/**
	 * Constructors
	 */
	public ContratDeTravail() {
		
	}

	public ContratDeTravail(double nbrHeureJour, double nbrHeureNuit, double coutTrajetVide) {
		this.nbrHeureJour = nbrHeureJour;
		this.nbrHeureNuit = nbrHeureNuit;
		this.coutTrajetVide = coutTrajetVide;
	}

	/**
	 * Getters & Setters
	 */
	public double getNbrHeureJour() {
		return nbrHeureJour;
	}

	public void setNbrHeureJour(double nbrHeureJour) {
		this.nbrHeureJour = nbrHeureJour;
	}

	public double getNbrHeureNuit() {
		return nbrHeureNuit;
	}

	public void setNbrHeureNuit(double nbrHeureNuit) {
		this.nbrHeureNuit = nbrHeureNuit;
	}

	public double getCoutTrajetVide() {
		return coutTrajetVide;
	}

	public void setCoutTrajetVide(double coutTrajetVide) {
		this.coutTrajetVide = coutTrajetVide;
	}
}
