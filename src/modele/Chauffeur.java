package modele;

/**
 * Cette classe represente un chauffeur Symone
 */
public class Chauffeur implements Cloneable{

	/**
	 * Attributes
	 */
	private int identifiant;
	private Ville villeRattachement;
	private ContratDeTravail contratDeTravail;
	private double coutHotellerie;

	/**
	 * Constructors
	 */
	public Chauffeur() {
		
	}
	
	public Chauffeur(int identifiant, Ville villeRattachement, ContratDeTravail contratDeTravail, double coutHotellerie) {
		super();
		this.identifiant = identifiant;
		this.villeRattachement = villeRattachement;
		this.contratDeTravail = contratDeTravail;
		this.coutHotellerie = coutHotellerie;
	}

	public Object clone() {
		Chauffeur chauffeur = null;
		try {
			// On récupère l'instance à renvoyer par l'appel de la
			// méthode super.clone()
			chauffeur = (Chauffeur) super.clone();
		} catch(CloneNotSupportedException cnse) {
			// Ne devrait jamais arriver, car nous implémentons
			// l'interface Cloneable
			cnse.printStackTrace(System.err);
		}
		// on renvoie le clone
		return chauffeur;
	}

	/**
	 * Getters & Setters
	 */
	public int getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}
	public Ville getVilleRattachement() {
		return villeRattachement;
	}
	public void setVilleRattachement(Ville villeRattachement) {
		this.villeRattachement = villeRattachement;
	}
	public ContratDeTravail getContratDeTravail() {
		return contratDeTravail;
	}
	public void setContratDeTravail(ContratDeTravail contratDeTravail) {
		this.contratDeTravail = contratDeTravail;
	}
	public double getCoutHotellerie() {
		return coutHotellerie;
	}
	public void setCoutHotellerie(double coutHotellerie) {
		this.coutHotellerie = coutHotellerie;
	}
	
	
}
