package modele;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe representant un TrajetFixe qui hérite de la classe Trajet
 * un trajet fixe se caractérise par un jour et une heure de départ fixe
 */
public class TrajetFixe extends Trajet implements Cloneable{

	/**Attributes**/
	private Jour jourDepart;
	private LocalTime heureDepart;

	/**Constructors**/
	public TrajetFixe() {
		super();
	}

	public TrajetFixe(int identifiant, Ville villeDepart, Ville villeArrivee, double tempsDeConduite, ArrayList<Double> tempsDePause, HashMap<Ville, Double> listeVilleStop,Jour jourDepart, LocalTime heureDepart) {
		super(identifiant, villeDepart, villeArrivee, tempsDeConduite, tempsDePause, listeVilleStop);
		this.jourDepart = jourDepart;
		this.heureDepart = heureDepart;

	}

	public TrajetFixe(int identifiant, Ville villeDepart, Ville villeArrivee, double tempsDeConduite, HashMap<Ville, Double> listeVilleStop,Jour jourDepart, LocalTime heureDepart) {
		super(identifiant, villeDepart, villeArrivee, tempsDeConduite, listeVilleStop);
		this.jourDepart = jourDepart;
		this.heureDepart = heureDepart;

	}

	public Object clone() {
		Object o = null;
		// On récupère l'instance à renvoyer par l'appel de la
		// méthode super.clone()
		o = super.clone();
		// on renvoie le clone
		return o;
	}

	/**Getters & Setters**/
	@Override
	public String toString() {
		return "identifiant=" + getIdentifiant() +
				", villeDepart=" + getVilleDepart() +
				", villeArrivee=" + getVilleArrivee() +
				", tempsDeConduite=" + getTempsDeConduite() +
				", tempsDePause=" + getTempsDePause() +
				", listeVilleStop=" + getListeVilleStop() +
				"jourDepart=" + jourDepart +
				", heureDepart=" + heureDepart;
	}

	public Jour getJourDepart() {
		return jourDepart;
	}

	public void setJourDepart(Jour jourDepart) {
		this.jourDepart = jourDepart;
	}

	public LocalTime getHeureDepart() {
		return heureDepart;
	}

	public void setHeureDepart(LocalTime heureDepart) {
		this.heureDepart = heureDepart;
	}

	/**
	 * Verifie que deux trajets fixe sont égaux
	 * @param obj1 premier trajet fixe
	 * @param obj2 second trajet fixe
	 * @return true si égaux, faux sinon
	 */
	public static boolean egale(TrajetFixe obj1,TrajetFixe obj2) {
		boolean checker = true;

		checker = (obj1.getVilleArrivee().getIdentifiant() == obj2.getVilleArrivee().getIdentifiant());
		checker = (obj1.getVilleDepart().getIdentifiant() == obj2.getVilleDepart().getIdentifiant());
		checker = (obj1.getTempsDeConduite() == obj2.getTempsDeConduite());
		checker = (obj1.getJourDepart().getIdentifiant()) == obj2.getJourDepart().getIdentifiant();
		checker = (obj1.getHeureDepart()).compareTo(obj2.getHeureDepart()) == 0;

		return checker;
	}
}
