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
	
	
}
