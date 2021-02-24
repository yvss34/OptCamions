package modele;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Cette classe correspond au moteur de résolution
 * permettant la plannification et la proposition d'une solution
 */
public class MoteurDeResolution {

    /**Attributes**/
    Plannification plannification;

    /**Constructors**/
    public MoteurDeResolution(Plannification plannification) {
        this.plannification = plannification;
    }

    /**Getters & Setters**/
    public Plannification getPlannification() {
        return plannification;
    }

    public void setPlannification(Plannification plannification) {
        this.plannification = plannification;
    }

    /**
     * Retourne une liste de trajets composés de
     * position 0 : l'identifiant du trajet
     * position 1 : le numero de frequence du trajet
     * position 2 : le jour de la semaine du trajet
     * @return La liste de trajets respectant le standard (id,num frequence,jour)
     */
    public ArrayList<ArrayList<Integer>> listeTrajetsStandard(){
        ArrayList<ArrayList<Integer>> trajetTrier = new ArrayList<ArrayList<Integer>>();

        for (TrajetNonFixe trajet : plannification.getTrajetsNonFixe()){
            trajet.liste();
            for(int i=0 ; i<trajet.getListeJourDepart().size();i++){
                for(int j=0; j<trajet.getListeJourDepart().get(i).size();j++){
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(trajet.getIdentifiant());
                    list.add(i);
                    list.add(trajet.getListeJourDepart().get(i).get(j));

                    trajetTrier.add(list);
                }
            }
        }

        for (TrajetFixe trajet : plannification.getTrajetsFixe()){
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(trajet.getIdentifiant());
            list.add(-1);
            list.add(trajet.getJourDepart().getIdentifiant());

            trajetTrier.add(list);
        }
        return trajetTrier;
    }

    /**
     * Tri la liste de trajets en fonction du jour de la semaine
     * @param trajets la liste de trajets respectant le standard (id,num frequence,jour)
     */
    public void triTrajetsStandard(ArrayList<ArrayList<Integer>> trajets){
        int i,j;
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for(i = 0; i<trajets.size(); i++){
            for(j=0; j<trajets.size();j++){
                if(trajets.get(i).get(2) <= trajets.get(j).get(2)){
                    if(trajets.get(i).get(2) == trajets.get(j).get(2)){
                        if(trajets.get(i).get(0) == trajets.get(j).get(0)){
                            if(trajets.get(i).get(1) < trajets.get(j).get(1)){
                                temp = trajets.get(i);
                                trajets.set(i, trajets.get(j));
                                trajets.set(j, temp);
                            }
                        }else {
                            LocalTime horaire1 = LocalTime.of(0, 0);
                            LocalTime horaire2 = LocalTime.of(0, 0);

                            TrajetFixe trajet1_1 = plannification.getTrajetFixeById(trajets.get(i).get(0));
                            TrajetFixe trajet2_1 = plannification.getTrajetFixeById(trajets.get(j).get(0));

                            TrajetNonFixe trajet1_2 = plannification.getTrajetNonFixeById(trajets.get(i).get(0));
                            TrajetNonFixe trajet2_2 = plannification.getTrajetNonFixeById(trajets.get(j).get(0));

                            if (trajet1_1 != null) {
                                horaire1 = trajet1_1.getHeureDepart();
                            } else if (trajet1_2 != null) {
                                String[] fenetreTempsDepart = trajet1_2.getFenetreDeTemps()[0].split(";");
                                String[] tempsDepart = fenetreTempsDepart[0].split("-");
                                double heureArrivee = Double.parseDouble(tempsDepart[0]);
                                horaire1 = LocalTime.of((int) heureArrivee, (int) ((heureArrivee - (int) heureArrivee) * 100));
                            }

                            if (trajet2_1 != null) {
                                horaire2 = trajet2_1.getHeureDepart();
                            } else if (trajet2_2 != null) {
                                String[] fenetreTempsDepart = trajet2_2.getFenetreDeTemps()[0].split(";");
                                String[] tempsDepart = fenetreTempsDepart[0].split("-");
                                double heureArrivee = Double.parseDouble(tempsDepart[0]);
                                horaire2 = LocalTime.of((int) heureArrivee, (int) ((heureArrivee - (int) heureArrivee) * 100));
                            }
                            if (horaire1.isBefore(horaire2)) {
                                temp = trajets.get(i);
                                trajets.set(i, trajets.get(j));
                                trajets.set(j, temp);
                            }

                        }
                    }else{
                        temp = trajets.get(i);
                        trajets.set(i,trajets.get(j));
                        trajets.set(j,temp);
                    }
                }
            }
        }
    }


