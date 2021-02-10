package modele;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TriTrajetNonFixeTest {

    TriTrajetNonFixe trajetNF;

    @Before
    public void initSolution() {

        //Ville
        Ville paris = new Ville(1, "Paris");
        Ville marseille = new Ville(2, "Marseille");
        Ville lyon = new Ville(3, "Lyon");

        //Trajets
        String[] fenetreDeTemps = {"10.00-20.00"};
        trajetNF = new TriTrajetNonFixe(1, paris, lyon, 4.0,  null,null, 1,fenetreDeTemps,0,0 );


    }

    @Test
    public void testTrierTrajetNonFixe1(){
        //ARRANGE

        ArrayList<Integer> premier = new ArrayList<Integer>();
        premier.add(0);
        premier.add(1);
        premier.add(2);
        premier.add(3);
        premier.add(4);
        premier.add(5);
        premier.add(6);

        // ACT
        trajetNF.liste();

        System.out.println(premier);
        System.out.println(trajetNF.getListeJourDepart().get(0));

        //ASSERT
        assertEquals(premier,trajetNF.getListeJourDepart().get(0));
    }

    @Test
    public void testTrierTrajetNonFixe2(){
        //ARRANGE
        trajetNF.setNombreTrajetWeekend(1);
        ArrayList<Integer> premier = new ArrayList<Integer>();
        premier.add(5);
        premier.add(6);

        // ACT
        trajetNF.liste();

        System.out.println(premier);
        System.out.println(trajetNF.getListeJourDepart().get(0));

        //ASSERT
        assertEquals(premier,trajetNF.getListeJourDepart().get(0));
    }

    @Test
    public void testTrierTrajetNonFixe3(){
        //ARRANGE
        trajetNF.setFrequenceSemaine(2);
        ArrayList<Integer> premier = new ArrayList<Integer>();
        premier.add(0);
        premier.add(1);
        premier.add(2);
        premier.add(3);
        premier.add(4);
        premier.add(5);
        ArrayList<Integer> second = new ArrayList<Integer>();
        second.add(1);
        second.add(2);
        second.add(3);
        second.add(4);
        second.add(5);
        second.add(6);

        // ACT
        trajetNF.liste();

        System.out.println(premier);
        System.out.println(trajetNF.getListeJourDepart().get(0));

        System.out.println(second);
        System.out.println(trajetNF.getListeJourDepart().get(1));

        //ASSERT
        assertEquals(premier,trajetNF.getListeJourDepart().get(0));
        assertEquals(second,trajetNF.getListeJourDepart().get(1));
    }

    @Test
    public void testTrierTrajetNonFixe4(){
        //ARRANGE
        trajetNF.setFrequenceSemaine(2);
        trajetNF.setNombreTrajetWeekend(1);
        ArrayList<Integer> premier = new ArrayList<Integer>();
        premier.add(0);
        premier.add(1);
        premier.add(2);
        premier.add(3);
        premier.add(4);
        premier.add(5);
        ArrayList<Integer> second = new ArrayList<Integer>();
        second.add(5);
        second.add(6);

        // ACT
        trajetNF.liste();

        System.out.println(premier);
        System.out.println(trajetNF.getListeJourDepart().get(0));

        System.out.println(second);
        System.out.println(trajetNF.getListeJourDepart().get(1));

        //ASSERT
        assertEquals(premier,trajetNF.getListeJourDepart().get(0));
        assertEquals(second,trajetNF.getListeJourDepart().get(1));
    }

    @Test
    public void testTrierTrajetNonFixe5(){
        //ARRANGE
        trajetNF.setFrequenceSemaine(2);
        trajetNF.setNombreTrajetWeekend(2);
        ArrayList<Integer> premier = new ArrayList<Integer>();
        premier.add(5);
        ArrayList<Integer> second = new ArrayList<Integer>();
        second.add(6);

        // ACT
        trajetNF.liste();

        System.out.println(premier);
        System.out.println(trajetNF.getListeJourDepart().get(0));

        System.out.println(second);
        System.out.println(trajetNF.getListeJourDepart().get(1));

        //ASSERT
        assertEquals(premier,trajetNF.getListeJourDepart().get(0));
        assertEquals(second,trajetNF.getListeJourDepart().get(1));
    }

    @Test
    public void testTrierTrajetNonFixe6(){
        //ARRANGE
        trajetNF.setFrequenceSemaine(2);
        trajetNF.setNbrJourMin(1);
        ArrayList<Integer> premier = new ArrayList<Integer>();
        premier.add(0);
        premier.add(1);
        premier.add(2);
        premier.add(3);
        premier.add(4);


        ArrayList<Integer> second = new ArrayList<Integer>();
        second.add(2);
        second.add(3);
        second.add(4);
        second.add(5);
        second.add(6);

        // ACT
        trajetNF.liste();

        System.out.println(premier);
        System.out.println(trajetNF.getListeJourDepart().get(0));

        System.out.println(second);
        System.out.println(trajetNF.getListeJourDepart().get(1));

        //ASSERT
        assertEquals(premier,trajetNF.getListeJourDepart().get(0));
        assertEquals(second,trajetNF.getListeJourDepart().get(1));
    }

    @Test
    public void testTrierTrajetNonFixe7(){
        //ARRANGE
        trajetNF.setFrequenceSemaine(2);
        trajetNF.setNbrJourMin(1);
        trajetNF.setNombreTrajetWeekend(1);
        ArrayList<Integer> premier = new ArrayList<Integer>();
        premier.add(0);
        premier.add(1);
        premier.add(2);
        premier.add(3);
        premier.add(4);


        ArrayList<Integer> second = new ArrayList<Integer>();
        second.add(5);
        second.add(6);

        // ACT
        trajetNF.liste();

        System.out.println(premier);
        System.out.println(trajetNF.getListeJourDepart().get(0));

        System.out.println(second);
        System.out.println(trajetNF.getListeJourDepart().get(1));

        //ASSERT
        assertEquals(premier,trajetNF.getListeJourDepart().get(0));
        assertEquals(second,trajetNF.getListeJourDepart().get(1));
    }

    @Test
    public void testTrierTrajetNonFixe8(){
        //ARRANGE
        trajetNF.setFrequenceSemaine(2);
        trajetNF.setNbrJourMin(2);
        ArrayList<Integer> premier = new ArrayList<Integer>();
        premier.add(0);
        premier.add(1);
        premier.add(2);
        premier.add(3);


        ArrayList<Integer> second = new ArrayList<Integer>();
        second.add(3);
        second.add(4);
        second.add(5);
        second.add(6);

        // ACT
        trajetNF.liste();

        System.out.println(premier);
        System.out.println(trajetNF.getListeJourDepart().get(0));

        System.out.println(second);
        System.out.println(trajetNF.getListeJourDepart().get(1));

        //ASSERT
        assertEquals(premier,trajetNF.getListeJourDepart().get(0));
        assertEquals(second,trajetNF.getListeJourDepart().get(1));
    }

    @Test
    public void testTrierTrajetNonFixe9(){
        //ARRANGE
        trajetNF.setFrequenceSemaine(2);
        trajetNF.setNbrJourMin(2);
        trajetNF.setNombreTrajetWeekend(1);
        ArrayList<Integer> premier = new ArrayList<Integer>();
        premier.add(0);
        premier.add(1);
        premier.add(2);
        premier.add(3);


        ArrayList<Integer> second = new ArrayList<Integer>();
        second.add(5);
        second.add(6);

        // ACT
        trajetNF.liste();

        System.out.println(premier);
        System.out.println(trajetNF.getListeJourDepart().get(0));

        System.out.println(second);
        System.out.println(trajetNF.getListeJourDepart().get(1));

        //ASSERT
        assertEquals(premier,trajetNF.getListeJourDepart().get(0));
        assertEquals(second,trajetNF.getListeJourDepart().get(1));
    }

    @Test
    public void testTrierTrajetNonFixe10(){
        //ARRANGE
        trajetNF.setFrequenceSemaine(3);
        ArrayList<Integer> premier = new ArrayList<Integer>();
        premier.add(0);
        premier.add(1);
        premier.add(2);
        premier.add(3);
        premier.add(4);


        ArrayList<Integer> second = new ArrayList<Integer>();
        second.add(1);
        second.add(2);
        second.add(3);
        second.add(4);
        second.add(5);

        ArrayList<Integer> troisieme = new ArrayList<Integer>();
        troisieme.add(2);
        troisieme.add(3);
        troisieme.add(4);
        troisieme.add(5);
        troisieme.add(6);

        // ACT
        trajetNF.liste();

        System.out.println(premier);
        System.out.println(trajetNF.getListeJourDepart().get(0));

        System.out.println(second);
        System.out.println(trajetNF.getListeJourDepart().get(1));

        System.out.println(troisieme);
        System.out.println(trajetNF.getListeJourDepart().get(2));

        //ASSERT
        assertEquals(premier,trajetNF.getListeJourDepart().get(0));
        assertEquals(second,trajetNF.getListeJourDepart().get(1));
        assertEquals(troisieme,trajetNF.getListeJourDepart().get(2));
    }

    @Test
    public void testTrierTrajetNonFixe11(){
        //ARRANGE
        trajetNF.setFrequenceSemaine(3);
        trajetNF.setNombreTrajetWeekend(1);
        ArrayList<Integer> premier = new ArrayList<Integer>();
        premier.add(0);
        premier.add(1);
        premier.add(2);
        premier.add(3);
        premier.add(4);


        ArrayList<Integer> second = new ArrayList<Integer>();
        second.add(1);
        second.add(2);
        second.add(3);
        second.add(4);
        second.add(5);

        ArrayList<Integer> troisieme = new ArrayList<Integer>();
        troisieme.add(5);
        troisieme.add(6);

        // ACT
        trajetNF.liste();

        System.out.println(premier);
        System.out.println(trajetNF.getListeJourDepart().get(0));

        System.out.println(second);
        System.out.println(trajetNF.getListeJourDepart().get(1));

        System.out.println(troisieme);
        System.out.println(trajetNF.getListeJourDepart().get(2));

        //ASSERT
        assertEquals(premier,trajetNF.getListeJourDepart().get(0));
        assertEquals(second,trajetNF.getListeJourDepart().get(1));
        assertEquals(troisieme,trajetNF.getListeJourDepart().get(2));
    }

    @Test
    public void testTrierTrajetNonFixe12(){
        //ARRANGE
        trajetNF.setFrequenceSemaine(3);
        trajetNF.setNombreTrajetWeekend(2);
        ArrayList<Integer> premier = new ArrayList<Integer>();
        premier.add(0);
        premier.add(1);
        premier.add(2);
        premier.add(3);
        premier.add(4);


        ArrayList<Integer> second = new ArrayList<Integer>();
        second.add(5);

        ArrayList<Integer> troisieme = new ArrayList<Integer>();
        troisieme.add(6);

        // ACT
        trajetNF.liste();

        System.out.println(premier);
        System.out.println(trajetNF.getListeJourDepart().get(0));

        System.out.println(second);
        System.out.println(trajetNF.getListeJourDepart().get(1));

        System.out.println(troisieme);
        System.out.println(trajetNF.getListeJourDepart().get(2));

        //ASSERT
        assertEquals(premier,trajetNF.getListeJourDepart().get(0));
        assertEquals(second,trajetNF.getListeJourDepart().get(1));
        assertEquals(troisieme,trajetNF.getListeJourDepart().get(2));
    }

    @Test
    public void testTrierTrajetNonFixe13(){
        //ARRANGE
        trajetNF.setFrequenceSemaine(3);
        trajetNF.setNbrJourMin(1);
        ArrayList<Integer> premier = new ArrayList<Integer>();
        premier.add(0);
        premier.add(1);
        premier.add(2);


        ArrayList<Integer> second = new ArrayList<Integer>();
        second.add(2);
        second.add(3);
        second.add(4);

        ArrayList<Integer> troisieme = new ArrayList<Integer>();
        troisieme.add(4);
        troisieme.add(5);
        troisieme.add(6);

        // ACT
        trajetNF.liste();

        System.out.println(premier);
        System.out.println(trajetNF.getListeJourDepart().get(0));

        System.out.println(second);
        System.out.println(trajetNF.getListeJourDepart().get(1));

        System.out.println(troisieme);
        System.out.println(trajetNF.getListeJourDepart().get(2));

        //ASSERT
        assertEquals(premier,trajetNF.getListeJourDepart().get(0));
        assertEquals(second,trajetNF.getListeJourDepart().get(1));
        assertEquals(troisieme,trajetNF.getListeJourDepart().get(2));
    }

    @Test
    public void testTrierTrajetNonFixe14(){
        //ARRANGE
        trajetNF.setFrequenceSemaine(3);
        trajetNF.setNbrJourMin(1);
        trajetNF.setNombreTrajetWeekend(1);
        ArrayList<Integer> premier = new ArrayList<Integer>();
        premier.add(0);
        premier.add(1);
        premier.add(2);


        ArrayList<Integer> second = new ArrayList<Integer>();
        second.add(2);
        second.add(3);
        second.add(4);

        ArrayList<Integer> troisieme = new ArrayList<Integer>();
        troisieme.add(5);
        troisieme.add(6);

        // ACT
        trajetNF.liste();

        System.out.println(premier);
        System.out.println(trajetNF.getListeJourDepart().get(0));

        System.out.println(second);
        System.out.println(trajetNF.getListeJourDepart().get(1));

        System.out.println(troisieme);
        System.out.println(trajetNF.getListeJourDepart().get(2));

        //ASSERT
        assertEquals(premier,trajetNF.getListeJourDepart().get(0));
        assertEquals(second,trajetNF.getListeJourDepart().get(1));
        assertEquals(troisieme,trajetNF.getListeJourDepart().get(2));
    }

    @Test
    public void testTrierTrajetNonFixe15(){
        //ARRANGE
        trajetNF.setFrequenceSemaine(4);
        ArrayList<Integer> premier = new ArrayList<Integer>();
        premier.add(0);
        premier.add(1);
        premier.add(2);
        premier.add(3);


        ArrayList<Integer> second = new ArrayList<Integer>();
        second.add(1);
        second.add(2);
        second.add(3);
        second.add(4);

        ArrayList<Integer> troisieme = new ArrayList<Integer>();
        troisieme.add(2);
        troisieme.add(3);
        troisieme.add(4);
        troisieme.add(5);

        ArrayList<Integer> quatrieme = new ArrayList<Integer>();
        quatrieme.add(3);
        quatrieme.add(4);
        quatrieme.add(5);
        quatrieme.add(6);

        // ACT
        trajetNF.liste();

        System.out.println(premier);
        System.out.println(trajetNF.getListeJourDepart().get(0));

        System.out.println(second);
        System.out.println(trajetNF.getListeJourDepart().get(1));

        System.out.println(troisieme);
        System.out.println(trajetNF.getListeJourDepart().get(2));

        System.out.println(quatrieme);
        System.out.println(trajetNF.getListeJourDepart().get(3));

        //ASSERT
        assertEquals(premier,trajetNF.getListeJourDepart().get(0));
        assertEquals(second,trajetNF.getListeJourDepart().get(1));
        assertEquals(troisieme,trajetNF.getListeJourDepart().get(2));
        assertEquals(quatrieme,trajetNF.getListeJourDepart().get(3));
    }

    @Test
    public void testTrierTrajetNonFixe16(){
        //ARRANGE
        trajetNF.setFrequenceSemaine(4);
        trajetNF.setNombreTrajetWeekend(1);
        ArrayList<Integer> premier = new ArrayList<Integer>();
        premier.add(0);
        premier.add(1);
        premier.add(2);
        premier.add(3);


        ArrayList<Integer> second = new ArrayList<Integer>();
        second.add(1);
        second.add(2);
        second.add(3);
        second.add(4);

        ArrayList<Integer> troisieme = new ArrayList<Integer>();
        troisieme.add(2);
        troisieme.add(3);
        troisieme.add(4);
        troisieme.add(5);

        ArrayList<Integer> quatrieme = new ArrayList<Integer>();
        quatrieme.add(5);
        quatrieme.add(6);

        // ACT
        trajetNF.liste();

        System.out.println(premier);
        System.out.println(trajetNF.getListeJourDepart().get(0));

        System.out.println(second);
        System.out.println(trajetNF.getListeJourDepart().get(1));

        System.out.println(troisieme);
        System.out.println(trajetNF.getListeJourDepart().get(2));

        System.out.println(quatrieme);
        System.out.println(trajetNF.getListeJourDepart().get(3));

        //ASSERT
        assertEquals(premier,trajetNF.getListeJourDepart().get(0));
        assertEquals(second,trajetNF.getListeJourDepart().get(1));
        assertEquals(troisieme,trajetNF.getListeJourDepart().get(2));
        assertEquals(quatrieme,trajetNF.getListeJourDepart().get(3));
    }

    @Test
    public void testTrierTrajetNonFixe17(){
        //ARRANGE
        trajetNF.setFrequenceSemaine(4);
        trajetNF.setNombreTrajetWeekend(2);
        ArrayList<Integer> premier = new ArrayList<Integer>();
        premier.add(0);
        premier.add(1);
        premier.add(2);
        premier.add(3);


        ArrayList<Integer> second = new ArrayList<Integer>();
        second.add(1);
        second.add(2);
        second.add(3);
        second.add(4);

        ArrayList<Integer> troisieme = new ArrayList<Integer>();
        troisieme.add(5);

        ArrayList<Integer> quatrieme = new ArrayList<Integer>();
        quatrieme.add(6);

        // ACT
        trajetNF.liste();

        System.out.println(premier);
        System.out.println(trajetNF.getListeJourDepart().get(0));

        System.out.println(second);
        System.out.println(trajetNF.getListeJourDepart().get(1));

        System.out.println(troisieme);
        System.out.println(trajetNF.getListeJourDepart().get(2));

        System.out.println(quatrieme);
        System.out.println(trajetNF.getListeJourDepart().get(3));

        //ASSERT
        assertEquals(premier,trajetNF.getListeJourDepart().get(0));
        assertEquals(second,trajetNF.getListeJourDepart().get(1));
        assertEquals(troisieme,trajetNF.getListeJourDepart().get(2));
        assertEquals(quatrieme,trajetNF.getListeJourDepart().get(3));
    }

    @Test
    public void testTrierTrajetNonFixe18(){
        //ARRANGE
        trajetNF.setFrequenceSemaine(5);
        ArrayList<Integer> premier = new ArrayList<Integer>();
        premier.add(0);
        premier.add(1);
        premier.add(2);


        ArrayList<Integer> second = new ArrayList<Integer>();
        second.add(1);
        second.add(2);
        second.add(3);

        ArrayList<Integer> troisieme = new ArrayList<Integer>();
        troisieme.add(2);
        troisieme.add(3);
        troisieme.add(4);

        ArrayList<Integer> quatrieme = new ArrayList<Integer>();
        quatrieme.add(3);
        quatrieme.add(4);
        quatrieme.add(5);

        ArrayList<Integer> cinquieme = new ArrayList<Integer>();
        cinquieme.add(4);
        cinquieme.add(5);
        cinquieme.add(6);

        // ACT
        trajetNF.liste();

        System.out.println(premier);
        System.out.println(trajetNF.getListeJourDepart().get(0));

        System.out.println(second);
        System.out.println(trajetNF.getListeJourDepart().get(1));

        System.out.println(troisieme);
        System.out.println(trajetNF.getListeJourDepart().get(2));

        System.out.println(quatrieme);
        System.out.println(trajetNF.getListeJourDepart().get(3));

        System.out.println(cinquieme);
        System.out.println(trajetNF.getListeJourDepart().get(4));

        //ASSERT
        assertEquals(premier,trajetNF.getListeJourDepart().get(0));
        assertEquals(second,trajetNF.getListeJourDepart().get(1));
        assertEquals(troisieme,trajetNF.getListeJourDepart().get(2));
        assertEquals(quatrieme,trajetNF.getListeJourDepart().get(3));
        assertEquals(cinquieme,trajetNF.getListeJourDepart().get(4));
    }

    @Test
    public void testTrierTrajetNonFixe19(){
        //ARRANGE
        trajetNF.setFrequenceSemaine(5);
        trajetNF.setNombreTrajetWeekend(1);
        ArrayList<Integer> premier = new ArrayList<Integer>();
        premier.add(0);
        premier.add(1);
        premier.add(2);


        ArrayList<Integer> second = new ArrayList<Integer>();
        second.add(1);
        second.add(2);
        second.add(3);

        ArrayList<Integer> troisieme = new ArrayList<Integer>();
        troisieme.add(2);
        troisieme.add(3);
        troisieme.add(4);

        ArrayList<Integer> quatrieme = new ArrayList<Integer>();
        quatrieme.add(3);
        quatrieme.add(4);
        quatrieme.add(5);

        ArrayList<Integer> cinquieme = new ArrayList<Integer>();
        cinquieme.add(5);
        cinquieme.add(6);

        // ACT
        trajetNF.liste();

        System.out.println(premier);
        System.out.println(trajetNF.getListeJourDepart().get(0));

        System.out.println(second);
        System.out.println(trajetNF.getListeJourDepart().get(1));

        System.out.println(troisieme);
        System.out.println(trajetNF.getListeJourDepart().get(2));

        System.out.println(quatrieme);
        System.out.println(trajetNF.getListeJourDepart().get(3));

        System.out.println(cinquieme);
        System.out.println(trajetNF.getListeJourDepart().get(4));

        //ASSERT
        assertEquals(premier,trajetNF.getListeJourDepart().get(0));
        assertEquals(second,trajetNF.getListeJourDepart().get(1));
        assertEquals(troisieme,trajetNF.getListeJourDepart().get(2));
        assertEquals(quatrieme,trajetNF.getListeJourDepart().get(3));
        assertEquals(cinquieme,trajetNF.getListeJourDepart().get(4));
    }

    @Test
    public void testTrierTrajetNonFixe20(){
        //ARRANGE
        trajetNF.setFrequenceSemaine(5);
        trajetNF.setNombreTrajetWeekend(2);
        ArrayList<Integer> premier = new ArrayList<Integer>();
        premier.add(0);
        premier.add(1);
        premier.add(2);


        ArrayList<Integer> second = new ArrayList<Integer>();
        second.add(1);
        second.add(2);
        second.add(3);

        ArrayList<Integer> troisieme = new ArrayList<Integer>();
        troisieme.add(2);
        troisieme.add(3);
        troisieme.add(4);

        ArrayList<Integer> quatrieme = new ArrayList<Integer>();
        quatrieme.add(5);

        ArrayList<Integer> cinquieme = new ArrayList<Integer>();
        cinquieme.add(6);

        // ACT
        trajetNF.liste();

        System.out.println(premier);
        System.out.println(trajetNF.getListeJourDepart().get(0));

        System.out.println(second);
        System.out.println(trajetNF.getListeJourDepart().get(1));

        System.out.println(troisieme);
        System.out.println(trajetNF.getListeJourDepart().get(2));

        System.out.println(quatrieme);
        System.out.println(trajetNF.getListeJourDepart().get(3));

        System.out.println(cinquieme);
        System.out.println(trajetNF.getListeJourDepart().get(4));

        //ASSERT
        assertEquals(premier,trajetNF.getListeJourDepart().get(0));
        assertEquals(second,trajetNF.getListeJourDepart().get(1));
        assertEquals(troisieme,trajetNF.getListeJourDepart().get(2));
        assertEquals(quatrieme,trajetNF.getListeJourDepart().get(3));
        assertEquals(cinquieme,trajetNF.getListeJourDepart().get(4));
    }

    @Test
    public void testTrierTrajetNonFixe21(){
        //ARRANGE
        trajetNF.setFrequenceSemaine(6);
        ArrayList<Integer> premier = new ArrayList<Integer>();
        premier.add(0);
        premier.add(1);


        ArrayList<Integer> second = new ArrayList<Integer>();
        second.add(1);
        second.add(2);

        ArrayList<Integer> troisieme = new ArrayList<Integer>();
        troisieme.add(2);
        troisieme.add(3);

        ArrayList<Integer> quatrieme = new ArrayList<Integer>();
        quatrieme.add(3);
        quatrieme.add(4);

        ArrayList<Integer> cinquieme = new ArrayList<Integer>();
        cinquieme.add(4);
        cinquieme.add(5);

        ArrayList<Integer> sixieme = new ArrayList<Integer>();
        sixieme.add(5);
        sixieme.add(6);

        // ACT
        trajetNF.liste();

        System.out.println(premier);
        System.out.println(trajetNF.getListeJourDepart().get(0));

        System.out.println(second);
        System.out.println(trajetNF.getListeJourDepart().get(1));

        System.out.println(troisieme);
        System.out.println(trajetNF.getListeJourDepart().get(2));

        System.out.println(quatrieme);
        System.out.println(trajetNF.getListeJourDepart().get(3));

        System.out.println(cinquieme);
        System.out.println(trajetNF.getListeJourDepart().get(4));

        System.out.println(sixieme);
        System.out.println(trajetNF.getListeJourDepart().get(5));

        //ASSERT
        assertEquals(premier,trajetNF.getListeJourDepart().get(0));
        assertEquals(second,trajetNF.getListeJourDepart().get(1));
        assertEquals(troisieme,trajetNF.getListeJourDepart().get(2));
        assertEquals(quatrieme,trajetNF.getListeJourDepart().get(3));
        assertEquals(cinquieme,trajetNF.getListeJourDepart().get(4));
        assertEquals(sixieme,trajetNF.getListeJourDepart().get(5));
    }

    @Test
    public void testTrierTrajetNonFixe22(){
        //ARRANGE
        trajetNF.setFrequenceSemaine(6);
        trajetNF.setNombreTrajetWeekend(1);
        ArrayList<Integer> premier = new ArrayList<Integer>();
        premier.add(0);
        premier.add(1);


        ArrayList<Integer> second = new ArrayList<Integer>();
        second.add(1);
        second.add(2);

        ArrayList<Integer> troisieme = new ArrayList<Integer>();
        troisieme.add(2);
        troisieme.add(3);

        ArrayList<Integer> quatrieme = new ArrayList<Integer>();
        quatrieme.add(3);
        quatrieme.add(4);

        ArrayList<Integer> cinquieme = new ArrayList<Integer>();
        cinquieme.add(4);
        cinquieme.add(5);

        ArrayList<Integer> sixieme = new ArrayList<Integer>();
        sixieme.add(5);
        sixieme.add(6);

        // ACT
        trajetNF.liste();

        System.out.println(premier);
        System.out.println(trajetNF.getListeJourDepart().get(0));

        System.out.println(second);
        System.out.println(trajetNF.getListeJourDepart().get(1));

        System.out.println(troisieme);
        System.out.println(trajetNF.getListeJourDepart().get(2));

        System.out.println(quatrieme);
        System.out.println(trajetNF.getListeJourDepart().get(3));

        System.out.println(cinquieme);
        System.out.println(trajetNF.getListeJourDepart().get(4));

        System.out.println(sixieme);
        System.out.println(trajetNF.getListeJourDepart().get(5));

        //ASSERT
        assertEquals(premier,trajetNF.getListeJourDepart().get(0));
        assertEquals(second,trajetNF.getListeJourDepart().get(1));
        assertEquals(troisieme,trajetNF.getListeJourDepart().get(2));
        assertEquals(quatrieme,trajetNF.getListeJourDepart().get(3));
        assertEquals(cinquieme,trajetNF.getListeJourDepart().get(4));
        assertEquals(sixieme,trajetNF.getListeJourDepart().get(5));
    }

    @Test
    public void testTrierTrajetNonFixe23(){
        //ARRANGE
        trajetNF.setFrequenceSemaine(6);
        trajetNF.setNombreTrajetWeekend(2);
        ArrayList<Integer> premier = new ArrayList<Integer>();
        premier.add(0);
        premier.add(1);


        ArrayList<Integer> second = new ArrayList<Integer>();
        second.add(1);
        second.add(2);

        ArrayList<Integer> troisieme = new ArrayList<Integer>();
        troisieme.add(2);
        troisieme.add(3);

        ArrayList<Integer> quatrieme = new ArrayList<Integer>();
        quatrieme.add(3);
        quatrieme.add(4);

        ArrayList<Integer> cinquieme = new ArrayList<Integer>();
        cinquieme.add(5);

        ArrayList<Integer> sixieme = new ArrayList<Integer>();
        sixieme.add(6);

        // ACT
        trajetNF.liste();

        System.out.println(premier);
        System.out.println(trajetNF.getListeJourDepart().get(0));

        System.out.println(second);
        System.out.println(trajetNF.getListeJourDepart().get(1));

        System.out.println(troisieme);
        System.out.println(trajetNF.getListeJourDepart().get(2));

        System.out.println(quatrieme);
        System.out.println(trajetNF.getListeJourDepart().get(3));

        System.out.println(cinquieme);
        System.out.println(trajetNF.getListeJourDepart().get(4));

        System.out.println(sixieme);
        System.out.println(trajetNF.getListeJourDepart().get(5));

        //ASSERT
        assertEquals(premier,trajetNF.getListeJourDepart().get(0));
        assertEquals(second,trajetNF.getListeJourDepart().get(1));
        assertEquals(troisieme,trajetNF.getListeJourDepart().get(2));
        assertEquals(quatrieme,trajetNF.getListeJourDepart().get(3));
        assertEquals(cinquieme,trajetNF.getListeJourDepart().get(4));
        assertEquals(sixieme,trajetNF.getListeJourDepart().get(5));
    }

    @Test
    public void testTrierTrajetNonFixe24(){
        //ARRANGE
        trajetNF.setFrequenceSemaine(7);
        ArrayList<Integer> premier = new ArrayList<Integer>();
        premier.add(0);


        ArrayList<Integer> second = new ArrayList<Integer>();
        second.add(1);

        ArrayList<Integer> troisieme = new ArrayList<Integer>();
        troisieme.add(2);

        ArrayList<Integer> quatrieme = new ArrayList<Integer>();
        quatrieme.add(3);

        ArrayList<Integer> cinquieme = new ArrayList<Integer>();
        cinquieme.add(4);

        ArrayList<Integer> sixieme = new ArrayList<Integer>();
        sixieme.add(5);

        ArrayList<Integer> septieme = new ArrayList<Integer>();
        septieme.add(6);

        // ACT
        trajetNF.liste();

        System.out.println(premier);
        System.out.println(trajetNF.getListeJourDepart().get(0));

        System.out.println(second);
        System.out.println(trajetNF.getListeJourDepart().get(1));

        System.out.println(troisieme);
        System.out.println(trajetNF.getListeJourDepart().get(2));

        System.out.println(quatrieme);
        System.out.println(trajetNF.getListeJourDepart().get(3));

        System.out.println(cinquieme);
        System.out.println(trajetNF.getListeJourDepart().get(4));

        System.out.println(sixieme);
        System.out.println(trajetNF.getListeJourDepart().get(5));

        System.out.println(septieme);
        System.out.println(trajetNF.getListeJourDepart().get(6));

        //ASSERT
        assertEquals(premier,trajetNF.getListeJourDepart().get(0));
        assertEquals(second,trajetNF.getListeJourDepart().get(1));
        assertEquals(troisieme,trajetNF.getListeJourDepart().get(2));
        assertEquals(quatrieme,trajetNF.getListeJourDepart().get(3));
        assertEquals(cinquieme,trajetNF.getListeJourDepart().get(4));
        assertEquals(sixieme,trajetNF.getListeJourDepart().get(5));
        assertEquals(septieme,trajetNF.getListeJourDepart().get(6));
    }
}
