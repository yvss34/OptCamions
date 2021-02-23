package modele;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

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

	/****************************************************Algorithme************************************************************************/

	/**
	 * Retourne une liste de trajets composés de
	 * position 0 : l'identifiant du trajet
	 * position 1 : le numero de frequence du trajet
	 * position 2 : le jour de la semaine du trajet
	 * @return La liste de trajets respectant le standard (id,num frequence,jour)
	 */
	public ArrayList<ArrayList<Integer>> listeTrajetsStandard(){
		ArrayList<ArrayList<Integer>> trajetTrier = new ArrayList<ArrayList<Integer>>();

		for (TrajetNonFixe trajet : trajetsNonFixe){
			trajet.liste();
			for(int i=0 ; i<trajet.getListeJourDepart().size();i++){
				for(int j=0; j<trajet.getListeJourDepart().get(i).size();j++){
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

		return trajetTrier;
	}

	/**
	 * @param trajets, la liste de trajets respectant le standard (id,num frequence,jour)
	 * Tri la liste de trajets en fonction du jour de la semaine
	 */
	public void triTrajetsStandard(ArrayList<ArrayList<Integer>> trajets){
		int i,j;
		ArrayList<Integer> c = new ArrayList<Integer>();
		for(i = 0; i<trajets.size(); i++){
			for(j=0; j<trajets.size();j++){
				if(trajets.get(i).get(2) <= trajets.get(j).get(2)){
					if(trajets.get(i).get(2) == trajets.get(j).get(2)){
						if(trajets.get(i).get(0) == trajets.get(j).get(0)){
							if(trajets.get(i).get(1) < trajets.get(j).get(1)){
								c = trajets.get(i);
								trajets.set(i, trajets.get(j));
								trajets.set(j, c);
							}
						}else {
							LocalTime horaire1 = LocalTime.of(0, 0);
							LocalTime horaire2 = LocalTime.of(0, 0);

							TrajetFixe trajet1_1 = getTrajetFixeById(trajets.get(i).get(0));
							TrajetFixe trajet2_1 = getTrajetFixeById(trajets.get(j).get(0));

							TrajetNonFixe trajet1_2 = getTrajetNonFixeById(trajets.get(i).get(0));
							TrajetNonFixe trajet2_2 = getTrajetNonFixeById(trajets.get(j).get(0));

							if (trajet1_1 != null) {
								horaire1 = trajet1_1.getHeureDepart();
							} else if (trajet1_2 != null) {
								String[] fenetreTempsDepart = trajet1_2.getFenetreDeTemps()[0].split(";");
								String[] tempsDepart = fenetreTempsDepart[0].split("-");
								double heureArrivee = Double.parseDouble(tempsDepart[0]);
								horaire1 = LocalTime.of((int) heureArrivee, (int) ((heureArrivee - (int) heureArrivee) * 100));
							}

							if (trajet2_1 != null) {
								horaire2 = trajet2_1.getHeureDepart();
							} else if (trajet2_2 != null) {
								String[] fenetreTempsDepart = trajet2_2.getFenetreDeTemps()[0].split(";");
								String[] tempsDepart = fenetreTempsDepart[0].split("-");
								double heureArrivee = Double.parseDouble(tempsDepart[0]);
								horaire2 = LocalTime.of((int) heureArrivee, (int) ((heureArrivee - (int) heureArrivee) * 100));
							}
							if (horaire1.isBefore(horaire2)) {
								c = trajets.get(i);
								trajets.set(i, trajets.get(j));
								trajets.set(j, c);
							}

						}
					}else{
						c = trajets.get(i);
						trajets.set(i,trajets.get(j));
						trajets.set(j,c);
					}
				}
			}
		}
	}

	/**
	 *
	 * @param index, position du dernier trajet ajouté à la liste
	 * @param trajetTrier, la liste de trajets respectant le standard (id,num frequence,jour) trié
	 * Met a jour les trajets connectés du dernier trajet ajouté
	 */
	public void miseAJour(int index,ArrayList<ArrayList<Integer>> trajetTrier){
		ArrayList<Integer> indexTrajet = trajetTrier.get(index);
		ArrayList<ArrayList<Integer>> tableauASupprimer = new ArrayList<ArrayList<Integer>>();

		ArrayList<Integer> MAJ = new ArrayList<Integer>();
		for (ArrayList<Integer> MAJTrajet : trajetTrier){
			if (indexTrajet.get(1) == MAJTrajet.get(1) && indexTrajet.get(0) == MAJTrajet.get(0) && indexTrajet.get(2) == MAJTrajet.get(2)) {
				tableauASupprimer.add(MAJTrajet);
			}
			else if (indexTrajet.get(1) == MAJTrajet.get(1) && indexTrajet.get(0) == MAJTrajet.get(0)&& indexTrajet.get(2) != MAJTrajet.get(2)) {
				tableauASupprimer.add(MAJTrajet);
			}
			else if(indexTrajet.get(1) < MAJTrajet.get(1) && indexTrajet.get(0) == MAJTrajet.get(0)){
				TrajetNonFixe trajetNonFixe = getTrajetNonFixeById(MAJTrajet.get(0));
				if(MAJTrajet.get(2) <= indexTrajet.get(2)+trajetNonFixe.getNbrJourMin()){
					tableauASupprimer.add(MAJTrajet);
				}
			}
			else if(indexTrajet.get(1) > MAJTrajet.get(1) && indexTrajet.get(0) == MAJTrajet.get(0)){
				TrajetNonFixe trajetNonFixe = getTrajetNonFixeById(MAJTrajet.get(0));
				if(MAJTrajet.get(2) >= indexTrajet.get(2)-trajetNonFixe.getNbrJourMin()){
					tableauASupprimer.add(MAJTrajet);
				}
			}
		}
		for (ArrayList<Integer> trajet : tableauASupprimer) {
			trajetTrier.remove(trajet);
		}
	}

	/**
	 * Affectation Camions-Trajets
	 */
	public HashMap<Integer,ArrayList<Integer>> camionTrajets(ArrayList<TrajetFixe> trajets){

		//Etape 1 : trier l'ensemble des trajets par date de départ possible
		ArrayList<ArrayList<Integer>> trajetTrier = this.listeTrajetsStandard();
		triTrajetsStandard(trajetTrier);

		//Etape 2 : liste Symone et ajout des camions
		HashMap<Integer,ArrayList<Integer>> camionTrajet = new HashMap<Integer,ArrayList<Integer>>();
		ArrayList<TrajetFixe> trajet = new ArrayList<TrajetFixe>();
		ArrayList<Integer> idTrajet = new ArrayList<Integer>();
		int identifiantCamion = 1;
		int compteur = 0;
		int index=0;
		boolean premierTrajet = true;
		Ville premiereVille = null;
		Jour jourDepart = null;
		TrajetFixe trajetActuelle = new TrajetFixe();
		int nbrTrajetNonFixe = 100;
		int nbrTrajetAVide = 200;
		boolean put = false;
		//Etape 3 : Retirer trajet de la liste et ajouter à un camion
		while(trajetTrier.isEmpty() == false){
			put = false;
			if(premierTrajet == true){
				if(getTrajetFixeById(trajetTrier.get(compteur).get(0)) != null){
					TrajetFixe trajetFixe = getTrajetFixeById(trajetTrier.get(compteur).get(0));
					trajet.add(trajetFixe);
					idTrajet.add(trajetFixe.getIdentifiant());
					index = compteur;
					premierTrajet = false;
					trajetActuelle = getTrajetFixeById(trajetTrier.get(compteur).get(0));
					premiereVille = trajetActuelle.getVilleDepart();
					jourDepart = trajetActuelle.getJourDepart();
				}else{
					TrajetNonFixe trajetNonFixe= getTrajetNonFixeById(trajetTrier.get(compteur).get(0));
					String[] fenetreTempsDepart = trajetNonFixe.getFenetreDeTemps()[0].split(";");
					String[] tempsDepart = fenetreTempsDepart[0].split("-");
					double heureArrivee = Double.parseDouble(tempsDepart[0]);
					LocalTime horaire = LocalTime.of((int)heureArrivee,(int)(heureArrivee-(int)heureArrivee)*100);

					TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetNonFixe,trajetNonFixe.getVilleDepart(),trajetNonFixe.getVilleArrivee(),trajetNonFixe.getTempsDeConduite(),trajetNonFixe.getListeVilleStop()
							,Jour.getJourById(trajetTrier.get(compteur).get(2)),horaire);

					trajet.add(trajetAdd);
					idTrajet.add(trajetAdd.getIdentifiant());

					index = compteur;
					premierTrajet = false;
					trajetActuelle = trajetAdd;
					premiereVille = trajetActuelle.getVilleDepart();
					jourDepart = trajetActuelle.getJourDepart();
					nbrTrajetNonFixe++;
				}
				miseAJour(index,trajetTrier);
			}
			else{

				boolean ajoutTrajet = false;
				while(compteur<trajetTrier.size() && ajoutTrajet == false){
					ajoutTrajet = false;
					TrajetFixe trajetFixe = getTrajetFixeById(trajetTrier.get(compteur).get(0));
					TrajetNonFixe trajetNonFixe = getTrajetNonFixeById(trajetTrier.get(compteur).get(0));
					if(trajetFixe != null){
						if(trajetFixe.getVilleDepart().getIdentifiant() == trajetActuelle.getVilleArrivee().getIdentifiant()){
							if(trajetFixe.getJourDepart().getIdentifiant() == trajetActuelle.getJourDepart().getIdentifiant()){
								LocalTime heureArrivee = trajetActuelle.getHeureDepart();
								heureArrivee.plusHours((int)trajetActuelle.getTempsDeConduite());
								heureArrivee.plusMinutes((int)(trajetActuelle.getTempsDeConduite()-(int)trajetActuelle.getTempsDeConduite())*100);
								if(trajetFixe.getHeureDepart().isAfter(heureArrivee)){
									if(trajetFixe.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant() || trajetFixe.getJourDepart().getIdentifiant() < jourDepart.getIdentifiant()+6){
										trajet.add(trajetFixe);
										idTrajet.add(trajetFixe.getIdentifiant());
										index = compteur;
										ajoutTrajet = true;
										trajetActuelle = trajetFixe;
									}
								}
							}else if(trajetFixe.getJourDepart().getIdentifiant() > trajetActuelle.getJourDepart().getIdentifiant()){
								if(trajetFixe.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant() || trajetFixe.getJourDepart().getIdentifiant() < jourDepart.getIdentifiant()+6){
									trajet.add(trajetFixe);
									idTrajet.add(trajetFixe.getIdentifiant());
									index = compteur;
									ajoutTrajet = true;
									trajetActuelle = trajetFixe;
								}
							}
						}
					}
					else{
							if(trajetNonFixe.getVilleDepart().getIdentifiant() == trajetActuelle.getVilleArrivee().getIdentifiant()){
								if(trajetTrier.get(compteur).get(2) == trajetActuelle.getJourDepart().getIdentifiant()){
									String[] fenetreTempsDepart = trajetNonFixe.getFenetreDeTemps()[0].split(";");
									String[] tempsDepart = fenetreTempsDepart[0].split("-");
									double heureDebut = Double.parseDouble(tempsDepart[0]);
									double heureFin = Double.parseDouble(tempsDepart[1]);
									LocalTime horaireDebut = LocalTime.of((int) heureDebut, (int) (heureDebut - (int) heureDebut )* 100);
									LocalTime horaireFin = LocalTime.of((int) heureFin, (int) (heureFin - (int) heureFin )* 100);

									LocalTime heureArrivee = trajetActuelle.getHeureDepart();
									heureArrivee.plusHours((int)trajetActuelle.getTempsDeConduite());
									heureArrivee.plusMinutes((int)(trajetActuelle.getTempsDeConduite()-(int)trajetActuelle.getTempsDeConduite())*100);

									if(heureArrivee.isBefore(horaireDebut)){
										if(trajetNonFixe.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant() || trajetTrier.get(compteur).get(2) < jourDepart.getIdentifiant()+6) {
											TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetNonFixe, trajetNonFixe.getVilleDepart(), trajetNonFixe.getVilleArrivee(), trajetNonFixe.getTempsDeConduite(), trajetNonFixe.getListeVilleStop()
													, Jour.getJourById(trajetTrier.get(compteur).get(2)), horaireDebut);
											trajet.add(trajetAdd);
											idTrajet.add(trajetAdd.getIdentifiant());
											index = compteur;
											ajoutTrajet = true;
											trajetActuelle = trajetAdd;
											nbrTrajetNonFixe++;
										}
									}
									else if(heureArrivee.isBefore(horaireFin)){
										if(trajetNonFixe.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant() || trajetTrier.get(compteur).get(2) < jourDepart.getIdentifiant()+6) {
											TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetNonFixe, trajetNonFixe.getVilleDepart(), trajetNonFixe.getVilleArrivee(), trajetNonFixe.getTempsDeConduite(), trajetNonFixe.getListeVilleStop()
													, Jour.getJourById(trajetTrier.get(compteur).get(2)), horaireFin);
											trajet.add(trajetAdd);
											idTrajet.add(trajetAdd.getIdentifiant());
											index = compteur;
											ajoutTrajet = true;
											trajetActuelle = trajetAdd;
											nbrTrajetNonFixe++;
										}
									}
								}else if(trajetTrier.get(compteur).get(2) > trajetActuelle.getJourDepart().getIdentifiant()){
									if(trajetNonFixe.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant() || trajetTrier.get(compteur).get(2) < jourDepart.getIdentifiant()+6) {
										String[] fenetreTempsDepart = trajetNonFixe.getFenetreDeTemps()[0].split(";");
										String[] tempsDepart = fenetreTempsDepart[0].split("-");
										double heureDebut = Double.parseDouble(tempsDepart[0]);
										double heureFin = Double.parseDouble(tempsDepart[1]);
										LocalTime horaireDebut = LocalTime.of((int) heureDebut, (int) (heureDebut - (int) heureDebut )* 100);
										TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetNonFixe, trajetNonFixe.getVilleDepart(), trajetNonFixe.getVilleArrivee(), trajetNonFixe.getTempsDeConduite(), trajetNonFixe.getListeVilleStop()
												, Jour.getJourById(trajetTrier.get(compteur).get(2)), horaireDebut);
										trajet.add(trajetAdd);
										idTrajet.add(trajetAdd.getIdentifiant());
										index = compteur;
										ajoutTrajet = true;
										trajetActuelle = trajetAdd;
										nbrTrajetNonFixe++;
									}
								}
							}
					}
					compteur++;
				}
				if(ajoutTrajet == true){
					if(trajetActuelle.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant()){
						if(trajetActuelle.getJourDepart().getIdentifiant()>=4){
							camionTrajet.put(identifiantCamion,idTrajet);
							identifiantCamion++;
							idTrajet = new ArrayList<Integer>();
							compteur=0;
							put = true;
							premierTrajet = true;
						}
					}
					miseAJour(index,trajetTrier);
				}else{
					if(trajetActuelle.getVilleArrivee().getIdentifiant() != premiereVille.getIdentifiant()){
						LocalTime heureArrivee = trajetActuelle.getHeureDepart();
						heureArrivee.plusHours((int)trajetActuelle.getTempsDeConduite());
						heureArrivee.plusMinutes((int)(trajetActuelle.getTempsDeConduite()-(int)trajetActuelle.getTempsDeConduite())*100);
						TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetAVide, trajetActuelle.getVilleArrivee(), premiereVille, 9, null
								, trajetActuelle.getJourDepart(), heureArrivee);

						trajet.add(trajetAdd);
						idTrajet.add(trajetAdd.getIdentifiant());
						nbrTrajetAVide++;
					}
					camionTrajet.put(identifiantCamion,idTrajet);
					identifiantCamion++;
					idTrajet = new ArrayList<Integer>();
					compteur=  0;
					put = true;
					premierTrajet = true;
				}
			}
		}
		if(put == false){
			if(trajetActuelle.getVilleArrivee().getIdentifiant() != premiereVille.getIdentifiant()){
				LocalTime heureArrivee = trajetActuelle.getHeureDepart();
				heureArrivee.plusHours((int)trajetActuelle.getTempsDeConduite());
				heureArrivee.plusMinutes((int)(trajetActuelle.getTempsDeConduite()-(int)trajetActuelle.getTempsDeConduite())*100);
				TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetAVide, trajetActuelle.getVilleArrivee(), premiereVille, 9, null
						, trajetActuelle.getJourDepart(), heureArrivee);

				trajet.add(trajetAdd);
				idTrajet.add(trajetAdd.getIdentifiant());
				nbrTrajetAVide++;
			}
			camionTrajet.put(identifiantCamion,idTrajet);
			identifiantCamion++;
			idTrajet = new ArrayList<Integer>();
			compteur=  0;
			put = true;
		}

		trajets = trajet;
		return camionTrajet;
	}

	/**
	 * Affectation Camions-Trajets
	 */
	public ArrayList<HashMap<Integer,ArrayList<Integer>>> camionTrajetsAleatoire(int nombreRepetition) {



		//Etape 2 : liste Symone et ajout des camions
		ArrayList<HashMap<Integer, ArrayList<Integer>>> listeCamionsTrajets = new ArrayList<HashMap<Integer, ArrayList<Integer>>>();
		int occurence = 0;
		int nombreCamionMin = 100;

		while (occurence < nombreRepetition) {

			//Etape 1 : trier l'ensemble des trajets par date de départ possible
			ArrayList<ArrayList<Integer>> trajetTrier = this.listeTrajetsStandard();
			triTrajetsStandard(trajetTrier);


			HashMap<Integer, ArrayList<Integer>> camionTrajet = new HashMap<Integer, ArrayList<Integer>>();
			ArrayList<TrajetFixe> trajet = new ArrayList<TrajetFixe>();
			ArrayList<Integer> idTrajet = new ArrayList<Integer>();
			int identifiantCamion = 1;
			int compteur = 0;
			int index = 0;

			boolean premierTrajet = true;
			Ville premiereVille = null;
			Jour jourDepart = null;
			TrajetFixe trajetActuelle = new TrajetFixe();
			int nbrTrajetNonFixe = 100;
			int nbrTrajetAVide = 200;
			boolean put = false;
			//Etape 3 : Retirer trajet de la liste et ajouter à un camion
			while (trajetTrier.isEmpty() == false) {
				put = false;
				if (premierTrajet == true) {
					do {
						Random r = new Random();
						compteur = r.nextInt(3);
					} while (compteur >= trajetTrier.size());

					if (getTrajetFixeById(trajetTrier.get(compteur).get(0)) != null) {
						TrajetFixe trajetFixe = getTrajetFixeById(trajetTrier.get(compteur).get(0));
						trajet.add(trajetFixe);
						idTrajet.add(trajetFixe.getIdentifiant());
						index = compteur;
						premierTrajet = false;
						trajetActuelle = getTrajetFixeById(trajetTrier.get(compteur).get(0));
						premiereVille = trajetActuelle.getVilleDepart();
						jourDepart = trajetActuelle.getJourDepart();
					} else {
						TrajetNonFixe trajetNonFixe = getTrajetNonFixeById(trajetTrier.get(compteur).get(0));
						String[] fenetreTempsDepart = trajetNonFixe.getFenetreDeTemps()[0].split(";");
						String[] tempsDepart = fenetreTempsDepart[0].split("-");
						double heureArrivee = Double.parseDouble(tempsDepart[0]);
						LocalTime horaire = LocalTime.of((int) heureArrivee, (int) (heureArrivee - (int) heureArrivee) * 100);

						TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetNonFixe, trajetNonFixe.getVilleDepart(), trajetNonFixe.getVilleArrivee(), trajetNonFixe.getTempsDeConduite(), trajetNonFixe.getListeVilleStop()
								, Jour.getJourById(trajetTrier.get(compteur).get(2)), horaire);

						trajet.add(trajetAdd);
						idTrajet.add(trajetAdd.getIdentifiant());

						index = compteur;
						premierTrajet = false;
						trajetActuelle = trajetAdd;
						premiereVille = trajetActuelle.getVilleDepart();
						jourDepart = trajetActuelle.getJourDepart();
						nbrTrajetNonFixe++;
					}
					miseAJour(index, trajetTrier);
					compteur = 0;
				} else {

					boolean ajoutTrajet = false;
					ArrayList<TrajetFixe> trajetAjout = new ArrayList<TrajetFixe>();
					int nombreAjout = 0;
					while (compteur < trajetTrier.size() && nombreAjout < 2) {
						ajoutTrajet = false;
						TrajetFixe trajetFixe = getTrajetFixeById(trajetTrier.get(compteur).get(0));
						TrajetNonFixe trajetNonFixe = getTrajetNonFixeById(trajetTrier.get(compteur).get(0));
						if (trajetFixe != null) {
							if (trajetFixe.getVilleDepart().getIdentifiant() == trajetActuelle.getVilleArrivee().getIdentifiant()) {
								if (trajetFixe.getJourDepart().getIdentifiant() == trajetActuelle.getJourDepart().getIdentifiant()) {
									LocalTime heureArrivee = trajetActuelle.getHeureDepart();
									heureArrivee.plusHours((int) trajetActuelle.getTempsDeConduite());
									heureArrivee.plusMinutes((int) (trajetActuelle.getTempsDeConduite() - (int) trajetActuelle.getTempsDeConduite()) * 100);
									if (trajetFixe.getHeureDepart().isAfter(heureArrivee)) {
										if (trajetFixe.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant() || trajetFixe.getJourDepart().getIdentifiant() < jourDepart.getIdentifiant() + 6) {
											trajetAjout.add(trajetFixe);
											nombreAjout++;
										}
									}
								} else if (trajetFixe.getJourDepart().getIdentifiant() > trajetActuelle.getJourDepart().getIdentifiant()) {
									if (trajetFixe.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant() || trajetFixe.getJourDepart().getIdentifiant() < jourDepart.getIdentifiant() + 6) {
										trajetAjout.add(trajetFixe);
										nombreAjout++;
									}
								}
							}
						} else {
							if (trajetNonFixe.getVilleDepart().getIdentifiant() == trajetActuelle.getVilleArrivee().getIdentifiant()) {
								if (trajetTrier.get(compteur).get(2) == trajetActuelle.getJourDepart().getIdentifiant()) {
									String[] fenetreTempsDepart = trajetNonFixe.getFenetreDeTemps()[0].split(";");
									String[] tempsDepart = fenetreTempsDepart[0].split("-");
									double heureDebut = Double.parseDouble(tempsDepart[0]);
									double heureFin = Double.parseDouble(tempsDepart[1]);
									LocalTime horaireDebut = LocalTime.of((int) heureDebut, (int) (heureDebut - (int) heureDebut) * 100);
									LocalTime horaireFin = LocalTime.of((int) heureFin, (int) (heureFin - (int) heureFin) * 100);

									LocalTime heureArrivee = trajetActuelle.getHeureDepart();
									heureArrivee.plusHours((int) trajetActuelle.getTempsDeConduite());
									heureArrivee.plusMinutes((int) (trajetActuelle.getTempsDeConduite() - (int) trajetActuelle.getTempsDeConduite()) * 100);

									if (heureArrivee.isBefore(horaireDebut)) {
										if (trajetNonFixe.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant() || trajetTrier.get(compteur).get(2) < jourDepart.getIdentifiant() + 6) {
											TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetNonFixe, trajetNonFixe.getVilleDepart(), trajetNonFixe.getVilleArrivee(), trajetNonFixe.getTempsDeConduite(), trajetNonFixe.getListeVilleStop()
													, Jour.getJourById(trajetTrier.get(compteur).get(2)), horaireDebut);
											trajetAjout.add(trajetAdd);
											nombreAjout++;
											nbrTrajetNonFixe++;
										}
									} else if (heureArrivee.isBefore(horaireFin)) {
										if (trajetNonFixe.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant() || trajetTrier.get(compteur).get(2) < jourDepart.getIdentifiant() + 6) {
											TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetNonFixe, trajetNonFixe.getVilleDepart(), trajetNonFixe.getVilleArrivee(), trajetNonFixe.getTempsDeConduite(), trajetNonFixe.getListeVilleStop()
													, Jour.getJourById(trajetTrier.get(compteur).get(2)), horaireFin);
											trajetAjout.add(trajetAdd);
											nombreAjout++;
											nbrTrajetNonFixe++;
										}
									}
								} else if (trajetTrier.get(compteur).get(2) > trajetActuelle.getJourDepart().getIdentifiant()) {
									if (trajetNonFixe.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant() || trajetTrier.get(compteur).get(2) < jourDepart.getIdentifiant() + 6) {
										String[] fenetreTempsDepart = trajetNonFixe.getFenetreDeTemps()[0].split(";");
										String[] tempsDepart = fenetreTempsDepart[0].split("-");
										double heureDebut = Double.parseDouble(tempsDepart[0]);
										double heureFin = Double.parseDouble(tempsDepart[1]);
										LocalTime horaireDebut = LocalTime.of((int) heureDebut, (int) (heureDebut - (int) heureDebut) * 100);
										TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetNonFixe, trajetNonFixe.getVilleDepart(), trajetNonFixe.getVilleArrivee(), trajetNonFixe.getTempsDeConduite(), trajetNonFixe.getListeVilleStop()
												, Jour.getJourById(trajetTrier.get(compteur).get(2)), horaireDebut);
										trajetAjout.add(trajetFixe);
										nombreAjout++;
										nbrTrajetNonFixe++;
									}
								}
							}
						}
						compteur++;
					}
					if (nombreAjout > 0) {
						Random r = new Random();
						int nombreAleatoire = r.nextInt(nombreAjout-1);

						trajet.add(trajetAjout.get(nombreAleatoire));
						idTrajet.add(trajetAjout.get(nombreAleatoire).getIdentifiant());
						index = compteur;
						ajoutTrajet = true;
						trajetActuelle = trajetAjout.get(nombreAleatoire);

						if (trajetActuelle.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant()) {
							if (trajetActuelle.getJourDepart().getIdentifiant() >= 4) {
								camionTrajet.put(identifiantCamion, idTrajet);
								identifiantCamion++;
								idTrajet = new ArrayList<Integer>();
								compteur = 0;
								put = true;
								premierTrajet = true;
							}
						}
						miseAJour(index, trajetTrier);
					} else {
						if (trajetActuelle.getVilleArrivee().getIdentifiant() != premiereVille.getIdentifiant()) {
							LocalTime heureArrivee = trajetActuelle.getHeureDepart();
							heureArrivee.plusHours((int) trajetActuelle.getTempsDeConduite());
							heureArrivee.plusMinutes((int) (trajetActuelle.getTempsDeConduite() - (int) trajetActuelle.getTempsDeConduite()) * 100);
							TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetAVide, trajetActuelle.getVilleArrivee(), premiereVille, 9, null
									, trajetActuelle.getJourDepart(), heureArrivee);

							trajet.add(trajetAdd);
							idTrajet.add(trajetAdd.getIdentifiant());
							nbrTrajetAVide++;
						}
						camionTrajet.put(identifiantCamion, idTrajet);
						identifiantCamion++;
						idTrajet = new ArrayList<Integer>();
						compteur = 0;
						put = true;
						premierTrajet = true;
					}
				}
			}
			if (put == false) {
				if (trajetActuelle.getVilleArrivee().getIdentifiant() != premiereVille.getIdentifiant()) {
					LocalTime heureArrivee = trajetActuelle.getHeureDepart();
					heureArrivee.plusHours((int) trajetActuelle.getTempsDeConduite());
					heureArrivee.plusMinutes((int) (trajetActuelle.getTempsDeConduite() - (int) trajetActuelle.getTempsDeConduite()) * 100);
					TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetAVide, trajetActuelle.getVilleArrivee(), premiereVille, 9, null
							, trajetActuelle.getJourDepart(), heureArrivee);

					trajet.add(trajetAdd);
					idTrajet.add(trajetAdd.getIdentifiant());
					nbrTrajetAVide++;
				}
				camionTrajet.put(identifiantCamion, idTrajet);
				identifiantCamion++;
			}
			if(identifiantCamion == nombreCamionMin){
				listeCamionsTrajets.add(camionTrajet);
			}else if(identifiantCamion < nombreCamionMin){
				listeCamionsTrajets.clear();
				listeCamionsTrajets.add(camionTrajet);
				nombreCamionMin = identifiantCamion;
			}
			occurence++;
		}

		return listeCamionsTrajets;
	}

}
