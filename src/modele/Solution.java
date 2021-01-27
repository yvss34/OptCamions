package modele;

import javax.swing.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Solution{

	//Attributs
	
	private int nbrCamions;
	private int nbrChauffeurs;
	private Plannification plannification;

	private ArrayList<Chauffeur> chauffeurs;
	private ArrayList<TrajetFixe> trajets;

	private HashMap<Integer,ArrayList<Integer>> camionsTrajets;
	private HashMap<Integer,ArrayList<Integer>> chauffeursTrajets;
	
	
	
	//Constructeurs
	
	public Solution() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Solution(int nbrCamions, int nbrChauffeurs, Plannification plannification, ArrayList<Chauffeur> chauffeurs, ArrayList<TrajetFixe> trajets, HashMap<Integer, ArrayList<Integer>> camionsTrajets, HashMap<Integer, ArrayList<Integer>> chauffeursTrajets) {
		this.nbrCamions = nbrCamions;
		this.nbrChauffeurs = nbrChauffeurs;
		this.plannification = plannification;
		this.chauffeurs = chauffeurs;
		this.trajets = trajets;
		this.camionsTrajets = camionsTrajets;
		this.chauffeursTrajets = chauffeursTrajets;
	}

	// Getters & Setters
	
	public Plannification getPlannification() {
		return plannification;
	}


	public void setPlannification(Plannification plannification) {
		this.plannification = plannification;
	}


	public int getNbrCamions() {
		return nbrCamions;
	}


	public void setNbrCamions(int nbrCamions) {
		this.nbrCamions = nbrCamions;
	}


	public int getNbrChauffeurs() {
		return nbrChauffeurs;
	}


	public void setNbrChauffeurs(int nbrChauffeurs) {
		this.nbrChauffeurs = nbrChauffeurs;
	}


	public HashMap<Integer, ArrayList<Integer>> getCamionsTrajets() {
		return camionsTrajets;
	}


	public void setCamionsTrajets(HashMap<Integer, ArrayList<Integer>> camionsTrajets) {
		this.camionsTrajets = camionsTrajets;
	}


	public HashMap<Integer, ArrayList<Integer>> getChauffeursTrajets() {
		return chauffeursTrajets;
	}


	public void setChauffeursTrajets(HashMap<Integer, ArrayList<Integer>> chauffeursTrajets) {
		this.chauffeursTrajets = chauffeursTrajets;
	}

	public ArrayList<Chauffeur> getChauffeurs() {return chauffeurs;}

	public void setChauffeurs(ArrayList<Chauffeur> chauffeurs){
		this.chauffeurs = chauffeurs;
	}

	public ArrayList<TrajetFixe> getTrajets() {return trajets;}

	public void setTrajets(ArrayList<TrajetFixe> trajets) {this.trajets = trajets;}

	public Chauffeur getChauffeurById(int identifiant){
		for (int i =0; i<getChauffeurs().size();i++){
			if(getChauffeurs().get(i).getIdentifiant() == identifiant)
				return getChauffeurs().get(i);
		}
		return null;
	}

	public TrajetFixe getTrajetById(int identifiant){
		for (int i =0; i<getTrajets().size();i++){
			if(getTrajets().get(i).getIdentifiant() == identifiant)
				return getTrajets().get(i);
		}
		return null;
	}

}
