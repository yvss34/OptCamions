package modele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TrajetNonFixe extends Trajet{

	//Attributs
	
	private int frequenceSemaine;
	private String[] fenetreDeTemps;
	private int nombreTrajetWeekend;
	private int nbrJourMin;
	
	//Constructeurs
	public TrajetNonFixe() {
		super();
	}

	public TrajetNonFixe(int identifiant, Ville villeDepart, Ville villeArrivee, double tempsDeConduite, ArrayList<Double> tempsDePause, HashMap<Ville, Double> listeVilleStop,int frequenceSemaine, String[] fenetreDeTemps, int nombreTrajetWeekend, int nbrJourMin) {
		super(identifiant, villeDepart, villeArrivee, tempsDeConduite, tempsDePause, listeVilleStop);
		this.frequenceSemaine = frequenceSemaine;
		this.fenetreDeTemps = fenetreDeTemps;
		this.nombreTrajetWeekend = nombreTrajetWeekend;
		this.nbrJourMin = nbrJourMin;

	}

	public TrajetNonFixe(int identifiant, Ville villeDepart, Ville villeArrivee, double tempsDeConduite, HashMap<Ville, Double> listeVilleStop,int frequenceSemaine, String[] fenetreDeTemps, int nombreTrajetWeekend, int nbrJourMin) {
		super(identifiant, villeDepart, villeArrivee, tempsDeConduite, listeVilleStop);
		this.frequenceSemaine = frequenceSemaine;
		this.fenetreDeTemps = fenetreDeTemps;
		this.nombreTrajetWeekend = nombreTrajetWeekend;
		this.nbrJourMin = nbrJourMin;

	}

//Getters & Setters


	public int getFrequenceSemaine() {
		return frequenceSemaine;
	}

	public void setFrequenceSemaine(int frequenceSemaine) {
		this.frequenceSemaine = frequenceSemaine;
	}

	public String[] getFenetreDeTemps() {
		return fenetreDeTemps;
	}

	public void setFenetreDeTemps(String[] fenetreDeTemps) {
		this.fenetreDeTemps = fenetreDeTemps;
	}

	public int getNombreTrajetWeekend() {
		return nombreTrajetWeekend;
	}

	public void setNombreTrajetWeekend(int nombreTrajetWeekend) {
		this.nombreTrajetWeekend = nombreTrajetWeekend;
	}

	public int getNbrJourMin() {
		return nbrJourMin;
	}

	public void setNbrJourMin(int nbrJourMin) {
		this.nbrJourMin = nbrJourMin;
	}

	@Override
	public String toString() {
		return 	"identifiant=" + getIdentifiant() +
				", villeDepart=" + getVilleDepart() +
				", villeArrivee=" + getVilleArrivee() +
				", tempsDeConduite=" + getTempsDeConduite() +
				", tempsDePause=" + getTempsDePause() +
				", listeVilleStop=" + getListeVilleStop() +
				"frequenceSemaine=" + frequenceSemaine +
				", fenetreDeTemps=" + Arrays.toString(fenetreDeTemps) +
				", nombreTrajetWeekend=" + nombreTrajetWeekend +
				", nbrJourMin=" + nbrJourMin ;
	}

	public static boolean egale(TrajetNonFixe obj1,TrajetNonFixe obj2) {
		boolean checker = true;

		checker = (obj1.getVilleArrivee().getIdentifiant() == obj2.getVilleArrivee().getIdentifiant());
		checker = (obj1.getVilleDepart().getIdentifiant() == obj2.getVilleDepart().getIdentifiant());
		checker = (obj1.getTempsDeConduite() == obj2.getTempsDeConduite());
		checker = (obj1.getFenetreDeTemps()) == obj2.getFenetreDeTemps();
		checker = obj1.getFrequenceSemaine() == obj2.getFrequenceSemaine();
		checker = obj1.getNombreTrajetWeekend() == obj2.getNombreTrajetWeekend();

		return checker;
	}
}
