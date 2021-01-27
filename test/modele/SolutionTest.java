package modele;

import org.junit.jupiter.api.*;
import static org.junit.Assert.assertEquals;

import java.time.LocalTime;
import java.util.ArrayList;

public class SolutionTest {

    Solution solution;

    @BeforeEach
    public void initSolution(){
        solution = new Solution();
        solution.setChauffeurs(new ArrayList<Chauffeur>());
        solution.setTrajets(new ArrayList<TrajetFixe>());
    }


    @Test
    public void testGetChauffeursById(){
        //ARRANGE

        Chauffeur chauffeur1 = new Chauffeur(18,null,null,0);
        Chauffeur chauffeur2 = new Chauffeur(1424,null,null,0);
        Chauffeur chauffeur3 = new Chauffeur(114,null,null,0);
        Chauffeur chauffeur4 = new Chauffeur(1221,null,null,0);
        Chauffeur chauffeur5 = new Chauffeur();

        solution.getChauffeurs().add(chauffeur1);
        solution.getChauffeurs().add(chauffeur2);
        solution.getChauffeurs().add(chauffeur3);
        solution.getChauffeurs().add(chauffeur4);
        solution.getChauffeurs().add(chauffeur5);

        //ACT
        Chauffeur chauffeurTest1 = solution.getChauffeurById(0);
        Chauffeur chauffeurTest2 = solution.getChauffeurById(1424);
        Chauffeur chauffeurTest3 = solution.getChauffeurById(-1);

        //ASSERT
        assertEquals(chauffeur5,chauffeurTest1);
        assertEquals(chauffeur2,chauffeurTest2);
        assertEquals(null,chauffeurTest3);
    }

    @Test
    public void testGetTrajetById(){
        //ARRANGE

        //Ville
        Ville paris = new Ville(1,"Paris");
        Ville marseille = new Ville(2,"Marseille");
        Ville lyon = new Ville(3,"Lyon");

        //Trajets
        TrajetFixe trajet1_1 = new TrajetFixe(1,paris,lyon,4.0,new ArrayList<Double>(),null,new Jour(1,"Lundi"), LocalTime.of(8,0));
        TrajetFixe trajet1_2 = new TrajetFixe(2,lyon,paris,4.0,new ArrayList<Double>(),null,new Jour(2,"Mardi"),LocalTime.of(8,0));
        TrajetFixe trajet1_3 = new TrajetFixe(3,paris,lyon,4.0,new ArrayList<Double>(),null,new Jour(3,"Mercredi"),LocalTime.of(8,0));
        TrajetFixe trajet1_4 = new TrajetFixe(4,lyon,paris,4.0,new ArrayList<Double>(),null,new Jour(4,"Jeudi"),LocalTime.of(8,0));

        TrajetFixe trajet2_1 = new TrajetFixe(5,marseille,paris,8.0,new ArrayList<Double>(),null,new Jour(1,"Lundi"),LocalTime.of(8,0));
        TrajetFixe trajet2_2 = new TrajetFixe(6,paris,marseille,8.0,new ArrayList<Double>(),null,new Jour(2,"Mardi"),LocalTime.of(8,0));
        TrajetFixe trajet2_3 = new TrajetFixe(7,marseille,paris,8.0,new ArrayList<Double>(),null,new Jour(3,"Mercredi"),LocalTime.of(8,0));
        TrajetFixe trajet2_4 = new TrajetFixe();

        solution.getTrajets().add(trajet1_1);
        solution.getTrajets().add(trajet1_2);
        solution.getTrajets().add(trajet1_3);
        solution.getTrajets().add(trajet1_4);

        solution.getTrajets().add(trajet2_1);
        solution.getTrajets().add(trajet2_2);
        solution.getTrajets().add(trajet2_3);
        solution.getTrajets().add(trajet2_4);

        //ACT
        TrajetFixe TrajetFixeTest1 = solution.getTrajetById(0);
        TrajetFixe TrajetFixeTest2 = solution.getTrajetById(1424);
        TrajetFixe TrajetFixeTest3 = solution.getTrajetById(7);

        //ASSERT
        assertEquals(trajet2_4,TrajetFixeTest1);
        assertEquals(null,TrajetFixeTest2);
        assertEquals(trajet2_3,TrajetFixeTest3);
    }


}
