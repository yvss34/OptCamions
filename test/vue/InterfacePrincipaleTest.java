package vue;

import modele.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class InterfacePrincipaleTest {

    InterfacePrincipale interfacePrincipale;
    Plannification plannification;

    @Before
    public void initSolution() throws IOException {

        interfacePrincipale = new InterfacePrincipale("SymoneTest");

        interfacePrincipale.getNombreHeureMinText().setText("10");
        interfacePrincipale.getNombreHeureMaxText().setText("40");
        interfacePrincipale.getCoutHoraireJourText().setText("12");
        interfacePrincipale.getCoutHoraireNuitText().setText("14");
        interfacePrincipale.getCoutHottelerieText().setText("50");
        interfacePrincipale.getNombreHeureConduiteMaximumText().setText("4.5");
        interfacePrincipale.getTempsReposJournalierText().setText("11");
        interfacePrincipale.getNombreHeuresConduiteJournaliereText().setText("9");
        interfacePrincipale.getNombreHeuresHebdomadaireText().setText("54");
        interfacePrincipale.getDureeReposHebdomadaireText().setText("24");

        //Ville
        Ville paris = new Ville(1, "Paris");
        Ville marseille = new Ville(2, "Marseille");
        Ville lyon = new Ville(3, "Lyon");

        //Trajets
        TrajetFixe trajet1_1 = new TrajetFixe(1, paris, lyon, 4.0, new ArrayList<Double>(), null, new Jour(0, "Lundi"), LocalTime.of(8, 0));
        TrajetFixe trajet1_2 = new TrajetFixe(2, lyon, paris, 4.0, new ArrayList<Double>(), null, new Jour(1, "Mardi"), LocalTime.of(8, 0));
        TrajetFixe trajet1_3 = new TrajetFixe(3, paris, lyon, 4.0, new ArrayList<Double>(), null, new Jour(2, "Mercredi"), LocalTime.of(8, 0));
        TrajetFixe trajet1_4 = new TrajetFixe(4, lyon, paris, 4.0, new ArrayList<Double>(), null, new Jour(3, "Jeudi"), LocalTime.of(8, 0));
        interfacePrincipale.getTrajetFixe().add(trajet1_1);
        interfacePrincipale.getTrajetFixe().add(trajet1_2);
        interfacePrincipale.getTrajetFixe().add(trajet1_3);
        interfacePrincipale.getTrajetFixe().add(trajet1_4);

        String[] fenetreDeTemps = {"10.00-20.00"};
        TrajetNonFixe trajet2_1 = new TrajetNonFixe(3, paris, lyon, 4.0,  null,null, 2,fenetreDeTemps,1,0 );
        TrajetNonFixe trajet2_2 = new TrajetNonFixe(4, lyon, paris, 4.0,  null,null, 3,fenetreDeTemps,2,0 );
        interfacePrincipale.getTrajetNonFixe().add(trajet2_1);
        interfacePrincipale.getTrajetNonFixe().add(trajet2_2);

        ArrayList<TrajetFixe> trajetFixeTableau= new ArrayList<TrajetFixe>();
        trajetFixeTableau.add(trajet1_1);
        trajetFixeTableau.add(trajet1_2);
        trajetFixeTableau.add(trajet1_3);
        trajetFixeTableau.add(trajet1_4);

        ArrayList<TrajetNonFixe> trajetNonFixeTableau= new ArrayList<TrajetNonFixe>();
        trajetNonFixeTableau.add(trajet2_1);
        trajetNonFixeTableau.add(trajet2_2);
        plannification = new Plannification(10.0,40.0,12,14,50,4.5,11,9,54,24,trajetNonFixeTableau,trajetFixeTableau);

    }

    @Test
    public void creationPlannification() {

        // ACT
        interfacePrincipale.getResolutionButton().doClick();

        //ASSERT
        assertEquals(true, Plannification.egale(interfacePrincipale.getPlannification(),plannification));

    }


}
