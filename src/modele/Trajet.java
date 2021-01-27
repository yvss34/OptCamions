package modele;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Trajet {
	
	//Attributs
	
	private int identifiant;
	private Ville villeDepart;
	private Ville villeArrivee;
	private double tempsDeConduite;
	private ArrayList<Double> tempsDePause;
	private HashMap<Ville,Double> listeVilleStop;
	
	//Constructeurs
	public Trajet() {
		
	}

	public Trajet(int identifiant, Ville villeDepart, Ville villeArrivee, double tempsDeConduite, ArrayList<Double> tempsDePause, HashMap<Ville, Double> listeVilleStop) {
		this.identifiant = identifiant;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.tempsDeConduite = tempsDeConduite;
		this.tempsDePause = tempsDePause;
		this.listeVilleStop = listeVilleStop;
	}


//Getters & Setters


	public int getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public Ville getVilleDepart() {
		return villeDepart;
	}

	public void setVilleDepart(Ville villeDepart) {
		this.villeDepart = villeDepart;
	}

	public Ville getVilleArrivee() {
		return villeArrivee;
	}

	public void setVilleArrivee(Ville villeArrivee) {
		this.villeArrivee = villeArrivee;
	}

	public double getTempsDeConduite() {
		return tempsDeConduite;
	}

	public void setTempsDeConduite(double tempsDeConduite) {
		this.tempsDeConduite = tempsDeConduite;
	}

	public ArrayList<Double> getTempsDePause() {
		return tempsDePause;
	}

	public void setTempsDePause(ArrayList<Double> tempsDePause) {
		this.tempsDePause = tempsDePause;
	}

	public HashMap<Ville, Double> getListeVilleStop() {
		return listeVilleStop;
	}

	public void setListeVilleStop(HashMap<Ville, Double> listeVilleStop) {
		this.listeVilleStop = listeVilleStop;
	}
}
