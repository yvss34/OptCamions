package modele;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe abstraite représentant un trajet
 */
public abstract class Trajet {
	
	/**Attributes**/
	private int identifiant;
	private Ville villeDepart;
	private Ville villeArrivee;
	private double tempsDeConduite;
	private ArrayList<Double> tempsDePause;
	private HashMap<Ville,Double> listeVilleStop;
	static int nbrTrajet = 0;

	/**Constructors**/
	public Trajet() {
		nbrTrajet++;
	}

	public Trajet(int identifiant,Ville villeDepart, Ville villeArrivee, double tempsDeConduite, ArrayList<Double> tempsDePause, HashMap<Ville, Double> listeVilleStop) {
		this.identifiant = identifiant;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.tempsDeConduite = tempsDeConduite;
		this.tempsDePause = tempsDePause;
		this.listeVilleStop = listeVilleStop;
		nbrTrajet++;
	}

	public Trajet(int identifiant,Ville villeDepart, Ville villeArrivee, double tempsDeConduite, HashMap<Ville, Double> listeVilleStop) {
		this.identifiant = identifiant;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.tempsDeConduite = tempsDeConduite;
		this.listeVilleStop = listeVilleStop;
		nbrTrajet++;
	}

	/**Getters & Setters**/
	@Override
	public String toString() {
		return "Trajet{" +
				"identifiant=" + identifiant +
				", villeDepart=" + villeDepart +
				", villeArrivee=" + villeArrivee +
				", tempsDeConduite=" + tempsDeConduite +
				", tempsDePause=" + tempsDePause +
				", listeVilleStop=" + listeVilleStop +
				'}';
	}

	public static int getNbrTrajet(){return nbrTrajet;}

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
