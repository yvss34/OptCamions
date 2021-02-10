package modele;

import org.junit.Before;
import org.junit.Test;
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

    @Before
    public void initSolution() {


        //Trajets

        String[] fenetreDeTemps = {"10.00-20.00"};
        TrajetNonFixe trajet2_1 = new TrajetNonFixe(1, paris, lyon, 4.0,  null,null, 2,fenetreDeTemps,1,1 );

        ArrayList<TrajetFixe> trajetFixeTableau= new ArrayList<TrajetFixe>();


        ArrayList<TrajetNonFixe> trajetNonFixeTableau= new ArrayList<TrajetNonFixe>();
        trajetNonFixeTableau.add(trajet2_1);
        plannification = new Plannification(10.0,40.0,12,14,50,4.5,11,9,54,24,trajetNonFixeTableau,trajetFixeTableau);
    }
}
