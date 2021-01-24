package modele;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Trajet {
	
	//Attributs
	
	private int identifiant;
	private Ville villeDepart;
	private Ville villeArrivee;
	private float tempsDeConduite;
	private ArrayList<Float> tempsDePause;
	private HashMap<Ville,Float> listeVilleStop;
	
	//Constructeurs
	public Trajet() {
		
	}
	
	public Trajet(int identifiant, Ville villeDepart, Ville villeArrivee, float tempsDeConduite,ArrayList<Float> tempsDePause,
			HashMap<Ville, Float> listeVilleStop) {
		super();
		this.identifiant = identifiant;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.tempsDePause = tempsDePause;
		this.tempsDeConduite = tempsDeConduite;
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

	public float getTempsDeConduite() {
		return tempsDeConduite;
	}

	public void setTempsDeConduite(float tempsDeConduite) {
		this.tempsDeConduite = tempsDeConduite;
	}

	public ArrayList<Float> getTempsDePause() {
		return tempsDePause;
	}

	public void setTempsDePause(ArrayList<Float> tempsDePause) {
		this.tempsDePause = tempsDePause;
	}

	public HashMap<Ville, Float> getListeVilleStop() {
		return listeVilleStop;
	}

	public void setListeVilleStop(HashMap<Ville, Float> listeVilleStop) {
		this.listeVilleStop = listeVilleStop;
	} 
}
