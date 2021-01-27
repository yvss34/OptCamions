package modele;

import java.time.LocalTime;
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

	public TrajetNonFixe(int identifiant, Ville villeDepart, Ville villeArrivee, double tempsDeConduite, ArrayList<Double> tempsDePause, HashMap<Ville, Double> listeVilleStop,int frequenceSemaine, float[][] fenetreDeTemps, int nombreTrajetWeekend, int nbrJourMin) {
		super(identifiant, villeDepart, villeArrivee, tempsDeConduite, tempsDePause, listeVilleStop);
		this.frequenceSemaine = frequenceSemaine;
		this.fenetreDeTemps = fenetreDeTemps;
		this.nombreTrajetWeekend = nombreTrajetWeekend;
		this.nbrJourMin = nbrJourMin;

	}

//Getters & Setters


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
