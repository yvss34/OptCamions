package modele;

public class Chauffeur {
	
	//Attributs
	
	private int identifiant;
	private Ville villeRattachement;
	private ContratDeTravail contratDeTravail;
	private int coutHotellerie;
	
	//Constructeur
	public Chauffeur() {
		
	}
	
	public Chauffeur(int identifiant, Ville villeRattachement, ContratDeTravail contratDeTravail, int coutHotellerie) {
		super();
		this.identifiant = identifiant;
		this.villeRattachement = villeRattachement;
		this.contratDeTravail = contratDeTravail;
		this.coutHotellerie = coutHotellerie;
	}
	//Getters & Setters
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
	public int getCoutHotellerie() {
		return coutHotellerie;
	}
	public void setCoutHotellerie(int coutHotellerie) {
		this.coutHotellerie = coutHotellerie;
	}
	
	
}