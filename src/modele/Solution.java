package modele;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Cette classe represente la solution d'une plannification
 */
public class Solution{

	/**Attributes**/
	private int nbrCamions;
	private int nbrChauffeurs;
	private Plannification plannification;

	private ArrayList<Chauffeur> chauffeurs;
	private ArrayList<TrajetFixe> trajets;

	private HashMap<Integer,ArrayList<Integer>> camionsTrajets;
	private HashMap<Integer,ArrayList<Integer>> chauffeursTrajets;



	/**Constructors**/
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

	/**Getters & Setters**/
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

	/**
	 * Recupere le chauffeur d'identifiant passé en paramètre
	 * @param identifiant du chauffeur
	 * @return l'objet Chauffeur
	 */
	public Chauffeur getChauffeurById(int identifiant){
		for (int i =0; i<getChauffeurs().size();i++){
			if(getChauffeurs().get(i).getIdentifiant() == identifiant)
				return getChauffeurs().get(i);
		}
		return null;
	}

	/**
	 * Recupere le trahet d'identifiant passé en paramètre
	 * @param identifiant du trajet
	 * @return l'objet TrajetFixe
	 */
	public TrajetFixe getTrajetById(int identifiant){
		for (int i =0; i<getTrajets().size();i++){
			if(getTrajets().get(i).getIdentifiant() == identifiant)
				return getTrajets().get(i);
		}
		return null;
	}

	/**
	 * Exporte la solution sous un format CSV, nommé Solution.csv dans le
	 * dossier du programme
	 * @throws IOException
	 */
	public void creationCsv() throws IOException {
		final String DELIMITER = ";";
		final String SEPARATOR = "\n";

		final String HEADER1 = "Nombre camions;Nombre Chauffeurs";
		final String HEADER2 = "Camions;Identifiant";
		final String HEADER3 = "Chauffeurs;Identifiant;Ville;Heure de jour; Heure de nuit; Cout trajet à vide; Cout hotellerie";
		final String HEADER4 = "Trajets;Ville depart;Ville arrivee;Jour de la semaine; Heure de depart; Identifiant chauffeur; Identifiant camion";

		FileWriter file = null;
		try {
			file = new FileWriter("Solution.csv");

			// HEADER 1
			file.append(HEADER1);
			file.append(SEPARATOR);
			file.append(Integer.toString(this.getNbrCamions()));
			file.append(DELIMITER);
			file.append(Integer.toString(this.getNbrChauffeurs()));
			file.append(SEPARATOR);

			// HEADER 2

			file.append(SEPARATOR);
			file.append(HEADER2);
			file.append(SEPARATOR);
			for (int i = 0; i < this.getNbrCamions(); i++) {
				file.append(DELIMITER);
				file.append(Integer.toString(i+1));
				file.append(SEPARATOR);
			}


			// HEADER 3
			file.append(SEPARATOR);
			file.append(HEADER3);
			file.append(SEPARATOR);
			for (int i = 0; i < this.getChauffeurs().size(); i++) {
				file.append(DELIMITER);
				file.append(Integer.toString(getChauffeurs().get(i).getIdentifiant()));
				file.append(DELIMITER);
				file.append(getChauffeurs().get(i).getVilleRattachement().getNom());
				file.append(DELIMITER);
				file.append(Double.toString(getChauffeurs().get(i).getContratDeTravail().getNbrHeureJour()));
				file.append(DELIMITER);
				file.append(Double.toString(getChauffeurs().get(i).getContratDeTravail().getNbrHeureNuit()));
				file.append(DELIMITER);
				file.append(Double.toString(getChauffeurs().get(i).getContratDeTravail().getCoutTrajetVide()));
				file.append(DELIMITER);
				file.append(Double.toString(getChauffeurs().get(i).getCoutHotellerie()));
				file.append(SEPARATOR);
			}

			// HEADER 4
			file.append(SEPARATOR);
			file.append(HEADER4);
			file.append(SEPARATOR);
			for (int i = 0; i < this.getTrajets().size(); i++) {
				file.append(DELIMITER);
				file.append(getTrajets().get(i).getVilleDepart().getNom());
				file.append(DELIMITER);
				file.append(getTrajets().get(i).getVilleArrivee().getNom());
				file.append(DELIMITER);
				file.append(getTrajets().get(i).getJourDepart().getNom());
				file.append(DELIMITER);
				file.append(getTrajets().get(i).getHeureDepart().toString());
				file.append(DELIMITER);
				for (Map.Entry<Integer, ArrayList<Integer>> mapentry : getChauffeursTrajets().entrySet()) {
					for (int j = 0; j < mapentry.getValue().size(); j++) {
						if (getTrajets().get(i).getIdentifiant() == mapentry.getValue().get(j))
							file.append(Integer.toString(mapentry.getKey()));
					}
				}
				file.append(DELIMITER);
				for (Map.Entry<Integer, ArrayList<Integer>> mapentry : getCamionsTrajets().entrySet()) {
					for (int j = 0; j < mapentry.getValue().size(); j++) {
						if (getTrajets().get(i).getIdentifiant() == mapentry.getValue().get(j))
							file.append(Integer.toString(mapentry.getKey()));
					}
				}
				file.append(SEPARATOR);
			}
			file.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
