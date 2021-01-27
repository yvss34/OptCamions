package modele;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class CheckerTestOperationnelle {

    Solution solution;

    @Before
    public void initSolution(){

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
        Ville paris = new Ville(1,"Paris");
        Ville marseille = new Ville(2,"Marseille");
        Ville lyon = new Ville(3,"Lyon");

        //Chauffeurs
        solution.setChauffeurs(new ArrayList<Chauffeur>());
        Chauffeur chauffeur1 = new Chauffeur(18,paris,null,0);
        Chauffeur chauffeur2 = new Chauffeur(1424,marseille,null,0);
        solution.getChauffeurs().add(chauffeur1);
        solution.getChauffeurs().add(chauffeur2);

        //Trajets
        TrajetFixe trajet1_1 = new TrajetFixe(1,paris,lyon,4.0,new ArrayList<Double>(),null,new Jour(0,"Lundi"),LocalTime.of(8,0));
        TrajetFixe trajet1_2 = new TrajetFixe(2,lyon,paris,4.0,new ArrayList<Double>(),null,new Jour(1,"Mardi"),LocalTime.of(8,0));
        TrajetFixe trajet1_3 = new TrajetFixe(3,paris,lyon,4.0,new ArrayList<Double>(),null,new Jour(2,"Mercredi"),LocalTime.of(8,0));
        TrajetFixe trajet1_4 = new TrajetFixe(4,lyon,paris,4.0,new ArrayList<Double>(),null,new Jour(3,"Jeudi"),LocalTime.of(8,0));

        TrajetFixe trajet2_1 = new TrajetFixe(5,marseille,paris,8.0,new ArrayList<Double>(),null,new Jour(0,"Lundi"),LocalTime.of(8,0));
        TrajetFixe trajet2_2 = new TrajetFixe(6,paris,marseille,8.0,new ArrayList<Double>(),null,new Jour(1,"Mardi"),LocalTime.of(8,0));
        TrajetFixe trajet2_3 = new TrajetFixe(7,marseille,paris,8.0,new ArrayList<Double>(),null,new Jour(2,"Mercredi"),LocalTime.of(8,0));
        TrajetFixe trajet2_4 = new TrajetFixe(8,paris,marseille,8.0,new ArrayList<Double>(),null,new Jour(3,"Jeudi"),LocalTime.of(8,0));

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


        ArrayList<Integer> trajets1Chauffeurs= new ArrayList<Integer>();
        trajets1Chauffeurs.add(trajet1_1.getIdentifiant());
        trajets1Chauffeurs.add(trajet1_2.getIdentifiant());
        trajets1Chauffeurs.add(trajet1_3.getIdentifiant());
        trajets1Chauffeurs.add(trajet1_4.getIdentifiant());

        ArrayList<Integer> trajets1Camions= new ArrayList<Integer>();
        trajets1Camions.add(trajet1_1.getIdentifiant());
        trajets1Camions.add(trajet1_2.getIdentifiant());
        trajets1Camions.add(trajet1_3.getIdentifiant());
        trajets1Camions.add(trajet1_4.getIdentifiant());

        ArrayList<Integer> trajets2Chauffeurs= new ArrayList<Integer>();
        trajets2Chauffeurs.add(trajet2_1.getIdentifiant());
        trajets2Chauffeurs.add(trajet2_2.getIdentifiant());
        trajets2Chauffeurs.add(trajet2_3.getIdentifiant());
        trajets2Chauffeurs.add(trajet2_4.getIdentifiant());

        ArrayList<Integer> trajets2Camions= new ArrayList<Integer>();
        trajets2Camions.add(trajet2_1.getIdentifiant());
        trajets2Camions.add(trajet2_2.getIdentifiant());
        trajets2Camions.add(trajet2_3.getIdentifiant());
        trajets2Camions.add(trajet2_4.getIdentifiant());

        //camionTrajets
        solution.setCamionsTrajets(new HashMap<Integer,ArrayList<Integer>>());
        solution.getCamionsTrajets().put(1,trajets1Camions);
        solution.getCamionsTrajets().put(2,trajets2Camions);

        //chauffeursTrajets
        solution.setChauffeursTrajets(new HashMap<Integer,ArrayList<Integer>>());
        solution.getChauffeursTrajets().put(18,trajets1Chauffeurs);
        solution.getChauffeursTrajets().put(1424,trajets2Chauffeurs);

    }

    @Test
    public void testUnTrjetALaFoisChauffeur1(){

        //ARRANGE
        Checker checker = new Checker(solution);

        // ACT
        boolean resultat = checker.unTrajetALaFoisChauffeurs();

        //ASSERT
        assertEquals(true,resultat);

    }

    @Test
    public void testUnTrjetALaFoisChauffeur2(){

        //ARRANGE
        Checker checker = new Checker(solution);

        TrajetFixe trajet = new TrajetFixe(256,new Ville(1,"Paris"),new Ville(1,"lyon"),9.0,new ArrayList<Double>(),null,new Jour(0,"Lundi"),LocalTime.of(0,0));
        trajet.getTempsDePause().add(1.00);
        trajet.getTempsDePause().add(2.5);
        solution.getTrajets().add(trajet);
        solution.getChauffeursTrajets().get(18).add(256);

        // ACT
        boolean resultat =  checker.unTrajetALaFoisChauffeurs();

        //ASSERT
        assertEquals(false,resultat);

    }

    @Test
    public void testUnTrjetALaFoisChauffeur3(){

        //ARRANGE
        Checker checker = new Checker(solution);

        TrajetFixe trajet1 = new TrajetFixe(256,new Ville(1,"Paris"),new Ville(1,"lyon"),8.0,new ArrayList<Double>(),null,new Jour(0,"Jeudi"),LocalTime.of(20,0));
        solution.getTrajets().add(trajet1);
        solution.getChauffeursTrajets().get(18).add(256);

        TrajetFixe trajet2 = new TrajetFixe(257,new Ville(1,"Paris"),new Ville(1,"lyon"),1.0,new ArrayList<Double>(),null,new Jour(0,"Jeudi"),LocalTime.of(22,0));
        solution.getTrajets().add(trajet2);
        solution.getChauffeursTrajets().get(18).add(257);

        // ACT
        boolean resultat =  checker.unTrajetALaFoisChauffeurs();

        //ASSERT
        assertEquals(false,resultat);

    }

    @Test
    public void testUnTrjetALaFoisChauffeur4(){

        //ARRANGE
        Checker checker = new Checker(solution);

        TrajetFixe trajet1 = new TrajetFixe(256,new Ville(1,"Paris"),new Ville(1,"lyon"),8.0,new ArrayList<Double>(),null,new Jour(0,"Jeudi"),LocalTime.of(20,0));
        solution.getTrajets().add(trajet1);
        solution.getChauffeursTrajets().get(18).add(256);

        TrajetFixe trajet2 = new TrajetFixe(257,new Ville(1,"Paris"),new Ville(1,"lyon"),3.0,new ArrayList<Double>(),null,new Jour(0,"Jeudi"),LocalTime.of(22,0));
        solution.getTrajets().add(trajet2);
        solution.getChauffeursTrajets().get(18).add(257);

        // ACT
        boolean resultat =  checker.unTrajetALaFoisChauffeurs();

        //ASSERT
        assertEquals(false,resultat);

    }

    @Test
    public void testUnTrjetALaFoisChauffeur5(){

        //ARRANGE
        Checker checker = new Checker(solution);

        TrajetFixe trajet1 = new TrajetFixe(256,new Ville(1,"Paris"),new Ville(1,"lyon"),8.0,new ArrayList<Double>(),null,new Jour(4,"Jeudi"),LocalTime.of(8,0));
        solution.getTrajets().add(trajet1);
        solution.getChauffeursTrajets().get(18).add(256);

        TrajetFixe trajet2 = new TrajetFixe(257,new Ville(1,"Paris"),new Ville(1,"lyon"),2.0,new ArrayList<Double>(),null,new Jour(4,"Jeudi"),LocalTime.of(22,0));
        solution.getTrajets().add(trajet2);
        solution.getChauffeursTrajets().get(18).add(257);

        // ACT
        boolean resultat =  checker.unTrajetALaFoisChauffeurs();

        //ASSERT
        assertEquals(true,resultat);

    }

    @Test
    public void testUnTrjetALaFoisChauffeur6(){

        //ARRANGE
        Checker checker = new Checker(solution);

        TrajetFixe trajet1 = new TrajetFixe(256,new Ville(1,"Paris"),new Ville(1,"lyon"),8.0,new ArrayList<Double>(),null,new Jour(4,"Jeudi"),LocalTime.of(20,0));
        solution.getTrajets().add(trajet1);
        solution.getChauffeursTrajets().get(18).add(256);

        TrajetFixe trajet2 = new TrajetFixe(257,new Ville(1,"Paris"),new Ville(1,"lyon"),2.0,new ArrayList<Double>(),null,new Jour(5,"Vendredi"),LocalTime.of(1,0));
        solution.getTrajets().add(trajet2);
        solution.getChauffeursTrajets().get(18).add(257);

        // ACT
        boolean resultat =  checker.unTrajetALaFoisChauffeurs();

        //ASSERT
        assertEquals(false,resultat);

    }

    @Test
    public void testUnTrjetALaFoisCamion1(){

        //ARRANGE
        Checker checker = new Checker(solution);

        // ACT
        boolean resultat = checker.unTrajetALaFoisCamions();

        //ASSERT
        assertEquals(true,resultat);

    }

    @Test
    public void testUnTrjetALaFoisCamion2(){

        //ARRANGE
        Checker checker = new Checker(solution);

        TrajetFixe trajet = new TrajetFixe(256,new Ville(1,"Paris"),new Ville(1,"lyon"),9.0,new ArrayList<Double>(),null,new Jour(0,"Lundi"),LocalTime.of(0,0));
        trajet.getTempsDePause().add(1.00);
        trajet.getTempsDePause().add(2.5);
        solution.getTrajets().add(trajet);
        solution.getCamionsTrajets().get(1).add(256);

        // ACT
        boolean resultat =  checker.unTrajetALaFoisCamions();

        //ASSERT
        assertEquals(false,resultat);

    }

    @Test
    public void testUnTrjetALaFoisCamion3(){

        //ARRANGE
        Checker checker = new Checker(solution);

        TrajetFixe trajet1 = new TrajetFixe(256,new Ville(1,"Paris"),new Ville(1,"lyon"),8.0,new ArrayList<Double>(),null,new Jour(0,"Jeudi"),LocalTime.of(20,0));
        solution.getTrajets().add(trajet1);
        solution.getCamionsTrajets().get(1).add(256);

        TrajetFixe trajet2 = new TrajetFixe(257,new Ville(1,"Paris"),new Ville(1,"lyon"),1.0,new ArrayList<Double>(),null,new Jour(0,"Jeudi"),LocalTime.of(22,0));
        solution.getTrajets().add(trajet2);
        solution.getCamionsTrajets().get(1).add(257);

        // ACT
        boolean resultat =  checker.unTrajetALaFoisCamions();

        //ASSERT
        assertEquals(false,resultat);

    }

    @Test
    public void testUnTrjetALaFoisCamion4(){

        //ARRANGE
        Checker checker = new Checker(solution);

        TrajetFixe trajet1 = new TrajetFixe(256,new Ville(1,"Paris"),new Ville(1,"lyon"),8.0,new ArrayList<Double>(),null,new Jour(0,"Jeudi"),LocalTime.of(20,0));
        solution.getTrajets().add(trajet1);
        solution.getCamionsTrajets().get(1).add(256);

        TrajetFixe trajet2 = new TrajetFixe(257,new Ville(1,"Paris"),new Ville(1,"lyon"),3.0,new ArrayList<Double>(),null,new Jour(0,"Jeudi"),LocalTime.of(22,0));
        solution.getTrajets().add(trajet2);
        solution.getCamionsTrajets().get(1).add(257);

        // ACT
        boolean resultat =  checker.unTrajetALaFoisCamions();

        //ASSERT
        assertEquals(false,resultat);

    }

    @Test
    public void testUnTrjetALaFoisCamion5(){

        //ARRANGE
        Checker checker = new Checker(solution);

        TrajetFixe trajet1 = new TrajetFixe(256,new Ville(1,"Paris"),new Ville(1,"lyon"),8.0,new ArrayList<Double>(),null,new Jour(4,"Jeudi"),LocalTime.of(8,0));
        solution.getTrajets().add(trajet1);
        solution.getCamionsTrajets().get(1).add(256);

        TrajetFixe trajet2 = new TrajetFixe(257,new Ville(1,"Paris"),new Ville(1,"lyon"),2.0,new ArrayList<Double>(),null,new Jour(4,"Jeudi"),LocalTime.of(22,0));
        solution.getTrajets().add(trajet2);
        solution.getCamionsTrajets().get(1).add(257);

        // ACT
        boolean resultat =  checker.unTrajetALaFoisCamions();

        //ASSERT
        assertEquals(true,resultat);

    }

    @Test
    public void testUnTrjetALaFoisCamion6(){

        //ARRANGE
        Checker checker = new Checker(solution);

        TrajetFixe trajet1 = new TrajetFixe(256,new Ville(1,"Paris"),new Ville(1,"lyon"),8.0,new ArrayList<Double>(),null,new Jour(4,"Jeudi"),LocalTime.of(20,0));
        solution.getTrajets().add(trajet1);
        solution.getCamionsTrajets().get(1).add(256);

        TrajetFixe trajet2 = new TrajetFixe(257,new Ville(1,"Paris"),new Ville(1,"lyon"),2.0,new ArrayList<Double>(),null,new Jour(5,"Vendredi"),LocalTime.of(1,0));
        solution.getTrajets().add(trajet2);
        solution.getCamionsTrajets().get(1).add(257);

        // ACT
        boolean resultat =  checker.unTrajetALaFoisCamions();

        //ASSERT
        assertEquals(false,resultat);

    }

    @Test
    public void testUnTrajetCamionChauffeur1(){

        //ARRANGE
        Checker checker = new Checker(solution);

        // ACT
        boolean resultat =  checker.trajetCamionChauffeur();

        //ASSERT
        assertEquals(true,resultat);
    }

    @Test
    public void testUnTrajetCamionChauffeur2(){

        //ARRANGE
        Checker checker = new Checker(solution);

        TrajetFixe trajet1 = new TrajetFixe(256,new Ville(1,"Paris"),new Ville(1,"lyon"),8.0,new ArrayList<Double>(),null,new Jour(4,"Jeudi"),LocalTime.of(20,0));
        solution.getTrajets().add(trajet1);

        // ACT
        boolean resultat =  checker.trajetCamionChauffeur();

        //ASSERT
        assertEquals(false,resultat);
    }

    @Test
    public void testUnTrajetCamionChauffeur3(){

        //ARRANGE
        Checker checker = new Checker(solution);

        TrajetFixe trajet1 = new TrajetFixe(256,new Ville(1,"Paris"),new Ville(1,"lyon"),8.0,new ArrayList<Double>(),null,new Jour(4,"Jeudi"),LocalTime.of(20,0));
        solution.getTrajets().add(trajet1);
        solution.getChauffeursTrajets().get(18).add(256);

        // ACT
        boolean resultat =  checker.trajetCamionChauffeur();

        //ASSERT
        assertEquals(false,resultat);
    }

    @Test
    public void testUnTrajetCamionChauffeur4(){

        //ARRANGE
        Checker checker = new Checker(solution);

        TrajetFixe trajet1 = new TrajetFixe(256,new Ville(1,"Paris"),new Ville(1,"lyon"),8.0,new ArrayList<Double>(),null,new Jour(4,"Jeudi"),LocalTime.of(20,0));
        solution.getTrajets().add(trajet1);
        solution.getChauffeursTrajets().get(18).add(256);
        solution.getCamionsTrajets().get(1).add(256);

        // ACT
        boolean resultat =  checker.trajetCamionChauffeur();

        //ASSERT
        assertEquals(true,resultat);
    }

    @Test
    public void testReposHebdomadaireVilleRattachement1(){

        //ARRANGE
        Checker checker = new Checker(solution);

        // ACT
        boolean resultat =  checker.reposHebdomadaireVilleRattachement();

        //ASSERT
        assertEquals(true,resultat);
    }

    @Test
    public void testReposHebdomadaireVilleRattachement2(){

        //ARRANGE
        Checker checker = new Checker(solution);

        TrajetFixe trajet1 = new TrajetFixe(256,new Ville(1,"Paris"),new Ville(4,"Luxembourg"),24.0,new ArrayList<Double>(),null,new Jour(4,"Jeudi"),LocalTime.of(20,0));
        solution.getTrajets().add(trajet1);
        solution.getChauffeursTrajets().get(18).add(256);

        // ACT
        boolean resultat =  checker.reposHebdomadaireVilleRattachement();

        //ASSERT
        assertEquals(false,resultat);
    }

}
