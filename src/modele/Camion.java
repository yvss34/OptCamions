package modele;

/**
 * Cette classe represente une Symone
 */
public class Camion {

	/**
	 * Attributes
	 */
	private int identifiant;

	/**
	 * Constructors
	 */
	public Camion() {
		
	}
	
	public Camion(int identifiant) {
		super();
		this.identifiant = identifiant;
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
	
	
}
