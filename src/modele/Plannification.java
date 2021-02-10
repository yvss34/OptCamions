package modele;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Plannification {

	/***Atttributs***/
	
	private double nombreHeuresMin;
	private double nombreHeuresMax;

	private double coutHoraireJour;
	private double coutHoraireNuit;
	
	private double coutHotellerie;
	
	private double nbrConduiteContinueMax;
	private double tempsDeReposJournalier;

	private double nbrConduiteJournaliereMax;
	private double nbrConduiteHebdomadaireMax;
	private double dureeReposHebdomadaire;
	
	private ArrayList<TrajetNonFixe> trajetsNonFixe;
	private ArrayList<TrajetFixe> trajetsFixe;


	/***Constructeurs***/
	
	public Plannification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Plannification(double nombreHeuresMin, double nombreHeuresMax, double coutHoraireJour, double coutHoraireNuit, double coutHotellerie, double nbrConduiteContinueMax, double tempsDeReposJournalier, double nbrConduiteJournaliereMax, double nbrConduiteHebdomadaireMax, double dureeReposHebdomadaire, ArrayList<TrajetNonFixe> trajetsNonFixe, ArrayList<TrajetFixe> trajetsFixe) {
		this.nombreHeuresMin = nombreHeuresMin;
		this.nombreHeuresMax = nombreHeuresMax;
		this.coutHoraireJour = coutHoraireJour;
		this.coutHoraireNuit = coutHoraireNuit;
		this.coutHotellerie = coutHotellerie;
		this.nbrConduiteContinueMax = nbrConduiteContinueMax;
		this.tempsDeReposJournalier = tempsDeReposJournalier;
		this.nbrConduiteJournaliereMax = nbrConduiteJournaliereMax;
		this.nbrConduiteHebdomadaireMax = nbrConduiteHebdomadaireMax;
		this.dureeReposHebdomadaire = dureeReposHebdomadaire;
		this.trajetsNonFixe = trajetsNonFixe;
		this.trajetsFixe = trajetsFixe;
	}

	/***Getters & Setters***/


	@Override
	public String toString() {
		return "Plannification{" +
				"nombreHeuresMin=" + nombreHeuresMin +
				", nombreHeuresMax=" + nombreHeuresMax +
				", coutHoraireJour=" + coutHoraireJour +
				", coutHoraireNuit=" + coutHoraireNuit +
				", coutHotellerie=" + coutHotellerie +
				", nbrConduiteContinueMax=" + nbrConduiteContinueMax +
				", tempsDeReposJournalier=" + tempsDeReposJournalier +
				", nbrConduiteJournaliereMax=" + nbrConduiteJournaliereMax +
				", nbrConduiteHebdomadaireMax=" + nbrConduiteHebdomadaireMax +
				", dureeReposHebdomadaire=" + dureeReposHebdomadaire +
				", trajetsNonFixe=" + trajetsNonFixe +
				", trajetsFixe=" + trajetsFixe +
				'}';
	}

	public double getNombreHeuresMin() {
		return nombreHeuresMin;
	}

	public void setNombreHeuresMin(double nombreHeuresMin) {
		this.nombreHeuresMin = nombreHeuresMin;
	}

	public double getNombreHeuresMax() {
		return nombreHeuresMax;
	}

	public void setNombreHeuresMax(double nombreHeuresMax) {
		this.nombreHeuresMax = nombreHeuresMax;
	}

	public double getCoutHoraireJour() {
		return coutHoraireJour;
	}

	public void setCoutHoraireJour(double coutHoraireJour) {
		this.coutHoraireJour = coutHoraireJour;
	}

	public double getCoutHoraireNuit() {
		return coutHoraireNuit;
	}

	public void setCoutHoraireNuit(double coutHoraireNuit) {
		this.coutHoraireNuit = coutHoraireNuit;
	}

	public double getCoutHotellerie() {
		return coutHotellerie;
	}

	public void setCoutHotellerie(double coutHotellerie) {
		this.coutHotellerie = coutHotellerie;
	}

	public double getNbrConduiteContinueMax() {
		return nbrConduiteContinueMax;
	}

	public void setNbrConduiteContinueMax(double nbrConduiteContinueMax) {
		this.nbrConduiteContinueMax = nbrConduiteContinueMax;
	}

	public double getTempsDeReposJournalier() {
		return tempsDeReposJournalier;
	}

	public void setTempsDeReposJournalier(double tempsDeReposJournalier) {
		this.tempsDeReposJournalier = tempsDeReposJournalier;
	}

	public double getNbrConduiteJournaliereMax() {
		return nbrConduiteJournaliereMax;
	}

	public void setNbrConduiteJournaliereMax(double nbrConduiteJournaliereMax) {
		this.nbrConduiteJournaliereMax = nbrConduiteJournaliereMax;
	}

	public double getNbrConduiteHebdomadaireMax() {
		return nbrConduiteHebdomadaireMax;
	}

	public void setNbrConduiteHebdomadaireMax(double nbrConduiteHebdomadaireMax) {
		this.nbrConduiteHebdomadaireMax = nbrConduiteHebdomadaireMax;
	}

	public double getDureeReposHebdomadaire() {
		return dureeReposHebdomadaire;
	}

	public void setDureeReposHebdomadaire(double dureeReposHebdomadaire) {
		this.dureeReposHebdomadaire = dureeReposHebdomadaire;
	}

	public ArrayList<TrajetNonFixe> getTrajetsNonFixe() {
		return trajetsNonFixe;
	}

	public void setTrajetsNonFixe(ArrayList<TrajetNonFixe> trajetsNonFixe) {
		this.trajetsNonFixe = trajetsNonFixe;
	}

	public ArrayList<TrajetFixe> getTrajetsFixe() {
		return trajetsFixe;
	}

	public void setTrajetsFixe(ArrayList<TrajetFixe> trajetsFixe) {
		this.trajetsFixe = trajetsFixe;
	}

	/***surcharge =***/

	public static boolean egale(Plannification obj1,Plannification obj2) {
		boolean checker = true;

		checker = (obj1.getCoutHoraireJour() == obj2.getCoutHoraireJour());
		checker = (obj1.getCoutHoraireNuit() == obj2.getCoutHoraireNuit());
		checker = (obj1.getCoutHoraireNuit() == obj2.getCoutHoraireNuit());
		checker = (obj1.nombreHeuresMin == obj2.getNombreHeuresMin());
		checker = (obj1.nombreHeuresMax == obj2.getNombreHeuresMax());
		checker = (obj1.coutHotellerie == obj2.getCoutHotellerie());
		checker = (obj1.nbrConduiteContinueMax == obj2.getNbrConduiteContinueMax());
		checker = (obj1.tempsDeReposJournalier == obj2.getTempsDeReposJournalier());
		checker = (obj1.nbrConduiteJournaliereMax == obj2.getNbrConduiteJournaliereMax());
		checker = (obj1.nbrConduiteHebdomadaireMax == obj2.getNbrConduiteHebdomadaireMax());
		checker = (obj1.dureeReposHebdomadaire == obj2.getDureeReposHebdomadaire());
		//checker = (this.trajetsNonFixe.equals(obj.getTrajetsNonFixe()));
		//checker = (this.trajetsFixe.equals(obj.getTrajetsFixe()));
		return checker;
	}

	public TrajetFixe getTrajetFixeById(int identifiant){
		for (int i =0; i<getTrajetsFixe().size();i++){
			if(getTrajetsFixe().get(i).getIdentifiant() == identifiant)
				return getTrajetsFixe().get(i);
		}

		return null;
	}

	public TrajetNonFixe getTrajetNonFixeById(int identifiant){
		for (int i =0; i<getTrajetsNonFixe().size();i++){
			if(getTrajetsNonFixe().get(i).getIdentifiant() == identifiant)
				return getTrajetsNonFixe().get(i);
		}

		return null;
	}

	/***Algorithme***/

	public void camionTrajets(){



		//Etape 1 : trier l'ensemble des trajets par date de départ possible
		ArrayList<ArrayList<Integer>> trajetTrier = new ArrayList<ArrayList<Integer>>();

		for (TrajetNonFixe trajet : trajetsNonFixe){
			trajet.liste();
			for(int i=0 ; i<trajet.getListeJourDepart().size();i++){
				for(int j=0; j<trajet.getListeJourDepart().get(j).size();j++){
					ArrayList<Integer> list = new ArrayList<Integer>();
					list.add(trajet.getIdentifiant());
					list.add(i);
					list.add(trajet.getListeJourDepart().get(i).get(j));

					trajetTrier.add(list);
				}
			}
		}

		for (TrajetFixe trajet : trajetsFixe){
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(trajet.getIdentifiant());
			list.add(-1);
			list.add(trajet.getJourDepart().getIdentifiant());

			trajetTrier.add(list);
		}

		int i,j;
		ArrayList<Integer> c = new ArrayList<Integer>();
		for(i = 1; i<=trajetTrier.size(); i++){
			for(j=0; j<trajetTrier.size()-1;j++){
				if(trajetTrier.get(i).get(2) >= trajetTrier.get(i+1).get(2)){
					if(trajetTrier.get(i).get(2) == trajetTrier.get(i+1).get(2)){
						LocalTime horaire1 = LocalTime.of(0,0);
						LocalTime horaire2= LocalTime.of(0,0);

						TrajetFixe trajet1_1 = getTrajetFixeById(trajetTrier.get(i).get(0));
						TrajetFixe trajet2_1 = getTrajetFixeById(trajetTrier.get(i+1).get(0));

						TrajetNonFixe trajet1_2 = getTrajetNonFixeById(trajetTrier.get(i).get(0));
						TrajetNonFixe trajet2_2 = getTrajetNonFixeById(trajetTrier.get(i+1).get(0));

						if(trajet1_1 != null){
							horaire1 = trajet1_1.getHeureDepart();
						}else if(trajet1_2 != null){
							String[] fenetreTempsDepart = trajet1_2.getFenetreDeTemps()[0].split(";");
							String[] tempsDepart = fenetreTempsDepart[0].split("-");
							double heureArrivee = Double.parseDouble(tempsDepart[0]);
							horaire1 = LocalTime.of((int)heureArrivee,(int)(heureArrivee-(int)heureArrivee*100));
						}

						if(trajet2_1 != null){
							horaire2 = trajet2_1.getHeureDepart();
						}else if(trajet2_2 != null){
							String[] fenetreTempsDepart = trajet2_2.getFenetreDeTemps()[0].split(";");
							String[] tempsDepart = fenetreTempsDepart[0].split("-");
							double heureArrivee = Double.parseDouble(tempsDepart[0]);
							horaire1 = LocalTime.of((int)heureArrivee,(int)(heureArrivee-(int)heureArrivee*100));
						}
						if(horaire1.isAfter(horaire2)){
							c = trajetTrier.get(i);
							trajetTrier.set(i,trajetTrier.get(i+1));
							trajetTrier.set(i+1,c);
						}
					}else{
						c = trajetTrier.get(i);
						trajetTrier.set(i,trajetTrier.get(i+1));
						trajetTrier.set(i+1,c);
					}
				}
			}
		}

		//Etape 2 : liste Symone et ajout des camions
		HashMap<Integer,ArrayList<TrajetFixe>>camionTrajet = new HashMap<Integer,ArrayList<TrajetFixe>>();
		ArrayList<TrajetFixe> trajet = new ArrayList<TrajetFixe>();
		int identifiantCamion = 1;
		int compteur = 0;
		int index=0;
		boolean premierTrajet = true;
		//Etape 3 : Retirer trajet de la liste et ajouter à un camion
		while(trajetTrier.isEmpty() == false){
			if(premierTrajet == true){
				if(getTrajetFixeById(trajetTrier.get(compteur).get(0)) != null){
					trajet.add(getTrajetFixeById(trajetTrier.get(compteur).get(0)));
					index = compteur;
					premierTrajet = false;
				}else{
					TrajetNonFixe trajetNonFixe= getTrajetNonFixeById(trajetTrier.get(compteur).get(0));
					String[] fenetreTempsDepart = trajetNonFixe.getFenetreDeTemps()[0].split(";");
					String[] tempsDepart = fenetreTempsDepart[0].split("-");
					double heureArrivee = Double.parseDouble(tempsDepart[0]);
					LocalTime horaire = LocalTime.of((int)heureArrivee,(int)(heureArrivee-(int)heureArrivee*100));

					TrajetFixe trajetAdd = new TrajetFixe(trajetNonFixe.getIdentifiant(),trajetNonFixe.getVilleDepart(),trajetNonFixe.getVilleArrivee(),trajetNonFixe.getTempsDeConduite(),trajetNonFixe.getListeVilleStop()
							,Jour.getJourById(trajetTrier.get(compteur).get(2)),horaire);

					trajet.add(trajetAdd);

					index = compteur;
					premierTrajet = false;
				}
			}
			else{

			}

			//Etape 4 : MAJ

			trajetTrier.remove(index);
			compteur++;
		}


		//Etape 5 : Tq Liste <> vide alors

			//Etape 6:Cherche trajet suivant

			//3 Cas spéciaux


	}


}
