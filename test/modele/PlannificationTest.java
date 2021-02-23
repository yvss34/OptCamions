package modele;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vue.InterfacePrincipale;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class PlannificationTest {

    Plannification plannification;


    //Ville
    Ville paris = new Ville(1, "Paris");
    Ville marseille = new Ville(2, "Marseille");
    Ville lyon = new Ville(3, "Lyon");

    Jour jour = new Jour(0,"Lundi");

    @BeforeEach
    public void initSolution() {
        //Trajets
        String[] fenetreDeTemps = {"10.00-20.00"};
        TrajetNonFixe trajet2_1 = new TrajetNonFixe(0, paris, lyon, 4.0,  null,null, 1,fenetreDeTemps,0,0 );

        LocalTime heureDepart = LocalTime.of(8,15);
        TrajetFixe trajet1_1 = new TrajetFixe(1, lyon, paris, 4.0, new ArrayList<Double>(), null, jour, heureDepart);
        ArrayList<TrajetFixe> trajetFixeTableau= new ArrayList<TrajetFixe>();
        trajetFixeTableau.add(trajet1_1);


        ArrayList<TrajetNonFixe> trajetNonFixeTableau= new ArrayList<TrajetNonFixe>();
        trajetNonFixeTableau.add(trajet2_1);

        plannification = new Plannification(10.0,40.0,12,14,50,4.5,11,9,54,24,trajetNonFixeTableau,trajetFixeTableau);
    }

    @Test
    public void listeTrajetsStandard1(){
        //ARRANGE
        ArrayList<ArrayList<Integer>> trajetTrier = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> trajetTrierPlannification = new ArrayList<ArrayList<Integer>>();
        for(int i = 0;i<7;i++){
            ArrayList<Integer> trajet = new ArrayList<Integer>();
            trajet.add(0);
            trajet.add(0);
            trajet.add(i);
            trajetTrier.add(trajet);
        }

        ArrayList<Integer> trajet = new ArrayList<Integer>();
        trajet.add(1);
        trajet.add(-1);
        trajet.add(0);
        trajetTrier.add(trajet);

        //ACT
        trajetTrierPlannification = plannification.listeTrajetsStandard();

        //ASSERT
        assertEquals(trajetTrier,trajetTrierPlannification);

    }

    @Test
    public void triTrajetsStandard1(){
        //ARRANGE
        ArrayList<ArrayList<Integer>> trajetTrier = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> trajetTrierPlannification = new ArrayList<ArrayList<Integer>>();

        ArrayList<Integer> trajet = new ArrayList<Integer>();
        trajet.add(1);
        trajet.add(-1);
        trajet.add(0);
        trajetTrier.add(trajet);

        for(int i = 0;i<7;i++){
            trajet = new ArrayList<Integer>();
            trajet.add(0);
            trajet.add(0);
            trajet.add(i);
            trajetTrier.add(trajet);
        }


        //ACT
        trajetTrierPlannification = plannification.listeTrajetsStandard();
        plannification.triTrajetsStandard(trajetTrierPlannification);

        //ASSERT
        assertEquals(trajetTrier,trajetTrierPlannification);

    }

    @Test
    public void triTrajetsStandard2(){
        //ARRANGE
        LocalTime heureDepart2 = LocalTime.of(11,00);
        TrajetFixe trajet1_2 = new TrajetFixe(2, lyon, paris, 4.0, new ArrayList<Double>(), null, jour, heureDepart2);
        plannification.getTrajetsFixe().add(trajet1_2);

        ArrayList<ArrayList<Integer>> trajetTrier = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> trajetTrierPlannification = new ArrayList<ArrayList<Integer>>();

        ArrayList<Integer> trajet = new ArrayList<Integer>();
        trajet.add(1);
        trajet.add(-1);
        trajet.add(0);
        trajetTrier.add(trajet);

        trajet = new ArrayList<Integer>();
        trajet.add(0);
        trajet.add(0);
        trajet.add(0);
        trajetTrier.add(trajet);

        trajet = new ArrayList<Integer>();
        trajet.add(2);
        trajet.add(-1);
        trajet.add(0);
        trajetTrier.add(trajet);

        for(int i = 1;i<7;i++){
            trajet = new ArrayList<Integer>();
            trajet.add(0);
            trajet.add(0);
            trajet.add(i);
            trajetTrier.add(trajet);
        }


        //ACT
        trajetTrierPlannification = plannification.listeTrajetsStandard();
        plannification.triTrajetsStandard(trajetTrierPlannification);

        //ASSERT
        assertEquals(trajetTrier,trajetTrierPlannification);

    }

    @Test
    public void triTrajetsStandard3(){

        //ARRANGE
        String[] fenetreDeTemps = {"12.00-20.00"};
        TrajetNonFixe trajetNonFixe = new TrajetNonFixe(2, paris, lyon, 4.0,  null,null, 3,fenetreDeTemps,0,0 );
        plannification.getTrajetsNonFixe().add(trajetNonFixe);

        ArrayList<ArrayList<Integer>> trajetTrier = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> trajetTrierPlannification = new ArrayList<ArrayList<Integer>>();

        ArrayList<Integer> trajet = new ArrayList<Integer>();
        trajet.add(1);
        trajet.add(-1);
        trajet.add(0);
        trajetTrier.add(trajet);


        for(int i = 0;i<7;i++){

            trajet = new ArrayList<Integer>();
            trajet.add(0);
            trajet.add(0);
            trajet.add(i);
            trajetTrier.add(trajet);

            if(i<5){
                trajet = new ArrayList<Integer>();
                trajet.add(2);
                trajet.add(0);
                trajet.add(i);
                trajetTrier.add(trajet);
            }

            if(i>=1 && i<6){
                trajet = new ArrayList<Integer>();
                trajet.add(2);
                trajet.add(1);
                trajet.add(i);
                trajetTrier.add(trajet);
            }

            if(i>=2 && i<7){
                trajet = new ArrayList<Integer>();
                trajet.add(2);
                trajet.add(2);
                trajet.add(i);
                trajetTrier.add(trajet);
            }
        }

        //ACT
        trajetTrierPlannification = plannification.listeTrajetsStandard();
        plannification.triTrajetsStandard(trajetTrierPlannification);

        //ASSERT
        assertEquals(trajetTrier,trajetTrierPlannification);

    }

    @Test
    public void MAJ1(){
        //ARRANGE
        ArrayList<ArrayList<Integer>> trajetTrier = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> trajetTrierPlannification = new ArrayList<ArrayList<Integer>>();

        ArrayList<Integer> trajet = new ArrayList<Integer>();

        for(int i = 0;i<7;i++){
            trajet = new ArrayList<Integer>();
            trajet.add(0);
            trajet.add(0);
            trajet.add(i);
            trajetTrier.add(trajet);
        }

        //ACT
        trajetTrierPlannification = plannification.listeTrajetsStandard();
        plannification.triTrajetsStandard(trajetTrierPlannification);
        plannification.miseAJour(0,trajetTrierPlannification);

        //ASSERT
        assertEquals(trajetTrier,trajetTrierPlannification);

    }

    @Test
    public void MAJ2(){
        //ARRANGE
        ArrayList<ArrayList<Integer>> trajetTrier = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> trajetTrierPlannification = new ArrayList<ArrayList<Integer>>();

        ArrayList<Integer> trajet = new ArrayList<Integer>();
        trajet.add(1);
        trajet.add(-1);
        trajet.add(0);
        trajetTrier.add(trajet);

        //ACT
        trajetTrierPlannification = plannification.listeTrajetsStandard();
        plannification.triTrajetsStandard(trajetTrierPlannification);
        plannification.miseAJour(2,trajetTrierPlannification);

        //ASSERT
        assertEquals(trajetTrier,trajetTrierPlannification);

    }

    @Test
    public void MAJ3(){

        //ARRANGE
        String[] fenetreDeTemps = {"12.00-20.00"};
        TrajetNonFixe trajetNonFixe = new TrajetNonFixe(2, paris, lyon, 4.0,  null,null, 3,fenetreDeTemps,0,0 );
        plannification.getTrajetsNonFixe().add(trajetNonFixe);

        ArrayList<ArrayList<Integer>> trajetTrier = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> trajetTrierPlannification = new ArrayList<ArrayList<Integer>>();

        ArrayList<Integer> trajet = new ArrayList<Integer>();
        trajet.add(1);
        trajet.add(-1);
        trajet.add(0);
        trajetTrier.add(trajet);


        for(int i = 0;i<7;i++){

            trajet = new ArrayList<Integer>();
            trajet.add(0);
            trajet.add(0);
            trajet.add(i);
            trajetTrier.add(trajet);

            if(i<4){
                trajet = new ArrayList<Integer>();
                trajet.add(2);
                trajet.add(0);
                trajet.add(i);
                trajetTrier.add(trajet);
            }

            if(i>=5 && i<7){
                trajet = new ArrayList<Integer>();
                trajet.add(2);
                trajet.add(2);
                trajet.add(i);
                trajetTrier.add(trajet);
            }
        }

        //ACT
        trajetTrierPlannification = plannification.listeTrajetsStandard();
        plannification.triTrajetsStandard(trajetTrierPlannification);
        plannification.miseAJour(16,trajetTrierPlannification);

        //ASSERT
        assertEquals(trajetTrier,trajetTrierPlannification);

    }

    @Test
    public void camionTrajets1(){
        //ARRANGE
        HashMap<Integer,ArrayList<Integer>> camionTrajet = new HashMap<Integer,ArrayList<Integer>>();
        HashMap<Integer,ArrayList<Integer>> camionTrajetTest = new HashMap<Integer,ArrayList<Integer>>();
        ArrayList<Integer> idTrajet = new ArrayList<Integer>();
        ArrayList<TrajetFixe> trajets = new ArrayList<TrajetFixe>();

        idTrajet.add(1);
        idTrajet.add(100);
        camionTrajet.put(1,idTrajet);

        //ACT
        camionTrajetTest = plannification.camionTrajets(trajets);

        //ASSERT
        assertEquals(camionTrajet,camionTrajetTest);

    }

    @Test
    public void camionTrajets2(){
        //ARRANGE
        HashMap<Integer,ArrayList<Integer>> camionTrajet = new HashMap<Integer,ArrayList<Integer>>();
        HashMap<Integer,ArrayList<Integer>> camionTrajetTest = new HashMap<Integer,ArrayList<Integer>>();
        ArrayList<Integer> idTrajet = new ArrayList<Integer>();
        ArrayList<TrajetFixe> trajets = new ArrayList<TrajetFixe>();

        idTrajet.add(100);
        idTrajet.add(1);
        camionTrajet.put(1,idTrajet);

        plannification.getTrajetsFixe().get(0).setJourDepart(Jour.getJourById(5));

        //ACT
        camionTrajetTest = plannification.camionTrajets(trajets);

        //ASSERT
        assertEquals(camionTrajet,camionTrajetTest);

    }

    @Test
    public void camionTrajets3(){
        //ARRANGE
        ArrayList<HashMap<Integer,ArrayList<Integer>>> camionTrajetTest = new ArrayList<HashMap<Integer,ArrayList<Integer>>>();
        //ACT
        camionTrajetTest = plannification.camionTrajetsAleatoire(100);

        //ASSERT
        assertEquals(null,camionTrajetTest);


    }
}
