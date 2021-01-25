package modele;

import javax.swing.*;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Solution {

	//Attributs
	
	private int nbrCamions;
	private int nbrChauffeurs;
	private Plannification plannification;

	private ArrayList<Chauffeur> chauffeurs;
	private ArrayList<TrajetFixe> trajets;

	private HashMap<Integer,ArrayList<TrajetFixe>> camionsTrajets;
	private HashMap<Integer,ArrayList<TrajetFixe>> chauffeursTrajets;
	
	
	
	//Constructeurs
	
	public Solution() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Solution(int nbrCamions, int nbrChauffeurs,Plannification plannification, HashMap<Integer, ArrayList<TrajetFixe>> camionsTrajets,
			HashMap<Integer, ArrayList<TrajetFixe>> chauffeursTrajets, ArrayList<Chauffeur> chauffeurs) {
		super();
		this.nbrCamions = nbrCamions;
		this.nbrChauffeurs = nbrChauffeurs;
		this.plannification = plannification;
		this.camionsTrajets = camionsTrajets;
		this.chauffeursTrajets = chauffeursTrajets;
		this.chauffeurs = chauffeurs;
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


	public HashMap<Integer, ArrayList<TrajetFixe>> getCamionsTrajets() {
		return camionsTrajets;
	}


	public void setCamionsTrajets(HashMap<Integer, ArrayList<TrajetFixe>> camionsTrajets) {
		this.camionsTrajets = camionsTrajets;
	}


	public HashMap<Integer, ArrayList<TrajetFixe>> getChauffeursTrajets() {
		return chauffeursTrajets;
	}


	public void setChauffeursTrajets(HashMap<Integer, ArrayList<TrajetFixe>> chauffeursTrajets) {
		this.chauffeursTrajets = chauffeursTrajets;
	}

	public ArrayList<Chauffeur> getChauffeurs() {return chauffeurs;}

	public void setChauffeurs(ArrayList<Chauffeur> chauffeurs){
		this.chauffeurs = chauffeurs;
	}

	public ArrayList<TrajetFixe> getTrajets() {return trajets;}

	public Chauffeur getChauffeurById(int identifiant){
		for (int i =0; i<getChauffeurs().size();i++){
			if(getChauffeurs().get(i).getIdentifiant() == identifiant)
				return getChauffeurs().get(i);
		}
		return null;
	}
	
	/*
	 * Fonction de verifiaction
	 * respect des contraintes
	 */
	public void checkerContraintes() {
		/*
		 * Reglementations
		 */

		//Dur�e maximum de conduite continue

		//Dur�e maximum de conduite journali�re
		boolean maxConduiteJournaliere = true;
		for (Entry<Integer, ArrayList<TrajetFixe>> mapentry : this.chauffeursTrajets.entrySet()) {
			LocalTime conduiteJournaliere[] = {LocalTime.of(00,00),LocalTime.of(00,00)
					,LocalTime.of(00,00),LocalTime.of(00,00),LocalTime.of(00,00),
					LocalTime.of(00,00),LocalTime.of(00,00),LocalTime.of(00,00)};
			for(int i = 0; i<mapentry.getValue().size(); i++) {
				TrajetFixe trajet = mapentry.getValue().get(i);
				LocalTime tempsDeConduite = trajet.getTempsDeConduite();
				int jour = trajet.getJourDepart().getIdentifiant();

				conduiteJournaliere[jour].plusHours(tempsDeConduite.getHour()) ;
				conduiteJournaliere[jour].plusMinutes(tempsDeConduite.getMinute()) ;
			}
			for(LocalTime nbrHeureConduite : conduiteJournaliere) {
				if(nbrHeureConduite.isAfter(plannification.getNbrConduiteJournaliereMax()) == true)
					maxConduiteJournaliere = false;
			}
		}

		//Temps de conduite hebdomadaire
		boolean maxConduiteHebdomadaire = true;
		for (Entry<Integer, ArrayList<TrajetFixe>> mapentry : this.chauffeursTrajets.entrySet()) {
			LocalTime conduiteHebdomadaire = LocalTime.MIN;
			for(int i = 0; i<mapentry.getValue().size(); i++) {
				TrajetFixe trajet = mapentry.getValue().get(i);
				LocalTime tempsDeConduite = trajet.getTempsDeConduite();

				conduiteHebdomadaire.plusHours(tempsDeConduite.getHour());
				conduiteHebdomadaire.plusMinutes(tempsDeConduite.getMinute());
			}
			if(conduiteHebdomadaire.isAfter(plannification.getNbrConduiteHebdomadaireMax()) == true)
				maxConduiteHebdomadaire = false;
		}

		//Temps de repos journalier
		boolean tempsReposJournalier = true;
		for (Entry<Integer, ArrayList<TrajetFixe>> mapentry : this.chauffeursTrajets.entrySet()) {
			LocalTime conduiteJournaliere[] = {LocalTime.of(00,00),LocalTime.of(00,00)
					,LocalTime.of(00,00),LocalTime.of(00,00),LocalTime.of(00,00),
					LocalTime.of(00,00),LocalTime.of(00,00),LocalTime.of(00,00)};
			for(int i = 0; i<mapentry.getValue().size(); i++) {
				TrajetFixe trajet = mapentry.getValue().get(i);
				LocalTime tempsDeConduite = trajet.getTempsDeConduite();
				int jour = trajet.getJourDepart().getIdentifiant();

				conduiteJournaliere[jour].plusHours(tempsDeConduite.getHour()) ;
				conduiteJournaliere[jour].plusMinutes(tempsDeConduite.getMinute()) ;
			}


			for(LocalTime nbrHeureConduite : conduiteJournaliere) {
				LocalTime localT = LocalTime.of(24,00);
				localT = localT.minusHours(nbrHeureConduite.getHour());
				localT = localT.minusMinutes(nbrHeureConduite.getMinute());

				if(localT.isAfter(plannification.getTempsDeReposJournalier()) == true)
					tempsReposJournalier = false;
			}
		}

		//Temps de repos hebdomadaire
		boolean tempsReposHebdomadaire = true;
		for (Entry<Integer, ArrayList<TrajetFixe>> mapentry : this.chauffeursTrajets.entrySet()) {
			LocalTime conduiteHebdomadaire = LocalTime.of(00,00);
			for(int i = 0; i<mapentry.getValue().size(); i++) {
				TrajetFixe trajet = mapentry.getValue().get(i);
				LocalTime tempsDeConduite = trajet.getTempsDeConduite();
				conduiteHebdomadaire.plusHours(tempsDeConduite.getHour());
				conduiteHebdomadaire.plusMinutes(tempsDeConduite.getMinute());
			}
			LocalTime localT = LocalTime.of(168, 00);
			localT = localT.minusHours(conduiteHebdomadaire.getHour());
			localT = localT.minusMinutes(conduiteHebdomadaire.getMinute());
			if(localT.isAfter(plannification.getNbrConduiteHebdomadaireMax()) == true) {
				tempsReposHebdomadaire = false;
			}
		}


		/*
		 * Op�rationelles
		 */

		//Un chauffeur peut faire un trajet � la fois
		boolean unTrajetParChauffeur = true;
		for (Entry<Integer, ArrayList<TrajetFixe>> mapentry : this.chauffeursTrajets.entrySet()) {
			for(int i = 0; i<mapentry.getValue().size(); i++) {
				TrajetFixe trajet1 = mapentry.getValue().get(i);
				int jour1 = trajet1.getJourDepart().getIdentifiant();
				LocalTime heureDepart1 = trajet1.getHeureDepart();
			for(int j = 0; j<mapentry.getValue().size(); j++) {

				if(i != j) {
					TrajetFixe trajet2 = mapentry.getValue().get(i);
					LocalTime tempsDeConduite2 = trajet2.getTempsDeConduite();
					int jour2 = trajet2.getJourDepart().getIdentifiant();
					LocalTime heureDepart2 = trajet2.getHeureDepart();

					if(jour1 == jour2) {
						LocalTime cptTime = heureDepart2.plusHours(tempsDeConduite2.getHour());
						cptTime = cptTime.plusMinutes(tempsDeConduite2.getMinute());
						if(heureDepart1.compareTo(heureDepart2) >= 0  && heureDepart1.isBefore(cptTime)) {
							unTrajetParChauffeur = false;
						}
					}
				}
			}


			}
		}

		//Un camion peut faire un trajet � la fois
		boolean unTrajetParCamion = true;
		for (Entry<Integer, ArrayList<TrajetFixe>> mapentry : this.camionsTrajets.entrySet()) {
			for(int i = 0; i<mapentry.getValue().size(); i++) {
				TrajetFixe trajet1 = mapentry.getValue().get(i);
				int jour1 = trajet1.getJourDepart().getIdentifiant();
				LocalTime heureDepart1 = trajet1.getHeureDepart();
			for(int j = 0; j<mapentry.getValue().size(); j++) {

				if(i != j) {
					TrajetFixe trajet2 = mapentry.getValue().get(i);
					LocalTime tempsDeConduite2 = trajet2.getTempsDeConduite();
					int jour2 = trajet2.getJourDepart().getIdentifiant();
					LocalTime heureDepart2 = trajet2.getHeureDepart();

					if(jour1 == jour2) {
						LocalTime cptTime = heureDepart2.plusHours(tempsDeConduite2.getHour());
						cptTime = cptTime.plusMinutes(tempsDeConduite2.getMinute());
						if(heureDepart1.compareTo(heureDepart2) >=0 && heureDepart1.isBefore(cptTime)) {
							unTrajetParChauffeur = false;
						}
					}
				}
			}


			}
		}
		//Tous les trajets camion + chauffeur
		boolean trajetCamionChauffeur = true;

		for(Trajet trajet : plannification.getTrajetsFixe()) {
			if(chauffeursTrajets.containsValue(trajet) == false) {
				trajetCamionChauffeur = false;
			}
			if(camionsTrajets.containsValue(trajet) == false) {
				trajetCamionChauffeur = false;
			}
		}

		//Repos hebdomadaire dans la ville de rattachement
		boolean reposHebdomadaireVilleRattachement = true;
		for (Entry<Integer, ArrayList<TrajetFixe>> mapentry : this.chauffeursTrajets.entrySet()) {
			Chauffeur chauffeur = getChauffeurById(mapentry.getKey());
			boolean cptBoolean = false;
			for(int i = 0; i<mapentry.getValue().size(); i++) {
				TrajetFixe trajet1 = mapentry.getValue().get(i);
				if(trajet1.getVilleArrivee() == chauffeur.getVilleRattachement())
					cptBoolean = true;
			}
			if(cptBoolean == false){
				reposHebdomadaireVilleRattachement = false;
			}

		}

		//CT respecte durée min et durée max
		boolean respectDureeCT = true;
		for(int i = 0; i<getChauffeurs().size();i++){
			ContratDeTravail contratDeTravail = getChauffeurs().get(i).getContratDeTravail();
			LocalTime cptTime = contratDeTravail.getNbrHeureJour().plusHours(contratDeTravail.getNbrHeureNuit().getHour());
			cptTime = cptTime.plusMinutes(contratDeTravail.getNbrHeureNuit().getMinute());
			if(cptTime.isBefore(plannification.getNombreHeuresMin()))
				respectDureeCT = false;
			if(cptTime.isAfter(plannification.getNombreHeuresMin()))
				respectDureeCT = false;
		}
		
		//Tous les trajets ont des pauses
		boolean trajetPause = true;
		for(int i = 0; i<getTrajets().size();i++)
		{
			if (getTrajets().get(i).getTempsDePause().isEmpty()){
				trajetPause = false;
			}
		}
		
		//Aucun trajet ne dépasse 9h de jour et 8h de nuit
		boolean tempsTrajet = true;
		for(int i = 0; i<getTrajets().size();i++)
		{
			LocalTime FtempsTrajet = getTrajets().get(i).getTempsDeConduite();
			LocalTime cptTime = getTrajets().get(i).getHeureDepart().plusHours(FtempsTrajet.getHour());
			cptTime = cptTime.plusMinutes(FtempsTrajet.getMinute());
			if (cptTime.isBefore(LocalTime.of(21,0))){
				if(FtempsTrajet.isAfter(LocalTime.of(9,0)))
					tempsTrajet = false;
			}
			else if(FtempsTrajet.isAfter(LocalTime.of(8,0)))
				tempsTrajet = false;
			}
		}

	
	/*
	 * Fonction de verifiaction
	 * respect des co�ts
	 */
	
	public void checkerCout() {
		boolean CoutHotellerie = true;
		for (Entry<Integer, ArrayList<TrajetFixe>> mapentry : this.chauffeursTrajets.entrySet()) {
			int nombreRepos = 0;
			int compteur = 1;
			while(compteur < 7){
				TrajetFixe dernierTrajet = null;
				for(int i = 0; i<mapentry.getValue().size(); i++) {
					TrajetFixe trajet = mapentry.getValue().get(i);
					Ville villeArrivee = trajet.getVilleArrivee();
					int jour = trajet.getJourDepart().getIdentifiant();
					if(jour == compteur){
						if(dernierTrajet == null)
							dernierTrajet = trajet;
						else if(trajet.getHeureDepart().isAfter(dernierTrajet.getHeureDepart()))
							dernierTrajet = trajet;
					}
				}
				if(dernierTrajet.getVilleArrivee() != getChauffeurById(mapentry.getKey()).getVilleRattachement() )
					nombreRepos += 1;
			}
			if(getChauffeurById(mapentry.getKey()).getCoutHotellerie() != nombreRepos*plannification.getCoutHotellerie())
				CoutHotellerie = false;
		}
		//Calcul cout CT
		boolean coutCT = true;
		for (Entry<Integer, ArrayList<TrajetFixe>> mapentry : this.chauffeursTrajets.entrySet()) {
			LocalTime nombreHeureJour = LocalTime.of(0,0);
			LocalTime nombreHeureNuit = LocalTime.of(0,0);
			for(int i = 0; i<mapentry.getValue().size(); i++) {
				TrajetFixe trajet = mapentry.getValue().get(i);
				LocalTime cptTime = trajet.getHeureDepart().plusHours(trajet.getTempsDeConduite().getHour());
				cptTime = cptTime.plusMinutes(trajet.getTempsDeConduite().getMinute());
				if (cptTime.isBefore(LocalTime.of(18,0))){
					nombreHeureJour.plusHours(trajet.getTempsDeConduite().getHour());
					nombreHeureJour.plusMinutes(trajet.getTempsDeConduite().getMinute());
				}
				else {
					nombreHeureNuit.plusHours(trajet.getTempsDeConduite().getHour());
					nombreHeureNuit.plusMinutes(trajet.getTempsDeConduite().getMinute());
				}
			}
			ContratDeTravail CT = getChauffeurById(mapentry.getKey()).getContratDeTravail();
			if(CT.getNbrHeureJour().compareTo(nombreHeureJour) != 0 || CT.getNbrHeureNuit().compareTo(nombreHeureNuit) != 0)
				coutCT = false;
		}

	}
}
