package modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Solution {

	//Attributs
	
	private int nbrCamions;
	private int nbrChauffeurs;
	private Plannification plannification;
	
	private HashMap<Integer,ArrayList<TrajetFixe>> camionsTrajets;
	private HashMap<Integer,ArrayList<TrajetFixe>> chauffeursTrajets;
	
	
	
	//Constructeurs
	
	public Solution() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Solution(int nbrCamions, int nbrChauffeurs,Plannification plannification, HashMap<Integer, ArrayList<TrajetFixe>> camionsTrajets,
			HashMap<Integer, ArrayList<TrajetFixe>> chauffeursTrajets) {
		super();
		this.nbrCamions = nbrCamions;
		this.nbrChauffeurs = nbrChauffeurs;
		this.plannification = plannification;
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
	
	/*
	 * Fonction de verifiaction
	 * respect des contraintes
	 */
	public void checkerContraintes() {
		/*
		 * Reglementations
		 */
		
		//Durée maximum de conduite continue
		boolean maxConduiteContinue = true;
		for (Entry<Integer, ArrayList<TrajetFixe>> mapentry : this.chauffeursTrajets.entrySet()) {
			for(int i = 0; i<mapentry.getValue().size(); i++) {
				TrajetFixe trajet = mapentry.getValue().get(i);
				float tempsDeConduite = trajet.getTempsDeConduite();
				ArrayList<Float> tempsDePause = trajet.getTempsDePause();
				
				if(tempsDePause.get(0) >plannification.getNbrConduiteContinueMax())
					maxConduiteContinue = false;
				
				if(tempsDePause.get(tempsDePause.size()-1) - tempsDeConduite >plannification.getNbrConduiteContinueMax())
					maxConduiteContinue = false;
				
				for(int j = 0; j<tempsDePause.size()-1; j++) {
					if(tempsDePause.get(j+1) - tempsDePause.get(j) > plannification.getNbrConduiteContinueMax())
						maxConduiteContinue = false;
				}
				
			}
		}
		//Durée maximum de conduite journalière
		boolean maxConduiteJournaliere = true;
		for (Entry<Integer, ArrayList<TrajetFixe>> mapentry : this.chauffeursTrajets.entrySet()) {
			float conduiteJournaliere[] = {0,0,0,0,0,0,0};
			for(int i = 0; i<mapentry.getValue().size(); i++) {
				TrajetFixe trajet = mapentry.getValue().get(i);
				float tempsDeConduite = trajet.getTempsDeConduite();
				int jour = trajet.getJourDepart().getIdentifiant();
				
				conduiteJournaliere[jour] += tempsDeConduite;	
			}
			for(float nbrHeureConduite : conduiteJournaliere) {
				if(nbrHeureConduite > plannification.getNbrConduiteJournaliereMax())
					maxConduiteJournaliere = false;
			}
		}
		
		//Temps de conduite hebdomadaire
		boolean maxConduiteHebdomadaire = true;
		for (Entry<Integer, ArrayList<TrajetFixe>> mapentry : this.chauffeursTrajets.entrySet()) {
			float conduiteHebdomadaire = 0;
			for(int i = 0; i<mapentry.getValue().size(); i++) {
				TrajetFixe trajet = mapentry.getValue().get(i);
				float tempsDeConduite = trajet.getTempsDeConduite();
				conduiteHebdomadaire += tempsDeConduite;
			}
			if(conduiteHebdomadaire > plannification.getNbrConduiteHebdomadaireMax())
				maxConduiteHebdomadaire = false;
		}
		
		//Temps de repos journalier
		boolean tempsReposJournalier = true;
		for (Entry<Integer, ArrayList<TrajetFixe>> mapentry : this.chauffeursTrajets.entrySet()) {
			float conduiteJournaliere[] = {0,0,0,0,0,0,0};
			for(int i = 0; i<mapentry.getValue().size(); i++) {
				TrajetFixe trajet = mapentry.getValue().get(i);
				float tempsDeConduite = trajet.getTempsDeConduite();
				int jour = trajet.getJourDepart().getIdentifiant();
				
				conduiteJournaliere[jour] += tempsDeConduite;	
			}
			for(float nbrHeureConduite : conduiteJournaliere) {
				if(24.0-nbrHeureConduite > plannification.getTempsDeReposJournalier())
					tempsReposJournalier = false;
			}
		}	
		
		//Temps de repos hebdomadaire
		boolean tempsReposHebdomadaire = true;
		for (Entry<Integer, ArrayList<TrajetFixe>> mapentry : this.chauffeursTrajets.entrySet()) {
			float conduiteHebdomadaire = 0;
			for(int i = 0; i<mapentry.getValue().size(); i++) {
				TrajetFixe trajet = mapentry.getValue().get(i);
				float tempsDeConduite = trajet.getTempsDeConduite();
				conduiteHebdomadaire += tempsDeConduite;
			}
			if(24.0*7 - conduiteHebdomadaire > plannification.getNbrConduiteHebdomadaireMax())
				tempsReposHebdomadaire = false;
		}	
		
		
		/*
		 * Opérationelles
		 */
		
		//Un chauffeur peut faire un trajet à la fois
		boolean unTrajetParChauffeur = true;
		for (Entry<Integer, ArrayList<TrajetFixe>> mapentry : this.chauffeursTrajets.entrySet()) {
			for(int i = 0; i<mapentry.getValue().size(); i++) {
				TrajetFixe trajet1 = mapentry.getValue().get(i);
				int jour1 = trajet1.getJourDepart().getIdentifiant();
				float heureDepart1 = trajet1.getHeureDepart();
			for(int j = 0; j<mapentry.getValue().size(); j++) {
				
				if(i != j) {
					TrajetFixe trajet2 = mapentry.getValue().get(i);
					float tempsDeConduite2 = trajet2.getTempsDeConduite();
					int jour2 = trajet2.getJourDepart().getIdentifiant();
					float heureDepart2 = trajet2.getHeureDepart();
					
					if(jour1 == jour2) {
						if(heureDepart1>=heureDepart2 && heureDepart1 < heureDepart2+tempsDeConduite2) {
							unTrajetParChauffeur = false;
						}
					}
				}	
			}
					
				
			}
		}	
		
		//Un camion peut faire un trajet à la fois
		boolean unTrajetParCamion = true;
		for (Entry<Integer, ArrayList<TrajetFixe>> mapentry : this.camionsTrajets.entrySet()) {
			for(int i = 0; i<mapentry.getValue().size(); i++) {
				TrajetFixe trajet1 = mapentry.getValue().get(i);
				int jour1 = trajet1.getJourDepart().getIdentifiant();
				float heureDepart1 = trajet1.getHeureDepart();
			for(int j = 0; j<mapentry.getValue().size(); j++) {
				
				if(i != j) {
					TrajetFixe trajet2 = mapentry.getValue().get(i);
					float tempsDeConduite2 = trajet2.getTempsDeConduite();
					int jour2 = trajet2.getJourDepart().getIdentifiant();
					float heureDepart2 = trajet2.getHeureDepart();
					
					if(jour1 == jour2) {
						if(heureDepart1>=heureDepart2 && heureDepart1 < heureDepart2+tempsDeConduite2) {
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
		
		//CT respecte durée min et durée max
		
		//Tous les trajets ont des pauses
		
		//Aucun trajet ne dépasse 9h de jour et 8h de nuit
	}
	
	/*
	 * Fonction de verifiaction
	 * respect des coûts
	 */
	
	public void checkerCout() {
		//Calcul couts d'hotelleries
		
		//Calcul cout CT
	}
}
