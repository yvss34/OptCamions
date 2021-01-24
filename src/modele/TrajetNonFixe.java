package modele;

import java.util.ArrayList;
import java.util.HashMap;

public class TrajetNonFixe extends Trajet{

	//Attributs
	
	private int frequenceSemaine;
	private float[][] fenetreDeTemps;
	private int nombreTrajetWeekend;
	private int nbrJourMin;
	
	//Constructeurs
	public TrajetNonFixe() {
		super();
	}


	//Getters & Setters
	
	public TrajetNonFixe(int identifiant, Ville villeDepart, Ville villeArrivee, float tempsDeConduite,
			ArrayList<Float> tempsDePause, HashMap<Ville, Float> listeVilleStop) {
		super(identifiant, villeDepart, villeArrivee, tempsDeConduite, tempsDePause, listeVilleStop);
		// TODO Auto-generated constructor stub
	}


	public int getFrequenceSemaine() {
		return frequenceSemaine;
	}

	public void setFrequenceSemaine(int frequenceSemaine) {
		this.frequenceSemaine = frequenceSemaine;
	}

	public float[][] getFenetreDeTemps() {
		return fenetreDeTemps;
	}

	public void setFenetreDeTemps(float[][] fenetreDeTemps) {
		this.fenetreDeTemps = fenetreDeTemps;
	}

	public int getNombreTrajetWeekend() {
		return nombreTrajetWeekend;
	}

	public void setNombreTrajetWeekend(int nombreTrajetWeekend) {
		this.nombreTrajetWeekend = nombreTrajetWeekend;
	}

	public int getNbrJourMin() {
		return nbrJourMin;
	}

	public void setNbrJourMin(int nbrJourMin) {
		this.nbrJourMin = nbrJourMin;
	}

	
	

	
	
	
}
