package modele;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 * Classe representant une liste de trajets à plannifier
 */
public class Plannification {

	/***Atttributes***/
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

	private final int dureeTempsDePause = 45;

	private ArrayList<TrajetNonFixe> trajetsNonFixe;
	private ArrayList<TrajetFixe> trajetsFixe;


	/***Constructors***/
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

	/**
	 * Verifie que deux pbjets plannifications sont égaux
	 * @param obj1 le premier objet Plannification
	 * @param obj2 le second objet Plannification
	 * @return true si deux plannifications sont égales, faux sinon
	 */
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

	/**
	 * Retourne le TrajetFixe, dans la liste de Trajet fixe si il existe, d'identifiant passé en paramètre
	 * @param identifiant du trajet à retourner
	 * @return un objet TrajetFixe
	 */
	public TrajetFixe getTrajetFixeById(int identifiant){
		for (int i =0; i<getTrajetsFixe().size();i++){
			if(getTrajetsFixe().get(i).getIdentifiant() == identifiant)
				return getTrajetsFixe().get(i);
		}

		return null;
	}

	/**
	 * Retourne le TrajetNonFixe, dans la liste de Trajet non fixe si il existe, d'identifiant passé en paramètre
	 * @param identifiant du trajet à retourner
	 * @return un objet TrajetNonFixe
	 */
	public TrajetNonFixe getTrajetNonFixeById(int identifiant){
		for (int i =0; i<getTrajetsNonFixe().size();i++){
			if(getTrajetsNonFixe().get(i).getIdentifiant() == identifiant)
				return getTrajetsNonFixe().get(i);
		}

		return null;
	}
}
