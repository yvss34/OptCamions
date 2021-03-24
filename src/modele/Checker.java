package modele;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Cette classe est une classe de verification
 * des solutions, pour vérifier que la solution proposée
 * par l'outil est réalisable
 */
public class Checker {

    /**
     * Attributes
     */
    private Solution solution;

    /**
     * Constructors
     */
    public Checker(Solution solution) {
        this.solution = solution;
    }

    /**
     * Getters & Setters
     */
    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }


/*********************************************Contraintes de Reglementations***********************************************/


    /**
     *
     * @return true si durée de conduite continue respecté, false sinon
     */
    public boolean dureeConduiteContinue(){
        boolean checker = true;

        for(int i = 0; i<getSolution().getTrajets().size(); i++) {
            TrajetFixe trajet = getSolution().getTrajets().get(i);
            double tempsDeConduite = trajet.getTempsDeConduite();
            ArrayList<Double> tempsDePause = trajet.getTempsDePause();

            if (trajet.getIdentifiant() < 5000) {
                if (tempsDePause == null || tempsDePause.isEmpty())
                    checker = false;
                else {
                    if (tempsDePause.get(0) > getSolution().getPlannification().getNbrConduiteContinueMax())
                        checker = false;

                    if (tempsDeConduite - tempsDePause.get(tempsDePause.size() - 1) > getSolution().getPlannification().getNbrConduiteContinueMax())
                        checker = false;

                    for (int j = 0; j < tempsDePause.size() - 1; j++) {
                        if (tempsDePause.get(j + 1) - tempsDePause.get(j) > getSolution().getPlannification().getNbrConduiteContinueMax())
                            checker = false;
                    }
                }
            }
        }

        return checker;
    }

    /**
     *
     * @return true si un durée de conduite journaliere respecté, false sinon
     */
    public boolean dureeConduiteJournaliere(){
        boolean checker = true;
        for (Map.Entry<Integer, ArrayList<Integer>> mapentry : getSolution().getChauffeursTrajets().entrySet()) {
            double conduiteJournaliere[] = {0,0,0,0,0,0,0};

            for(int i = 0; i<mapentry.getValue().size(); i++) {

                TrajetFixe trajet = getSolution().getTrajetById(mapentry.getValue().get(i));
                double tempsDeConduite = trajet.getTempsDeConduite();
                int jour = trajet.getJourDepart().getIdentifiant();

                conduiteJournaliere[jour] += tempsDeConduite;
            }
            for(double nbrHeureConduite : conduiteJournaliere) {
                if(nbrHeureConduite > getSolution().getPlannification().getNbrConduiteJournaliereMax())
                    checker = false;
            }
        }
        return checker;
    }

    /**
     *
     * @return true si un durée de conduite hebdomadaire respecté, false sinon
     */
    public boolean dureeConduiteHebdomadaire(){
        boolean checker = true;
        for (Map.Entry<Integer, ArrayList<Integer>> mapentry : getSolution().getChauffeursTrajets().entrySet()) {
            float conduiteHebdomadaire = 0;
            for(int i = 0; i<mapentry.getValue().size(); i++) {
                TrajetFixe trajet = getSolution().getTrajetById(mapentry.getValue().get(i));
                double tempsDeConduite = trajet.getTempsDeConduite();
                conduiteHebdomadaire += tempsDeConduite;
            }
            if(conduiteHebdomadaire > getSolution().getPlannification().getNbrConduiteHebdomadaireMax())
                checker = false;
        }
        return checker;
    }


    /**
     *
     * @return true si un temps de repos journalier respecté, false sinon
     */
    public boolean tempsReposJournalier() {
        boolean checker = true;
        for (Map.Entry<Integer, ArrayList<Integer>> mapentry : getSolution().getChauffeursTrajets().entrySet()) {
            double conduiteJournaliere[] = {0,0,0,0,0,0,0};
            for(int i = 0; i<mapentry.getValue().size(); i++) {
                TrajetFixe trajet = getSolution().getTrajetById(mapentry.getValue().get(i));
                double tempsDeConduite = trajet.getTempsDeConduite();
                int jour = trajet.getJourDepart().getIdentifiant();

                conduiteJournaliere[jour] += tempsDeConduite;
            }
            for(double nbrHeureConduite : conduiteJournaliere) {
                if(24.0-nbrHeureConduite < getSolution().getPlannification().getTempsDeReposJournalier())
                    checker = false;
            }
        }
        return checker;
    }

    /**
     *
     * @return true si un temps de repos hebdomadaire respecté, false sinon
     */
    public boolean tempsReposHebdomadaire() {

        //verification que pour un trajet il n'y a pas de trajet pendant 2 jours
        for (Map.Entry<Integer, ArrayList<Integer>> mapentry : getSolution().getChauffeursTrajets().entrySet()) {
            boolean cptBoolean = false;
            ArrayList<Double> tableauDebutTrajet = new ArrayList<Double>();
            ArrayList<Double> tableauFinTrajet = new ArrayList<Double>();
            for(int i = 0; i<mapentry.getValue().size(); i++) {
                TrajetFixe trajet = getSolution().getTrajetById(mapentry.getValue().get(i));
                int jour = trajet.getJourDepart().getIdentifiant();
                double horraireDepart = trajet.getHeureDepart().getHour() + ((double)trajet.getHeureDepart().getMinute() / 6);
                double tempsDeConduite = trajet.getTempsDeConduite();
                double finTrajet = horraireDepart+tempsDeConduite;

                tableauDebutTrajet.add(horraireDepart+(24*jour));
                tableauFinTrajet.add(finTrajet+(24*jour));
            }

            tableauDebutTrajet.add((double)24*7);

            Collections.sort(tableauDebutTrajet);
            Collections.sort(tableauFinTrajet);


            for(int i =0; i<tableauFinTrajet.size();i++){

                if (tableauDebutTrajet.get(i+1) - tableauFinTrajet.get(i) >= getSolution().getPlannification().getDureeReposHebdomadaire())
                    cptBoolean = true;
            }

            if(tableauDebutTrajet.get(0) >= getSolution().getPlannification().getDureeReposHebdomadaire())
                cptBoolean = true;

            if(cptBoolean == false)
                return false;

        }
        return true;
    }

    /**
     *
     * @return true si temps de CT minimum respecté, false sinon
     */
    public boolean tempsConduiteMinimum() {

        boolean checker = true;
        for (Map.Entry<Integer, ArrayList<Integer>> mapentry : getSolution().getChauffeursTrajets().entrySet()) {
            float conduiteHebdomadaire = 0;
            for(int i = 0; i<mapentry.getValue().size(); i++) {
                TrajetFixe trajet = getSolution().getTrajetById(mapentry.getValue().get(i));
                double tempsDeConduite = trajet.getTempsDeConduite();
                conduiteHebdomadaire += tempsDeConduite;
            }
            if(conduiteHebdomadaire < getSolution().getPlannification().getNombreHeuresMin())
                checker = false;
        }
        return checker;
    }

