package modele;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Checker {

    private Solution solution;

    public Checker(Solution solution) {
        this.solution = solution;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

/*********************************************Contraintes de Reglementations***********************************************/

    /**
     * Durée de conduite continue maximale
     * respectée pour chaque chauffeurs
     */
    public boolean dureeConduiteContinue(){
        boolean checker = true;

        for(int i = 0; i<getSolution().getTrajets().size(); i++) {
            TrajetFixe trajet = getSolution().getTrajets().get(i);
            double tempsDeConduite = trajet.getTempsDeConduite();
            ArrayList<Double> tempsDePause = trajet.getTempsDePause();

            if(tempsDePause == null || tempsDePause.isEmpty())
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

        return checker;
    }

    /**
     * Durée de conduite journaliere maximale
     * respectée pour chaque chauffeurs
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
     * Durée de conduite hebdomadaire maximale
     * respectée pour chaque chauffeurs
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
     * Temps de repos journalier
     * respectée pour chaque chauffeurs
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
     * Temps de repos hebdomadaire
     * respectée pour chaque chauffeurs
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
            if(cptBoolean == false)
                return false;

        }
        return true;
    }

/*********************************************Contraintes operationnelles***********************************************/

    /**
     * Un trajet à la fois par chauffeurs
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


                            LocalTime tempsDeConduite = LocalTime.of(new Double(tempsDeConduite2).intValue(), (int) ((tempsDeConduite2-(new Double(tempsDeConduite2).intValue()))*600));
                            LocalTime cptTime = heureDepart2.plusHours(tempsDeConduite.getHour());
                            cptTime = cptTime.plusMinutes(tempsDeConduite.getMinute());

                            int JourSuivant = tempsDeConduite.getHour() + heureDepart2.getHour();

                            if(JourSuivant < 24) {
                                if ( (jour1 == jour2) && heureDepart1.compareTo(heureDepart2) >= 0 && heureDepart1.isBefore(cptTime)) {
                                    checker = false;
                                }
                            }
                            else{
                                if ((jour1 == jour2) && heureDepart1.compareTo(heureDepart2) >= 0 && heureDepart1.isBefore(LocalTime.of(23,59))) {
                                    checker = false;
                                }
                                else if((jour1 == jour2+1) && heureDepart1.isBefore(cptTime)){
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
     * Un trajet à la fois par camions
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


                        LocalTime tempsDeConduite = LocalTime.of(new Double(tempsDeConduite2).intValue(), (int) ((tempsDeConduite2-(new Double(tempsDeConduite2).intValue()))*600));
                        LocalTime cptTime = heureDepart2.plusHours(tempsDeConduite.getHour());
                        cptTime = cptTime.plusMinutes(tempsDeConduite.getMinute());

                        int JourSuivant = tempsDeConduite.getHour() + heureDepart2.getHour();

                        if(JourSuivant < 24) {
                            if ( (jour1 == jour2) && heureDepart1.compareTo(heureDepart2) >= 0 && heureDepart1.isBefore(cptTime)) {
                                checker = false;
                            }
                        }
                        else{
                            if ((jour1 == jour2) && heureDepart1.compareTo(heureDepart2) >= 0 && heureDepart1.isBefore(LocalTime.of(23,59))) {
                                checker = false;
                            }
                            else if((jour1 == jour2+1) && heureDepart1.isBefore(cptTime)){
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
     * Tous les trajets ont un camion et un chauffeur
     */
    public boolean trajetCamionChauffeur() {
        boolean checker = true;

        for(TrajetFixe trajet : getSolution().getTrajets()) {
            int compteur = 0;

            for (Map.Entry<Integer, ArrayList<Integer>> mapentry1 : getSolution().getCamionsTrajets().entrySet()) {
                if (mapentry1.getValue().contains(trajet.getIdentifiant()) == true) {
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
     * Les chauffeurs passent leur repos hebdomadaire dans
     * leurs villes de rattachements
     */
    public boolean reposHebdomadaireVilleRattachement() {
        for (Map.Entry<Integer, ArrayList<Integer>> mapentry : getSolution().getChauffeursTrajets().entrySet()) {

            ArrayList<Double> tableauDebutTrajet = new ArrayList<Double>();
            ArrayList<Double> tableauFinTrajet = new ArrayList<Double>();
            for (int i = 0; i < mapentry.getValue().size(); i++) {
                TrajetFixe trajet = getSolution().getTrajetById(mapentry.getValue().get(i));
                int jour = trajet.getJourDepart().getIdentifiant();
                double horraireDepart = trajet.getHeureDepart().getHour() + ((double) trajet.getHeureDepart().getMinute() / 600);
                double tempsDeConduite = trajet.getTempsDeConduite();
                double finTrajet = horraireDepart + tempsDeConduite;

                tableauDebutTrajet.add(horraireDepart + (24 * jour));
                tableauFinTrajet.add(finTrajet + (24 * jour));
            }

            tableauDebutTrajet.add((double) 24 * 7);

            Collections.sort(tableauDebutTrajet);
            Collections.sort(tableauFinTrajet);

            for (int i = 0; i < tableauFinTrajet.size(); i++) {

                if (tableauDebutTrajet.get(i + 1) - tableauFinTrajet.get(i) >= getSolution().getPlannification().getDureeReposHebdomadaire()) {
                    for (int j = 0; j < mapentry.getValue().size(); j++) {
                        TrajetFixe trajetRepos = getSolution().getTrajetById(mapentry.getValue().get(i));
                        int jour = trajetRepos.getJourDepart().getIdentifiant();
                        double horraireDepart = trajetRepos.getHeureDepart().getHour() + ((double) trajetRepos.getHeureDepart().getMinute() / 600);
                        double tempsDeConduite = trajetRepos.getTempsDeConduite();
                        double finTrajet = horraireDepart + tempsDeConduite;
                        if (finTrajet + (24 * jour) == tableauFinTrajet.get(i)) {
                            if (trajetRepos.getVilleArrivee() != getSolution().getChauffeurById(mapentry.getKey()).getVilleRattachement()) {
                                return false;
                            }
                        }
                    }
                }
            }

        }
        return true;
    }

    /**
     * Les contrats de travail repecte les durées minimales et maximales
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
     * Tous les trajets ont des pauses
     */
    public boolean trajetsPause() {
        boolean checker = true;
        for(int i = 0; i<getSolution().getTrajets().size();i++)
        {
            if (getSolution().getTrajets().get(i).getTempsDePause().isEmpty()){
                checker = false;
            }
        }
        return checker;
    }

    /**
     * Aucun trajet ne dépasse 9h00 et 8h00 de nuit
     */
    public boolean tempsTrajets() {
        boolean checker = true;
        for(int i = 0; i<getSolution().getTrajets().size();i++) {
            double FtempsTrajet = getSolution().getTrajets().get(i).getTempsDeConduite();
            LocalTime tempsDeConduite = LocalTime.of(new Double(FtempsTrajet).intValue(), (int) ((FtempsTrajet-(new Double(FtempsTrajet).intValue()))*600));
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
     * Un chauffeur doit être dans la bonne ville pour faire le trajet
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
                    if(map.getKey().getVilleDepart() != getSolution().getChauffeurById(mapentry.getKey()).getVilleRattachement()){
                        checker = false;
                    }
                    ville = map.getKey().getVilleArrivee();
                }
                else{
                    if(ville != map.getKey().getVilleDepart()){
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
     * Un camion doit être dans la bonne ville pour faire le trajet
     */
    public boolean camionBonneVille() {
        boolean checker = true;
        for (Map.Entry<Integer, ArrayList<Integer>> mapentry : getSolution().getCamionsTrajets().entrySet()) {

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
                if(ville != map.getKey().getVilleDepart() && compteur !=0){
                    checker = false;
                }
                ville = map.getKey().getVilleArrivee();
                compteur += 1;
            }

        }

        return checker;
    }

/*********************************************Respects des coûts***********************************************/

    /**
     * Verifie les coûts d'hottelerie
     * de chaque chauffeurs
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
            if(getSolution().getChauffeurById(mapentry.getKey()).getCoutHotellerie() != nombreRepos*(getSolution().getPlannification().getCoutHotellerie()))
                checker = false;
        }
        return checker;
    }

    /**
     * Verifie les coûts du contrat de travail
     * de chaque chauffeurs
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
            if(CT.getNbrHeureJour()!= (nombreHeureJour) || CT.getNbrHeureNuit()!= (nombreHeureNuit))
                checker = false;
        }
        return checker;
    }


/*********************************************Verififaction***********************************************/
    public boolean verification(){
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


}
