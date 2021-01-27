package modele;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Map;

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

            if(tempsDePause == null)
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
        boolean checker = true;
        for (Map.Entry<Integer, ArrayList<Integer>> mapentry : getSolution().getChauffeursTrajets().entrySet()) {
            double conduiteHebdomadaire = 0.0;
            for(int i = 0; i<mapentry.getValue().size(); i++) {
                TrajetFixe trajet = getSolution().getTrajetById(mapentry.getValue().get(i));
                double tempsDeConduite = trajet.getTempsDeConduite();
                conduiteHebdomadaire += tempsDeConduite;
            }
            if(24.0*7 - conduiteHebdomadaire < getSolution().getPlannification().getNbrConduiteHebdomadaireMax())
                checker = false;
        }
        return checker;
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
                        TrajetFixe trajet2 = getSolution().getTrajetById(mapentry.getValue().get(i));
                        double tempsDeConduite2 = trajet2.getTempsDeConduite();
                        int jour2 = trajet2.getJourDepart().getIdentifiant();
                        LocalTime heureDepart2 = trajet2.getHeureDepart();

                        if(jour1 == jour2) {
                            LocalTime tempsDeConduite = LocalTime.of(new Double(tempsDeConduite2).intValue(), (int) ((tempsDeConduite2-(new Double(tempsDeConduite2).intValue()))*6));
                            LocalTime cptTime = heureDepart2.plusHours(tempsDeConduite.getHour());
                            cptTime = cptTime.plusMinutes(tempsDeConduite.getMinute());
                            if(heureDepart1.compareTo(heureDepart2) >= 0  && heureDepart1.isBefore(cptTime)) {
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
        for (Map.Entry<Integer, ArrayList<Integer>> mapentry : getSolution().getChauffeursTrajets().entrySet()) {
            for(int i = 0; i<mapentry.getValue().size(); i++) {
                TrajetFixe trajet1 = getSolution().getTrajetById(mapentry.getValue().get(i));
                int jour1 = trajet1.getJourDepart().getIdentifiant();
                LocalTime heureDepart1 = trajet1.getHeureDepart();
                for(int j = 0; j<mapentry.getValue().size(); j++) {

                    if(i != j) {
                        TrajetFixe trajet2 = getSolution().getTrajetById(mapentry.getValue().get(i));
                        double tempsDeConduite2 = trajet2.getTempsDeConduite();
                        int jour2 = trajet2.getJourDepart().getIdentifiant();
                        LocalTime heureDepart2 = trajet2.getHeureDepart();

                        if(jour1 == jour2) {
                            LocalTime tempsDeConduite = LocalTime.of(new Double(tempsDeConduite2).intValue(), (int) ((tempsDeConduite2-(new Double(tempsDeConduite2).intValue()))*6));
                            LocalTime cptTime = heureDepart2.plusHours(tempsDeConduite.getHour());
                            cptTime = cptTime.plusMinutes(tempsDeConduite.getMinute());
                            if(heureDepart1.compareTo(heureDepart2) >=0 && heureDepart1.isBefore(cptTime)) {
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
        for(Trajet trajet : getSolution().getPlannification().getTrajetsFixe()) {
            if(getSolution().getChauffeursTrajets().containsValue(trajet) == false) {
                checker = false;
            }
            if(getSolution().getCamionsTrajets().containsValue(trajet) == false) {
                checker = false;
            }
        }
        return checker;
    }

    /**
     * Les chauffeurs passent leur repos hebdomadaire dans
     * leurs villes de rattachements
     */
    public boolean reposHebdomadaireVilleRattachement() {
        boolean checker = true;
        for (Map.Entry<Integer, ArrayList<Integer>> mapentry : getSolution().getChauffeursTrajets().entrySet()) {
            Chauffeur chauffeur = getSolution().getChauffeurById(mapentry.getKey());
            boolean cptBoolean = false;
            for(int i = 0; i<mapentry.getValue().size(); i++) {
                TrajetFixe trajet1 = getSolution().getTrajetById(mapentry.getValue().get(i));
                if(trajet1.getVilleArrivee() == chauffeur.getVilleRattachement())
                    cptBoolean = true;
            }
            if(cptBoolean == false){
                checker = false;
            }
        }
        return checker;
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
            LocalTime tempsDeConduite = LocalTime.of(new Double(FtempsTrajet).intValue(), (int) ((FtempsTrajet-(new Double(FtempsTrajet).intValue()))*6));
            LocalTime cptTime = getSolution().getTrajets().get(i).getHeureDepart().plusHours(tempsDeConduite.getHour());
            cptTime = cptTime.plusMinutes(tempsDeConduite.getMinute());
            if (cptTime.isBefore(LocalTime.of(21, 0))) {
                if (tempsDeConduite.isAfter(LocalTime.of(9, 0)))
                    checker = false;
            } else if (tempsDeConduite.isAfter(LocalTime.of(8, 0)))
                checker = false;
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
            int compteur = 1;
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
                if(dernierTrajet.getVilleArrivee() != getSolution().getChauffeurById(mapentry.getKey()).getVilleRattachement() )
                    nombreRepos += 1;
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
                double FtempsTrajet = getSolution().getTrajets().get(i).getTempsDeConduite();
                LocalTime tempsDeConduite = LocalTime.of(new Double(FtempsTrajet).intValue(), (int) ((FtempsTrajet-(new Double(FtempsTrajet).intValue()))*6));
                LocalTime cptTime = trajet.getHeureDepart().plusHours(tempsDeConduite.getHour());
                cptTime = cptTime.plusMinutes(tempsDeConduite.getMinute());
                if (cptTime.isBefore(LocalTime.of(18,0))){
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

}