/*********************************************Contraintes operationnelles***********************************************/

    /**
     *
     * @return true si un trajet à la fois par chauffeur, false sinon
     */
    public boolean unTrajetALaFoisChauffeurs() {
        boolean checker = true;
        for (Map.Entry<Integer, ArrayList<Integer>> mapentry : getSolution().getChauffeursTrajets().entrySet()) {
            for(int i = 0; i<mapentry.getValue().size(); i++) {
                TrajetFixe trajet1 = getSolution().getTrajetById(mapentry.getValue().get(i));
                int jour1 = trajet1.getJourDepart().getIdentifiant();
                LocalTime heureDepart1 = trajet1.getHeureDepart();
                for(int j = 0; j<mapentry.getValue().size(); j++) {
                    if(i != j) {
                        TrajetFixe trajet2 = getSolution().getTrajetById(mapentry.getValue().get(j));
                        double tempsDeConduite2 = trajet2.getTempsDeConduite();
                        int jour2 = trajet2.getJourDepart().getIdentifiant();
                        LocalTime heureDepart2 = trajet2.getHeureDepart();
                        heureDepart2 = heureDepart2.plusHours((int) tempsDeConduite2);
                        heureDepart2 = heureDepart2.plusMinutes((int) (tempsDeConduite2 - (int) tempsDeConduite2) * 100);

                        if(!heureDepart2.isBefore(trajet2.getHeureDepart())) {
                            if ( (jour1 == jour2) && !(heureDepart1.compareTo(heureDepart2) >= 0 || heureDepart1.isBefore(trajet2.getHeureDepart()))) {
                                checker = false;
                            }
                        }
                        else{
                            if ((jour1 == jour2) && heureDepart1.compareTo(trajet2.getHeureDepart()) >= 0 ) {
                                checker = false;
                            }
                            else if((jour1 == jour2+1) && heureDepart1.isBefore(heureDepart2)){
                                checker = false;
                            }
                        }

                    }
                }
            }
        }
        return checker;
    }

    /**
     *
     * @return true si un trajet à la fois par camion, false sinon
     */
    public boolean unTrajetALaFoisCamions() {
        boolean checker = true;
        for (Map.Entry<Integer, ArrayList<Integer>> mapentry : getSolution().getCamionsTrajets().entrySet()) {
            for(int i = 0; i<mapentry.getValue().size(); i++) {
                TrajetFixe trajet1 = getSolution().getTrajetById(mapentry.getValue().get(i));
                int jour1 = trajet1.getJourDepart().getIdentifiant();
                LocalTime heureDepart1 = trajet1.getHeureDepart();
                for(int j = 0; j<mapentry.getValue().size(); j++) {
                    if(i != j) {
                        TrajetFixe trajet2 = getSolution().getTrajetById(mapentry.getValue().get(j));
                        double tempsDeConduite2 = trajet2.getTempsDeConduite();
                        int jour2 = trajet2.getJourDepart().getIdentifiant();

                        LocalTime heureDepart2 = trajet2.getHeureDepart();
                        heureDepart2 = heureDepart2.plusHours((int) tempsDeConduite2);
                        heureDepart2 = heureDepart2.plusMinutes((int) (tempsDeConduite2 - (int) tempsDeConduite2) * 100);


                        if(!heureDepart2.isBefore(trajet2.getHeureDepart())) {
                            if ( (jour1 == jour2) && !(heureDepart1.compareTo(heureDepart2) >= 0 || heureDepart1.isBefore(trajet2.getHeureDepart()))) {
                                checker = false;
                            }
                        }
                        else{
                            if ((jour1 == jour2) && heureDepart1.compareTo(trajet2.getHeureDepart()) >= 0 ) {
                                checker = false;
                            }
                            else if((jour1 == jour2+1) && heureDepart1.isBefore(heureDepart2)){
                                checker = false;
                            }
                        }

                    }
                }
            }
        }
        return checker;
    }

    /**
     *
     * @return true si tous les trajets ont un camion et un chauffeur, false sinon
     */
    public boolean trajetCamionChauffeur() {
        boolean checker = true;

        for(TrajetFixe trajet : getSolution().getTrajets()) {
            int compteur = 0;

            for (Map.Entry<Integer, ArrayList<Integer>> mapentry1 : getSolution().getCamionsTrajets().entrySet()) {
                if (trajet.getIdentifiant() >= 5000){
                    compteur+=1;
                }
                else if (mapentry1.getValue().contains(trajet.getIdentifiant()) == true) {
                    compteur += 1;
                }
            }
            for (Map.Entry<Integer, ArrayList<Integer>> mapentry2 : getSolution().getChauffeursTrajets().entrySet()) {
                if (mapentry2.getValue().contains(trajet.getIdentifiant()) == true) {
                    compteur += 1;
                }
            }
           if (compteur < 2)
               checker = false;
        }
        return checker;
    }

    /**
     *
     * @return true si tous les trajets ont un camion, false sinon
     */
    public boolean trajetCamion() {
        boolean checker = true;

        for(TrajetFixe trajet : getSolution().getTrajets()) {
            int compteur = 0;

            for (Map.Entry<Integer, ArrayList<Integer>> mapentry1 : getSolution().getCamionsTrajets().entrySet()) {
                if (mapentry1.getValue().contains(trajet.getIdentifiant()) == true) {
                    compteur += 1;
                }
            }
            if (compteur < 1)
                checker = false;
        }
        return checker;
    }



    /**
     *
     * @return true si les chauffeurs passent leur repos hebdomadaire dans leurs villes de rattachements, false sinon
     *
     */
    public boolean reposHebdomadaireVilleRattachement() {
        for (Map.Entry<Integer, ArrayList<Integer>> mapentry : getSolution().getChauffeursTrajets().entrySet()) {

            int i = mapentry.getValue().size() - 1;
            TrajetFixe trajet = getSolution().getTrajetById(mapentry.getValue().get(i));
            if(trajet.getVilleArrivee().getIdentifiant() != getSolution().getChauffeurById(mapentry.getKey()).getVilleRattachement().getIdentifiant()){
                return false;
            }

            //1ere solution
//            TrajetFixe trajet = null;
//
//            for(int i = 0; i < mapentry.getValue().size(); i++){
//                if(trajet == null){
//                    trajet = getSolution().getTrajetById(mapentry.getValue().get(i));
//                }else{
//                    TrajetFixe trajet2 = getSolution().getTrajetById(mapentry.getValue().get(i));
//                    if(trajet2.getJourDepart().getIdentifiant()>trajet.getJourDepart().getIdentifiant()){
//                        trajet = trajet2;
//                    }else if(trajet2.getJourDepart().getIdentifiant() == trajet.getJourDepart().getIdentifiant()){
//                        LocalTime heureArrivee = trajet.getHeureDepart();
//                        double tempsDeConduite = trajet.getTempsDeConduite();
//                        heureArrivee = heureArrivee.plusHours((int) tempsDeConduite);
//                        heureArrivee = heureArrivee.plusMinutes((int) (tempsDeConduite - (int) tempsDeConduite) * 100);
//                        if(trajet2.getHeureDepart().compareTo(heureArrivee)>=0){
//                            trajet = trajet2;
//                        }
//                    }
//                }
//            }
//
//            if(trajet.getVilleArrivee().getIdentifiant() != getSolution().getChauffeurById(mapentry.getKey()).getVilleRattachement().getIdentifiant()){
//                return false;
//            }

        }
        return true;
    }

    /**
     *
     * @return true si les contrats de travail repecte les durées minimales et maximales, false sinon
     */
    public boolean tempsContratTravail() {
        boolean checker = true;
        for(int i = 0; i<getSolution().getChauffeurs().size();i++){
            ContratDeTravail contratDeTravail = getSolution().getChauffeurs().get(i).getContratDeTravail();

            double cptTime = contratDeTravail.getNbrHeureJour() + contratDeTravail.getNbrHeureNuit();

            if(cptTime < (getSolution().getPlannification().getNombreHeuresMin()))
                checker = false;
            if(cptTime > (getSolution().getPlannification().getNombreHeuresMax()))
                checker = false;
        }
        return checker;
    }

    /**
     *
     * @return true si tous les trajets ont des pauses, false sinon
     */
    public boolean trajetsPause() {
        boolean checker = true;
        for(int i = 0; i<getSolution().getTrajets().size();i++)
        {
            if(getSolution().getTrajets().get(i).getIdentifiant() < 5000){
                if (getSolution().getTrajets().get(i).getTempsDePause().isEmpty()){
                    checker = false;
                }
            }
        }
        return checker;
    }

    /**
     *
     * @return true si aucun trajet ne dépasse 9h00 et 8h00 de nuit, false sinon
     */
    public boolean tempsTrajets() {
        boolean checker = true;
        for(int i = 0; i<getSolution().getTrajets().size();i++) {
            double FtempsTrajet = getSolution().getTrajets().get(i).getTempsDeConduite();
            LocalTime tempsDeConduite = LocalTime.of(new Double(FtempsTrajet).intValue(), (int) ((FtempsTrajet-(new Double(FtempsTrajet).intValue()))*60));
            LocalTime cptTime = getSolution().getTrajets().get(i).getHeureDepart().plusHours(tempsDeConduite.getHour());
            cptTime = cptTime.plusMinutes(tempsDeConduite.getMinute());

            if (getSolution().getTrajets().get(i).getHeureDepart().isAfter(LocalTime.of(6, 0)) &&getSolution().getTrajets().get(i).getHeureDepart().isBefore(LocalTime.of(21, 0))) {
                if (FtempsTrajet>9.00)
                    checker = false;
            }
            else if(getSolution().getTrajets().get(i).getHeureDepart().isAfter(LocalTime.of(20, 59))
            || getSolution().getTrajets().get(i).getHeureDepart().isBefore(LocalTime.of(6, 0)))
            {
                if (FtempsTrajet>8.00)
                checker = false;
            }
        }
        return checker;
    }

    /**
     * Tri un HashMap<Integer,ArrayList<Integer>> en fonction de l'ArrayList<Integer>
     * @param map
     * @return un HashMap<Integer,ArrayList<Integer> trié
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
     *
     * @return true si chaque chauffeur est dans la bonne ville pour faire le trajet, false sinon
     */
    public boolean chauffeurBonneVille() {

        boolean checker = true;

        for (Map.Entry<Integer, ArrayList<Integer>> mapentry : getSolution().getChauffeursTrajets().entrySet()) {

            HashMap<TrajetFixe,Double> tableauTrajet = new HashMap<TrajetFixe,Double>();

            for (int i = 0; i < mapentry.getValue().size(); i++) {
                TrajetFixe trajet = getSolution().getTrajetById(mapentry.getValue().get(i));
                int jour = trajet.getJourDepart().getIdentifiant();
                double horraireDepart = (trajet.getHeureDepart().getHour()*60 + ((double) trajet.getHeureDepart().getMinute() ))+(24*60*jour);

                tableauTrajet.put(trajet,horraireDepart);
            }

            Map<TrajetFixe, Double> tableauTrajetTrier = sort(tableauTrajet);

            int compteur = 0;
            Ville ville = new Ville();

            for (Map.Entry<TrajetFixe, Double> map : tableauTrajetTrier.entrySet()) {

                if(compteur == 0){
                    ville = map.getKey().getVilleArrivee();
                }
                else{
                    if(ville.getIdentifiant() != map.getKey().getVilleDepart().getIdentifiant()){
                        checker = false;
                    }
                    ville = map.getKey().getVilleArrivee();
                }
                compteur += 1;
            }

            }

        return checker;
    }

    /**
     *
     * @return true si chaque camion est dans la bonne ville pour faire le trajet, false sinon
     */
    public boolean camionBonneVille() {
        boolean checker = true;
        for (Map.Entry<Integer, ArrayList<Integer>> mapentry : getSolution().getCamionsTrajets().entrySet()) {

            HashMap<TrajetFixe, Double> tableauTrajet = new HashMap<TrajetFixe, Double>();

            for (int i = 0; i < mapentry.getValue().size(); i++) {
                TrajetFixe trajet = getSolution().getTrajetById(mapentry.getValue().get(i));
                int jour = trajet.getJourDepart().getIdentifiant();
                double horraireDepart = (trajet.getHeureDepart().getHour() * 60 + ((double) trajet.getHeureDepart().getMinute())) + (24 * 60 * jour);

                tableauTrajet.put(trajet, horraireDepart);
            }

            Map<TrajetFixe, Double> tableauTrajetTrier = sort(tableauTrajet);

            int compteur = 0;
            Ville ville = new Ville();

            for (Map.Entry<TrajetFixe, Double> map : tableauTrajetTrier.entrySet()) {
                if (compteur == 0) {
                    ville = map.getKey().getVilleArrivee();
                    compteur += 1;
                } else {
                    if (ville.getIdentifiant() != map.getKey().getVilleDepart().getIdentifiant()) {
                        checker = false;
                    }
                    ville = map.getKey().getVilleArrivee();
                    compteur += 1;
                }

            }
        }
        return checker;
    }

