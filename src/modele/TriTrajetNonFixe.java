package modele;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class TriTrajetNonFixe extends TrajetNonFixe{

    private ArrayList<ArrayList<Integer>> listeJourDepart;


    public TriTrajetNonFixe(int identifiant, Ville villeDepart, Ville villeArrivee, double tempsDeConduite, ArrayList<Double> tempsDePause, HashMap<Ville, Double> listeVilleStop, int frequenceSemaine, String[] fenetreDeTemps, int nombreTrajetWeekend, int nbrJourMin) {
        super(identifiant, villeDepart, villeArrivee, tempsDeConduite, tempsDePause, listeVilleStop, frequenceSemaine, fenetreDeTemps, nombreTrajetWeekend, nbrJourMin);
    }

    public ArrayList<ArrayList<Integer>> getListeJourDepart() {
        return listeJourDepart;
    }

    public void setListeJourDepart(ArrayList<ArrayList<Integer>> listeJourDepart) {
        this.listeJourDepart = listeJourDepart;
    }

    public void liste(){

        listeJourDepart = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> premier = new ArrayList<Integer>();
        ArrayList<Integer> second = new ArrayList<Integer>();
        ArrayList<Integer> troisieme = new ArrayList<Integer>();
        ArrayList<Integer> quatrieme = new ArrayList<Integer>();
        ArrayList<Integer> cinquieme = new ArrayList<Integer>();
        ArrayList<Integer> sixieme = new ArrayList<Integer>();
        ArrayList<Integer> septieme = new ArrayList<Integer>();


        switch(getFrequenceSemaine()){
            case 1:
                switch(getNbrJourMin()){
                    case 0:
                        switch (getNombreTrajetWeekend()){
                            case 0:
                                premier.add(0);
                                premier.add(1);
                                premier.add(2);
                                premier.add(3);
                                premier.add(4);
                                premier.add(5);
                                premier.add(6);
                                break;
                            case 1:
                                premier.add(5);
                                premier.add(6);
                                break;
                        }
                        break;
                }
                listeJourDepart.add(premier);
                break;
            case 2:
                switch(getNbrJourMin()){
                    case 0:
                        switch (getNombreTrajetWeekend()){
                            case 0:
                                premier.add(0);
                                premier.add(1);
                                premier.add(2);
                                premier.add(3);
                                premier.add(4);
                                premier.add(5);

                                second.add(1);
                                second.add(2);
                                second.add(3);
                                second.add(4);
                                second.add(5);
                                second.add(6);

                                break;
                            case 1:
                                premier.add(0);
                                premier.add(1);
                                premier.add(2);
                                premier.add(3);
                                premier.add(4);
                                premier.add(5);

                                second.add(5);
                                second.add(6);
                                break;
                            case 2:
                                premier.add(5);

                                second.add(6);
                                break;
                        }
                        break;
                    case 1:
                        switch (getNombreTrajetWeekend()){
                            case 0:
                                premier.add(0);
                                premier.add(1);
                                premier.add(2);
                                premier.add(3);
                                premier.add(4);

                                second.add(2);
                                second.add(3);
                                second.add(4);
                                second.add(5);
                                second.add(6);
                                break;
                            case 1:
                                premier.add(0);
                                premier.add(1);
                                premier.add(2);
                                premier.add(3);
                                premier.add(4);

                                second.add(5);
                                second.add(6);
                                break;
                        }
                        break;
                    case 2:
                        switch (getNombreTrajetWeekend()){
                            case 0:
                                premier.add(0);
                                premier.add(1);
                                premier.add(2);
                                premier.add(3);

                                second.add(3);
                                second.add(4);
                                second.add(5);
                                second.add(6);
                                break;
                            case 1:
                                premier.add(0);
                                premier.add(1);
                                premier.add(2);
                                premier.add(3);

                                second.add(5);
                                second.add(6);
                                break;
                        }
                        break;
                }
                listeJourDepart.add(premier);
                listeJourDepart.add(second);
                break;
            case 3:
                switch(getNbrJourMin()){
                    case 0:
                        switch (getNombreTrajetWeekend()){
                            case 0:
                                premier.add(0);
                                premier.add(1);
                                premier.add(2);
                                premier.add(3);
                                premier.add(4);

                                second.add(1);
                                second.add(2);
                                second.add(3);
                                second.add(4);
                                second.add(5);

                                troisieme.add(2);
                                troisieme.add(3);
                                troisieme.add(4);
                                troisieme.add(5);
                                troisieme.add(6);

                                break;
                            case 1:
                                premier.add(0);
                                premier.add(1);
                                premier.add(2);
                                premier.add(3);
                                premier.add(4);

                                second.add(1);
                                second.add(2);
                                second.add(3);
                                second.add(4);
                                second.add(5);

                                troisieme.add(5);
                                troisieme.add(6);
                                break;
                            case 2:
                                premier.add(0);
                                premier.add(1);
                                premier.add(2);
                                premier.add(3);
                                premier.add(4);

                                second.add(5);

                                troisieme.add(6);
                                break;
                        }
                        break;
                    case 1:
                        switch (getNombreTrajetWeekend()){
                            case 0:
                                premier.add(0);
                                premier.add(1);
                                premier.add(2);

                                second.add(2);
                                second.add(3);
                                second.add(4);

                                troisieme.add(4);
                                troisieme.add(5);
                                troisieme.add(6);
                                break;

                            case 1:
                                premier.add(0);
                                premier.add(1);
                                premier.add(2);

                                second.add(2);
                                second.add(3);
                                second.add(4);

                                troisieme.add(5);
                                troisieme.add(6);
                                break;
                        }
                        break;
                }
                listeJourDepart.add(premier);
                listeJourDepart.add(second);
                listeJourDepart.add(troisieme);
                break;
            case 4:
                switch(getNbrJourMin()){
                    case 0:
                        switch (getNombreTrajetWeekend()){
                            case 0:
                                premier.add(0);
                                premier.add(1);
                                premier.add(2);
                                premier.add(3);

                                second.add(1);
                                second.add(2);
                                second.add(3);
                                second.add(4);

                                troisieme.add(2);
                                troisieme.add(3);
                                troisieme.add(4);
                                troisieme.add(5);

                                quatrieme.add(3);
                                quatrieme.add(4);
                                quatrieme.add(5);
                                quatrieme.add(6);
                                break;
                            case 1:
                                premier.add(0);
                                premier.add(1);
                                premier.add(2);
                                premier.add(3);

                                second.add(1);
                                second.add(2);
                                second.add(3);
                                second.add(4);

                                troisieme.add(2);
                                troisieme.add(3);
                                troisieme.add(4);
                                troisieme.add(5);

                                quatrieme.add(5);
                                quatrieme.add(6);
                                break;
                            case 2:
                                premier.add(0);
                                premier.add(1);
                                premier.add(2);
                                premier.add(3);

                                second.add(1);
                                second.add(2);
                                second.add(3);
                                second.add(4);

                                troisieme.add(5);

                                quatrieme.add(6);
                                break;
                        }
                        break;
                }
                listeJourDepart.add(premier);
                listeJourDepart.add(second);
                listeJourDepart.add(troisieme);
                listeJourDepart.add(quatrieme);
                break;
            case 5:
                switch(getNbrJourMin()){
                    case 0:
                        switch (getNombreTrajetWeekend()){
                            case 0:
                                premier.add(0);
                                premier.add(1);
                                premier.add(2);

                                second.add(1);
                                second.add(2);
                                second.add(3);

                                troisieme.add(2);
                                troisieme.add(3);
                                troisieme.add(4);

                                quatrieme.add(3);
                                quatrieme.add(4);
                                quatrieme.add(5);

                                cinquieme.add(4);
                                cinquieme.add(5);
                                cinquieme.add(6);
                                break;
                            case 1:
                                premier.add(0);
                                premier.add(1);
                                premier.add(2);

                                second.add(1);
                                second.add(2);
                                second.add(3);

                                troisieme.add(2);
                                troisieme.add(3);
                                troisieme.add(4);

                                quatrieme.add(3);
                                quatrieme.add(4);
                                quatrieme.add(5);

                                cinquieme.add(5);
                                cinquieme.add(6);
                                break;
                            case 2:
                                premier.add(0);
                                premier.add(1);
                                premier.add(2);

                                second.add(1);
                                second.add(2);
                                second.add(3);

                                troisieme.add(2);
                                troisieme.add(3);
                                troisieme.add(4);

                                quatrieme.add(5);

                                cinquieme.add(6);
                                break;
                        }
                        break;
                }
                listeJourDepart.add(premier);
                listeJourDepart.add(second);
                listeJourDepart.add(troisieme);
                listeJourDepart.add(quatrieme);
                listeJourDepart.add(cinquieme);
                break;
            case 6:
                switch(getNbrJourMin()){
                    case 0:
                        switch (getNombreTrajetWeekend()){
                            case 2:
                                premier.add(0);
                                premier.add(1);

                                second.add(1);
                                second.add(2);

                                troisieme.add(2);
                                troisieme.add(3);

                                quatrieme.add(3);
                                quatrieme.add(4);

                                cinquieme.add(5);

                                sixieme.add(6);
                                break;

                            default:
                                premier.add(0);
                                premier.add(1);

                                second.add(1);
                                second.add(2);

                                troisieme.add(2);
                                troisieme.add(3);

                                quatrieme.add(3);
                                quatrieme.add(4);

                                cinquieme.add(4);
                                cinquieme.add(5);

                                sixieme.add(5);
                                sixieme.add(6);
                                break;
                        }
                        break;
                }
                listeJourDepart.add(premier);
                listeJourDepart.add(second);
                listeJourDepart.add(troisieme);
                listeJourDepart.add(quatrieme);
                listeJourDepart.add(cinquieme);
                listeJourDepart.add(sixieme);
                break;
            case 7:
                switch(getNbrJourMin()){
                    case 0:
                        premier.add(0);

                        second.add(1);

                        troisieme.add(2);

                        quatrieme.add(3);

                        cinquieme.add(4);

                        sixieme.add(5);

                        septieme.add(6);
                        break;
                }
                listeJourDepart.add(premier);
                listeJourDepart.add(second);
                listeJourDepart.add(troisieme);
                listeJourDepart.add(quatrieme);
                listeJourDepart.add(cinquieme);
                listeJourDepart.add(sixieme);
                listeJourDepart.add(septieme);
                break;
        }

    }
}
