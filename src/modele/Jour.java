package modele;

import java.util.ArrayList;

public class Jour {

		//Attributs
		private int identifiant;
		private String nom;
		
		//Constructeurs
		public Jour() {
			
		}
		
		public Jour(int identifiant, String nom) {
			super();
			this.identifiant = identifiant;
			this.nom = nom;
		}
		
		//Getters & Setters
		public int getIdentifiant() {
			return identifiant;
		}
		public void setIdentifiant(int identifiant) {
			this.identifiant = identifiant;
		}
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}

		public static ArrayList<Jour> jourDeLaSemaine(){
			ArrayList<Jour> jours = new ArrayList<Jour>();

			Jour lundi = new Jour(0,"Lundi");
			Jour mardi = new Jour(1,"Mardi");
			Jour mercredi = new Jour(2,"Mercredi");
			Jour jeudi = new Jour(3,"Jeudi");
			Jour Vendredi = new Jour(4,"Vendredi");
			Jour Samedi = new Jour(5,"Samedi");
			Jour Dimanche = new Jour(6,"Dimanche");

			return jours;
		}
}
