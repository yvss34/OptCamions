package modele;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class main {
    public static void main (String[] args) throws IOException {

        Solution solution = new Solution();

        solution.setNbrChauffeurs(3);
        solution.setNbrCamions(2);

        //Plannification
        Plannification plannification = new Plannification();
        plannification.setNombreHeuresMin(5.0);
        plannification.setNombreHeuresMax(60);
        plannification.setCoutHoraireJour(10);
        plannification.setCoutHoraireNuit(12);
        plannification.setCoutHotellerie(50);
        plannification.setNbrConduiteContinueMax(5.0);
        plannification.setTempsDeReposJournalier(11.0);
        plannification.setNbrConduiteJournaliereMax(10.0);
        plannification.setNbrConduiteHebdomadaireMax(54.0);
        plannification.setDureeReposHebdomadaire(24.0);
        solution.setPlannification(plannification);

        //Ville
        Ville paris = new Ville(1, "Paris");
        Ville marseille = new Ville(2, "Marseille");
        Ville lyon = new Ville(3, "Lyon");
        Ville toulouse = new Ville(4, "Toulouse");
        Ville bordeaux = new Ville(5, "Bordeaux");

        //Contrat Travail
        ContratDeTravail contratDeTravail1 = new ContratDeTravail(32,0,0);
        ContratDeTravail contratDeTravail2 = new ContratDeTravail(23,0,0);
        ContratDeTravail contratDeTravail3 = new ContratDeTravail(8,8,0);

        //Chauffeurs
        solution.setChauffeurs(new ArrayList<Chauffeur>());
        Chauffeur chauffeur1 = new Chauffeur(1, paris, contratDeTravail1, 100);
        Chauffeur chauffeur2 = new Chauffeur(2, paris, contratDeTravail2, 50);
        Chauffeur chauffeur3 = new Chauffeur(3, marseille, contratDeTravail3, 100);
        solution.getChauffeurs().add(chauffeur1);
        solution.getChauffeurs().add(chauffeur2);
        solution.getChauffeurs().add(chauffeur3);

        //Trajets
        TrajetFixe trajet1_1 = new TrajetFixe(1, paris, marseille, 8.0, new ArrayList<Double>(), null, new Jour(0, "Lundi"), LocalTime.of(8, 0));
        TrajetFixe trajet1_2 = new TrajetFixe(2, paris, toulouse, 8.0, new ArrayList<Double>(), null, new Jour(0, "Lundi"), LocalTime.of(8, 0));
        TrajetFixe trajet1_3 = new TrajetFixe(3, marseille, lyon, 4.0, new ArrayList<Double>(), null, new Jour(0, "Lundi"), LocalTime.of(20, 0));

        TrajetFixe trajet2_1 = new TrajetFixe(4, lyon, marseille, 4.0, new ArrayList<Double>(), null, new Jour(1, "Mardi"), LocalTime.of(2, 0));
        TrajetFixe trajet2_2 = new TrajetFixe(5, marseille, lyon, 4.0, new ArrayList<Double>(), null, new Jour(1, "Mardi"), LocalTime.of(11, 0));
        TrajetFixe trajet2_3 = new TrajetFixe(6, lyon, marseille, 4.0, new ArrayList<Double>(), null, new Jour(1, "Mardi"), LocalTime.of(16, 0));
        TrajetFixe trajet2_4 = new TrajetFixe(7, toulouse, paris, 5.0, new ArrayList<Double>(), null, new Jour(1, "Mardi"), LocalTime.of(8, 0));
        TrajetFixe trajet2_5 = new TrajetFixe(8, marseille, lyon, 4.0, new ArrayList<Double>(), null, new Jour(1, "Mardi"), LocalTime.of(20, 0));

        TrajetFixe trajet3_1 = new TrajetFixe(9, lyon, marseille, 4.0, new ArrayList<Double>(), null, new Jour(2, "Mercredi"), LocalTime.of(2, 0));
        TrajetFixe trajet3_2 = new TrajetFixe(10, marseille, paris, 8.0, new ArrayList<Double>(), null, new Jour(2, "Mercredi"), LocalTime.of(8, 30));

        TrajetFixe trajet4_1 = new TrajetFixe(11, lyon, paris, 4.0, new ArrayList<Double>(), null, new Jour(3, "Jeudi"), LocalTime.of(8, 0));
        TrajetFixe trajet4_2 = new TrajetFixe(12, lyon, paris, 4.0, new ArrayList<Double>(), null, new Jour(3, "Jeudi"), LocalTime.of(15, 0));

        TrajetFixe trajet5_1 = new TrajetFixe(13, paris, bordeaux, 5.0, new ArrayList<Double>(), null, new Jour(3, "Jeudi"), LocalTime.of(7, 0));
        TrajetFixe trajet5_2 = new TrajetFixe(14, bordeaux, paris, 5.0, new ArrayList<Double>(), null, new Jour(3, "Jeudi"), LocalTime.of(14, 30));

        //Ajout temps de pauses

        trajet1_1.getTempsDePause().add(4.0);
        trajet1_2.getTempsDePause().add(4.0);
        trajet1_3.getTempsDePause().add(4.0);

        trajet2_1.getTempsDePause().add(4.0);
        trajet2_2.getTempsDePause().add(4.0);
        trajet2_3.getTempsDePause().add(4.0);
        trajet2_4.getTempsDePause().add(4.0);
        trajet2_5.getTempsDePause().add(4.0);

        trajet3_1.getTempsDePause().add(4.0);
        trajet3_2.getTempsDePause().add(4.0);

        trajet4_1.getTempsDePause().add(4.0);
        trajet4_2.getTempsDePause().add(4.0);

        trajet5_1.getTempsDePause().add(4.0);
        trajet5_2.getTempsDePause().add(4.0);



        solution.setTrajets(new ArrayList<TrajetFixe>());
        solution.getTrajets().add(trajet1_1);
        solution.getTrajets().add(trajet1_2);
        solution.getTrajets().add(trajet1_3);
        solution.getTrajets().add(trajet2_1);
        solution.getTrajets().add(trajet2_2);
        solution.getTrajets().add(trajet2_3);
        solution.getTrajets().add(trajet2_4);
        solution.getTrajets().add(trajet2_5);
        solution.getTrajets().add(trajet3_1);
        solution.getTrajets().add(trajet3_2);
        solution.getTrajets().add(trajet4_1);
        solution.getTrajets().add(trajet4_2);
        solution.getTrajets().add(trajet5_1);
        solution.getTrajets().add(trajet5_2);


        ArrayList<Integer> trajets1Chauffeurs = new ArrayList<Integer>();
        trajets1Chauffeurs.add(trajet1_1.getIdentifiant());
        trajets1Chauffeurs.add(trajet2_2.getIdentifiant());
        trajets1Chauffeurs.add(trajet2_3.getIdentifiant());
        trajets1Chauffeurs.add(trajet3_2.getIdentifiant());
        trajets1Chauffeurs.add(trajet4_1.getIdentifiant());
        trajets1Chauffeurs.add(trajet4_2.getIdentifiant());


        ArrayList<Integer> trajets2Chauffeurs = new ArrayList<Integer>();
        trajets2Chauffeurs.add(trajet1_2.getIdentifiant());
        trajets2Chauffeurs.add(trajet2_4.getIdentifiant());
        trajets2Chauffeurs.add(trajet5_1.getIdentifiant());
        trajets2Chauffeurs.add(trajet5_2.getIdentifiant());


        ArrayList<Integer> trajets3chauffeurs = new ArrayList<Integer>();
        trajets3chauffeurs.add(trajet1_3.getIdentifiant());
        trajets3chauffeurs.add(trajet2_1.getIdentifiant());
        trajets3chauffeurs.add(trajet2_5.getIdentifiant());
        trajets3chauffeurs.add(trajet3_1.getIdentifiant());

        ArrayList<Integer> trajets1Camions = new ArrayList<Integer>();
        trajets1Camions.add(trajet1_1.getIdentifiant());
        trajets1Camions.add(trajet1_3.getIdentifiant());
        trajets1Camions.add(trajet2_1.getIdentifiant());
        trajets1Camions.add(trajet2_2.getIdentifiant());
        trajets1Camions.add(trajet2_3.getIdentifiant());
        trajets1Camions.add(trajet2_5.getIdentifiant());
        trajets1Camions.add(trajet3_1.getIdentifiant());
        trajets1Camions.add(trajet3_2.getIdentifiant());
        trajets1Camions.add(trajet4_1.getIdentifiant());
        trajets1Camions.add(trajet4_2.getIdentifiant());



        ArrayList<Integer> trajets2Camions = new ArrayList<Integer>();
        trajets2Camions.add(trajet1_2.getIdentifiant());
        trajets2Camions.add(trajet2_4.getIdentifiant());
        trajets2Camions.add(trajet5_1.getIdentifiant());
        trajets2Camions.add(trajet5_2.getIdentifiant());


        //camionTrajets
        solution.setCamionsTrajets(new HashMap<Integer, ArrayList<Integer>>());
        solution.getCamionsTrajets().put(1, trajets1Camions);
        solution.getCamionsTrajets().put(2, trajets2Camions);

        //chauffeursTrajets
        solution.setChauffeursTrajets(new HashMap<Integer, ArrayList<Integer>>());
        solution.getChauffeursTrajets().put(1, trajets1Chauffeurs);
        solution.getChauffeursTrajets().put(2, trajets2Chauffeurs);
        solution.getChauffeursTrajets().put(3, trajets3chauffeurs);

        Checker checker = new Checker(solution);
        System.out.println(checker.verificationComplete());

        solution.creationCsv();
    }
}

