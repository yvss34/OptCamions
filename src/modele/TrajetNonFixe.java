package modele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Cette classe représente un trajet non fixe qui hérite de la classe Trajet
 * un trajet non fixe se caracterise par une fréquence par semaine, une fenêtre
 * de temps de départ, un nombre de trajet minimum le week-end et un nombre de jours
 * minimum séparant deux trajets
 */
public class TrajetNonFixe extends Trajet{

	/**Attributes**/
	private int frequenceSemaine;
	private String[] fenetreDeTemps;
	private int nombreTrajetWeekend;
	private int nbrJourMin;
	/**
	 * Cette liste contient la liste des départs possible d'un trajet non fixe
	 */
	private ArrayList<ArrayList<Integer>> listeJourDepart;

	/**Constructors**/
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

	/**Getters & Setters**/
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

	public ArrayList<ArrayList<Integer>> getListeJourDepart() {
		return listeJourDepart;
	}

	public void setListeJourDepart(ArrayList<ArrayList<Integer>> listeJourDepart) {
		this.listeJourDepart = listeJourDepart;
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

	/**
	 * Verifie que deux objets TrajetNonFixe sont égaux
	 * @param obj1 premier objet TrajetNonFixe
	 * @param obj2 second objet TrajetNonFixe
	 * @return true si les deux obejts sont égaux, faux sinon
	 */
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

	/**
	 * Cette fonction permet de lister les différents départ possibles du trajet non fixe,
	 * ces differents départ sont stockés dans l'attribut listeJourDepart
	 */
	public void liste() {

		listeJourDepart = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> premier = new ArrayList<Integer>();
		ArrayList<Integer> second = new ArrayList<Integer>();
		ArrayList<Integer> troisieme = new ArrayList<Integer>();
		ArrayList<Integer> quatrieme = new ArrayList<Integer>();
		ArrayList<Integer> cinquieme = new ArrayList<Integer>();
		ArrayList<Integer> sixieme = new ArrayList<Integer>();
		ArrayList<Integer> septieme = new ArrayList<Integer>();


		switch (getFrequenceSemaine()) {
			case 1:
				switch (getNbrJourMin()) {
					case 0:
						switch (getNombreTrajetWeekend()) {
							case 0:
								premier.add(0);
								premier.add(1);
								premier.add(2);
								premier.add(3);
								premier.add(4);
								premier.add(5);
								premier.add(6);
								break;
							case 1:
								premier.add(5);
								premier.add(6);
								break;
						}
						break;
				}
				listeJourDepart.add(premier);
				break;
			case 2:
				switch (getNbrJourMin()) {
					case 0:
						switch (getNombreTrajetWeekend()) {
							case 0:
								premier.add(0);
								premier.add(1);
								premier.add(2);
								premier.add(3);
								premier.add(4);
								premier.add(5);

								second.add(1);
								second.add(2);
								second.add(3);
								second.add(4);
								second.add(5);
								second.add(6);

								break;
							case 1:
								premier.add(0);
								premier.add(1);
								premier.add(2);
								premier.add(3);
								premier.add(4);
								premier.add(5);

								second.add(5);
								second.add(6);
								break;
							case 2:
								premier.add(5);

								second.add(6);
								break;
						}
						break;
					case 1:
						switch (getNombreTrajetWeekend()) {
							case 0:
								premier.add(0);
								premier.add(1);
								premier.add(2);
								premier.add(3);
								premier.add(4);

								second.add(2);
								second.add(3);
								second.add(4);
								second.add(5);
								second.add(6);
								break;
							case 1:
								premier.add(0);
								premier.add(1);
								premier.add(2);
								premier.add(3);
								premier.add(4);

								second.add(5);
								second.add(6);
								break;
						}
						break;
					case 2:
						switch (getNombreTrajetWeekend()) {
							case 0:
								premier.add(0);
								premier.add(1);
								premier.add(2);
								premier.add(3);

								second.add(3);
								second.add(4);
								second.add(5);
								second.add(6);
								break;
							case 1:
								premier.add(0);
								premier.add(1);
								premier.add(2);
								premier.add(3);

								second.add(5);
								second.add(6);
								break;
						}
						break;
				}
				listeJourDepart.add(premier);
				listeJourDepart.add(second);
				break;
			case 3:
				switch (getNbrJourMin()) {
					case 0:
						switch (getNombreTrajetWeekend()) {
							case 0:
								premier.add(0);
								premier.add(1);
								premier.add(2);
								premier.add(3);
								premier.add(4);

								second.add(1);
								second.add(2);
								second.add(3);
								second.add(4);
								second.add(5);

								troisieme.add(2);
								troisieme.add(3);
								troisieme.add(4);
								troisieme.add(5);
								troisieme.add(6);

								break;
							case 1:
								premier.add(0);
								premier.add(1);
								premier.add(2);
								premier.add(3);
								premier.add(4);

								second.add(1);
								second.add(2);
								second.add(3);
								second.add(4);
								second.add(5);

								troisieme.add(5);
								troisieme.add(6);
								break;
							case 2:
								premier.add(0);
								premier.add(1);
								premier.add(2);
								premier.add(3);
								premier.add(4);

								second.add(5);

								troisieme.add(6);
								break;
						}
						break;
					case 1:
						switch (getNombreTrajetWeekend()) {
							case 0:
								premier.add(0);
								premier.add(1);
								premier.add(2);

								second.add(2);
								second.add(3);
								second.add(4);

								troisieme.add(4);
								troisieme.add(5);
								troisieme.add(6);
								break;

							case 1:
								premier.add(0);
								premier.add(1);
								premier.add(2);

								second.add(2);
								second.add(3);
								second.add(4);

								troisieme.add(5);
								troisieme.add(6);
								break;
						}
						break;
				}
				listeJourDepart.add(premier);
				listeJourDepart.add(second);
				listeJourDepart.add(troisieme);
				break;
			case 4:
				switch (getNbrJourMin()) {
					case 0:
						switch (getNombreTrajetWeekend()) {
							case 0:
								premier.add(0);
								premier.add(1);
								premier.add(2);
								premier.add(3);

								second.add(1);
								second.add(2);
								second.add(3);
								second.add(4);

								troisieme.add(2);
								troisieme.add(3);
								troisieme.add(4);
								troisieme.add(5);

								quatrieme.add(3);
								quatrieme.add(4);
								quatrieme.add(5);
								quatrieme.add(6);
								break;
							case 1:
								premier.add(0);
								premier.add(1);
								premier.add(2);
								premier.add(3);

								second.add(1);
								second.add(2);
								second.add(3);
								second.add(4);

								troisieme.add(2);
								troisieme.add(3);
								troisieme.add(4);
								troisieme.add(5);

								quatrieme.add(5);
								quatrieme.add(6);
								break;
							case 2:
								premier.add(0);
								premier.add(1);
								premier.add(2);
								premier.add(3);

								second.add(1);
								second.add(2);
								second.add(3);
								second.add(4);

								troisieme.add(5);

								quatrieme.add(6);
								break;
						}
						break;
				}
				listeJourDepart.add(premier);
				listeJourDepart.add(second);
				listeJourDepart.add(troisieme);
				listeJourDepart.add(quatrieme);
				break;
			case 5:
				switch (getNbrJourMin()) {
					case 0:
						switch (getNombreTrajetWeekend()) {
							case 0:
								premier.add(0);
								premier.add(1);
								premier.add(2);

								second.add(1);
								second.add(2);
								second.add(3);

								troisieme.add(2);
								troisieme.add(3);
								troisieme.add(4);

								quatrieme.add(3);
								quatrieme.add(4);
								quatrieme.add(5);

								cinquieme.add(4);
								cinquieme.add(5);
								cinquieme.add(6);
								break;
							case 1:
								premier.add(0);
								premier.add(1);
								premier.add(2);

								second.add(1);
								second.add(2);
								second.add(3);

								troisieme.add(2);
								troisieme.add(3);
								troisieme.add(4);

								quatrieme.add(3);
								quatrieme.add(4);
								quatrieme.add(5);

								cinquieme.add(5);
								cinquieme.add(6);
								break;
							case 2:
								premier.add(0);
								premier.add(1);
								premier.add(2);

								second.add(1);
								second.add(2);
								second.add(3);

								troisieme.add(2);
								troisieme.add(3);
								troisieme.add(4);

								quatrieme.add(5);

								cinquieme.add(6);
								break;
						}
						break;
				}
				listeJourDepart.add(premier);
				listeJourDepart.add(second);
				listeJourDepart.add(troisieme);
				listeJourDepart.add(quatrieme);
				listeJourDepart.add(cinquieme);
				break;
			case 6:
				switch (getNbrJourMin()) {
					case 0:
						switch (getNombreTrajetWeekend()) {
							case 2:
								premier.add(0);
								premier.add(1);

								second.add(1);
								second.add(2);

								troisieme.add(2);
								troisieme.add(3);

								quatrieme.add(3);
								quatrieme.add(4);

								cinquieme.add(5);

								sixieme.add(6);
								break;

							default:
								premier.add(0);
								premier.add(1);

								second.add(1);
								second.add(2);

								troisieme.add(2);
								troisieme.add(3);

								quatrieme.add(3);
								quatrieme.add(4);

								cinquieme.add(4);
								cinquieme.add(5);

								sixieme.add(5);
								sixieme.add(6);
								break;
						}
						break;
				}
				listeJourDepart.add(premier);
				listeJourDepart.add(second);
				listeJourDepart.add(troisieme);
				listeJourDepart.add(quatrieme);
				listeJourDepart.add(cinquieme);
				listeJourDepart.add(sixieme);
				break;
			case 7:
				switch (getNbrJourMin()) {
					case 0:
						premier.add(0);

						second.add(1);

						troisieme.add(2);

						quatrieme.add(3);

						cinquieme.add(4);

						sixieme.add(5);

						septieme.add(6);
						break;
				}
				listeJourDepart.add(premier);
				listeJourDepart.add(second);
				listeJourDepart.add(troisieme);
				listeJourDepart.add(quatrieme);
				listeJourDepart.add(cinquieme);
				listeJourDepart.add(sixieme);
				listeJourDepart.add(septieme);
				break;
		}
	}
}
