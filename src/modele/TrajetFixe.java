package modele;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class TrajetFixe extends Trajet{

	//Attributs
	
	private Jour jourDepart;
	private LocalTime heureDepart;
	
	//Constructeurs

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

	//Getters & Setters
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

	public static boolean egale(TrajetFixe obj1,TrajetFixe obj2) {
		boolean checker = true;

		checker = (obj1.getVilleArrivee().getIdentifiant() == obj2.getVilleArrivee().getIdentifiant());
		checker = (obj1.getVilleDepart().getIdentifiant() == obj2.getVilleDepart().getIdentifiant());
		checker = (obj1.getTempsDeConduite() == obj2.getTempsDeConduite());
		checker = (obj1.getJourDepart().getIdentifiant()) == obj2.getJourDepart().getIdentifiant();

		return checker;
	}
}
