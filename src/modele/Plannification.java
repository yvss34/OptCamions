package modele;

import java.time.LocalTime;
import java.util.ArrayList;

public class Plannification {
	
	//Attributs
	
	private LocalTime nombreHeuresMin;
	private LocalTime nombreHeuresMax;
	private float coutHoraireJour;
	private float coutHoraireNuit;
	
	private float coutHotellerie;
	
	private LocalTime nbrConduiteContinueMax;
	private LocalTime tempsDeReposJournalier;
	private LocalTime nbrConduiteJournaliereMax;
	private LocalTime nbrConduiteHebdomadaireMax;
	private LocalTime dureeReposHebdomadaire;
	
	private ArrayList<TrajetNonFixe> trajetsNonFixe;
	private ArrayList<TrajetFixe> trajetsFixe;
	
	
	//Constructeurs
	
	public Plannification() {
		super();
		// TODO Auto-generated constructor stub
	}


//Getters & Setters


	public LocalTime getNombreHeuresMin() {
		return nombreHeuresMin;
	}

	public void setNombreHeuresMin(LocalTime nombreHeuresMin) {
		this.nombreHeuresMin = nombreHeuresMin;
	}

	public LocalTime getNombreHeuresMax() {
		return nombreHeuresMax;
	}

	public void setNombreHeuresMax(LocalTime nombreHeuresMax) {
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

	public LocalTime getNbrConduiteContinueMax() {
		return nbrConduiteContinueMax;
	}

	public void setNbrConduiteContinueMax(LocalTime nbrConduiteContinueMax) {
		this.nbrConduiteContinueMax = nbrConduiteContinueMax;
	}

	public LocalTime getTempsDeReposJournalier() {
		return tempsDeReposJournalier;
	}

	public void setTempsDeReposJournalier(LocalTime tempsDeReposJournalier) {
		this.tempsDeReposJournalier = tempsDeReposJournalier;
	}

	public LocalTime getNbrConduiteJournaliereMax() {
		return nbrConduiteJournaliereMax;
	}

	public void setNbrConduiteJournaliereMax(LocalTime nbrConduiteJournaliereMax) {
		this.nbrConduiteJournaliereMax = nbrConduiteJournaliereMax;
	}

	public LocalTime getNbrConduiteHebdomadaireMax() {
		return nbrConduiteHebdomadaireMax;
	}

	public void setNbrConduiteHebdomadaireMax(LocalTime nbrConduiteHebdomadaireMax) {
		this.nbrConduiteHebdomadaireMax = nbrConduiteHebdomadaireMax;
	}

	public LocalTime getDureeReposHebdomadaire() {
		return dureeReposHebdomadaire;
	}

	public void setDureeReposHebdomadaire(LocalTime dureeReposHebdomadaire) {
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
