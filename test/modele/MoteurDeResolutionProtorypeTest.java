package modele;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class MoteurDeResolutionProtorypeTest {

    Plannification plannification;
    MoteurDeResolutionPrototype moteurDeResolution;

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


    @Test
    public void listeTrajetsUnitaire1(){
        //ARRANGE

        HashMap<Ville,Double> listeVilleStop = new HashMap<Ville,Double>();
        listeVilleStop.put(lyon,5.0);

        TrajetFixe trajet1_1 = new TrajetFixe(1, paris, marseille, 8.0, new ArrayList<Double>(), listeVilleStop, new Jour(0, "Lundi"), LocalTime.of(8, 0));
        listeVilleStop = new HashMap<Ville,Double>();
        listeVilleStop.put(lyon,5.0);
        listeVilleStop.put(marseille,9.0);
        TrajetFixe trajet1_2 = new TrajetFixe(2, paris, toulouse, 14.0, new ArrayList<Double>(), listeVilleStop, new Jour(0, "Lundi"), LocalTime.of(8, 0));
        TrajetFixe trajet1_3 = new TrajetFixe(3, marseille, lyon, 4.0, new ArrayList<Double>(), null, new Jour(0, "Lundi"), LocalTime.of(20, 0));

        ArrayList<TrajetFixe> trajetFixeTableau= new ArrayList<TrajetFixe>();
        trajetFixeTableau.add(trajet1_1);
        trajetFixeTableau.add(trajet1_2);
        trajetFixeTableau.add(trajet1_3);

        TrajetFixe trajetFixe;
        ArrayList<TrajetFixe> trajetFixeTestTableau= new ArrayList<TrajetFixe>();

        ArrayList<Double> tempsPause = new ArrayList<Double>();
        tempsPause.add(4.5);
        tempsPause.add(5.0);
        trajetFixe = new TrajetFixe(0, paris, lyon, 5.0, tempsPause, null, new Jour(0, "Lundi"), LocalTime.of(8, 0));
        trajetFixeTestTableau.add(trajetFixe);

        tempsPause = new ArrayList<Double>();
        tempsPause.add(4.5);
        trajetFixe = new TrajetFixe(1, lyon, marseille, 3.0, tempsPause, null, new Jour(0, "Lundi"), LocalTime.of(14, 30));
        trajetFixeTestTableau.add(trajetFixe);

        tempsPause = new ArrayList<Double>();
        tempsPause.add(4.5);
        tempsPause.add(5.0);
        trajetFixe = new TrajetFixe(2, paris, lyon, 5.0, tempsPause, null, new Jour(0, "Lundi"), LocalTime.of(8, 0));
        trajetFixeTestTableau.add(trajetFixe);

        tempsPause = new ArrayList<Double>();
        tempsPause.add(4.5);
        trajetFixe = new TrajetFixe(3, lyon, marseille, 4.0, tempsPause, null, new Jour(0, "Lundi"), LocalTime.of(14, 30));
        trajetFixeTestTableau.add(trajetFixe);

        tempsPause = new ArrayList<Double>();
        tempsPause.add(4.5);
        tempsPause.add(5.0);
        trajetFixe = new TrajetFixe(4, marseille, toulouse, 5.0, tempsPause, null, new Jour(0, "Lundi"), LocalTime.of(19, 15));
        trajetFixeTestTableau.add(trajetFixe);

        tempsPause = new ArrayList<Double>();
        tempsPause.add(4.5);
        trajetFixe = new TrajetFixe(5, marseille, lyon, 4.0, tempsPause, null, new Jour(0, "Lundi"), LocalTime.of(20, 0));
        trajetFixeTestTableau.add(trajetFixe);


        //ACT
        plannification = new Plannification(10.0,40.0,12,14,50,4.5,11,9,54,24,null,trajetFixeTableau);
        moteurDeResolution = new MoteurDeResolutionPrototype(plannification);
        HashMap<Integer,ArrayList<Integer>> map  = new HashMap<Integer,ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();

        list.add(1);
        list.add(2);
        list.add(3);

        map.put(1,list);

        Solution solution = new Solution(0,0,plannification,null,trajetFixeTableau,map,null);

        moteurDeResolution.listeTrajetsUnitaire(solution);

        //ASSERT
        assertEquals(6,trajetFixeTestTableau.size());
        for(int i = 0 ; i<6 ; i++){
            assertEquals(true,TrajetFixe.egale(solution.getTrajets().get(i),trajetFixeTestTableau.get(i)));
        }
    }

    @Test
    public void triListeTrajetFixe(){

        //Arrange
        TrajetFixe trajet1_1 = new TrajetFixe(1, paris, marseille, 8.0, new ArrayList<Double>(), null, new Jour(0, "Lundi"), LocalTime.of(8, 0));
        TrajetFixe trajet1_2 = new TrajetFixe(2, paris, toulouse, 8.0, new ArrayList<Double>(), null, new Jour(0, "Lundi"), LocalTime.of(9, 0));
        TrajetFixe trajet1_3 = new TrajetFixe(3, marseille, lyon, 4.0, new ArrayList<Double>(), null, new Jour(0, "Lundi"), LocalTime.of(20, 0));

        TrajetFixe trajet2_1 = new TrajetFixe(4, lyon, marseille, 4.0, new ArrayList<Double>(), null, new Jour(1, "Mardi"), LocalTime.of(1, 0));
        TrajetFixe trajet2_2 = new TrajetFixe(5, marseille, lyon, 4.0, new ArrayList<Double>(), null, new Jour(1, "Mardi"), LocalTime.of(3, 0));
        TrajetFixe trajet2_3 = new TrajetFixe(6, lyon, marseille, 4.0, new ArrayList<Double>(), null, new Jour(1, "Mardi"), LocalTime.of(4, 0));
        TrajetFixe trajet2_4 = new TrajetFixe(7, toulouse, paris, 5.0, new ArrayList<Double>(), null, new Jour(1, "Mardi"), LocalTime.of(5, 0));
        TrajetFixe trajet2_5 = new TrajetFixe(8, marseille, lyon, 4.0, new ArrayList<Double>(), null, new Jour(1, "Mardi"), LocalTime.of(9, 0));

        TrajetFixe trajet3_1 = new TrajetFixe(9, lyon, marseille, 4.0, new ArrayList<Double>(), null, new Jour(2, "Mercredi"), LocalTime.of(2, 0));
        TrajetFixe trajet3_2 = new TrajetFixe(10, marseille, paris, 8.0, new ArrayList<Double>(), null, new Jour(2, "Mercredi"), LocalTime.of(8, 30));

        TrajetFixe trajet4_1 = new TrajetFixe(11, lyon, paris, 4.0, new ArrayList<Double>(), null, new Jour(3, "Jeudi"), LocalTime.of(8, 0));
        TrajetFixe trajet4_2 = new TrajetFixe(12, lyon, paris, 4.0, new ArrayList<Double>(), null, new Jour(3, "Jeudi"), LocalTime.of(15, 0));

        TrajetFixe trajet5_1 = new TrajetFixe(13, paris, bordeaux, 5.0, new ArrayList<Double>(), null, new Jour(4, "Vendredi"), LocalTime.of(7, 0));
        TrajetFixe trajet5_2 = new TrajetFixe(14, bordeaux, paris, 5.0, new ArrayList<Double>(), null, new Jour(4, "Vendredi"), LocalTime.of(14, 30));



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

        ArrayList<TrajetFixe> trajetFixeTableauTest= new ArrayList<TrajetFixe>();
        trajetFixeTableauTest.add(trajet1_2);
        trajetFixeTableauTest.add(trajet1_3);
        trajetFixeTableauTest.add(trajet1_1);

        trajetFixeTableauTest.add(trajet4_1);
        trajetFixeTableauTest.add(trajet4_2);

        trajetFixeTableauTest.add(trajet5_1);
        trajetFixeTableauTest.add(trajet2_1);
        trajetFixeTableauTest.add(trajet2_3);
        trajetFixeTableauTest.add(trajet2_4);
        trajetFixeTableauTest.add(trajet2_5);
        trajetFixeTableauTest.add(trajet2_2);

        trajetFixeTableauTest.add(trajet3_2);
        trajetFixeTableauTest.add(trajet3_1);

        trajetFixeTableauTest.add(trajet5_2);

        //ACT
        plannification = new Plannification(10.0,40.0,12,14,50,4.5,11,9,54,24,null,trajetFixeTableau);
        moteurDeResolution = new MoteurDeResolutionPrototype(plannification);
        trajetFixeTableauTest = moteurDeResolution.triListeTrajetFixe(trajetFixeTableauTest);


        //ASSERT
        for(int i = 0 ; i<14 ; i++){
            assertEquals(true,TrajetFixe.egale(trajetFixeTableauTest.get(i),trajetFixeTableau.get(i)));
        }
    }

    @Test
    public void chauffeursTrajetsAleatoire(){
        //ARRANGE
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

        TrajetNonFixe trajet6_1 = new TrajetNonFixe(15, tours, montpellier, 7.0,  null,null, 10,fenetreDeTemps1,0,0 );
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
        moteurDeResolution = new MoteurDeResolutionPrototype(plannification);

        ArrayList<Solution> solutions = new ArrayList<Solution>();

        //ACT
        solutions = moteurDeResolution.camionTrajetsAleatoire(1000);


        if(!solutions.isEmpty()) {
            solutions = moteurDeResolution.chauffeurTrajets(solutions,100000);
            if(!solutions.isEmpty()) {
                System.out.println(solutions.size());
                for (Solution solution : solutions) {
                    System.out.println(solution);
                    Checker checker = new Checker(solution);
                    assertEquals(true,checker.verificationChauffeur());
                }
            }
        }
    }

 //   @Test
//    public void optimisationCout(){
//        //ARRANGE
//        //Trajets Fixes
//        TrajetFixe trajet1_1 = new TrajetFixe(1, paris, marseille, 8.0, new ArrayList<Double>(), null, new Jour(0, "Lundi"), LocalTime.of(8, 0));
//        TrajetFixe trajet1_2 = new TrajetFixe(2, paris, toulouse, 8.0, new ArrayList<Double>(), null, new Jour(0, "Lundi"), LocalTime.of(8, 0));
//        TrajetFixe trajet1_3 = new TrajetFixe(3, marseille, lyon, 4.0, new ArrayList<Double>(), null, new Jour(0, "Lundi"), LocalTime.of(20, 0));
//
//        TrajetFixe trajet2_1 = new TrajetFixe(4, lyon, marseille, 4.0, new ArrayList<Double>(), null, new Jour(1, "Mardi"), LocalTime.of(2, 0));
//        TrajetFixe trajet2_2 = new TrajetFixe(5, marseille, lyon, 4.0, new ArrayList<Double>(), null, new Jour(1, "Mardi"), LocalTime.of(11, 0));
//        TrajetFixe trajet2_3 = new TrajetFixe(6, lyon, marseille, 4.0, new ArrayList<Double>(), null, new Jour(1, "Mardi"), LocalTime.of(16, 0));
//        TrajetFixe trajet2_4 = new TrajetFixe(7, toulouse, paris, 5.0, new ArrayList<Double>(), null, new Jour(1, "Mardi"), LocalTime.of(8, 0));
//        TrajetFixe trajet2_5 = new TrajetFixe(8, marseille, lyon, 4.0, new ArrayList<Double>(), null, new Jour(1, "Mardi"), LocalTime.of(20, 0));
//
//        TrajetFixe trajet3_1 = new TrajetFixe(9, lyon, marseille, 4.0, new ArrayList<Double>(), null, new Jour(2, "Mercredi"), LocalTime.of(2, 0));
//        TrajetFixe trajet3_2 = new TrajetFixe(10, marseille, paris, 8.0, new ArrayList<Double>(), null, new Jour(2, "Mercredi"), LocalTime.of(8, 30));
//
//        TrajetFixe trajet4_1 = new TrajetFixe(11, lyon, paris, 4.0, new ArrayList<Double>(), null, new Jour(3, "Jeudi"), LocalTime.of(8, 0));
//        TrajetFixe trajet4_2 = new TrajetFixe(12, lyon, paris, 4.0, new ArrayList<Double>(), null, new Jour(3, "Jeudi"), LocalTime.of(15, 0));
//
//        TrajetFixe trajet5_1 = new TrajetFixe(13, paris, bordeaux, 5.0, new ArrayList<Double>(), null, new Jour(3, "Jeudi"), LocalTime.of(7, 0));
//        TrajetFixe trajet5_2 = new TrajetFixe(14, bordeaux, paris, 5.0, new ArrayList<Double>(), null, new Jour(3, "Jeudi"), LocalTime.of(14, 30));
//
//
//
//        ArrayList<TrajetFixe> trajetFixeTableau= new ArrayList<TrajetFixe>();
//        trajetFixeTableau.add(trajet1_1);
//        trajetFixeTableau.add(trajet1_2);
//        trajetFixeTableau.add(trajet1_3);
//
//        trajetFixeTableau.add(trajet2_1);
//        trajetFixeTableau.add(trajet2_2);
//        trajetFixeTableau.add(trajet2_3);
//        trajetFixeTableau.add(trajet2_4);
//        trajetFixeTableau.add(trajet2_5);
//
//        trajetFixeTableau.add(trajet3_1);
//        trajetFixeTableau.add(trajet3_2);
//
//        trajetFixeTableau.add(trajet4_1);
//        trajetFixeTableau.add(trajet4_2);
//
//        trajetFixeTableau.add(trajet5_1);
//        trajetFixeTableau.add(trajet5_2);
//
//        //Trajets Non fixe
//        String[] fenetreDeTemps1 = {"10.00-15.00"};
//        String[] fenetreDeTemps2 = {"8.00-10.00"};
//        String[] fenetreDeTemps3 = {"9.00-12.00"};
//        String[] fenetreDeTemps4 = {"14.00-17.00"};
//
//        TrajetNonFixe trajet6_1 = new TrajetNonFixe(15, tours, montpellier, 7.0,  null,null, 10,fenetreDeTemps1,0,0 );
//        TrajetNonFixe trajet6_2 = new TrajetNonFixe(16, marseille, nice, 4.0,  null,null, 4,fenetreDeTemps2,2,0 );
//        TrajetNonFixe trajet6_3 = new TrajetNonFixe(16, nice, marseille, 4.0,  null,null, 4,fenetreDeTemps4,2,0 );
//        TrajetNonFixe trajet6_4 = new TrajetNonFixe(16, marseille, narbonne, 4.0,  null,null, 4,fenetreDeTemps3,2,0 );
//        TrajetNonFixe trajet6_5 = new TrajetNonFixe(16, narbonne, marseille, 4.0,  null,null, 4,fenetreDeTemps4,2,0 );
//
//        ArrayList<TrajetNonFixe> trajetNonFixeTableau= new ArrayList<TrajetNonFixe>();
//        trajetNonFixeTableau.add(trajet6_1);
//        trajetNonFixeTableau.add(trajet6_2);
//        trajetNonFixeTableau.add(trajet6_3);
//        trajetNonFixeTableau.add(trajet6_4);
//        trajetNonFixeTableau.add(trajet6_5);
//
//        plannification = new Plannification(10.0,40.0,12,14,50,4.5,11,9,54,24,trajetNonFixeTableau,trajetFixeTableau);
//        moteurDeResolution = new MoteurDeResolutionPrototype(plannification);
//
//        ArrayList<Solution> solutions = new ArrayList<Solution>();
//
//        //ACT
//        solutions = moteurDeResolution.camionTrajetsAleatoire(1000);
//
//
//        if(!solutions.isEmpty()) {
//            solutions = moteurDeResolution.chauffeurTrajets(solutions,10000);
//            if(!solutions.isEmpty()) {
//                System.out.println(solutions.size());
//                Solution solution = moteurDeResolution.optimisationCout(solutions);
//                System.out.println(solution);
//            }
//        }
//    }


    @Test
    public void FixCamionsChauffeurs(){

        //ARRANGE
        ArrayList<Solution> solutions = new ArrayList<Solution>();
        MoteurDeResolutionPrototype moteurDeResolutionTest;

        //Ville
        Ville paris = new Ville(1, "Paris");
        Ville marseille = new Ville(2, "Marseille");
        Ville lyon = new Ville(3, "Lyon");
        Ville bordeaux = new Ville(4, "Bordeaux");
        Ville toulouse = new Ville(5, "Toulouse");

        Jour jour1 = new Jour(0,"Lundi");
        Jour jour2 = new Jour(1,"Mardi");
        Jour jour3 = new Jour(2,"Mercredi");


        //Trajets
        String[] fenetreDeTemps = {"8.00-10.00"};
        HashMap<Ville,Double> listeVilleStop = new HashMap<Ville,Double>();
        listeVilleStop.put(lyon,5.0);
        TrajetNonFixe trajet2_1 = new TrajetNonFixe(0, paris, marseille, 9.0,  null,listeVilleStop, 1,fenetreDeTemps,0,0 );

        LocalTime heureDepart1 = LocalTime.of(8,00);
        TrajetFixe trajet1_1 = new TrajetFixe(1, marseille, toulouse, 5.0, new ArrayList<Double>(), null, jour2, heureDepart1);


        LocalTime heureDepart2 = LocalTime.of(8,00);
        TrajetFixe trajet1_2 = new TrajetFixe(2, toulouse, paris, 5.0, new ArrayList<Double>(), null, jour3, heureDepart2);


        ArrayList<TrajetNonFixe> trajetNonFixeTableauTest= new ArrayList<TrajetNonFixe>();
        trajetNonFixeTableauTest.add(trajet2_1);

        ArrayList<TrajetFixe> trajetFixeTableauTest= new ArrayList<TrajetFixe>();
        trajetFixeTableauTest.add(trajet1_1);
        trajetFixeTableauTest.add(trajet1_2);

        plannification = new Plannification(2.0,45,12,14,50,4.5,9,9,54,48,trajetNonFixeTableauTest,trajetFixeTableauTest);
        moteurDeResolutionTest = new MoteurDeResolutionPrototype(plannification);

        //ACT
        solutions = moteurDeResolutionTest.camionTrajetsAleatoire(10);

        System.out.println(solutions.size());
        if(!solutions.isEmpty()) {
            for (Solution solution : solutions) {
                assertEquals(1, solution.getNbrCamions());
            }
        }
    }

    @Test
    public void ListeTrajetUnitaire(){

        //ARRANGE
        ArrayList<Solution> solutions = new ArrayList<Solution>();
        MoteurDeResolutionPrototype moteurDeResolutionTest;

        //Ville
        Ville paris = new Ville(1, "Paris");
        Ville marseille = new Ville(2, "Marseille");
        Ville lyon = new Ville(3, "Lyon");
        Ville bordeaux = new Ville(4, "Bordeaux");
        Ville toulouse = new Ville(5, "Toulouse");

        Jour jour1 = new Jour(0,"Lundi");
        Jour jour2 = new Jour(1,"Mardi");
        Jour jour3 = new Jour(2,"Mercredi");


        //Trajets
        String[] fenetreDeTemps = {"8.00-10.00"};
        HashMap<Ville,Double> listeVilleStop = new HashMap<Ville,Double>();
        listeVilleStop.put(lyon,5.0);
        TrajetNonFixe trajet2_1 = new TrajetNonFixe(0, paris, marseille, 9.0,  null,listeVilleStop, 1,fenetreDeTemps,0,0 );

        LocalTime heureDepart1 = LocalTime.of(8,00);
        HashMap<Ville,Double> listeVilleStop1 = new HashMap<Ville,Double>();
        listeVilleStop1.put(lyon,5.0);
        listeVilleStop1.put(bordeaux,8.0);
        TrajetFixe trajet1_1 = new TrajetFixe(1, marseille, toulouse, 10.0, new ArrayList<Double>(), listeVilleStop1, jour2, heureDepart1);


        LocalTime heureDepart2 = LocalTime.of(8,00);
        TrajetFixe trajet1_2 = new TrajetFixe(2, toulouse, paris, 5.0, new ArrayList<Double>(), null, jour3, heureDepart2);


        ArrayList<TrajetNonFixe> trajetNonFixeTableauTest= new ArrayList<TrajetNonFixe>();
        trajetNonFixeTableauTest.add(trajet2_1);

        ArrayList<TrajetFixe> trajetFixeTableauTest= new ArrayList<TrajetFixe>();
        trajetFixeTableauTest.add(trajet1_1);
        trajetFixeTableauTest.add(trajet1_2);

        plannification = new Plannification(2.0,45,12,14,50,4.5,9,9,54,48,trajetNonFixeTableauTest,trajetFixeTableauTest);
        moteurDeResolutionTest = new MoteurDeResolutionPrototype(plannification);

        //ACT
        solutions = moteurDeResolutionTest.camionTrajetsAleatoire(100);

        System.out.println(solutions.size());
        if(!solutions.isEmpty()) {
            for (Solution solution : solutions) {

                System.out.println(solution.getCamionsTrajets());
//                for(Trajet trajet : solution.getTrajets()){
//                    System.out.println(trajet);
//                }
//                System.out.println("2eme");
//                moteurDeResolutionTest.listeTrajetsUnitaire(solution);
//                for(Trajet trajet : solution.getTrajets()){
//                    System.out.println(trajet);
//                }
//                Checker checker = new Checker(solution);
//                //assertEquals(true, checker.verificationCamion());
//                assertEquals(1, solution.getNbrCamions());
            }
        }

    }

    @Test
    public void ChauffeursTrajets(){

        //ARRANGE
        ArrayList<Solution> solutions = new ArrayList<Solution>();
        MoteurDeResolutionPrototype moteurDeResolutionTest;

        //Ville
        Ville paris = new Ville(1, "Paris");
        Ville marseille = new Ville(2, "Marseille");
        Ville lyon = new Ville(3, "Lyon");
        Ville bordeaux = new Ville(4, "Bordeaux");
        Ville toulouse = new Ville(5, "Toulouse");

        Jour jour1 = new Jour(0,"Lundi");
        Jour jour2 = new Jour(1,"Mardi");
        Jour jour3 = new Jour(2,"Mercredi");


        //Trajets
        LocalTime heureDepart1 = LocalTime.of(8,00);
        LocalTime heureDepart2 = LocalTime.of(8,00);

        HashMap<Ville,Double> listeVilleStop = new HashMap<Ville,Double>();
        listeVilleStop.put(lyon,5.0);
        TrajetFixe trajet2_1 = new TrajetFixe(0, paris, marseille, 9.0,  null,listeVilleStop, jour1,heureDepart1);


        HashMap<Ville,Double> listeVilleStop1 = new HashMap<Ville,Double>();
//        listeVilleStop1.put(lyon,5.0);
//        listeVilleStop1.put(bordeaux,8.0);
        TrajetFixe trajet1_1 = new TrajetFixe(1, marseille, toulouse, 7.0, new ArrayList<Double>(), null, jour2, heureDepart1);



        TrajetFixe trajet1_2 = new TrajetFixe(2, toulouse, paris, 5.0, new ArrayList<Double>(), null, jour3, heureDepart2);

        ArrayList<TrajetFixe> trajetFixeTableauTest= new ArrayList<TrajetFixe>();
        trajetFixeTableauTest.add(trajet1_1);
        trajetFixeTableauTest.add(trajet1_2);
        trajetFixeTableauTest.add(trajet2_1);

        HashMap<Integer,ArrayList<Integer>> camionsTrajets = new HashMap<Integer,ArrayList<Integer>>();
        ArrayList<Integer> trajetCamions = new ArrayList<Integer>();
        trajetCamions.add(0);
        trajetCamions.add(1);
        trajetCamions.add(2);
        camionsTrajets.put(1,trajetCamions);

        plannification = new Plannification(2.0,45,12,14,50,4.5,9,9,54,48,null,trajetFixeTableauTest);
        Solution solutionTest = new Solution(1,1000,plannification,null,trajetFixeTableauTest,camionsTrajets,null);
        moteurDeResolutionTest = new MoteurDeResolutionPrototype(plannification);

        ArrayList<Solution> mySolutions = new ArrayList<Solution>();
        mySolutions.add(solutionTest);

        //ACT
        ArrayList<Solution> mySolution = moteurDeResolutionTest.chauffeurTrajets(mySolutions,10);


        System.out.println(mySolution.size());
        if(!mySolution.isEmpty()) {
            for (Solution solution : mySolution) {
                System.out.println(solution.getChauffeursTrajets());
            }
        }

    }

    @Test
    public void ChauffeursTrajets2(){

        //ARRANGE
        ArrayList<Solution> solutions = new ArrayList<Solution>();
        MoteurDeResolutionPrototype moteurDeResolutionTest;

        //Ville
        Ville paris = new Ville(1, "Paris");
        Ville marseille = new Ville(2, "Marseille");
        Ville lyon = new Ville(3, "Lyon");
        Ville bordeaux = new Ville(4, "Bordeaux");
        Ville toulouse = new Ville(5, "Toulouse");

        Jour jour1 = new Jour(0,"Lundi");
        Jour jour2 = new Jour(1,"Mardi");
        Jour jour3 = new Jour(2,"Mercredi");


        //Trajets
        LocalTime heureDepart1 = LocalTime.of(8,00);
        LocalTime heureDepart2 = LocalTime.of(8,00);

        HashMap<Ville,Double> listeVilleStop = new HashMap<Ville,Double>();
        listeVilleStop.put(lyon,5.0);
        TrajetFixe trajet2_1 = new TrajetFixe(0, paris, marseille, 9.0,  null,listeVilleStop, jour1,heureDepart1);


        HashMap<Ville,Double> listeVilleStop1 = new HashMap<Ville,Double>();
//        listeVilleStop1.put(lyon,5.0);
//        listeVilleStop1.put(bordeaux,8.0);
        TrajetFixe trajet1_1 = new TrajetFixe(1, marseille, toulouse, 7.0, new ArrayList<Double>(), null, jour2, heureDepart1);



        TrajetFixe trajet1_2 = new TrajetFixe(2, toulouse, paris, 5.0, new ArrayList<Double>(), null, jour3, heureDepart2);

        ArrayList<TrajetFixe> trajetFixeTableauTest= new ArrayList<TrajetFixe>();
        trajetFixeTableauTest.add(trajet1_1);
        trajetFixeTableauTest.add(trajet1_2);
        trajetFixeTableauTest.add(trajet2_1);

        HashMap<Integer,ArrayList<Integer>> camionsTrajets = new HashMap<Integer,ArrayList<Integer>>();
        ArrayList<Integer> trajetCamions = new ArrayList<Integer>();
        trajetCamions.add(0);
        trajetCamions.add(1);
        trajetCamions.add(2);
        camionsTrajets.put(1,trajetCamions);

        plannification = new Plannification(2.0,45,12,14,50,4.5,9,9,54,48,null,trajetFixeTableauTest);
        Solution solutionTest = new Solution(1,1000,plannification,null,trajetFixeTableauTest,camionsTrajets,null);
        moteurDeResolutionTest = new MoteurDeResolutionPrototype(plannification);

        ArrayList<Solution> mySolutions = new ArrayList<Solution>();


        mySolutions = moteurDeResolutionTest.camionTrajetsAleatoire(100);

        //ACT
        ArrayList<Solution> mySolution = moteurDeResolutionTest.chauffeurTrajets(mySolutions,10);


        System.out.println(mySolution.size());

    }

    @Test
    public void ChauffeursTrajets3(){
        //ARRANGE
        //Trajets Fixes
        TrajetFixe trajet1_1 = new TrajetFixe(1, paris, marseille, 8.0, new ArrayList<Double>(), null, new Jour(0, "Lundi"), LocalTime.of(8, 0));
        TrajetFixe trajet1_2 = new TrajetFixe(2, paris, toulouse, 8.0, new ArrayList<Double>(), null, new Jour(0, "Lundi"), LocalTime.of(8, 0));
        TrajetFixe trajet1_3 = new TrajetFixe(3, marseille, lyon, 4.0, new ArrayList<Double>(), null, new Jour(0, "Lundi"), LocalTime.of(20, 0));

        TrajetFixe trajet2_1 = new TrajetFixe(4, lyon, marseille, 4.0, new ArrayList<Double>(), null, new Jour(1, "Mardi"), LocalTime.of(2, 0));
         TrajetFixe trajet2_4 = new TrajetFixe(7, toulouse, paris, 8.0, new ArrayList<Double>(), null, new Jour(1, "Mardi"), LocalTime.of(8, 0));
        TrajetFixe trajet2_5 = new TrajetFixe(8, marseille, lyon, 4.0, new ArrayList<Double>(), null, new Jour(1, "Mardi"), LocalTime.of(20, 0));

        TrajetFixe trajet3_1 = new TrajetFixe(9, lyon, marseille, 4.0, new ArrayList<Double>(), null, new Jour(2, "Mercredi"), LocalTime.of(2, 0));
        TrajetFixe trajet3_2 = new TrajetFixe(10, marseille, paris, 8.0, new ArrayList<Double>(), null, new Jour(2, "Mercredi"), LocalTime.of(8, 30));

        TrajetFixe trajet4_1 = new TrajetFixe(11, paris, bordeaux, 4.0, new ArrayList<Double>(), null, new Jour(3, "Jeudi"), LocalTime.of(8, 0));
        TrajetFixe trajet4_2 = new TrajetFixe(12, bordeaux, toulouse, 4.0, new ArrayList<Double>(), null, new Jour(3, "Jeudi"), LocalTime.of(15, 0));

        TrajetFixe trajet5_1 = new TrajetFixe(13, toulouse, paris, 5.0, new ArrayList<Double>(), null, new Jour(3, "Jeudi"), LocalTime.of(7, 0));



        ArrayList<TrajetFixe> trajetFixeTableau= new ArrayList<TrajetFixe>();
        trajetFixeTableau.add(trajet1_1);
        trajetFixeTableau.add(trajet1_2);
        trajetFixeTableau.add(trajet1_3);

        trajetFixeTableau.add(trajet2_1);

        trajetFixeTableau.add(trajet2_4);
        trajetFixeTableau.add(trajet2_5);

        trajetFixeTableau.add(trajet3_1);
        trajetFixeTableau.add(trajet3_2);

        trajetFixeTableau.add(trajet4_1);
        trajetFixeTableau.add(trajet4_2);

        trajetFixeTableau.add(trajet5_1);


        //Trajets Non fixe
        String[] fenetreDeTemps1 = {"10.00-15.00"};
        String[] fenetreDeTemps2 = {"8.00-10.00"};
        String[] fenetreDeTemps3 = {"9.00-12.00"};
        String[] fenetreDeTemps4 = {"14.00-17.00"};

        TrajetNonFixe trajet6_1 = new TrajetNonFixe(15, tours, montpellier, 7.0,  null,null, 1,fenetreDeTemps1,1,0 );
        TrajetNonFixe trajet6_2 = new TrajetNonFixe(16, montpellier, tours, 7.0,  null,null, 1,fenetreDeTemps2,1,0 );
        TrajetNonFixe trajet6_4 = new TrajetNonFixe(16, marseille, narbonne, 4.0,  null,null, 4,fenetreDeTemps3,2,0 );
        TrajetNonFixe trajet6_5 = new TrajetNonFixe(16, narbonne, marseille, 4.0,  null,null, 4,fenetreDeTemps4,2,0 );

        ArrayList<TrajetNonFixe> trajetNonFixeTableau= new ArrayList<TrajetNonFixe>();
        trajetNonFixeTableau.add(trajet6_1);
        trajetNonFixeTableau.add(trajet6_2);
        trajetNonFixeTableau.add(trajet6_4);
        trajetNonFixeTableau.add(trajet6_5);

        plannification = new Plannification(10.0,40.0,12,14,50,4.5,11,9,54,48,trajetNonFixeTableau,trajetFixeTableau);
        moteurDeResolution = new MoteurDeResolutionPrototype(plannification);

        ArrayList<Solution> solutions = new ArrayList<Solution>();

        //ACT
        solutions = moteurDeResolution.camionTrajetsAleatoire(100);


        if(!solutions.isEmpty()) {
            System.out.println(solutions.size());
            System.out.println(solutions.get(0).getNbrCamions());

            solutions = moteurDeResolution.chauffeurTrajets(solutions,100);
            System.out.println(solutions.size());
            System.out.println(solutions.get(0).getNbrChauffeurs());
        }

    }

    }
