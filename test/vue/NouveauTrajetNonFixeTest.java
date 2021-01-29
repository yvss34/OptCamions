package vue;

import modele.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class NouveauTrajetNonFixeTest {

    NouveauTrajetNonFixe nouveauTrajetNonFixe;
    Jour jour = new Jour(0,"Lundi");
    LocalTime heureDepart = LocalTime.of(8,15);
    InterfacePrincipale interfacePrincipale;
    @Before
    public void initSolution() {

        interfacePrincipale = new InterfacePrincipale("SymoneTest");
        nouveauTrajetNonFixe = new NouveauTrajetNonFixe("NouveauTrajetTest",interfacePrincipale);

        nouveauTrajetNonFixe.getVilleDepartComboBox().setSelectedItem(nouveauTrajetNonFixe.villes.get(2));
        nouveauTrajetNonFixe.getVilleArriveeComboBox().setSelectedItem(nouveauTrajetNonFixe.villes.get(3));
        System.out.println(nouveauTrajetNonFixe.getVilleArriveeComboBox().getSelectedItem());
        System.out.println(nouveauTrajetNonFixe.getVilleDepartComboBox().getSelectedItem());
        nouveauTrajetNonFixe.getTempsDeConduiteText().setText("4.0");
        nouveauTrajetNonFixe.getFrequenceParSemaineText().setText("2");
        nouveauTrajetNonFixe.getFenetreTempsDepartText().setText("10.00-20.00");
        nouveauTrajetNonFixe.getNombreTrajetMinText().setText("1");
        nouveauTrajetNonFixe.getNombreJoursMinText().setText("0");

    }

    @Test
    public void creationTrajetNonFixe() {

        //ARRANGE
        String[] fenetreDeTemps = {"10.00-20.00"};
        TrajetNonFixe trajet2_1 = new TrajetNonFixe(1, nouveauTrajetNonFixe.villes.get(2), nouveauTrajetNonFixe.villes.get(3), 4.0, null, 2,fenetreDeTemps,1,0 );

        // ACT
        nouveauTrajetNonFixe.getValiderButton().doClick();

        //ASSERT
        assertEquals(true,TrajetNonFixe.egale(nouveauTrajetNonFixe.getTrajet(),trajet2_1));

    }

    @Test
    public void envoiInterfacePrincipale() {
        // ACT
        nouveauTrajetNonFixe.getValiderButton().doClick();

        //ASSERT
        assertEquals(true,interfacePrincipale.getTrajetNonFixe().contains(nouveauTrajetNonFixe.getTrajet()));

    }

}
