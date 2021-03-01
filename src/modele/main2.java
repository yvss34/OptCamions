package modele;

import org.junit.jupiter.api.BeforeEach;

import java.time.LocalTime;
import java.util.*;

public class main2 {

    /**
     * Tri un HashMap<Ville,Double> en fonction du Double
     */
    private static HashMap sort(HashMap map) {
        List linkedlist = new LinkedList(map.entrySet());
        Collections.sort(linkedlist, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = linkedlist.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public static void main(String[] args) {
        // Creating an empty HashMap
        HashMap<Ville, Double> hash_map = new HashMap<Ville, Double>();


        Ville ville1 = new Ville(0,"Lyon");
        Ville ville2 = new Ville(1,"Paris");
        Ville ville3 = new Ville(2,"Mtp");
        Ville ville4 = new Ville(3,"Marseille");
        Ville ville5 = new Ville(4,"Toulouse");
        Ville ville6 = new Ville(5,"Reims");
        Ville ville7 = new Ville(6,"Carcassonne");

        // Mapping string values to int keys
        hash_map.put(ville1, 3.0);
        hash_map.put(ville2, 8.0);
        hash_map.put(ville3, 7.0);
        hash_map.put(ville4, 2.0);
        hash_map.put(ville5, 1.0);
        hash_map.put(ville6, 10.0);
        hash_map.put(ville7, 4.0);

        // Displaying the HashMap
        System.out.println("Initial Mappings are: " + hash_map);

        hash_map = sort(hash_map);

        // Displaying the HashMap
        System.out.println("Initial Mappings are: " + hash_map);
    }
}