    /**
     * Met a jour les trajets connectés du dernier trajet ajouté
     * @param index position du dernier trajet ajouté à la liste
     * @param trajetTrier la liste de trajets respectant le standard (id,num frequence,jour) trié
     */
    public void miseAJour(int index,ArrayList<ArrayList<Integer>> trajetTrier){
        ArrayList<Integer> indexTrajet = trajetTrier.get(index);
        ArrayList<ArrayList<Integer>> tableauASupprimer = new ArrayList<ArrayList<Integer>>();

        for (ArrayList<Integer> MAJTrajet : trajetTrier){
            if (indexTrajet.get(1) == MAJTrajet.get(1) && indexTrajet.get(0) == MAJTrajet.get(0) && indexTrajet.get(2) == MAJTrajet.get(2)) {
                tableauASupprimer.add(MAJTrajet);
            }
            else if (indexTrajet.get(1) == MAJTrajet.get(1) && indexTrajet.get(0) == MAJTrajet.get(0)&& indexTrajet.get(2) != MAJTrajet.get(2)) {
                tableauASupprimer.add(MAJTrajet);
            }
            else if(indexTrajet.get(1) < MAJTrajet.get(1) && indexTrajet.get(0) == MAJTrajet.get(0)){
                TrajetNonFixe trajetNonFixe = plannification.getTrajetNonFixeById(MAJTrajet.get(0));
                if(MAJTrajet.get(2) <= indexTrajet.get(2)+trajetNonFixe.getNbrJourMin()){
                    tableauASupprimer.add(MAJTrajet);
                }
            }
            else if(indexTrajet.get(1) > MAJTrajet.get(1) && indexTrajet.get(0) == MAJTrajet.get(0)){
                TrajetNonFixe trajetNonFixe = plannification.getTrajetNonFixeById(MAJTrajet.get(0));
                if(MAJTrajet.get(2) >= indexTrajet.get(2)-trajetNonFixe.getNbrJourMin()){
                    tableauASupprimer.add(MAJTrajet);
                }
            }
        }
        for (ArrayList<Integer> trajet : tableauASupprimer) {
            trajetTrier.remove(trajet);
        }
    }


