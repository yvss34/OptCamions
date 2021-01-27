package modele;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ChecherCouts {

    Solution solution;

    @Before
    public void initSolution() {

        //Création d'une solution valide
        solution = new Solution();

        //Nombre
        solution.setNbrCamions(2);
        solution.setNbrChauffeurs(2);

        //Plannification
        Plannification plannification = new Plannification();
        plannification.setNombreHeuresMin(5.0);
        plannification.setNombreHeuresMax(60);
        plannification.setCoutHoraireJour(10);
        plannification.setCoutHoraireNuit(12);
        plannification.setCoutHotellerie(50);
        plannification.setNbrConduiteContinueMax(5.0);
        plannification.setTempsDeReposJournalier(11.0);
        plannification.setNbrConduiteJournaliereMax(9.0);
        plannification.setNbrConduiteHebdomadaireMax(54.0);
        plannification.setDureeReposHebdomadaire(24.0);
        solution.setPlannification(plannification);

        //Ville
        Ville paris = new Ville(1, "Paris");
        Ville marseille = new Ville(2, "Marseille");
        Ville lyon = new Ville(3, "Lyon");

        //Chauffeurs
        solution.setChauffeurs(new ArrayList<Chauffeur>());
        Chauffeur chauffeur1 = new Chauffeur(18, paris, null, 100);
        Chauffeur chauffeur2 = new Chauffeur(1424, marseille, null, 100);
        solution.getChauffeurs().add(chauffeur1);
        solution.getChauffeurs().add(chauffeur2);

        //Trajets
        TrajetFixe trajet1_1 = new TrajetFixe(1, paris, lyon, 4.0, new ArrayList<Double>(), null, new Jour(0, "Lundi"), LocalTime.of(8, 0));
        TrajetFixe trajet1_2 = new TrajetFixe(2, lyon, paris, 4.0, new ArrayList<Double>(), null, new Jour(1, "Mardi"), LocalTime.of(8, 0));
        TrajetFixe trajet1_3 = new TrajetFixe(3, paris, lyon, 4.0, new ArrayList<Double>(), null, new Jour(2, "Mercredi"), LocalTime.of(8, 0));
        TrajetFixe trajet1_4 = new TrajetFixe(4, lyon, paris, 4.0, new ArrayList<Double>(), null, new Jour(3, "Jeudi"), LocalTime.of(8, 0));

        TrajetFixe trajet2_1 = new TrajetFixe(5, marseille, paris, 8.0, new ArrayList<Double>(), null, new Jour(0, "Lundi"), LocalTime.of(8, 0));
        TrajetFixe trajet2_2 = new TrajetFixe(6, paris, marseille, 8.0, new ArrayList<Double>(), null, new Jour(1, "Mardi"), LocalTime.of(8, 0));
        TrajetFixe trajet2_3 = new TrajetFixe(7, marseille, paris, 8.0, new ArrayList<Double>(), null, new Jour(2, "Mercredi"), LocalTime.of(8, 0));
        TrajetFixe trajet2_4 = new TrajetFixe(8, paris, marseille, 8.0, new ArrayList<Double>(), null, new Jour(3, "Jeudi"), LocalTime.of(8, 0));

        //Ajout temps de pauses
        trajet1_1.getTempsDePause().add(4.0);
        trajet1_2.getTempsDePause().add(4.0);
        trajet1_3.getTempsDePause().add(4.0);
        trajet1_4.getTempsDePause().add(4.0);

        trajet2_1.getTempsDePause().add(4.0);
        trajet2_2.getTempsDePause().add(4.0);
        trajet2_3.getTempsDePause().add(4.0);
        trajet2_4.getTempsDePause().add(4.0);


        solution.setTrajets(new ArrayList<TrajetFixe>());
        solution.getTrajets().add(trajet1_1);
        solution.getTrajets().add(trajet1_2);
        solution.getTrajets().add(trajet1_3);
        solution.getTrajets().add(trajet1_4);

        solution.getTrajets().add(trajet2_1);
        solution.getTrajets().add(trajet2_2);
        solution.getTrajets().add(trajet2_3);
        solution.getTrajets().add(trajet2_4);


        ArrayList<Integer> trajets1Chauffeurs = new ArrayList<Integer>();
        trajets1Chauffeurs.add(trajet1_1.getIdentifiant());
        trajets1Chauffeurs.add(trajet1_2.getIdentifiant());
        trajets1Chauffeurs.add(trajet1_3.getIdentifiant());
        trajets1Chauffeurs.add(trajet1_4.getIdentifiant());

        ArrayList<Integer> trajets1Camions = new ArrayList<Integer>();
        trajets1Camions.add(trajet1_1.getIdentifiant());
        trajets1Camions.add(trajet1_2.getIdentifiant());
        trajets1Camions.add(trajet1_3.getIdentifiant());
        trajets1Camions.add(trajet1_4.getIdentifiant());

        ArrayList<Integer> trajets2Chauffeurs = new ArrayList<Integer>();
        trajets2Chauffeurs.add(trajet2_1.getIdentifiant());
        trajets2Chauffeurs.add(trajet2_2.getIdentifiant());
        trajets2Chauffeurs.add(trajet2_3.getIdentifiant());
        trajets2Chauffeurs.add(trajet2_4.getIdentifiant());

        ArrayList<Integer> trajets2Camions = new ArrayList<Integer>();
        trajets2Camions.add(trajet2_1.getIdentifiant());
        trajets2Camions.add(trajet2_2.getIdentifiant());
        trajets2Camions.add(trajet2_3.getIdentifiant());
        trajets2Camions.add(trajet2_4.getIdentifiant());

        //camionTrajets
        solution.setCamionsTrajets(new HashMap<Integer, ArrayList<Integer>>());
        solution.getCamionsTrajets().put(1, trajets1Camions);
        solution.getCamionsTrajets().put(2, trajets2Camions);

        //chauffeursTrajets
        solution.setChauffeursTrajets(new HashMap<Integer, ArrayList<Integer>>());
        solution.getChauffeursTrajets().put(18, trajets1Chauffeurs);
        solution.getChauffeursTrajets().put(1424, trajets2Chauffeurs);

    }

    @Test
    public void coutHotellerie1() {
        //ARRANGE
        Checker checker = new Checker(solution);

        // ACT
        boolean resultat = checker.coutHottelerie();

        //ASSERT
        assertEquals(true, resultat);
    }

    @Test
    public void coutHotellerie2() {
        //ARRANGE
        Checker checker = new Checker(solution);
        solution.getChauffeurs().get(0).setCoutHotellerie(52.0);

        // ACT
        boolean resultat = checker.coutHottelerie();

        //ASSERT
        assertEquals(false, resultat);
    }

    @Test
        public void coutContratTravail1() {
        //ARRANGE
        Checker checker = new Checker(solution);

        ContratDeTravail contratDeTravail1 = new ContratDeTravail(16, 0, 0);
        ContratDeTravail contratDeTravail2 = new ContratDeTravail(32, 0, 00);


        solution.getChauffeurs().get(0).setContratDeTravail(contratDeTravail1);
        solution.getChauffeurs().get(1).setContratDeTravail(contratDeTravail2);

        // ACT
        boolean resultat = checker.coutContratTravail();

        //ASSERT
        assertEquals(true, resultat);
    }


    @Test
    public void coutContratTravail2() {
        //ARRANGE
        Checker checker = new Checker(solution);

        ContratDeTravail contratDeTravail1 = new ContratDeTravail(16, 1, 0);
        ContratDeTravail contratDeTravail2 = new ContratDeTravail(32, 0, 00);


        solution.getChauffeurs().get(0).setContratDeTravail(contratDeTravail1);
        solution.getChauffeurs().get(1).setContratDeTravail(contratDeTravail2);

        // ACT
        boolean resultat = checker.coutContratTravail();

        //ASSERT
        assertEquals(false, resultat);
    }

    @Test
    public void coutContratTravail3() {
        //ARRANGE
        Checker checker = new Checker(solution);

        ContratDeTravail contratDeTravail1 = new ContratDeTravail(16, 8, 0);
        ContratDeTravail contratDeTravail2 = new ContratDeTravail(32, 0, 00);
        TrajetFixe trajet1 = new TrajetFixe(256, new Ville(1, "Paris"), new Ville(1, "lyon"), 8.0, new ArrayList<Double>(), null, new Jour(4, "Jeudi"), LocalTime.of(21, 0));
        solution.getTrajets().add(trajet1);
        solution.getChauffeursTrajets().get(18).add(256);

        solution.getChauffeurs().get(0).setContratDeTravail(contratDeTravail1);
        solution.getChauffeurs().get(1).setContratDeTravail(contratDeTravail2);

        // ACT
        boolean resultat = checker.coutContratTravail();

        //ASSERT
        assertEquals(true, resultat);
    }


}
