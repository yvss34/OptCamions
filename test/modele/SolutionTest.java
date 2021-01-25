package modele;

import org.junit.jupiter.api.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class SolutionTest {

    Solution solution;

    @BeforeEach
    public void initSolution(){
        solution = new Solution();
        solution.setChauffeurs(new ArrayList<Chauffeur>());
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
}