    /**
     * Affectation des camions aux trajets
     * @param trajets va contenir la liste des trajets affecté
     * @return un objet HashMap avec une clé correspondant à l'identifiant du chauffeur et en valeur une liste d'Integer
     * correspondant aux identifiants des trajets
     */
    public HashMap<Integer,ArrayList<Integer>> camionTrajets(ArrayList<TrajetFixe> trajets){

        //Etape 1 : trier l'ensemble des trajets par date de départ possible
        ArrayList<ArrayList<Integer>> trajetTrier = this.listeTrajetsStandard();
        triTrajetsStandard(trajetTrier);

        //Etape 2 : liste Symone et ajout des camions
        HashMap<Integer,ArrayList<Integer>> camionTrajet = new HashMap<Integer,ArrayList<Integer>>();
        ArrayList<TrajetFixe> trajet = new ArrayList<TrajetFixe>();
        ArrayList<Integer> idTrajet = new ArrayList<Integer>();
        int identifiantCamion = 1;
        int compteur = 0;
        int index=0;
        boolean premierTrajet = true;
        Ville premiereVille = null;
        Jour jourDepart = null;
        TrajetFixe trajetActuelle = new TrajetFixe();
        int nbrTrajetNonFixe = 100;
        int nbrTrajetAVide = 200;
        boolean put = false;
        //Etape 3 : Retirer trajet de la liste et ajouter à un camion
        while(!trajetTrier.isEmpty()){
            put = false;
            if(premierTrajet){
                if(plannification.getTrajetFixeById(trajetTrier.get(compteur).get(0)) != null){
                    TrajetFixe trajetFixe = plannification.getTrajetFixeById(trajetTrier.get(compteur).get(0));
                    trajet.add(trajetFixe);
                    idTrajet.add(trajetFixe.getIdentifiant());
                    index = compteur;
                    premierTrajet = false;
                    trajetActuelle = plannification.getTrajetFixeById(trajetTrier.get(compteur).get(0));
                    premiereVille = trajetActuelle.getVilleDepart();
                    jourDepart = trajetActuelle.getJourDepart();
                }else{
                    TrajetNonFixe trajetNonFixe= plannification.getTrajetNonFixeById(trajetTrier.get(compteur).get(0));
                    String[] fenetreTempsDepart = trajetNonFixe.getFenetreDeTemps()[0].split(";");
                    String[] tempsDepart = fenetreTempsDepart[0].split("-");
                    double heureArrivee = Double.parseDouble(tempsDepart[0]);
                    LocalTime horaire = LocalTime.of((int)heureArrivee,(int)(heureArrivee-(int)heureArrivee)*100);

                    TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetNonFixe,trajetNonFixe.getVilleDepart(),trajetNonFixe.getVilleArrivee(),trajetNonFixe.getTempsDeConduite(),trajetNonFixe.getListeVilleStop()
                            ,Jour.getJourById(trajetTrier.get(compteur).get(2)),horaire);

                    trajet.add(trajetAdd);
                    idTrajet.add(trajetAdd.getIdentifiant());

                    index = compteur;
                    premierTrajet = false;
                    trajetActuelle = trajetAdd;
                    premiereVille = trajetActuelle.getVilleDepart();
                    jourDepart = trajetActuelle.getJourDepart();
                    nbrTrajetNonFixe++;
                }
                miseAJour(index,trajetTrier);
            }
            else{

                boolean ajoutTrajet = false;
                while(compteur<trajetTrier.size() && !ajoutTrajet){
                    ajoutTrajet = false;
                    TrajetFixe trajetFixe = plannification.getTrajetFixeById(trajetTrier.get(compteur).get(0));
                    TrajetNonFixe trajetNonFixe = plannification.getTrajetNonFixeById(trajetTrier.get(compteur).get(0));
                    if(trajetFixe != null){
                        if(trajetFixe.getVilleDepart().getIdentifiant() == trajetActuelle.getVilleArrivee().getIdentifiant()){
                            if(trajetFixe.getJourDepart().getIdentifiant() == trajetActuelle.getJourDepart().getIdentifiant()){
                                LocalTime heureArrivee = trajetActuelle.getHeureDepart();
                                heureArrivee = heureArrivee.plusHours((int)trajetActuelle.getTempsDeConduite());
                                heureArrivee = heureArrivee.plusMinutes((int)(trajetActuelle.getTempsDeConduite()-(int)trajetActuelle.getTempsDeConduite())*100);
                                if(trajetFixe.getHeureDepart().isAfter(heureArrivee)){
                                    if(trajetFixe.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant() || trajetFixe.getJourDepart().getIdentifiant() < jourDepart.getIdentifiant()+6){
                                        trajet.add(trajetFixe);
                                        idTrajet.add(trajetFixe.getIdentifiant());
                                        index = compteur;
                                        ajoutTrajet = true;
                                        trajetActuelle = trajetFixe;
                                    }
                                }
                            }else if(trajetFixe.getJourDepart().getIdentifiant() > trajetActuelle.getJourDepart().getIdentifiant()){
                                if(trajetFixe.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant() || trajetFixe.getJourDepart().getIdentifiant() < jourDepart.getIdentifiant()+6){
                                    trajet.add(trajetFixe);
                                    idTrajet.add(trajetFixe.getIdentifiant());
                                    index = compteur;
                                    ajoutTrajet = true;
                                    trajetActuelle = trajetFixe;
                                }
                            }
                        }
                    }
                    else{
                        if(trajetNonFixe.getVilleDepart().getIdentifiant() == trajetActuelle.getVilleArrivee().getIdentifiant()){
                            if(trajetTrier.get(compteur).get(2) == trajetActuelle.getJourDepart().getIdentifiant()){
                                String[] fenetreTempsDepart = trajetNonFixe.getFenetreDeTemps()[0].split(";");
                                String[] tempsDepart = fenetreTempsDepart[0].split("-");
                                double heureDebut = Double.parseDouble(tempsDepart[0]);
                                double heureFin = Double.parseDouble(tempsDepart[1]);
                                LocalTime horaireDebut = LocalTime.of((int) heureDebut, (int) (heureDebut - (int) heureDebut )* 100);
                                LocalTime horaireFin = LocalTime.of((int) heureFin, (int) (heureFin - (int) heureFin )* 100);

                                LocalTime heureArrivee = trajetActuelle.getHeureDepart();
                                heureArrivee = heureArrivee.plusHours((int)trajetActuelle.getTempsDeConduite());
                                heureArrivee = heureArrivee.plusMinutes((int)(trajetActuelle.getTempsDeConduite()-(int)trajetActuelle.getTempsDeConduite())*100);

                                if(heureArrivee.isBefore(horaireDebut)){
                                    if(trajetNonFixe.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant() || trajetTrier.get(compteur).get(2) < jourDepart.getIdentifiant()+6) {
                                        TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetNonFixe, trajetNonFixe.getVilleDepart(), trajetNonFixe.getVilleArrivee(), trajetNonFixe.getTempsDeConduite(), trajetNonFixe.getListeVilleStop()
                                                , Jour.getJourById(trajetTrier.get(compteur).get(2)), horaireDebut);
                                        trajet.add(trajetAdd);
                                        idTrajet.add(trajetAdd.getIdentifiant());
                                        index = compteur;
                                        ajoutTrajet = true;
                                        trajetActuelle = trajetAdd;
                                        nbrTrajetNonFixe++;
                                    }
                                }
                                else if(heureArrivee.isBefore(horaireFin)){
                                    if(trajetNonFixe.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant() || trajetTrier.get(compteur).get(2) < jourDepart.getIdentifiant()+6) {
                                        TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetNonFixe, trajetNonFixe.getVilleDepart(), trajetNonFixe.getVilleArrivee(), trajetNonFixe.getTempsDeConduite(), trajetNonFixe.getListeVilleStop()
                                                , Jour.getJourById(trajetTrier.get(compteur).get(2)), horaireFin);
                                        trajet.add(trajetAdd);
                                        idTrajet.add(trajetAdd.getIdentifiant());
                                        index = compteur;
                                        ajoutTrajet = true;
                                        trajetActuelle = trajetAdd;
                                        nbrTrajetNonFixe++;
                                    }
                                }
                            }else if(trajetTrier.get(compteur).get(2) > trajetActuelle.getJourDepart().getIdentifiant()){
                                if(trajetNonFixe.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant() || trajetTrier.get(compteur).get(2) < jourDepart.getIdentifiant()+6) {
                                    String[] fenetreTempsDepart = trajetNonFixe.getFenetreDeTemps()[0].split(";");
                                    String[] tempsDepart = fenetreTempsDepart[0].split("-");
                                    double heureDebut = Double.parseDouble(tempsDepart[0]);
                                    LocalTime horaireDebut = LocalTime.of((int) heureDebut, (int) (heureDebut - (int) heureDebut )* 100);
                                    TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetNonFixe, trajetNonFixe.getVilleDepart(), trajetNonFixe.getVilleArrivee(), trajetNonFixe.getTempsDeConduite(), trajetNonFixe.getListeVilleStop()
                                            , Jour.getJourById(trajetTrier.get(compteur).get(2)), horaireDebut);
                                    trajet.add(trajetAdd);
                                    idTrajet.add(trajetAdd.getIdentifiant());
                                    index = compteur;
                                    ajoutTrajet = true;
                                    trajetActuelle = trajetAdd;
                                    nbrTrajetNonFixe++;
                                }
                            }
                        }
                    }
                    compteur++;
                }
                if(ajoutTrajet){
                    if(trajetActuelle.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant()){
                        if(trajetActuelle.getJourDepart().getIdentifiant()>=4){
                            camionTrajet.put(identifiantCamion,idTrajet);
                            identifiantCamion++;
                            idTrajet = new ArrayList<Integer>();
                            compteur=0;
                            put = true;
                            premierTrajet = true;
                        }
                    }
                    miseAJour(index,trajetTrier);
                }else{
                    if(trajetActuelle.getVilleArrivee().getIdentifiant() != premiereVille.getIdentifiant()){
                        LocalTime heureArrivee = trajetActuelle.getHeureDepart();
                        heureArrivee = heureArrivee.plusHours((int)trajetActuelle.getTempsDeConduite());
                        heureArrivee = heureArrivee.plusMinutes((int)(trajetActuelle.getTempsDeConduite()-(int)trajetActuelle.getTempsDeConduite())*100);
                        TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetAVide, trajetActuelle.getVilleArrivee(), premiereVille, 9, null
                                , trajetActuelle.getJourDepart(), heureArrivee);

                        trajet.add(trajetAdd);
                        idTrajet.add(trajetAdd.getIdentifiant());
                        nbrTrajetAVide++;
                    }
                    camionTrajet.put(identifiantCamion,idTrajet);
                    identifiantCamion++;
                    idTrajet = new ArrayList<Integer>();
                    compteur=  0;
                    put = true;
                    premierTrajet = true;
                }
            }
        }
        if(!put){
            if(trajetActuelle.getVilleArrivee().getIdentifiant() != premiereVille.getIdentifiant()){
                LocalTime heureArrivee = trajetActuelle.getHeureDepart();
                heureArrivee = heureArrivee.plusHours((int)trajetActuelle.getTempsDeConduite());
                heureArrivee = heureArrivee.plusMinutes((int)(trajetActuelle.getTempsDeConduite()-(int)trajetActuelle.getTempsDeConduite())*100);
                TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetAVide, trajetActuelle.getVilleArrivee(), premiereVille, 9, null
                        , trajetActuelle.getJourDepart(), heureArrivee);

                trajet.add(trajetAdd);
                idTrajet.add(trajetAdd.getIdentifiant());
                nbrTrajetAVide++;
            }
            camionTrajet.put(identifiantCamion,idTrajet);
            identifiantCamion++;
            idTrajet = new ArrayList<Integer>();
            compteur=  0;
            put = true;
        }

        trajets = trajet;
        return camionTrajet;
    }

    /**
     * Affectation des camions aux trajets avec de l'aleatoire
     * @param nombreRepetition nombre de repetition de l'algorithme
     * @return Une liste d'objet HashMap contenant une clé correspondant à l'identifiant du chauffeur et en valeur une liste d'Integer
     * correspondant aux identifiants des trajets
     */
    public ArrayList<HashMap<Integer,ArrayList<Integer>>> camionTrajetsAleatoire(int nombreRepetition) {

        //Etape 2 : liste Symone et ajout des camions
        ArrayList<HashMap<Integer, ArrayList<Integer>>> listeCamionsTrajets = new ArrayList<HashMap<Integer, ArrayList<Integer>>>();
        int occurence = 0;
        int nombreCamionMin = 100;

        while (occurence < nombreRepetition) {

            //Etape 1 : trier l'ensemble des trajets par date de départ possible
            ArrayList<ArrayList<Integer>> trajetTrier = this.listeTrajetsStandard();
            triTrajetsStandard(trajetTrier);


            HashMap<Integer, ArrayList<Integer>> camionTrajet = new HashMap<Integer, ArrayList<Integer>>();
            ArrayList<TrajetFixe> trajet = new ArrayList<TrajetFixe>();
            ArrayList<Integer> idTrajet = new ArrayList<Integer>();
            int identifiantCamion = 1;
            int compteur = 0;
            int index = 0;

            boolean premierTrajet = true;
            Ville premiereVille = null;
            Jour jourDepart = null;
            TrajetFixe trajetActuelle = new TrajetFixe();
            int nbrTrajetNonFixe = 100;
            int nbrTrajetAVide = 200;
            boolean put = false;
            //Etape 3 : Retirer trajet de la liste et ajouter à un camion
            while (trajetTrier.isEmpty() == false) {
                put = false;
                if (premierTrajet == true) {
                    do {
                        Random r = new Random();
                        compteur = r.nextInt(3);
                    } while (compteur >= trajetTrier.size());

                    if (plannification.getTrajetFixeById(trajetTrier.get(compteur).get(0)) != null) {
                        TrajetFixe trajetFixe = plannification.getTrajetFixeById(trajetTrier.get(compteur).get(0));
                        trajet.add(trajetFixe);
                        idTrajet.add(trajetFixe.getIdentifiant());
                        index = compteur;
                        premierTrajet = false;
                        trajetActuelle = plannification.getTrajetFixeById(trajetTrier.get(compteur).get(0));
                        premiereVille = trajetActuelle.getVilleDepart();
                        jourDepart = trajetActuelle.getJourDepart();
                    } else {
                        TrajetNonFixe trajetNonFixe = plannification.getTrajetNonFixeById(trajetTrier.get(compteur).get(0));
                        String[] fenetreTempsDepart = trajetNonFixe.getFenetreDeTemps()[0].split(";");
                        String[] tempsDepart = fenetreTempsDepart[0].split("-");
                        double heureArrivee = Double.parseDouble(tempsDepart[0]);
                        LocalTime horaire = LocalTime.of((int) heureArrivee, (int) (heureArrivee - (int) heureArrivee) * 100);

                        TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetNonFixe, trajetNonFixe.getVilleDepart(), trajetNonFixe.getVilleArrivee(), trajetNonFixe.getTempsDeConduite(), trajetNonFixe.getListeVilleStop()
                                , Jour.getJourById(trajetTrier.get(compteur).get(2)), horaire);

                        trajet.add(trajetAdd);
                        idTrajet.add(trajetAdd.getIdentifiant());

                        index = compteur;
                        premierTrajet = false;
                        trajetActuelle = trajetAdd;
                        premiereVille = trajetActuelle.getVilleDepart();
                        jourDepart = trajetActuelle.getJourDepart();
                        nbrTrajetNonFixe++;
                    }
                    miseAJour(index, trajetTrier);
                    compteur = 0;
                } else {
                    ArrayList<TrajetFixe> trajetAjout = new ArrayList<TrajetFixe>();
                    int nombreAjout = 0;
                    while (compteur < trajetTrier.size() && nombreAjout < 2) {
                        TrajetFixe trajetFixe = plannification.getTrajetFixeById(trajetTrier.get(compteur).get(0));
                        TrajetNonFixe trajetNonFixe = plannification.getTrajetNonFixeById(trajetTrier.get(compteur).get(0));
                        if (trajetFixe != null) {
                            if (trajetFixe.getVilleDepart().getIdentifiant() == trajetActuelle.getVilleArrivee().getIdentifiant()) {
                                if (trajetFixe.getJourDepart().getIdentifiant() == trajetActuelle.getJourDepart().getIdentifiant()) {
                                    LocalTime heureArrivee = trajetActuelle.getHeureDepart();
                                    heureArrivee = heureArrivee.plusHours((int) trajetActuelle.getTempsDeConduite());
                                    heureArrivee = heureArrivee.plusMinutes((int) (trajetActuelle.getTempsDeConduite() - (int) trajetActuelle.getTempsDeConduite()) * 100);
                                    if (trajetFixe.getHeureDepart().isAfter(heureArrivee)) {
                                        if (trajetFixe.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant() || trajetFixe.getJourDepart().getIdentifiant() < jourDepart.getIdentifiant() + 6) {
                                            trajetAjout.add(trajetFixe);
                                            nombreAjout++;
                                        }
                                    }
                                } else if (trajetFixe.getJourDepart().getIdentifiant() > trajetActuelle.getJourDepart().getIdentifiant()) {
                                    if (trajetFixe.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant() || trajetFixe.getJourDepart().getIdentifiant() < jourDepart.getIdentifiant() + 6) {
                                        trajetAjout.add(trajetFixe);
                                        nombreAjout++;
                                    }
                                }
                            }
                        } else {
                            if (trajetNonFixe.getVilleDepart().getIdentifiant() == trajetActuelle.getVilleArrivee().getIdentifiant()) {
                                if (trajetTrier.get(compteur).get(2) == trajetActuelle.getJourDepart().getIdentifiant()) {
                                    String[] fenetreTempsDepart = trajetNonFixe.getFenetreDeTemps()[0].split(";");
                                    String[] tempsDepart = fenetreTempsDepart[0].split("-");
                                    double heureDebut = Double.parseDouble(tempsDepart[0]);
                                    double heureFin = Double.parseDouble(tempsDepart[1]);
                                    LocalTime horaireDebut = LocalTime.of((int) heureDebut, (int) (heureDebut - (int) heureDebut) * 100);
                                    LocalTime horaireFin = LocalTime.of((int) heureFin, (int) (heureFin - (int) heureFin) * 100);

                                    LocalTime heureArrivee = trajetActuelle.getHeureDepart();
                                    heureArrivee = heureArrivee.plusHours((int) trajetActuelle.getTempsDeConduite());
                                    heureArrivee = heureArrivee.plusMinutes((int) (trajetActuelle.getTempsDeConduite() - (int) trajetActuelle.getTempsDeConduite()) * 100);

                                    if (heureArrivee.isBefore(horaireDebut)) {
                                        if (trajetNonFixe.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant() || trajetTrier.get(compteur).get(2) < jourDepart.getIdentifiant() + 6) {
                                            TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetNonFixe, trajetNonFixe.getVilleDepart(), trajetNonFixe.getVilleArrivee(), trajetNonFixe.getTempsDeConduite(), trajetNonFixe.getListeVilleStop()
                                                    , Jour.getJourById(trajetTrier.get(compteur).get(2)), horaireDebut);
                                            trajetAjout.add(trajetAdd);
                                            nombreAjout++;
                                            nbrTrajetNonFixe++;
                                        }
                                    } else if (heureArrivee.isBefore(horaireFin)) {
                                        if (trajetNonFixe.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant() || trajetTrier.get(compteur).get(2) < jourDepart.getIdentifiant() + 6) {
                                            TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetNonFixe, trajetNonFixe.getVilleDepart(), trajetNonFixe.getVilleArrivee(), trajetNonFixe.getTempsDeConduite(), trajetNonFixe.getListeVilleStop()
                                                    , Jour.getJourById(trajetTrier.get(compteur).get(2)), horaireFin);
                                            trajetAjout.add(trajetAdd);
                                            nombreAjout++;
                                            nbrTrajetNonFixe++;
                                        }
                                    }
                                } else if (trajetTrier.get(compteur).get(2) > trajetActuelle.getJourDepart().getIdentifiant()) {
                                    if (trajetNonFixe.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant() || trajetTrier.get(compteur).get(2) < jourDepart.getIdentifiant() + 6) {
                                        String[] fenetreTempsDepart = trajetNonFixe.getFenetreDeTemps()[0].split(";");
                                        String[] tempsDepart = fenetreTempsDepart[0].split("-");
                                        double heureDebut = Double.parseDouble(tempsDepart[0]);
                                        LocalTime horaireDebut = LocalTime.of((int) heureDebut, (int) (heureDebut - (int) heureDebut) * 100);
                                        TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetNonFixe, trajetNonFixe.getVilleDepart(), trajetNonFixe.getVilleArrivee(), trajetNonFixe.getTempsDeConduite(), trajetNonFixe.getListeVilleStop()
                                                , Jour.getJourById(trajetTrier.get(compteur).get(2)), horaireDebut);
                                        trajetAjout.add(trajetAdd);
                                        nombreAjout++;
                                        nbrTrajetNonFixe++;
                                    }
                                }
                            }
                        }
                        compteur++;
                    }
                    if (nombreAjout > 0) {
                        Random r = new Random();
                        int nombreAleatoire = r.nextInt(nombreAjout-1);

                        trajet.add(trajetAjout.get(nombreAleatoire));
                        idTrajet.add(trajetAjout.get(nombreAleatoire).getIdentifiant());
                        index = compteur;
                        trajetActuelle = trajetAjout.get(nombreAleatoire);

                        if (trajetActuelle.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant()) {
                            if (trajetActuelle.getJourDepart().getIdentifiant() >= 4) {
                                camionTrajet.put(identifiantCamion, idTrajet);
                                identifiantCamion++;
                                idTrajet = new ArrayList<Integer>();
                                compteur = 0;
                                put = true;
                                premierTrajet = true;
                            }
                        }
                        miseAJour(index, trajetTrier);
                    } else {
                        if (trajetActuelle.getVilleArrivee().getIdentifiant() != premiereVille.getIdentifiant()) {
                            LocalTime heureArrivee = trajetActuelle.getHeureDepart();
                            heureArrivee = heureArrivee.plusHours((int) trajetActuelle.getTempsDeConduite());
                            heureArrivee = heureArrivee.plusMinutes((int) (trajetActuelle.getTempsDeConduite() - (int) trajetActuelle.getTempsDeConduite()) * 100);
                            TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetAVide, trajetActuelle.getVilleArrivee(), premiereVille, 9, null
                                    , trajetActuelle.getJourDepart(), heureArrivee);

                            trajet.add(trajetAdd);
                            idTrajet.add(trajetAdd.getIdentifiant());
                            nbrTrajetAVide++;
                        }
                        camionTrajet.put(identifiantCamion, idTrajet);
                        identifiantCamion++;
                        idTrajet = new ArrayList<Integer>();
                        compteur = 0;
                        put = true;
                        premierTrajet = true;
                    }
                }
            }
            if (put == false) {
                if (trajetActuelle.getVilleArrivee().getIdentifiant() != premiereVille.getIdentifiant()) {
                    LocalTime heureArrivee = trajetActuelle.getHeureDepart();
                    heureArrivee = heureArrivee.plusHours((int) trajetActuelle.getTempsDeConduite());
                    heureArrivee = heureArrivee.plusMinutes((int) (trajetActuelle.getTempsDeConduite() - (int) trajetActuelle.getTempsDeConduite()) * 100);
                    TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetAVide, trajetActuelle.getVilleArrivee(), premiereVille, 9, null
                            , trajetActuelle.getJourDepart(), heureArrivee);

                    trajet.add(trajetAdd);
                    idTrajet.add(trajetAdd.getIdentifiant());
                    nbrTrajetAVide++;
                }
                camionTrajet.put(identifiantCamion, idTrajet);
                identifiantCamion++;
            }
            if(identifiantCamion == nombreCamionMin){
                listeCamionsTrajets.add(camionTrajet);
            }else if(identifiantCamion < nombreCamionMin){
                listeCamionsTrajets.clear();
                listeCamionsTrajets.add(camionTrajet);
                nombreCamionMin = identifiantCamion;
            }
            occurence++;
        }

        return listeCamionsTrajets;
    }
}
