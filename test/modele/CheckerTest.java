package modele;

import org.junit.Test;
import org.junit.jupiter.api.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class CheckerTest {

    Solution solution;
    Checker checker;

    @BeforeEach
    public void initSolution(){
    }

    @Test
    public void testDureeConduiteContinue(){

        //ARRANGE

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

        //Trajets
        TrajetFixe trajet1_1 = new TrajetFixe(1,paris,lyon,4.0,new ArrayList<Double>(),null,new Jour(1,"Lundi"),LocalTime.of(8,0));
        TrajetFixe trajet1_2 = new TrajetFixe(2,lyon,paris,4.0,new ArrayList<Double>(),null,new Jour(2,"Mardi"),LocalTime.of(8,0));
        TrajetFixe trajet1_3 = new TrajetFixe(3,paris,lyon,4.0,new ArrayList<Double>(),null,new Jour(3,"Mercredi"),LocalTime.of(8,0));
        TrajetFixe trajet1_4 = new TrajetFixe(4,lyon,paris,4.0,new ArrayList<Double>(),null,new Jour(4,"Jeudi"),LocalTime.of(8,0));

        TrajetFixe trajet2_1 = new TrajetFixe(5,marseille,paris,8.0,new ArrayList<Double>(),null,new Jour(1,"Lundi"),LocalTime.of(8,0));
        TrajetFixe trajet2_2 = new TrajetFixe(6,paris,marseille,8.0,new ArrayList<Double>(),null,new Jour(2,"Mardi"),LocalTime.of(8,0));
        TrajetFixe trajet2_3 = new TrajetFixe(7,marseille,paris,8.0,new ArrayList<Double>(),null,new Jour(3,"Mercredi"),LocalTime.of(8,0));
        TrajetFixe trajet2_4 = new TrajetFixe(8,paris,marseille,8.0,new ArrayList<Double>(),null,new Jour(4,"Jeudi"),LocalTime.of(8,0));

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


        ArrayList<TrajetFixe> trajets1= new ArrayList<TrajetFixe>();
        trajets1.add(trajet1_1);
        trajets1.add(trajet1_2);
        trajets1.add(trajet1_3);
        trajets1.add(trajet1_4);

        ArrayList<TrajetFixe> trajets2= new ArrayList<TrajetFixe>();
        trajets2.add(trajet2_1);
        trajets2.add(trajet2_2);
        trajets2.add(trajet2_3);
        trajets2.add(trajet2_4);

        //camionTrajets
        solution.setCamionsTrajets(new HashMap<Integer,ArrayList<TrajetFixe>>());
        solution.getCamionsTrajets().put(1,trajets1);
        solution.getCamionsTrajets().put(2,trajets2);

        //chauffeursTrajets
        solution.setChauffeursTrajets(new HashMap<Integer,ArrayList<TrajetFixe>>());
        solution.getChauffeursTrajets().put(18,trajets1);
        solution.getChauffeursTrajets().put(1424,trajets2);

        checker = new Checker(solution);

        //ACT
        boolean resultat = checker.dureeConduiteContinue();

        //ASSERT
        assertEquals(true,resultat);

    }


}
