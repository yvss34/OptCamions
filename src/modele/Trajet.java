package modele;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Trajet {
	
	//Attributs
	
	private int identifiant;
	private Ville villeDepart;
	private Ville villeArrivee;
	private LocalTime tempsDeConduite;
	private ArrayList<LocalTime> tempsDePause;
	private HashMap<Ville,LocalTime> listeVilleStop;
	
	//Constructeurs
	public Trajet() {
		
	}

	public Trajet(int identifiant, Ville villeDepart, Ville villeArrivee, LocalTime tempsDeConduite, ArrayList<LocalTime> tempsDePause, HashMap<Ville, LocalTime> listeVilleStop) {
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

	public LocalTime getTempsDeConduite() {
		return tempsDeConduite;
	}

	public void setTempsDeConduite(LocalTime tempsDeConduite) {
		this.tempsDeConduite = tempsDeConduite;
	}

	public ArrayList<LocalTime> getTempsDePause() {
		return tempsDePause;
	}

	public void setTempsDePause(ArrayList<LocalTime> tempsDePause) {
		this.tempsDePause = tempsDePause;
	}

	public HashMap<Ville, LocalTime> getListeVilleStop() {
		return listeVilleStop;
	}

	public void setListeVilleStop(HashMap<Ville, LocalTime> listeVilleStop) {
		this.listeVilleStop = listeVilleStop;
	}
}
