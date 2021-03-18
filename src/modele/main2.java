package modele;

import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.time.LocalTime;
import java.util.*;

public class main2 {


    public static void main(String[] args) throws IOException {Plannification plannification;
//        MoteurDeResolutionPrototype moteurDeResolution;
//
//        //Ville
//        Ville paris = new Ville(1, "Paris");
//        Ville marseille = new Ville(2, "Marseille");
//        Ville lyon = new Ville(3, "Lyon");
//        Ville bordeaux = new Ville(4, "Bordeaux");
//        Ville toulouse = new Ville(5, "Toulouse");
//
//        Jour jour = new Jour(0,"Lundi");
//
//        //Trajets
//        String[] fenetreDeTemps = {"8.00-10.00"};
//        HashMap<Ville,Double> listeVilleStop = new HashMap<Ville,Double>();
//        listeVilleStop.put(lyon,5.0);
//        TrajetNonFixe trajet2_1 = new TrajetNonFixe(0, paris, marseille, 9.0,  null,listeVilleStop, 1,fenetreDeTemps,0,0 );
//
//        LocalTime heureDepart = LocalTime.of(14,00);
//        TrajetFixe trajet1_1 = new TrajetFixe(1, bordeaux, toulouse, 5.0, new ArrayList<Double>(), null, jour, heureDepart);
//        ArrayList<TrajetFixe> trajetFixeTableau= new ArrayList<TrajetFixe>();
//        trajetFixeTableau.add(trajet1_1);
//
//
//        ArrayList<TrajetNonFixe> trajetNonFixeTableau= new ArrayList<TrajetNonFixe>();
//        trajetNonFixeTableau.add(trajet2_1);
//
//        plannification = new Plannification(2.0,45,12,14,50,4.5,9,9,54,48,trajetNonFixeTableau,trajetFixeTableau);
//        moteurDeResolution = new MoteurDeResolutionPrototype(plannification);
//
//        ArrayList<Solution> solutions = moteurDeResolution.camionTrajetsAleatoire(1000);
//        solutions = moteurDeResolution.chauffeurTrajets(solutions,100000);
//        Solution solution = moteurDeResolution.optimisationCout(solutions);
//        solution.creationCsv();

        LocalTime localTime1 = LocalTime.of(8,0);
        LocalTime localTime2 = LocalTime.of(8,0);

        System.out.println(localTime1.compareTo(localTime2) == 0);

    }
}