/*********************************************Respects des coûts***********************************************/


    /**
     *
     * @return true si les coûts d'hotellerie sont corrects, false sinon
     */
    public boolean coutHottelerie() {
        boolean checker = true;
        for (Map.Entry<Integer, ArrayList<Integer>> mapentry : getSolution().getChauffeursTrajets().entrySet()) {
            int nombreRepos = 0;
            int compteur = 0;
            while(compteur < 7){
                TrajetFixe dernierTrajet = null;
                for(int i = 0; i<mapentry.getValue().size(); i++) {
                    TrajetFixe trajet = getSolution().getTrajetById(mapentry.getValue().get(i));
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
                    if(dernierTrajet.getVilleArrivee() != getSolution().getChauffeurById(mapentry.getKey()).getVilleRattachement() )
                        nombreRepos += 1;
                }
                compteur += 1;
            }
            if(getSolution().getChauffeurById(mapentry.getKey()).getCoutHotellerie() != nombreRepos*(getSolution().getPlannification().getCoutHotellerie())) {
                checker = false;
                System.out.println("Le bon cout d'hotellerie pour le chauffeur" + mapentry.getKey() + " = " + nombreRepos*(getSolution().getPlannification().getCoutHotellerie()));
            }
        }
        return checker;
    }


    /**
     *
     * @return true si les coûts du contrat de travail sont corrects, false sinon
     */
    public boolean coutContratTravail() {
        boolean checker = true;
        for (Map.Entry<Integer, ArrayList<Integer>> mapentry : getSolution().getChauffeursTrajets().entrySet()) {
            double nombreHeureJour = 0.0;
            double nombreHeureNuit = 0.0;
            for(int i = 0; i<mapentry.getValue().size(); i++) {
                TrajetFixe trajet = getSolution().getTrajetById(mapentry.getValue().get(i));
                double FtempsTrajet = trajet.getTempsDeConduite();
                LocalTime heureDepart = trajet.getHeureDepart();
                if (heureDepart.isBefore(LocalTime.of(21,0)) && heureDepart.isAfter(LocalTime.of(6,0))){
                    nombreHeureJour+=FtempsTrajet;
                }
                else {
                    nombreHeureNuit+=FtempsTrajet;
                }
            }
            ContratDeTravail CT = getSolution().getChauffeurById(mapentry.getKey()).getContratDeTravail();
            if(CT.getNbrHeureJour()!= (nombreHeureJour) || CT.getNbrHeureNuit()!= (nombreHeureNuit)){
                checker = false;
                System.out.println("Le bon nombre d'heures de CT pour le chauffeur" +mapentry.getKey()+" = "+nombreHeureJour +" de jour et "+nombreHeureNuit+" de nuit");
            }

        }
        return checker;
    }


