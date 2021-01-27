package modele;

import java.time.LocalTime;
import java.util.ArrayList;

public class Plannification {
	
	//Attributs
	
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
	
	
	//Constructeurs
	
	public Plannification() {
		super();
		// TODO Auto-generated constructor stub
	}


//Getters & Setters


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
}
