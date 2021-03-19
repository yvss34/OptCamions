package modele;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.*;

/**
 * Cette classe correspond au moteur de résolution
 * permettant la plannification et la proposition d'une solution
 */
public class MoteurDeResolutionPrototype {

    /**Attributes**/
    Plannification plannification;

    /**Constructors**/
    public MoteurDeResolutionPrototype(Plannification plannification) {
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
        solution.setNbrCamions(identifiantCamion-1);

        Checker checker = new Checker(solution);
        //ASSERT
        if(checker.verificationCamion()) {
            return solution;
        }else
            return null;
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

        int FACTEUR_ALEATOIRE = 5;
        int FACTEUR_JOUR = 6;

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
                compteur = 0;
                //Etape 3 : Retire 1er trajet de la liste aleatoirement parmi les 3 premiers et l'affecte au camion courant
                if (premierTrajet == true) {

                    if(FACTEUR_ALEATOIRE > trajetTrier.size()){
                        do {
                            Random r = new Random();
                            nombreAleatoire = r.nextInt(trajetTrier.size() );
                        } while (nombreAleatoire >= trajetTrier.size());
                    }else{
                        do {
                            Random r = new Random();
                            nombreAleatoire = r.nextInt(FACTEUR_ALEATOIRE);
                        } while (nombreAleatoire >= trajetTrier.size());
                    }

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
                    while (compteur < trajetTrier.size() && nombreAjout < (FACTEUR_ALEATOIRE-1)) {
                        TrajetFixe trajetFixe = plannification.getTrajetFixeById(trajetTrier.get(compteur).get(0));
                        TrajetNonFixe trajetNonFixe = plannification.getTrajetNonFixeById(trajetTrier.get(compteur).get(0));
                        if (trajetFixe != null) {
                            if (trajetFixe.getVilleDepart().getIdentifiant() == trajetActuelle.getVilleArrivee().getIdentifiant()) {
                                if (trajetFixe.getJourDepart().getIdentifiant() == trajetActuelle.getJourDepart().getIdentifiant()) {
                                    LocalTime heureArrivee = trajetActuelle.getHeureDepart();
                                    if(trajetFixe.getHeureDepart().isAfter(heureArrivee)) {
                                        heureArrivee = heureArrivee.plusHours((int) trajetActuelle.getTempsDeConduite());
                                        heureArrivee = heureArrivee.plusMinutes((int) (trajetActuelle.getTempsDeConduite() - (int) trajetActuelle.getTempsDeConduite()) * 100);
                                        if (trajetFixe.getHeureDepart().isAfter(heureArrivee)) {
                                            if (trajetFixe.getVilleArrivee().getIdentifiant() == premiereVille.getIdentifiant() || trajetFixe.getJourDepart().getIdentifiant() < jourDepart.getIdentifiant() + 6) {
                                                trajetAjout.add(trajetFixe);
                                                nombreAjout++;
                                            }
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
                            if (trajetActuelle.getJourDepart().getIdentifiant() >= FACTEUR_JOUR) {
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

            if (trajetActuelle.getVilleArrivee().getIdentifiant() != premiereVille.getIdentifiant()) {
                LocalTime heureArrivee = trajetActuelle.getHeureDepart();
                heureArrivee = heureArrivee.plusHours((int) trajetActuelle.getTempsDeConduite());
                heureArrivee = heureArrivee.plusMinutes((int) (trajetActuelle.getTempsDeConduite() - (int) trajetActuelle.getTempsDeConduite()) * 100);

                TrajetFixe trajetAdd = new TrajetFixe(identifiantTrajetAVide, trajetActuelle.getVilleArrivee(), premiereVille, 9, null, trajetActuelle.getJourDepart(), heureArrivee);

                trajet.add(trajetAdd);
                idTrajet.add(trajetAdd.getIdentifiant());
                identifiantTrajetAVide++;
                camionTrajet.put(identifiantCamion, idTrajet);
                identifiantCamion++;
                idTrajet = new ArrayList<Integer>();
                compteur = 0;
                premierTrajet = true;
            }


            //On ajoute la solution à la liste de solutions si le nombre de camion est egale au nombre de camion minimale existant
            if(identifiantCamion == nombreCamionMin){
                Solution solution = new Solution();
                solution.setCamionsTrajets(camionTrajet);

                solution.setNbrCamions(identifiantCamion-1);
                solution.setTrajets(trajet);
                solution.setPlannification(plannification);

                Checker checker = new Checker(solution);
                //ASSERT
                if(checker.verificationCamion()){
                    boolean existe = false;
                    for(Solution solutionIterateur : listeSolution){
                        existe = Solution.egale(solution,solutionIterateur);
                    }
                    if(!existe){
                        listeSolution.add(solution);
                    }
                }
            }
            //On ajoute la solution après avoir supprimer les anciennces solutions
            // si le nombre de camion est inferieur au nombre de camion minimale existant
            else if(identifiantCamion < nombreCamionMin){

                Solution solution = new Solution();
                solution.setCamionsTrajets(camionTrajet);

                solution.setNbrCamions(identifiantCamion-1);
                solution.setTrajets(trajet);
                solution.setPlannification(plannification);
                Checker checker = new Checker(solution);
                //ASSERT
                if(checker.verificationCamion()){

                    listeSolution = new ArrayList<Solution>();
                    listeSolution.add(solution);
                    nombreCamionMin = identifiantCamion;
                }
            }
            occurence++;
        }
        return listeSolution;
    }


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


    /**
     * Cette fonction permet de generer une liste de trajets unitaires en ajoutant temps de pauses et villes stops
     * @param pSolution une solution avec des trajets sans pause et ville stops
     * @return une liste de trajets fixes
     */
    public void listeTrajetsUnitaire(Solution pSolution){

        ArrayList<TrajetFixe> trajets = new ArrayList<TrajetFixe>();
        HashMap<Integer,ArrayList<Integer>> camionTrajets = new  HashMap<Integer,ArrayList<Integer>>();
        int nbrTrajet = 0;

        for (Map.Entry<Integer, ArrayList<Integer>> map : pSolution.getCamionsTrajets().entrySet()) {
            ArrayList<Integer> idTrajet = new ArrayList<Integer>();
            for (int i = 0; i < map.getValue().size(); i++) {
                TrajetFixe trajet = pSolution.getTrajetById(map.getValue().get(i));
                Ville villeActuelle = trajet.getVilleDepart();
                double tempsDeConduite = 0.0;
                double tempsDePause = 0.0;

                if(trajet.getListeVilleStop() != null){
                trajet.setListeVilleStop(sort(trajet.getListeVilleStop()));

                for (Map.Entry<Ville, Double> mapentry : trajet.getListeVilleStop().entrySet()) {
                    LocalTime heureDepart = trajet.getHeureDepart();
                    heureDepart = heureDepart.plusHours((int) tempsDeConduite);
                    heureDepart = heureDepart.plusMinutes((int) (tempsDeConduite - (int) tempsDeConduite) * 100);
                    heureDepart = heureDepart.plusMinutes((int) tempsDePause);

                    TrajetFixe pTrajet = new TrajetFixe(nbrTrajet, villeActuelle, mapentry.getKey(), mapentry.getValue() - tempsDeConduite, null, null, trajet.getJourDepart(), heureDepart);

                    if (pTrajet.getTempsDeConduite() <= pSolution.getPlannification().getNbrConduiteContinueMax()) {
                        ArrayList<Double> tempsDePauseList = new ArrayList<Double>();
                        tempsDePauseList.add(pSolution.getPlannification().getNbrConduiteContinueMax());
                        pTrajet.setTempsDePause(tempsDePauseList);
                        tempsDePause += 45;
                    } else {
                        ArrayList<Double> tempsDePauseList = new ArrayList<Double>();
                        tempsDePauseList.add(pSolution.getPlannification().getNbrConduiteContinueMax());
                        tempsDePauseList.add(pTrajet.getTempsDeConduite());
                        pTrajet.setTempsDePause(tempsDePauseList);
                        tempsDePause += 2 * 45;
                    }

                    nbrTrajet++;
                    villeActuelle = mapentry.getKey();
                    tempsDeConduite = mapentry.getValue();
                    trajets.add(pTrajet);
                    idTrajet.add(pTrajet.getIdentifiant());
                }

                LocalTime heureDepart = trajet.getHeureDepart();
                heureDepart = heureDepart.plusHours((int) tempsDeConduite);
                heureDepart = heureDepart.plusMinutes((int) (tempsDeConduite - (int) tempsDeConduite) * 100);
                heureDepart = heureDepart.plusMinutes((int) tempsDePause);
                TrajetFixe pTrajet = new TrajetFixe(nbrTrajet, villeActuelle, trajet.getVilleArrivee(), trajet.getTempsDeConduite() - tempsDeConduite, null, null, trajet.getJourDepart(), heureDepart);
                trajets.add(pTrajet);
                idTrajet.add(pTrajet.getIdentifiant());

                    if (pTrajet.getTempsDeConduite() <= pSolution.getPlannification().getNbrConduiteContinueMax()) {
                        ArrayList<Double> tempsDePauseList = new ArrayList<Double>();
                        tempsDePauseList.add(pSolution.getPlannification().getNbrConduiteContinueMax());
                        pTrajet.setTempsDePause(tempsDePauseList);
                        tempsDePause += 45;
                    } else {
                        ArrayList<Double> tempsDePauseList = new ArrayList<Double>();
                        tempsDePauseList.add(pSolution.getPlannification().getNbrConduiteContinueMax());
                        tempsDePauseList.add(pTrajet.getTempsDeConduite());
                        pTrajet.setTempsDePause(tempsDePauseList);
                        tempsDePause += 2 * 45;
                    }
                    nbrTrajet++;
            }else{
                    TrajetFixe pTrajet = new TrajetFixe(nbrTrajet, trajet.getVilleDepart(), trajet.getVilleArrivee(), trajet.getTempsDeConduite(), null, null, trajet.getJourDepart(), trajet.getHeureDepart());

                    if (pTrajet.getTempsDeConduite() <= pSolution.getPlannification().getNbrConduiteContinueMax()) {
                        ArrayList<Double> tempsDePauseList = new ArrayList<Double>();
                        tempsDePauseList.add(pSolution.getPlannification().getNbrConduiteContinueMax());
                        pTrajet.setTempsDePause(tempsDePauseList);
                        tempsDePause += 45;
                    } else {
                        ArrayList<Double> tempsDePauseList = new ArrayList<Double>();
                        tempsDePauseList.add(pSolution.getPlannification().getNbrConduiteContinueMax());
                        tempsDePauseList.add(pTrajet.getTempsDeConduite());
                        pTrajet.setTempsDePause(tempsDePauseList);
                        tempsDePause += 2 * 45;
                    }

                    nbrTrajet++;
                    villeActuelle = trajet.getVilleArrivee();
                    trajets.add(pTrajet);
                    idTrajet.add(pTrajet.getIdentifiant());
                }
        }
            camionTrajets.put(map.getKey(),idTrajet);
        }
        pSolution.setCamionsTrajets(camionTrajets);
        pSolution.setTrajets(trajets);
    }

    public ArrayList<TrajetFixe> triListeTrajetFixe(ArrayList<TrajetFixe> pTrajets){
        ArrayList<TrajetFixe> trajets = pTrajets;

        int i,j;
        TrajetFixe temp;
        for(i = 0; i<trajets.size(); i++){
            for(j=0; j<trajets.size();j++){
                if(trajets.get(i).getJourDepart().getIdentifiant() <= trajets.get(j).getJourDepart().getIdentifiant()){
                    if(trajets.get(i).getJourDepart().getIdentifiant() == trajets.get(j).getJourDepart().getIdentifiant()){
                        if(trajets.get(i).getIdentifiant() == trajets.get(j).getIdentifiant()){
                            if(trajets.get(i).getTempsDeConduite() < trajets.get(j).getTempsDeConduite()){
                                temp = trajets.get(i);
                                trajets.set(i, trajets.get(j));
                                trajets.set(j, temp);
                            }
                        }else {
                            LocalTime horaire1 = LocalTime.of(0, 0);
                            LocalTime horaire2 = LocalTime.of(0, 0);

                            TrajetFixe trajet1_1 = trajets.get(i);
                            TrajetFixe trajet2_1 = trajets.get(j);


                            horaire1 = trajet1_1.getHeureDepart();
                            horaire2 = trajet2_1.getHeureDepart();

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
        return trajets;
    }


    /**
     * Ajoute des trajets en train pour les chauffeurs effectue leur repos hebdomadaire chez eux
     * @param pSolution, un objet Solution
     */
    public void trainChauffeurs(Solution pSolution){
        int identifiantTrain = 5000;
        for (Map.Entry<Integer, ArrayList<Integer>> map : pSolution.getChauffeursTrajets().entrySet()) {
                TrajetFixe trajet = pSolution.getTrajetById(map.getValue().get(map.getValue().size()-1));
                Chauffeur chauffeur = pSolution.getChauffeurById(map.getKey());
                if(trajet.getVilleArrivee().getIdentifiant() != chauffeur.getVilleRattachement().getIdentifiant()){

                    Jour jour = trajet.getJourDepart();

                    LocalTime heureArrivee = trajet.getHeureDepart();
                    heureArrivee = heureArrivee.plusHours((int) trajet.getTempsDeConduite());
                    heureArrivee = heureArrivee.plusMinutes((int) (trajet.getTempsDeConduite() - (int) trajet.getTempsDeConduite()) * 100);
                    if(trajet.getHeureDepart().isAfter(heureArrivee)){
                        if(jour.getIdentifiant() == 0){
                            jour = new Jour(1,"Mardi");
                        }else if(jour.getIdentifiant() == 1){
                            jour = new Jour(2,"Mercredi");
                        }else if(jour.getIdentifiant() == 2){
                            jour = new Jour(3,"Jeudi");
                        }else if(jour.getIdentifiant() == 3){
                            jour = new Jour(4,"Vendredi");
                        }else if(jour.getIdentifiant() == 4){
                            jour = new Jour(5,"Samedi");
                        }else if(jour.getIdentifiant() == 5){
                            jour = new Jour(6,"Dimanche");
                        }else if(jour.getIdentifiant() == 6){
                            jour = new Jour(0,"Lundi");
                        }
                    }

                    TrajetFixe trajetAdd = new TrajetFixe(identifiantTrain, trajet.getVilleArrivee(), chauffeur.getVilleRattachement(), 0,null, null, jour, heureArrivee);
                    identifiantTrain++;

                    pSolution.getTrajets().add(trajetAdd);
                    pSolution.getChauffeursTrajets().get(map.getKey()).add(trajetAdd.getIdentifiant());
                }
        }
    }

    /**
     * Fusionne les chauffeurs qui peuvent être fusionnés
     * @param pSolution, un objet solution
     */
    public void optimisationChauffeurs(Solution pSolution){

    }

    public ArrayList<Solution> chauffeurTrajets(ArrayList<Solution> pListeSolution, int nombreRepetition){

        ArrayList<Solution> listeSolution = new ArrayList<Solution>();

        int occurence = 0;
        int nombreChauffeurMin = 1000;
        int FACTEUR_ALEATOIRE = 5;

        for(Solution solution : pListeSolution){

            while(occurence<nombreRepetition){

                HashMap<Integer,ArrayList<Integer>> chauffeursTrajets = new HashMap<Integer,ArrayList<Integer>>();
                Solution solutionClone = (Solution)solution.clone();

                //Etape 1 : Liste Trajet Fixe Unitaires
                this.listeTrajetsUnitaire(solutionClone);

                //Etape 2 : Tri liste trajets unitaires
                ArrayList<TrajetFixe> trajets = new ArrayList<TrajetFixe>();
                ArrayList<TrajetFixe> trajetsFinale = new ArrayList<TrajetFixe>();
                trajets = this.triListeTrajetFixe(solutionClone.getTrajets());


                int nombreAleatoire = 0;
                boolean premierTrajet = true;
                Ville villeCourante = null;
                TrajetFixe trajetActuelle = null;
                double tempsDeConduiteHebdomadaire = 0;
                double[] tempsDeConduiteJournalier = {0,0,0,0,0,0,0};

                ArrayList<Chauffeur> chauffeurs = new ArrayList<Chauffeur>();
                Chauffeur chauffeur = null;
                int identifiantChauffeur = 1;
                ArrayList<Integer> idTrajet = new ArrayList<Integer>();
                Jour premierJourSemaine = null;
                int identifiantReposHebdomadaire = 5;

                while (trajets.isEmpty() == false){

                    //Etape 3 : Nouveau Chauffeur
                    //Etape 4 : On récupère les N premiers trajets, et on en retire un de la liste aléatoirement
                    if(premierTrajet == true){
                        tempsDeConduiteHebdomadaire = 0;
                        for(int i = 0; i<7;i++){
                            tempsDeConduiteJournalier[i] = 0;
                        }
                        idTrajet = new ArrayList<Integer>();

                        do {
                            Random r = new Random();
                            nombreAleatoire = r.nextInt(FACTEUR_ALEATOIRE);
                        } while (nombreAleatoire >= trajets.size());

                        TrajetFixe trajet = trajets.get(nombreAleatoire);

                        //Etape 5 : On affecte le trajet au chauffeur à la date au plus tôt et on affecte une ville de rattachement au chauffeur

                        chauffeur = new Chauffeur(identifiantChauffeur,trajet.getVilleDepart(),null,0);
                        identifiantChauffeur++;
                        chauffeurs.add(chauffeur);
                        idTrajet.add(trajet.getIdentifiant());
                        trajetsFinale.add(trajet);
                        trajets.remove(trajet);
                        premierTrajet = false;

                        trajetActuelle = trajet;
                        villeCourante = trajet.getVilleArrivee();
                        tempsDeConduiteHebdomadaire += trajet.getTempsDeConduite();
                        tempsDeConduiteJournalier[trajet.getJourDepart().getIdentifiant()] += trajet.getTempsDeConduite();
                        premierJourSemaine = trajet.getJourDepart();
                        if(premierJourSemaine.getIdentifiant() == 0){
                            identifiantReposHebdomadaire = 4;
                        }else if(premierJourSemaine.getIdentifiant() == 1){
                            identifiantReposHebdomadaire = 5;
                        }else{
                            identifiantReposHebdomadaire = 6;
                        }
                    }

                    else{
                        int nombreAjout = 0;
                        ArrayList<TrajetFixe> trajetsValide = new ArrayList<TrajetFixe>();
                        int compteur = 0;
                        //Etape 6 : TQ Trajets <> vide :
                        //Etape 6a

                        if(tempsDeConduiteHebdomadaire < solutionClone.getPlannification().getNbrConduiteHebdomadaireMax() - 9){
                            //Etape 6ai. Cherche dans l’ordre les N premiers trajets selon 3 critères
                            while(compteur<trajets.size() && trajetsValide.size()<FACTEUR_ALEATOIRE) {
                                if (trajets.get(compteur).getJourDepart().getIdentifiant() <= identifiantReposHebdomadaire) {
                                    if (trajets.get(compteur).getVilleDepart().getIdentifiant() == villeCourante.getIdentifiant()) {
                                        if ((trajets.get(compteur).getTempsDeConduite() + tempsDeConduiteHebdomadaire <= (solutionClone.getPlannification().getNbrConduiteHebdomadaireMax() - 9)) || (trajets.get(compteur).getVilleArrivee().getIdentifiant() == chauffeur.getVilleRattachement().getIdentifiant() && (trajets.get(compteur).getTempsDeConduite() + tempsDeConduiteHebdomadaire < (solutionClone.getPlannification().getNbrConduiteHebdomadaireMax())))) {
                                            if (trajets.get(compteur).getTempsDeConduite() <= solutionClone.getPlannification().getNbrConduiteJournaliereMax() - tempsDeConduiteJournalier[trajets.get(compteur).getJourDepart().getIdentifiant()]) {
                                                nombreAjout += 1;
                                                trajetsValide.add(trajets.get(compteur));
                                            }
                                        }
                                    }
                                    compteur++;
                                }
                            }

                            if(nombreAjout > 0){

                                /**
                                 * On ne garde que les trajets ou le chauffeur retourne à sa ville de rattachement
                                 * s'il y en a
                                 */
                                int nombreAjoutRattachement = 0;
                                ArrayList<TrajetFixe> trajetsVilleRattachement = new ArrayList<TrajetFixe>();
                                for(TrajetFixe trajetFixe : trajetsValide){
                                    if(trajetFixe.getVilleArrivee().getIdentifiant() == chauffeur.getVilleRattachement().getIdentifiant()){
                                        nombreAjoutRattachement += 1;
                                        trajetsVilleRattachement.add(trajetFixe);
                                    }
                                }
                                if(nombreAjoutRattachement != 0){
                                    trajetsValide = trajetsVilleRattachement;
                                    nombreAjout = nombreAjoutRattachement;
                                }


                                /**
                                 * On ne garde que les trajets le même jour s'il y en a
                                 */

                                int nombreTrajetMemeJour = 0;
                                ArrayList<TrajetFixe> trajetsMemeJour = new ArrayList<TrajetFixe>();
                                for(TrajetFixe trajetFixe : trajetsValide){
                                    if(trajetFixe.getJourDepart().getIdentifiant() == trajetActuelle.getJourDepart().getIdentifiant()){
                                        nombreTrajetMemeJour += 1;
                                        trajetsMemeJour.add(trajetFixe);
                                    }
                                }
                                if(nombreTrajetMemeJour != 0){
                                    trajetsValide = trajetsMemeJour;
                                    nombreAjout = nombreTrajetMemeJour;
                                }

                                /**
                                 * On choisi aléatoirement un des trajets restants
                                 */
                                Random r = new Random();
                                if(nombreAjout == 1)
                                        nombreAleatoire = 0;
                                    else
                                        nombreAleatoire = r.nextInt(nombreAjout-1);

                                    TrajetFixe trajetFixe = trajetsValide.get(nombreAleatoire);
                                    idTrajet.add(trajetFixe.getIdentifiant());
                                    trajetActuelle = trajetFixe;
                                    trajetsFinale.add(trajetFixe);
                                    trajets.remove(trajetFixe);

                                    villeCourante = trajetFixe.getVilleArrivee();
                                    tempsDeConduiteHebdomadaire += trajetFixe.getTempsDeConduite();
                                    tempsDeConduiteJournalier[trajetFixe.getJourDepart().getIdentifiant()] += trajetFixe.getTempsDeConduite();

                            }else{
                                chauffeursTrajets.put(chauffeur.getIdentifiant(),idTrajet);
                                premierTrajet = true;
                            }
                        }
                        else{
                            chauffeursTrajets.put(chauffeur.getIdentifiant(),idTrajet);
                            premierTrajet = true;
                        }
                    }
                }
                if(premierTrajet == false){
                    chauffeursTrajets.put(chauffeur.getIdentifiant(),idTrajet);
                }

                /**
                 * RAJOUTER L'OPTIMISATION DES CHAUFFEURS AVANT D'AJOUTER DES TRAJETS EN TRAIN
                 */
                this.optimisationChauffeurs(solutionClone);

                //On ajoute la solution à la liste de solutions si le nombre de camion est egale au nombre de camion minimale existant
                    if(chauffeursTrajets.size() == nombreChauffeurMin){
                        solutionClone.setChauffeursTrajets(chauffeursTrajets);
                        solutionClone.setChauffeurs(chauffeurs);
                        solutionClone.setNbrChauffeurs(chauffeurs.size());
                        solutionClone.setTrajets(trajetsFinale);

                        /**
                         * RAJOUT TRAJETS TRAINS POUR LES CHAUFFEURS NECESSAIRES
                         */
                        this.trainChauffeurs(solutionClone);

                        Checker checker = new Checker(solutionClone);
                        //ASSERT
                        if(checker.verificationChauffeur()){
                            boolean existe = false;
                            for(Solution solutionIterateur : listeSolution){
                                if(Solution.egale(solutionClone,solutionIterateur)){
                                    existe = true;
                                }
                            }
                            if(!existe){
                                listeSolution.add(solutionClone);
                            }
                        }
                    }
                    //On ajoute la solution après avoir supprimer les anciennces solutions
                    // si le nombre de camion est inferieur au nombre de camion minimale existant
                    else if(chauffeursTrajets.size() < nombreChauffeurMin){

                        solutionClone.setChauffeursTrajets(chauffeursTrajets);
                        solutionClone.setChauffeurs(chauffeurs);
                        solutionClone.setNbrChauffeurs(chauffeurs.size());
                        solutionClone.setTrajets(trajetsFinale);

                        /**
                         * RAJOUT TRAJETS TRAINS POUR LES CHAUFFEURS NECESSAIRES
                         */
                        this.trainChauffeurs(solutionClone);


                        Checker checker = new Checker(solutionClone);
                        //ASSERT
                        if(checker.verificationChauffeur()){

                            listeSolution = new ArrayList<Solution>();
                            listeSolution.add(solutionClone);
                            nombreChauffeurMin = chauffeursTrajets.size();
                        }
                    }
                occurence++;
            }
        }
        return listeSolution;
    }

    public Solution optimisationCout(ArrayList<Solution> pListeSolution){
        double sommeCout = 100000000.0;
        Solution solution = new Solution();
        for(Solution pSolution : pListeSolution){

            //Cout hotellerie
            for (Map.Entry<Integer, ArrayList<Integer>> mapentry : pSolution.getChauffeursTrajets().entrySet()) {
                int nombreRepos = 0;
                int compteur = 0;
                while(compteur < 7){
                    TrajetFixe dernierTrajet = null;
                    for(int i = 0; i<mapentry.getValue().size(); i++) {
                        TrajetFixe trajet = pSolution.getTrajetById(mapentry.getValue().get(i));
                        Ville villeArrivee = trajet.getVilleArrivee();
                        int jour = trajet.getJourDepart().getIdentifiant();
                        if(jour == compteur){
                            if(dernierTrajet == null)
                                dernierTrajet = trajet;
                            else if(trajet.getHeureDepart().isAfter(dernierTrajet.getHeureDepart()))
                                dernierTrajet = trajet;
                        }
                    }
                    if(dernierTrajet != null) {
                        if(dernierTrajet.getVilleArrivee() != pSolution.getChauffeurById(mapentry.getKey()).getVilleRattachement() )
                            nombreRepos += 1;
                    }
                    compteur += 1;
                }
                pSolution.getChauffeurById(mapentry.getKey()).setCoutHotellerie(nombreRepos*(pSolution.getPlannification().getCoutHotellerie()));
            }


            //Cout contrat de travail
            for (Map.Entry<Integer, ArrayList<Integer>> mapentry : pSolution.getChauffeursTrajets().entrySet()) {
                double nombreHeureJour = 0.0;
                double nombreHeureNuit = 0.0;
                int nombreTrajetAVide = 0;
                for(int i = 0; i<mapentry.getValue().size(); i++) {
                    TrajetFixe trajet = pSolution.getTrajetById(mapentry.getValue().get(i));
                    double FtempsTrajet = trajet.getTempsDeConduite();
                    LocalTime heureDepart = trajet.getHeureDepart();
                    if(trajet.getIdentifiant()>=5000){
                        nombreTrajetAVide++;
                    }

                    if (heureDepart.isBefore(LocalTime.of(21,0)) && heureDepart.isAfter(LocalTime.of(6,0))){
                        nombreHeureJour+=FtempsTrajet;
                    }
                    else {
                        nombreHeureNuit+=FtempsTrajet;
                    }
                }
                ContratDeTravail CT = new ContratDeTravail();
                CT.setNbrHeureJour(nombreHeureJour);
                CT.setNbrHeureNuit(nombreHeureNuit);
                CT.setCoutTrajetVide(nombreTrajetAVide*pSolution.getPlannification().getCoutHotellerie());
                pSolution.getChauffeurById(mapentry.getKey()).setContratDeTravail(CT);
            }

            double coutPSolution = 0.0;
            for(Chauffeur chauffeur : pSolution.getChauffeurs()){
                coutPSolution += chauffeur.getCoutHotellerie();
                coutPSolution += chauffeur.getContratDeTravail().getNbrHeureJour()*pSolution.getPlannification().getCoutHoraireJour();
                coutPSolution += chauffeur.getContratDeTravail().getNbrHeureNuit()*pSolution.getPlannification().getCoutHoraireNuit();
                coutPSolution += chauffeur.getContratDeTravail().getCoutTrajetVide();
            }

            if(coutPSolution<sommeCout){
                sommeCout = coutPSolution;
                solution = pSolution;
            }
        }
        return solution;
    }
}