/*********************************************Verififaction***********************************************/
    public boolean verificationComplete(){
        boolean checker1 = true;

        System.out.println("**********************************Contraintes de reglementations***************************************************");
        checker1 = dureeConduiteContinue();
        if(dureeConduiteContinue() == false)
            System.out.println("dureeConduiteContinue == false");
        else
            System.out.println("dureeConduiteContinue == true");

        checker1 = dureeConduiteJournaliere();
        if(dureeConduiteJournaliere() == false)
            System.out.println("dureeConduiteJournaliere == false");
        else
            System.out.println("dureeConduiteJournaliere == true");

        checker1 = dureeConduiteHebdomadaire();
        if(dureeConduiteHebdomadaire() == false)
            System.out.println("dureeConduiteHebdomadaire == false");
        else
            System.out.println("dureeConduiteHebdomadaire == true");

        checker1 = tempsReposJournalier();
        if(tempsReposJournalier() == false)
            System.out.println("tempsReposJournalier == false");
        else
            System.out.println("tempsReposJournalier == true");

        checker1 = tempsReposHebdomadaire();
        if(tempsReposHebdomadaire() == false)
            System.out.println("tempsReposHebdomadaire == false");
        else
            System.out.println("tempsReposHebdomadaire == true");

        System.out.println("**********************************Contraintes operationnelles***************************************************");

        boolean checker2 = true;
        checker2 = unTrajetALaFoisChauffeurs();
        if(unTrajetALaFoisChauffeurs() == false)
            System.out.println("unTrajetALaFoisChauffeurs == false");
        else
            System.out.println("unTrajetALaFoisChauffeurs == true");

        checker2 = unTrajetALaFoisCamions();
        if(unTrajetALaFoisCamions() == false)
            System.out.println("unTrajetALaFoisCamions == false");
        else
            System.out.println("unTrajetALaFoisCamions == true");

        checker2 = trajetCamionChauffeur();
        if(trajetCamionChauffeur() == false)
            System.out.println("trajetCamionChauffeur == false");
        else
            System.out.println("trajetCamionChauffeur == true");

        checker2 = reposHebdomadaireVilleRattachement();
        if(reposHebdomadaireVilleRattachement() == false)
            System.out.println("reposHebdomadaireVilleRattachement == false");
        else
            System.out.println("reposHebdomadaireVilleRattachement == true");

        checker2 = tempsContratTravail();
        if(tempsContratTravail() == false)
            System.out.println("tempsContratTravail == false");
        else
            System.out.println("tempsContratTravail == true");

        checker2 = trajetsPause();
        if(trajetsPause() == false)
            System.out.println("trajetsPause == false");
        else
            System.out.println("trajetsPause == true");

        checker2 = tempsTrajets();
        if(tempsTrajets() == false)
            System.out.println("tempsTrajets == false");
        else
            System.out.println("tempsTrajets == true");

        checker2 = chauffeurBonneVille();
        if(chauffeurBonneVille() == false)
            System.out.println("chauffeurBonneVille == false");
        else
            System.out.println("chauffeurBonneVille == true");

        checker2 = camionBonneVille();
        if(camionBonneVille() == false)
            System.out.println("camionBonneVille == false");
        else
            System.out.println("camionBonneVille == true");

        System.out.println("**********************************Respect des couts***************************************************");
        boolean checker3 = true;

        checker3 = coutHottelerie();
        if(coutHottelerie() == false)
            System.out.println("coutHottelerie == false");
        else
            System.out.println("coutHottelerie == true");

        checker3 = coutContratTravail();
        if(coutContratTravail() == false)
            System.out.println("coutContratTravail == false");
        else
            System.out.println("coutContratTravail == true");

        return checker1 && checker2 && checker3;
    }

    public boolean verificationChauffeur(){
        boolean checker1 = true;

        checker1 = dureeConduiteContinue();
        if(dureeConduiteContinue() == false)
            System.out.println("dureeConduiteContinue == false");
//        else
//            //System.out.println("dureeConduiteContinue == true");

        checker1 = dureeConduiteJournaliere();
        if(dureeConduiteJournaliere() == false)
            System.out.println("dureeConduiteJournaliere == false");
//        else
//            //System.out.println("dureeConduiteJournaliere == true");

        checker1 = dureeConduiteHebdomadaire();
        if(dureeConduiteHebdomadaire() == false)
            System.out.println("dureeConduiteHebdomadaire == false");
//        else
//            //System.out.println("dureeConduiteHebdomadaire == true");

        checker1 = tempsReposJournalier();
        if(tempsReposJournalier() == false)
            System.out.println("tempsReposJournalier == false");
//        else
//            System.out.println("tempsReposJournalier == true");

        checker1 = tempsReposHebdomadaire();
        if(tempsReposHebdomadaire() == false)
            System.out.println("tempsReposHebdomadaire == false");
////        else
//            System.out.println("tempsReposHebdomadaire == true");

        checker1 = tempsConduiteMinimum();
        if(tempsConduiteMinimum() == false)
            System.out.println("tempsConduiteMinimum == false");


        boolean checker2 = true;
        checker2 = unTrajetALaFoisChauffeurs();
        if(unTrajetALaFoisChauffeurs() == false)
            System.out.println("unTrajetALaFoisChauffeurs == false");
//        else
//            System.out.println("unTrajetALaFoisChauffeurs == true");

        checker2 = unTrajetALaFoisCamions();
        if(unTrajetALaFoisCamions() == false)
            System.out.println("unTrajetALaFoisCamions == false");
//        else
//            System.out.println("unTrajetALaFoisCamions == true");

        checker2 = trajetCamionChauffeur();
        if(trajetCamionChauffeur() == false)
            System.out.println("trajetCamionChauffeur == false");
//        else
//            System.out.println("trajetCamionChauffeur == true");

        checker2 = reposHebdomadaireVilleRattachement();
        if(reposHebdomadaireVilleRattachement() == false)
            System.out.println("reposHebdomadaireVilleRattachement == false");
//        else
//            System.out.println("reposHebdomadaireVilleRattachement == true");

        checker2 = trajetsPause();
        if(trajetsPause() == false)
            System.out.println("trajetsPause == false");
//        else
//            System.out.println("trajetsPause == true");

        checker2 = tempsTrajets();
        if(tempsTrajets() == false)
            System.out.println("tempsTrajets == false");
//        else
//            System.out.println("tempsTrajets == true");

        checker2 = chauffeurBonneVille();
        if(chauffeurBonneVille() == false)
            System.out.println("chauffeurBonneVille == false");
//        else
//            System.out.println("chauffeurBonneVille == true");

        checker2 = camionBonneVille();
        if(camionBonneVille() == false)
            System.out.println("camionBonneVille == false");
//        else
//            System.out.println("camionBonneVille == true");


        boolean checker3 = true;

        if(checker1 && checker2 && checker3)
            System.out.println("OK");

        return checker1 && checker2 && checker3;
    }

    public boolean verificationCamion(){
        boolean checker = true;

        checker = unTrajetALaFoisCamions();
        if(unTrajetALaFoisCamions() == false)
            System.out.println("unTrajetALaFoisCamions == false");
//        else
//            System.out.println("unTrajetALaFoisCamions == true");

        checker = camionBonneVille();
        if(camionBonneVille() == false)
            System.out.println("camionBonneVille == false");
//        else
//            System.out.println("camionBonneVille == true");

        checker = trajetCamion();


        return checker;
    }

}
