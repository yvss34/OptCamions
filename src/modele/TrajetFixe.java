package modele;

import java.util.ArrayList;
import java.util.HashMap;

public class TrajetFixe extends Trajet{

	//Attributs
	
	private Jour jourDepart;
	private float heureDepart;
	
	//Constructeurs
	
	public TrajetFixe() {
		super();
	}
	


	//Getters & Setters

	public TrajetFixe(int identifiant, Ville villeDepart, Ville villeArrivee, float tempsDeConduite,
			ArrayList<Float> tempsDePause, HashMap<Ville, Float> listeVilleStop) {
		super(identifiant, villeDepart, villeArrivee, tempsDeConduite, tempsDePause, listeVilleStop);
		// TODO Auto-generated constructor stub
	}



	public Jour getJourDepart() {
		return jourDepart;
	}

	public void setJourDepart(Jour jourDepart) {
		this.jourDepart = jourDepart;
	}

	public float getHeureDepart() {
		return heureDepart;
	}

	public void setHeureDepart(float heureDepart) {
		this.heureDepart = heureDepart;
	}
	
	
}
