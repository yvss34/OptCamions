package modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.management.relation.RoleUnresolvedList;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

class MoteurDeResolutionTestGrandeInstance {

    Plannification plannification;
    MoteurDeResolution moteurDeResolution;

    //Ville
    Ville paris = new Ville(1, "Paris");
    Ville marseille = new Ville(2, "Marseille");
    Ville lyon = new Ville(3, "Lyon");
    Ville toulouse = new Ville(4, "Toulouse");
    Ville bordeaux = new Ville(5, "Bordeaux");
    Ville montpellier = new Ville(6, "Montpellier");
    Ville nice = new Ville(7, "Nice");
    Ville nantes = new Ville(8, "Nantes");
    Ville narbonne = new Ville(9, "Narbonne");
    Ville tours = new Ville(10, "Tours");

    Jour lundi = new Jour(0,"Lundi");
    Jour mardi = new Jour(1,"Mardi");
    Jour mercredi = new Jour(2,"Mercredi");
    Jour jeudi = new Jour(3,"Jeudi");
    Jour vendredi = new Jour(4,"Vendredi");
    Jour samedi = new Jour(5,"Samedi");
    Jour dimanche = new Jour(6,"Dimanche");

    @BeforeEach
    public void initSolution() {
        //Trajets Fixes
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



        ArrayList<TrajetFixe> trajetFixeTableau= new ArrayList<TrajetFixe>();
        trajetFixeTableau.add(trajet1_1);
        trajetFixeTableau.add(trajet1_2);
        trajetFixeTableau.add(trajet1_3);

        trajetFixeTableau.add(trajet2_1);
        trajetFixeTableau.add(trajet2_2);
        trajetFixeTableau.add(trajet2_3);
        trajetFixeTableau.add(trajet2_4);
        trajetFixeTableau.add(trajet2_5);

        trajetFixeTableau.add(trajet3_1);
        trajetFixeTableau.add(trajet3_2);

        trajetFixeTableau.add(trajet4_1);
        trajetFixeTableau.add(trajet4_2);

        trajetFixeTableau.add(trajet5_1);
        trajetFixeTableau.add(trajet5_2);

        //Trajets Non fixe
        String[] fenetreDeTemps1 = {"10.00-15.00"};
        String[] fenetreDeTemps2 = {"8.00-10.00"};
        String[] fenetreDeTemps3 = {"9.00-12.00"};
        String[] fenetreDeTemps4 = {"14.00-17.00"};

        TrajetNonFixe trajet6_1 = new TrajetNonFixe(15, tours, montpellier, 7.0,  null,null, 2,fenetreDeTemps1,1,0 );
        TrajetNonFixe trajet6_2 = new TrajetNonFixe(16, marseille, nice, 4.0,  null,null, 4,fenetreDeTemps2,2,0 );
        TrajetNonFixe trajet6_3 = new TrajetNonFixe(16, nice, marseille, 4.0,  null,null, 4,fenetreDeTemps4,2,0 );
        TrajetNonFixe trajet6_4 = new TrajetNonFixe(16, marseille, narbonne, 4.0,  null,null, 4,fenetreDeTemps3,2,0 );
        TrajetNonFixe trajet6_5 = new TrajetNonFixe(16, narbonne, marseille, 4.0,  null,null, 4,fenetreDeTemps4,2,0 );

        ArrayList<TrajetNonFixe> trajetNonFixeTableau= new ArrayList<TrajetNonFixe>();
        trajetNonFixeTableau.add(trajet6_1);
        trajetNonFixeTableau.add(trajet6_2);
        trajetNonFixeTableau.add(trajet6_3);
        trajetNonFixeTableau.add(trajet6_4);
        trajetNonFixeTableau.add(trajet6_5);

        plannification = new Plannification(10.0,40.0,12,14,50,4.5,11,9,54,24,trajetNonFixeTableau,trajetFixeTableau);
        moteurDeResolution = new MoteurDeResolution(plannification);
    }

    @Test
    public void camionTrajets(){
        //ARRANGE
        Solution solution = new Solution();

        //ACT
        solution = moteurDeResolution.camionTrajets();

        Checker checker = new Checker(solution);

        //ASSERT
        if(solution != null){
            assertEquals(true,checker.verificationCamion());
        }
    }

    @Test
    public void camionTrajetsAleatoire(){
        //ARRANGE
        ArrayList<Solution> solutions = new ArrayList<Solution>();

        //ACT
        solutions = moteurDeResolution.camionTrajetsAleatoire(1000);

        System.out.println(solutions);

        if(!solutions.isEmpty()) {
            for (Solution solution : solutions) {
                Checker checker = new Checker(solution);
                assertEquals(true, checker.verificationCamion());
            }
        }
    }
}
