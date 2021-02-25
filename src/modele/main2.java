package modele;

import org.junit.jupiter.api.BeforeEach;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class main2 {

    public static void main(String[] args) {
        Plannification plannification;
        MoteurDeResolution moteurDeResolution;

        //Ville
        Ville paris = new Ville(1, "Paris");
        Ville marseille = new Ville(2, "Marseille");
        Ville lyon = new Ville(3, "Lyon");

        Jour jour = new Jour(0,"Lundi");

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
            moteurDeResolution = new MoteurDeResolution(plannification);

        //ARRANGE
        HashMap<Integer,ArrayList<Integer>> camionTrajet = new HashMap<Integer,ArrayList<Integer>>();
        Solution solution = new Solution();
        ArrayList<Integer> idTrajet = new ArrayList<Integer>();
        ArrayList<TrajetFixe> trajets = new ArrayList<TrajetFixe>();

        idTrajet.add(100);
        idTrajet.add(1);
        camionTrajet.put(1,idTrajet);

        plannification.getTrajetsFixe().get(0).setJourDepart(Jour.getJourById(5));
        plannification.getTrajetsFixe().get(0).setVilleArrivee(marseille);
        moteurDeResolution = new MoteurDeResolution(plannification);

        //ACT
        solution = moteurDeResolution.camionTrajets();
        Checker checker = new Checker(solution);
    }
}
