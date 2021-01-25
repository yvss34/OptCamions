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
        for (Map.Entry<Integer, ArrayList<TrajetFixe>> mapentry : getSolution().getChauffeursTrajets().entrySet()) {
            for(int i = 0; i<mapentry.getValue().size(); i++) {
                TrajetFixe trajet = mapentry.getValue().get(i);
                LocalTime tempsDeConduite = trajet.getTempsDeConduite();
                ArrayList<LocalTime> tempsDePause = trajet.getTempsDePause();

                if(tempsDePause.get(0).isAfter(getSolution().getPlannification().getNbrConduiteContinueMax()) == true)
                    checker = false;

                LocalTime tps1 = tempsDePause.get(tempsDePause.size()-1).minus(tempsDeConduite.getHour(), ChronoUnit.HOURS);
                tps1 = tps1.minus(tempsDeConduite.getMinute(),ChronoUnit.MINUTES);

                if(tps1.isAfter(getSolution().getPlannification().getNbrConduiteContinueMax()) == true)
                    checker = false;

                for(int j = 0; j<tempsDePause.size()-1; j++) {
                    LocalTime tps2 = tempsDePause.get(j+1).minus(tempsDePause.get(j).getHour(), ChronoUnit.HOURS);
                    tps2 = tps2.minus(tempsDePause.get(j).getMinute(),ChronoUnit.MINUTES);

                    if(tps2.isAfter(getSolution().getPlannification().getNbrConduiteContinueMax()) == true)
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
        for (Map.Entry<Integer, ArrayList<TrajetFixe>> mapentry : getSolution().getChauffeursTrajets().entrySet()) {
            LocalTime conduiteJournaliere[] = {LocalTime.of(00,00),LocalTime.of(00,00)
                    ,LocalTime.of(00,00),LocalTime.of(00,00),LocalTime.of(00,00),
                    LocalTime.of(00,00),LocalTime.of(00,00),LocalTime.of(00,00)};
            for(int i = 0; i<mapentry.getValue().size(); i++) {
                TrajetFixe trajet = mapentry.getValue().get(i);
                LocalTime tempsDeConduite = trajet.getTempsDeConduite();
                int jour = trajet.getJourDepart().getIdentifiant();

                conduiteJournaliere[jour].plusHours(tempsDeConduite.getHour()) ;
                conduiteJournaliere[jour].plusMinutes(tempsDeConduite.getMinute()) ;
            }
            for(LocalTime nbrHeureConduite : conduiteJournaliere) {
                if(nbrHeureConduite.isAfter(getSolution().getPlannification().getNbrConduiteJournaliereMax()) == true)
                    checker = false;
            }
        }
        return checker;
    }

    /**
     * Temps de repos journalier
     * respectée pour chaque chauffeurs
     */
    public boolean tempsReposJournalier() {
        boolean checker = true;
        for (Map.Entry<Integer, ArrayList<TrajetFixe>> mapentry : getSolution().getChauffeursTrajets().entrySet()) {
            LocalTime conduiteJournaliere[] = {LocalTime.of(00, 00), LocalTime.of(00, 00)
                    , LocalTime.of(00, 00), LocalTime.of(00, 00), LocalTime.of(00, 00),
                    LocalTime.of(00, 00), LocalTime.of(00, 00), LocalTime.of(00, 00)};
            for (int i = 0; i < mapentry.getValue().size(); i++) {
                TrajetFixe trajet = mapentry.getValue().get(i);
                LocalTime tempsDeConduite = trajet.getTempsDeConduite();
                int jour = trajet.getJourDepart().getIdentifiant();

                conduiteJournaliere[jour].plusHours(tempsDeConduite.getHour());
                conduiteJournaliere[jour].plusMinutes(tempsDeConduite.getMinute());
            }


            for (LocalTime nbrHeureConduite : conduiteJournaliere) {
                LocalTime localT = LocalTime.of(24, 00);
                localT = localT.minusHours(nbrHeureConduite.getHour());
                localT = localT.minusMinutes(nbrHeureConduite.getMinute());

                if (localT.isAfter(getSolution().getPlannification().getTempsDeReposJournalier()) == true)
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
        for (Map.Entry<Integer, ArrayList<TrajetFixe>> mapentry : getSolution().getChauffeursTrajets().entrySet()) {
            LocalTime conduiteHebdomadaire = LocalTime.of(00,00);
            for(int i = 0; i<mapentry.getValue().size(); i++) {
                TrajetFixe trajet = mapentry.getValue().get(i);
                LocalTime tempsDeConduite = trajet.getTempsDeConduite();
                conduiteHebdomadaire.plusHours(tempsDeConduite.getHour());
                conduiteHebdomadaire.plusMinutes(tempsDeConduite.getMinute());
            }
            LocalTime localT = LocalTime.of(168, 00);
            localT = localT.minusHours(conduiteHebdomadaire.getHour());
            localT = localT.minusMinutes(conduiteHebdomadaire.getMinute());
            if(localT.isAfter(getSolution().getPlannification().getNbrConduiteHebdomadaireMax()) == true) {
                checker = false;
            }
        }
        return checker;
    }

/*********************************************Contraintes operationnelles***********************************************/

    /**
     * Un trajet à la fois par chauffeurs
     */
    public boolean unTrajetALaFoisChauffeurs() {
        boolean checker = true;
        for (Map.Entry<Integer, ArrayList<TrajetFixe>> mapentry : getSolution().getChauffeursTrajets().entrySet()) {
            for(int i = 0; i<mapentry.getValue().size(); i++) {
                TrajetFixe trajet1 = mapentry.getValue().get(i);
                int jour1 = trajet1.getJourDepart().getIdentifiant();
                LocalTime heureDepart1 = trajet1.getHeureDepart();
                for(int j = 0; j<mapentry.getValue().size(); j++) {

                    if(i != j) {
                        TrajetFixe trajet2 = mapentry.getValue().get(i);
                        LocalTime tempsDeConduite2 = trajet2.getTempsDeConduite();
                        int jour2 = trajet2.getJourDepart().getIdentifiant();
                        LocalTime heureDepart2 = trajet2.getHeureDepart();

                        if(jour1 == jour2) {
                            LocalTime cptTime = heureDepart2.plusHours(tempsDeConduite2.getHour());
                            cptTime = cptTime.plusMinutes(tempsDeConduite2.getMinute());
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
        for (Map.Entry<Integer, ArrayList<TrajetFixe>> mapentry : getSolution().getChauffeursTrajets().entrySet()) {
            for(int i = 0; i<mapentry.getValue().size(); i++) {
                TrajetFixe trajet1 = mapentry.getValue().get(i);
                int jour1 = trajet1.getJourDepart().getIdentifiant();
                LocalTime heureDepart1 = trajet1.getHeureDepart();
                for(int j = 0; j<mapentry.getValue().size(); j++) {

                    if(i != j) {
                        TrajetFixe trajet2 = mapentry.getValue().get(i);
                        LocalTime tempsDeConduite2 = trajet2.getTempsDeConduite();
                        int jour2 = trajet2.getJourDepart().getIdentifiant();
                        LocalTime heureDepart2 = trajet2.getHeureDepart();

                        if(jour1 == jour2) {
                            LocalTime cptTime = heureDepart2.plusHours(tempsDeConduite2.getHour());
                            cptTime = cptTime.plusMinutes(tempsDeConduite2.getMinute());
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
        for (Map.Entry<Integer, ArrayList<TrajetFixe>> mapentry : getSolution().getChauffeursTrajets().entrySet()) {
            Chauffeur chauffeur = getSolution().getChauffeurById(mapentry.getKey());
            boolean cptBoolean = false;
            for(int i = 0; i<mapentry.getValue().size(); i++) {
                TrajetFixe trajet1 = mapentry.getValue().get(i);
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
            LocalTime cptTime = contratDeTravail.getNbrHeureJour().plusHours(contratDeTravail.getNbrHeureNuit().getHour());
            cptTime = cptTime.plusMinutes(contratDeTravail.getNbrHeureNuit().getMinute());
            if(cptTime.isBefore(getSolution().getPlannification().getNombreHeuresMin()))
                checker = false;
            if(cptTime.isAfter(getSolution().getPlannification().getNombreHeuresMin()))
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
            LocalTime FtempsTrajet = getSolution().getTrajets().get(i).getTempsDeConduite();
            LocalTime cptTime = getSolution().getTrajets().get(i).getHeureDepart().plusHours(FtempsTrajet.getHour());
            cptTime = cptTime.plusMinutes(FtempsTrajet.getMinute());
            if (cptTime.isBefore(LocalTime.of(21, 0))) {
                if (FtempsTrajet.isAfter(LocalTime.of(9, 0)))
                    checker = false;
            } else if (FtempsTrajet.isAfter(LocalTime.of(8, 0)))
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
        for (Map.Entry<Integer, ArrayList<TrajetFixe>> mapentry : getSolution().getChauffeursTrajets().entrySet()) {
            int nombreRepos = 0;
            int compteur = 1;
            while(compteur < 7){
                TrajetFixe dernierTrajet = null;
                for(int i = 0; i<mapentry.getValue().size(); i++) {
                    TrajetFixe trajet = mapentry.getValue().get(i);
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
        for (Map.Entry<Integer, ArrayList<TrajetFixe>> mapentry : getSolution().getChauffeursTrajets().entrySet()) {
            LocalTime nombreHeureJour = LocalTime.of(0,0);
            LocalTime nombreHeureNuit = LocalTime.of(0,0);
            for(int i = 0; i<mapentry.getValue().size(); i++) {
                TrajetFixe trajet = mapentry.getValue().get(i);
                LocalTime cptTime = trajet.getHeureDepart().plusHours(trajet.getTempsDeConduite().getHour());
                cptTime = cptTime.plusMinutes(trajet.getTempsDeConduite().getMinute());
                if (cptTime.isBefore(LocalTime.of(18,0))){
                    nombreHeureJour.plusHours(trajet.getTempsDeConduite().getHour());
                    nombreHeureJour.plusMinutes(trajet.getTempsDeConduite().getMinute());
                }
                else {
                    nombreHeureNuit.plusHours(trajet.getTempsDeConduite().getHour());
                    nombreHeureNuit.plusMinutes(trajet.getTempsDeConduite().getMinute());
                }
            }
            ContratDeTravail CT = getSolution().getChauffeurById(mapentry.getKey()).getContratDeTravail();
            if(CT.getNbrHeureJour().compareTo(nombreHeureJour) != 0 || CT.getNbrHeureNuit().compareTo(nombreHeureNuit) != 0)
                checker = false;
        }
        return checker;
    }

}
