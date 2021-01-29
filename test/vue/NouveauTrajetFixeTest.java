package vue;

import modele.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class NouveauTrajetFixeTest {

    NouveauTrajetFixe nouveauTrajetFixe;
    Jour jour = new Jour(0,"Lundi");

    LocalTime heureDepart = LocalTime.of(8,15);
    InterfacePrincipale interfacePrincipale;
    @Before
    public void initSolution() {

        interfacePrincipale = new InterfacePrincipale("SymoneTest");
        nouveauTrajetFixe = new NouveauTrajetFixe("NouveauTrajetTest",interfacePrincipale);

        nouveauTrajetFixe.getVilleDepartComboBox().setSelectedItem(nouveauTrajetFixe.villes.get(2));
        nouveauTrajetFixe.getVilleArriveeComboBox().setSelectedItem(nouveauTrajetFixe.villes.get(3));
        nouveauTrajetFixe.getTempsDeConduiteText().setText("5");
        nouveauTrajetFixe.getJourDeDepartComboBox().setSelectedItem(jour);
        nouveauTrajetFixe.getHeureDeDepartText().setText("8.15");

    }

    @Test
    public void creationTrajetFixe() {

        //ARRANGE
        TrajetFixe trajet1_1 = new TrajetFixe(1, nouveauTrajetFixe.villes.get(2), nouveauTrajetFixe.villes.get(3), 4.0, new ArrayList<Double>(), null, jour, heureDepart);

        // ACT
        nouveauTrajetFixe.getValiderButton().doClick();

        //ASSERT
        assertEquals(true,TrajetFixe.egale(nouveauTrajetFixe.getTrajet(),trajet1_1));

    }

    @Test
    public void envoiInterfacePrincipale() {
        // ACT
        nouveauTrajetFixe.getValiderButton().doClick();

        //ASSERT
        assertEquals(true,interfacePrincipale.getTrajetFixe().contains(nouveauTrajetFixe.getTrajet()));

    }

}
