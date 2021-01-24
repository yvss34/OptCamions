package modele;

import java.util.ArrayList;

public class Plannification {
	
	//Attributs
	
	private float nombreHeuresMin;
	private float nombreHeuresMax;
	private float coutHoraireJour;
	private float coutHoraireNuit;
	
	private float coutHotellerie;
	
	private float nbrConduiteContinueMax;
	private float tempsDeReposJournalier;
	private float nbrConduiteJournaliereMax;
	private float nbrConduiteHebdomadaireMax;
	private float dureeReposHebdomadaire;
	
	private ArrayList<TrajetNonFixe> trajetsNonFixe;
	private ArrayList<TrajetFixe> trajetsFixe;
	
	
	//Constructeurs
	
	public Plannification() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Plannification(float nombreHeuresMin, float nombreHeuresMax, float coutHoraireJour, float coutHoraireNuit,
			float coutHotellerie, float nbrConduiteContinueMax, float tempsDeReposJournalier,
			float nbrConduiteJournaliereMax, float nbrConduiteHebdomadaireMax, float dureeReposHebdomadaire,
			ArrayList<TrajetNonFixe> trajetsNonFixe, ArrayList<TrajetFixe> trajetsFixe) {
		super();
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



	//Getters & Setters
	
	public float getNombreHeuresMin() {
		return nombreHeuresMin;
	}

	public void setNombreHeuresMin(float nombreHeuresMin) {
		this.nombreHeuresMin = nombreHeuresMin;
	}

	public float getNombreHeuresMax() {
		return nombreHeuresMax;
	}

	public void setNombreHeuresMax(float nombreHeuresMax) {
		this.nombreHeuresMax = nombreHeuresMax;
	}

	public float getCoutHoraireJour() {
		return coutHoraireJour;
	}

	public void setCoutHoraireJour(float coutHoraireJour) {
		this.coutHoraireJour = coutHoraireJour;
	}

	public float getCoutHoraireNuit() {
		return coutHoraireNuit;
	}

	public void setCoutHoraireNuit(float coutHoraireNuit) {
		this.coutHoraireNuit = coutHoraireNuit;
	}

	public float getCoutHotellerie() {
		return coutHotellerie;
	}

	public void setCoutHotellerie(float coutHotellerie) {
		this.coutHotellerie = coutHotellerie;
	}

	public float getNbrConduiteContinueMax() {
		return nbrConduiteContinueMax;
	}

	public void setNbrConduiteContinueMax(float nombreHeuresConduiteMax) {
		this.nbrConduiteContinueMax = nombreHeuresConduiteMax;
	}

	public float getTempsDeReposJournalier() {
		return tempsDeReposJournalier;
	}

	public void setTempsDeReposJournalier(float tempsDeReposJournalier) {
		this.tempsDeReposJournalier = tempsDeReposJournalier;
	}

	public float getNbrConduiteJournaliereMax() {
		return nbrConduiteJournaliereMax;
	}

	public void setNbrConduiteJournaliereMax(float amplitudeMaxParJour) {
		this.nbrConduiteJournaliereMax = amplitudeMaxParJour;
	}

	public float getDureeReposHebdomadaire() {
		return dureeReposHebdomadaire;
	}

	public void setDureeReposHebdomadaire(float dureeReposHebdomadaire) {
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



	public float getNbrConduiteHebdomadaireMax() {
		return nbrConduiteHebdomadaireMax;
	}



	public void setNbrConduiteHebdomadaireMax(float nbrConduiteHebdomadaireMax) {
		this.nbrConduiteHebdomadaireMax = nbrConduiteHebdomadaireMax;
	}
	
	
	
	
}
