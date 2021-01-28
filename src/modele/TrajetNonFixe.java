package modele;

import java.util.ArrayList;
import java.util.HashMap;

public class TrajetNonFixe extends Trajet{

	//Attributs
	
	private int frequenceSemaine;
	private String[] fenetreDeTemps;
	private int nombreTrajetWeekend;
	private int nbrJourMin;
	
	//Constructeurs
	public TrajetNonFixe() {
		super();
	}

	public TrajetNonFixe(int identifiant, Ville villeDepart, Ville villeArrivee, double tempsDeConduite, ArrayList<Double> tempsDePause, HashMap<Ville, Double> listeVilleStop,int frequenceSemaine, String[] fenetreDeTemps, int nombreTrajetWeekend, int nbrJourMin) {
		super(identifiant, villeDepart, villeArrivee, tempsDeConduite, tempsDePause, listeVilleStop);
		this.frequenceSemaine = frequenceSemaine;
		this.fenetreDeTemps = fenetreDeTemps;
		this.nombreTrajetWeekend = nombreTrajetWeekend;
		this.nbrJourMin = nbrJourMin;

	}

	public TrajetNonFixe(int identifiant, Ville villeDepart, Ville villeArrivee, double tempsDeConduite, HashMap<Ville, Double> listeVilleStop,int frequenceSemaine, String[] fenetreDeTemps, int nombreTrajetWeekend, int nbrJourMin) {
		super(identifiant, villeDepart, villeArrivee, tempsDeConduite, listeVilleStop);
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

	public String[] getFenetreDeTemps() {
		return fenetreDeTemps;
	}

	public void setFenetreDeTemps(String[] fenetreDeTemps) {
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

	public String toString(){

		return Integer.toString(this.getIdentifiant());
	}
	

	
	
	
}
