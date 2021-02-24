package modele;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

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
     * @return un objet Solution
     * correspondant aux identifiants des trajets
     */
    public Solution camionTrajets(){

        //Scanner clavier = new Scanner(System.in);

        Solution solution = new Solution();

        //Etape 1 : Tri de l'ensemble des trajets par date de départ possible
        ArrayList<ArrayList<Integer>> trajetTrier = this.listeTrajetsStandard();
        triTrajetsStandard(trajetTrier);

        //Etape 2 : Affectation d'une liste de trajets pour un Symone
        HashMap<Integer,ArrayList<Integer>> camionTrajet = new HashMap<Integer,ArrayList<Integer>>();
        ArrayList<TrajetFixe> trajet = new ArrayList<TrajetFixe>();
        ArrayList<Integer> idTrajet = new ArrayList<Integer>();

        int identifiantCamion = 1;
        int compteur = 0;
        int index=0;

        TrajetFixe trajetActuelle = new TrajetFixe();
        boolean premierTrajet = true;
        Ville premiereVille = null;
        Jour jourDepart = null;

        int nbrTrajetNonFixe = 100;
        int nbrTrajetAVide = 200;

        while(!trajetTrier.isEmpty()){
            //Etape 3 : Retire 1er trajet de la liste et l'affecte au camion courant
            if(premierTrajet){
                // Etape 4 : Si le trajet est un TrajetFixe, un TrajetNonFixe sinon
                // Affecte le trajet au camion courant + MAJ trajet connecté
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
            //Etape 5 : TQ List <>
            else{
                boolean ajoutTrajet = false;
                //Etape 6 : On cherche dans l'ordre le 1er trajet qui respecte les 3 criteres suivants
                // 1 - Ville de départ = ville courante de la symone
                // 2 - Succession possible au niveau temporelle
                // 3 - Terminer son trajet à la ville de départ || terminer avant le jour (départ+6)
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
                //Etape 7 (2cas) :
                // 1.1 - Si trajet trouvé on affecte un des trajets aleatoirement à la date au plus tôt + MAJ connecté
                // 1.2 - Si le dernier trajet ajouté est revenu à la ville de départ de la
                // symone et <= jeudi compris alors Etape 5) sinon on ajoute une nouvelle symone et Etape 3)
                // 2 - Si trajet non trouvé : ajout d'une nouvelle symone et d'un trajet à vide pour finir le cycle si besoin ==> Etape 3)
                if(ajoutTrajet){
                    boolean ajout = false;
                    if(trajetActuelle.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant()){
                        if(trajetActuelle.getJourDepart().getIdentifiant()>=4){
                            camionTrajet.put(identifiantCamion,idTrajet);
                            identifiantCamion++;
                            idTrajet = new ArrayList<Integer>();
                            compteur=0;
                            premierTrajet = true;
                            ajout = true;
                        }
                    }
                    miseAJour(index,trajetTrier);

                    if(trajetTrier.isEmpty() && ajout == false){
                        if(trajetActuelle.getVilleArrivee().getIdentifiant() != premiereVille.getIdentifiant()){
                            LocalTime heureArrivee = trajetActuelle.getHeureDepart();
                            heureArrivee = heureArrivee.plusHours((int)trajetActuelle.getTempsDeConduite());
                            heureArrivee = heureArrivee.plusMinutes((int)(trajetActuelle.getTempsDeConduite()-(int)trajetActuelle.getTempsDeConduite())*100);


                            //1ere solution pour trajet à vide
                            TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetAVide, trajetActuelle.getVilleArrivee(), premiereVille, 9, null, trajetActuelle.getJourDepart(), heureArrivee);

                            //2eme solution pour trajet à vide
//                            double tempsDeConduite = 9;
//                            boolean entreeCorrecte = false;
//                            while(entreeCorrecte == false){
//                                Scanner sc = new Scanner(System.in);
//                                System.out.println("Quel est le temps de conduite entre "+trajetActuelle.getVilleArrivee().getNom()+" et "+premiereVille.getNom()+ " ?");
//                                String str = sc.nextLine();
//
//                                try {
//                                    tempsDeConduite = Double.parseDouble(str);
//                                    entreeCorrecte = true;
//                                }catch (Exception e){
//                                    System.out.println("Veuillez entrer un nombre valide");
//                                }
//                            }
//                            TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetAVide, trajetActuelle.getVilleArrivee(), premiereVille, tempsDeConduite, null, trajetActuelle.getJourDepart(), heureArrivee);


                            trajet.add(trajetAdd);
                            idTrajet.add(trajetAdd.getIdentifiant());
                            nbrTrajetAVide++;
                        }
                        camionTrajet.put(identifiantCamion,idTrajet);
                        identifiantCamion++;
                        idTrajet = new ArrayList<Integer>();
                        compteur=  0;
                    }
                }else{
                    if(trajetActuelle.getVilleArrivee().getIdentifiant() != premiereVille.getIdentifiant()){
                        LocalTime heureArrivee = trajetActuelle.getHeureDepart();
                        heureArrivee = heureArrivee.plusHours((int)trajetActuelle.getTempsDeConduite());
                        heureArrivee = heureArrivee.plusMinutes((int)(trajetActuelle.getTempsDeConduite()-(int)trajetActuelle.getTempsDeConduite())*100);
                        //1ere solution pour trajet à vide
                        TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetAVide, trajetActuelle.getVilleArrivee(), premiereVille, 9, null, trajetActuelle.getJourDepart(), heureArrivee);

                        //2eme solution pour trajet à vide
//                        double tempsDeConduite = 9;
//                        boolean entreeCorrecte = false;
//                        while(entreeCorrecte == false){
//                            Scanner sc = new Scanner(System.in);
//                            System.out.println("Quel est le temps de conduite entre "+trajetActuelle.getVilleArrivee().getNom()+" et "+premiereVille.getNom()+ " ?");
//                            String str = sc.nextLine();
//
//                            try {
//                                tempsDeConduite = Double.parseDouble(str);
//                                entreeCorrecte = true;
//                            }catch (Exception e){
//                                System.out.println("Veuillez entrer un nombre valide");
//                            }
//                        }
//                        TrajetFixe trajetAdd = new TrajetFixe(nbrTrajetAVide, trajetActuelle.getVilleArrivee(), premiereVille, tempsDeConduite, null, trajetActuelle.getJourDepart(), heureArrivee);

                        trajet.add(trajetAdd);
                        idTrajet.add(trajetAdd.getIdentifiant());
                        nbrTrajetAVide++;
                    }
                    camionTrajet.put(identifiantCamion,idTrajet);
                    identifiantCamion++;
                    idTrajet = new ArrayList<Integer>();
                    compteur=  0;
                    premierTrajet = true;
                }
            }
        }

        solution.setTrajets(trajet);
        solution.setCamionsTrajets(camionTrajet);
        solution.setNbrCamions(identifiantCamion);

        return solution;
    }
    /**
     * Affectation des camions aux trajets avec de l'aleatoire
     * @param nombreRepetition nombre de repetition de l'algorithme
     * @return Une liste de solution
     */
    public ArrayList<Solution> camionTrajetsAleatoire(int nombreRepetition) {

        ArrayList<Solution> listeSolution = new ArrayList<Solution>();

        int occurence = 0;
        int nombreCamionMin = 100;

        while (occurence < nombreRepetition) {

            //Etape 1 : Tri de l'ensemble des trajets par date de départ possible
            ArrayList<ArrayList<Integer>> trajetTrier = this.listeTrajetsStandard();
            triTrajetsStandard(trajetTrier);

            //Etape 2 : Affectation d'une liste de trajets pour un Symone
            HashMap<Integer, ArrayList<Integer>> camionTrajet = new HashMap<Integer, ArrayList<Integer>>();
            ArrayList<TrajetFixe> trajet = new ArrayList<TrajetFixe>();
            ArrayList<Integer> idTrajet = new ArrayList<Integer>();

            int identifiantCamion = 1;
            int compteur = 0;
            int index = 0;
            int nombreAleatoire = 0;
            int identifiantTrajetNonFixe = 100;
            int identifiantTrajetAVide = 200;

            boolean premierTrajet = true;
            Ville premiereVille = null;
            Jour jourDepart = null;
            TrajetFixe trajetActuelle = new TrajetFixe();


            while (trajetTrier.isEmpty() == false) {

                //Etape 3 : Retire 1er trajet de la liste aleatoirement parmi les 3 premiers et l'affecte au camion courant
                if (premierTrajet == true) {
                    do {
                        Random r = new Random();
                        nombreAleatoire = r.nextInt(3);
                    } while (nombreAleatoire >= trajetTrier.size());

                    // Etape 4 : Si le trajet est un TrajetFixe, un TrajetNonFixe sinon
                    // Affecte le trajet au camion courant + MAJ trajet connecté
                    if (plannification.getTrajetFixeById(trajetTrier.get(nombreAleatoire).get(0)) != null) {
                        TrajetFixe trajetFixe = plannification.getTrajetFixeById(trajetTrier.get(nombreAleatoire).get(0));
                        trajet.add(trajetFixe);
                        idTrajet.add(trajetFixe.getIdentifiant());
                        index = nombreAleatoire;
                        premierTrajet = false;
                        trajetActuelle = plannification.getTrajetFixeById(trajetTrier.get(nombreAleatoire).get(0));
                        premiereVille = trajetActuelle.getVilleDepart();
                        jourDepart = trajetActuelle.getJourDepart();
                    } else {
                        TrajetNonFixe trajetNonFixe = plannification.getTrajetNonFixeById(trajetTrier.get(nombreAleatoire).get(0));
                        String[] fenetreTempsDepart = trajetNonFixe.getFenetreDeTemps()[0].split(";");
                        String[] tempsDepart = fenetreTempsDepart[0].split("-");
                        double heureArrivee = Double.parseDouble(tempsDepart[0]);
                        LocalTime horaire = LocalTime.of((int) heureArrivee, (int) (heureArrivee - (int) heureArrivee) * 100);

                        TrajetFixe trajetAdd = new TrajetFixe(identifiantTrajetNonFixe, trajetNonFixe.getVilleDepart(), trajetNonFixe.getVilleArrivee(), trajetNonFixe.getTempsDeConduite(), trajetNonFixe.getListeVilleStop()
                                , Jour.getJourById(trajetTrier.get(nombreAleatoire).get(2)), horaire);

                        trajet.add(trajetAdd);
                        idTrajet.add(trajetAdd.getIdentifiant());

                        index = nombreAleatoire;
                        premierTrajet = false;
                        trajetActuelle = trajetAdd;
                        premiereVille = trajetActuelle.getVilleDepart();
                        jourDepart = trajetActuelle.getJourDepart();
                        identifiantTrajetNonFixe++;
                    }
                    miseAJour(index, trajetTrier);
                }
                //Etape 5 : TQ List <>
                else {
                    ArrayList<TrajetFixe> trajetAjout = new ArrayList<TrajetFixe>();
                    int nombreAjout = 0;

                    //Etape 6 : On cherche dans l'ordre les 3 premiers trajets qui respecte les 3 critere suivants
                    // 1 - Ville de départ = ville courante de la symone
                    // 2 - Succession possible au niveau temporelle
                    // 3 - Terminer son trajet à la ville de départ || terminer avant le jour (départ+6)
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
                                            TrajetFixe trajetAdd = new TrajetFixe(identifiantTrajetNonFixe, trajetNonFixe.getVilleDepart(), trajetNonFixe.getVilleArrivee(), trajetNonFixe.getTempsDeConduite(), trajetNonFixe.getListeVilleStop()
                                                    , Jour.getJourById(trajetTrier.get(compteur).get(2)), horaireDebut);
                                            trajetAjout.add(trajetAdd);
                                            nombreAjout++;
                                            identifiantTrajetNonFixe++;
                                        }
                                    } else if (heureArrivee.isBefore(horaireFin)) {
                                        if (trajetNonFixe.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant() || trajetTrier.get(compteur).get(2) < jourDepart.getIdentifiant() + 6) {
                                            TrajetFixe trajetAdd = new TrajetFixe(identifiantTrajetNonFixe, trajetNonFixe.getVilleDepart(), trajetNonFixe.getVilleArrivee(), trajetNonFixe.getTempsDeConduite(), trajetNonFixe.getListeVilleStop()
                                                    , Jour.getJourById(trajetTrier.get(compteur).get(2)), horaireFin);
                                            trajetAjout.add(trajetAdd);
                                            nombreAjout++;
                                            identifiantTrajetNonFixe++;
                                        }
                                    }
                                } else if (trajetTrier.get(compteur).get(2) > trajetActuelle.getJourDepart().getIdentifiant()) {
                                    if (trajetNonFixe.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant() || trajetTrier.get(compteur).get(2) < jourDepart.getIdentifiant() + 6) {
                                        String[] fenetreTempsDepart = trajetNonFixe.getFenetreDeTemps()[0].split(";");
                                        String[] tempsDepart = fenetreTempsDepart[0].split("-");
                                        double heureDebut = Double.parseDouble(tempsDepart[0]);
                                        LocalTime horaireDebut = LocalTime.of((int) heureDebut, (int) (heureDebut - (int) heureDebut) * 100);
                                        TrajetFixe trajetAdd = new TrajetFixe(identifiantTrajetNonFixe, trajetNonFixe.getVilleDepart(), trajetNonFixe.getVilleArrivee(), trajetNonFixe.getTempsDeConduite(), trajetNonFixe.getListeVilleStop()
                                                , Jour.getJourById(trajetTrier.get(compteur).get(2)), horaireDebut);
                                        trajetAjout.add(trajetAdd);
                                        nombreAjout++;
                                        identifiantTrajetNonFixe++;
                                    }
                                }
                            }
                        }
                        compteur++;
                    }
                    //Etape 7 (2cas) :
                    // 1.1 - Si trajet trouvé on affecte un des trajets aleatoirement à la date au plus tôt + MAJ connecté
                    // 1.2 - Si le dernier trajet ajouté est revenu à la ville de départ de la
                    // symone et <= jeudi compris alors Etape 5) sinon on ajoute une nouvelle symone et Etape 3)
                    // 2 - Si trajet non trouvé : ajout d'une nouvelle symone et d'un trajet à vide pour finir le cycle si besoin ==> Etape 3)
                    if (nombreAjout > 0) {
                        boolean ajout = false;

                        Random r = new Random();
                        if(nombreAjout == 1)
                            nombreAleatoire = 0;
                        else
                            nombreAleatoire = r.nextInt(nombreAjout-1);

                        trajet.add(trajetAjout.get(nombreAleatoire));
                        idTrajet.add(trajetAjout.get(nombreAleatoire).getIdentifiant());
                        index = nombreAleatoire;
                        trajetActuelle = trajetAjout.get(nombreAleatoire);

                        if (trajetActuelle.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant()) {
                            if (trajetActuelle.getJourDepart().getIdentifiant() >= 4) {
                                camionTrajet.put(identifiantCamion, idTrajet);
                                identifiantCamion++;
                                idTrajet = new ArrayList<Integer>();
                                compteur = 0;
                                premierTrajet = true;
                                ajout = true;
                            }
                        }
                        miseAJour(index, trajetTrier);

                        if(trajetTrier.isEmpty() && ajout == false){
                            if (trajetActuelle.getVilleArrivee().getIdentifiant() != premiereVille.getIdentifiant()) {
                                LocalTime heureArrivee = trajetActuelle.getHeureDepart();
                                heureArrivee = heureArrivee.plusHours((int) trajetActuelle.getTempsDeConduite());
                                heureArrivee = heureArrivee.plusMinutes((int) (trajetActuelle.getTempsDeConduite() - (int) trajetActuelle.getTempsDeConduite()) * 100);

                                //1ere solution pour trajet à vide
                                TrajetFixe trajetAdd = new TrajetFixe(identifiantTrajetAVide, trajetActuelle.getVilleArrivee(), premiereVille, 9, null, trajetActuelle.getJourDepart(), heureArrivee);

                                //2eme solution pour trajet à vide
//                                double tempsDeConduite = 9;
//                                boolean entreeCorrecte = false;
//                                while(entreeCorrecte == false){
//                                    Scanner sc = new Scanner(System.in);
//                                    System.out.println("Quel est le temps de conduite entre "+trajetActuelle.getVilleArrivee().getNom()+" et "+premiereVille.getNom()+ " ?");
//                                    String str = sc.nextLine();
//
//                                    try {
//                                        tempsDeConduite = Double.parseDouble(str);
//                                        entreeCorrecte = true;
//                                    }catch (Exception e){
//                                        System.out.println("Veuillez entrer un nombre valide");
//                                    }
//                                }
//                                TrajetFixe trajetAdd = new TrajetFixe(identifiantTrajetAVide, trajetActuelle.getVilleArrivee(), premiereVille, tempsDeConduite, null, trajetActuelle.getJourDepart(), heureArrivee);

                                trajet.add(trajetAdd);
                                idTrajet.add(trajetAdd.getIdentifiant());
                                identifiantTrajetAVide++;
                            }
                            camionTrajet.put(identifiantCamion, idTrajet);
                            identifiantCamion++;
                        }

                    } else {
                        if (trajetActuelle.getVilleArrivee().getIdentifiant() != premiereVille.getIdentifiant()) {
                            LocalTime heureArrivee = trajetActuelle.getHeureDepart();
                            heureArrivee = heureArrivee.plusHours((int) trajetActuelle.getTempsDeConduite());
                            heureArrivee = heureArrivee.plusMinutes((int) (trajetActuelle.getTempsDeConduite() - (int) trajetActuelle.getTempsDeConduite()) * 100);
                            //1ere solution pour trajet à vide
                            TrajetFixe trajetAdd = new TrajetFixe(identifiantTrajetAVide, trajetActuelle.getVilleArrivee(), premiereVille, 9, null, trajetActuelle.getJourDepart(), heureArrivee);

                            //2eme solution pour trajet à vide
//                            double tempsDeConduite = 9;
//                            boolean entreeCorrecte = false;
//                            while(entreeCorrecte == false){
//                                Scanner sc = new Scanner(System.in);
//                                System.out.println("Quel est le temps de conduite entre "+trajetActuelle.getVilleArrivee().getNom()+" et "+premiereVille.getNom()+ " ?");
//                                String str = sc.nextLine();
//
//                                try {
//                                    tempsDeConduite = Double.parseDouble(str);
//                                    entreeCorrecte = true;
//                                }catch (Exception e){
//                                    System.out.println("Veuillez entrer un nombre valide");
//                                }
//                            }
//                            TrajetFixe trajetAdd = new TrajetFixe(identifiantTrajetAVide, trajetActuelle.getVilleArrivee(), premiereVille, tempsDeConduite, null, trajetActuelle.getJourDepart(), heureArrivee);


                            trajet.add(trajetAdd);
                            idTrajet.add(trajetAdd.getIdentifiant());
                            identifiantTrajetAVide++;
                        }
                        camionTrajet.put(identifiantCamion, idTrajet);
                        identifiantCamion++;
                        idTrajet = new ArrayList<Integer>();
                        compteur = 0;
                        premierTrajet = true;
                    }
                }
            }

            //On ajoute la solution à la liste de solutions si le nombre de camion est egale au nombre de camion minimale existant
            if(identifiantCamion == nombreCamionMin){
                Solution solution = new Solution();
                solution.setCamionsTrajets(camionTrajet);
                solution.setNbrCamions(identifiantCamion);
                solution.setTrajets(trajet);

                boolean existe = false;
                for(Solution solutionIterateur : listeSolution){
                    if(Solution.egale(solution,solutionIterateur)){
                        existe = true;
                    }
                }
                if(!existe){
                    listeSolution.add(solution);
                }
            }
            //On ajoute la solution après avoir supprimer les anciennces solutions
            // si le nombre de camion est inferieur au nombre de camion minimale existant
            else if(identifiantCamion < nombreCamionMin){
                listeSolution = new ArrayList<Solution>();
                Solution solution = new Solution();
                solution.setCamionsTrajets(camionTrajet);
                solution.setNbrCamions(identifiantCamion);
                solution.setTrajets(trajet);
                listeSolution.add(solution);
                nombreCamionMin = identifiantCamion;
            }

            occurence++;
        }
        return listeSolution;
    }
}
