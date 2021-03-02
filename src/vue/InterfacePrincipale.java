package vue;

import modele.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

public class InterfacePrincipale extends JFrame{
    private JPanel mainPanel;
    private JPanel contratTravailPanel;
    private JLabel nombreHeuresMinLabel;
    private JLabel nombreHeuresMaxLabel;
    private JLabel coutHoraireJourLabel;
    private JLabel coutHoraireNuitLabel;
    private JTextField nombreHeureMinText;
    private JTextField nombreHeureMaxText;
    private JTextField coutHoraireJourText;
    private JTextField coutHoraireNuitText;
    private JPanel coutHotteleriePanel;
    private JLabel coutHottelerieLabel;
    private JTextField coutHottelerieText;
    private JPanel contraintesPanel;
    private JLabel nombreHeureConduiteMaximumLabel;
    private JLabel tempsReposJournalierLabel;
    private JLabel nombreHeuresConduiteJournaliereLabel;
    private JLabel nombreHeuresHebdomadaireLabel;
    private JLabel dureeReposHebdomadaireLabel;
    private JTextField nombreHeureConduiteMaximumText;
    private JTextField tempsReposJournalierText;
    private JTextField nombreHeuresConduiteJournaliereText;
    private JTextField nombreHeuresHebdomadaireText;
    private JTextField dureeReposHebdomadaireText;
    private JButton nouveauTrajetNonFixeButton;
    private JButton nouveauTrajetFixeButton;

    private JList trajetNonFixeList;
    private JList trajetFixeList;

    private JButton resolutionButton;
    private JLabel messageErrorLabel;
    private JScrollPane JScreollPane1;
    private JScrollPane JScreollPane2;

    private boolean checkerResolution;
    ArrayList<TrajetFixe> trajetFixe = new  ArrayList<TrajetFixe>();
    ArrayList<TrajetNonFixe> trajetNonFixe= new  ArrayList<TrajetNonFixe>();

    private DefaultListModel modelTrajetFixe;
    private DefaultListModel modelTrajetNonFixe;

    Plannification plannification;

    InterfacePrincipale interfacePrincipale = this;

    public InterfacePrincipale(String titre){
        super(titre);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        nouveauTrajetNonFixeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new NouveauTrajetNonFixe("Nouveau trajet non fixe",interfacePrincipale);
                frame.setVisible(true);
            }
        });

        nouveauTrajetFixeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new NouveauTrajetFixe("Nouveau trajet fixe",interfacePrincipale);
                frame.setVisible(true);
            }
        });

        resolutionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                checkerResolution = true;

                /**Verification**/

                try
                {
                    double nombreHeureMin = Double.parseDouble(nombreHeureMinText.getText());
                } catch (NumberFormatException ex){
                    checkerResolution = false;
                    nombreHeureMinText.setText("?");
                }

                try
                {
                    double nombreHeureMax = Double.parseDouble(nombreHeureMaxText.getText());
                } catch (NumberFormatException ex){
                    checkerResolution = false;
                    nombreHeureMaxText.setText("?");
                }

                try
                {
                    double coutHoraireJour = Double.parseDouble(coutHoraireJourText.getText());
                } catch (NumberFormatException ex){
                    checkerResolution = false;
                    coutHoraireJourText.setText("?");
                }

                try
                {
                    double coutHoraireNuit = Double.parseDouble(coutHoraireNuitText.getText());
                } catch (NumberFormatException ex){
                    checkerResolution = false;
                    coutHoraireNuitText.setText("?");
                }

                try
                {
                    double coutHotellerie = Double.parseDouble(coutHottelerieText.getText());
                } catch (NumberFormatException ex){
                    checkerResolution = false;
                    coutHottelerieText.setText("?");
                }

                try
                {
                    double nombreHeureConduiteMaximum = Double.parseDouble(nombreHeureConduiteMaximumText.getText());
                } catch (NumberFormatException ex){
                    checkerResolution = false;
                    nombreHeureConduiteMaximumText.setText("?");
                }

                try
                {
                    double tempsReposJournalier = Double.parseDouble(tempsReposJournalierText.getText());
                } catch (NumberFormatException ex){
                    checkerResolution = false;
                    tempsReposJournalierText.setText("?");
                }

                try
                {
                    double nombreHeuresConduiteJournaliere = Double.parseDouble(nombreHeuresConduiteJournaliereText.getText());
                } catch (NumberFormatException ex){
                    checkerResolution = false;
                    nombreHeuresConduiteJournaliereText.setText("?");
                }

                try
                {
                    double nombreHeuresHebdomadaire = Double.parseDouble(nombreHeuresHebdomadaireText.getText());
                } catch (NumberFormatException ex){
                    checkerResolution = false;
                    nombreHeuresHebdomadaireText.setText("?");
                }

                try
                {
                    double dureeReposHebdomadaire = Double.parseDouble(dureeReposHebdomadaireText.getText());
                } catch (NumberFormatException ex){
                    checkerResolution = false;
                    dureeReposHebdomadaireText.setText("?");
                }



                /**Creation de l'objet plannification**/

                if(checkerResolution == true){
                    double nombreHeureMin = Double.parseDouble(nombreHeureMinText.getText());
                    double nombreHeureMax = Double.parseDouble(nombreHeureMaxText.getText());
                    double coutHoraireJour = Double.parseDouble(coutHoraireJourText.getText());
                    double coutHoraireNuit = Double.parseDouble(coutHoraireNuitText.getText());
                    double coutHotellerie = Double.parseDouble(coutHottelerieText.getText());
                    double nombreHeureConduiteMaximum = Double.parseDouble(nombreHeureConduiteMaximumText.getText());
                    double tempsReposJournalier = Double.parseDouble(tempsReposJournalierText.getText());
                    double nombreHeuresConduiteJournaliere = Double.parseDouble(nombreHeuresConduiteJournaliereText.getText());
                    double nombreHeuresHebdomadaire = Double.parseDouble(nombreHeuresHebdomadaireText.getText());
                    double dureeReposHebdomadaire = Double.parseDouble(dureeReposHebdomadaireText.getText());

                    if(trajetFixe.isEmpty()==false && trajetFixe!=null || trajetNonFixe.isEmpty()==false || trajetNonFixe!=null){
                        plannification = new Plannification(nombreHeureMin,nombreHeureMax,coutHoraireJour,coutHoraireNuit,coutHotellerie,nombreHeureConduiteMaximum,tempsReposJournalier,nombreHeuresConduiteJournaliere,nombreHeuresHebdomadaire,dureeReposHebdomadaire,trajetNonFixe,trajetFixe);
                        // Moteur de resolution

                        MoteurDeResolutionPrototype moteurDeResolution;
                        moteurDeResolution = new MoteurDeResolutionPrototype(plannification);
                        ArrayList<Solution> solutions = new ArrayList<Solution>();

                        System.out.println(plannification);

                        //ACT
                        solutions = moteurDeResolution.camionTrajetsAleatoire(10000000);
                        System.out.println(solutions.size());
                        if(!solutions.isEmpty()) {
                            try {
                                System.out.println(solutions.get(0));
                                solutions.get(0).creationCsvCamion();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }

                        // Moteur de resolution prototype
            //        MoteurDeResolutionPrototype moteurDeResolutionPrototype;
            //        moteurDeResolutionPrototype = new MoteurDeResolutionPrototype(plannification);
            //        ArrayList<Solution> solutionsPrototype = new ArrayList<Solution>();
            //        int compteur = 0;
            //        solutions = moteurDeResolutionPrototype.camionTrajetsAleatoire(1000);
            //        if(!solutions.isEmpty()) {
            //            do {
            //                solutions = moteurDeResolutionPrototype.chauffeurTrajets(solutions, 1000);
            //                compteur++;
            //            }while(solutions.isEmpty() && compteur < 5);
            //
            //            if(!solutions.isEmpty()) {
            //                Solution solution = moteurDeResolutionPrototype.optimisationCout(solutions);
            //                solution.creationCsv();
            //            }
            //        }
                    }

                }


            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new InterfacePrincipale("Symone");
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        JScrollPane scrollPane1 = new JScrollPane();
        JScrollPane scrollPane2 = new JScrollPane();

        modelTrajetFixe = new DefaultListModel();
        trajetFixeList = new JList(modelTrajetFixe);

        modelTrajetNonFixe = new DefaultListModel();
        trajetNonFixeList = new JList(modelTrajetNonFixe);



        scrollPane1.setViewportView(trajetFixeList);
        scrollPane2.setViewportView(trajetNonFixeList);
    }


    /** Getters & Setters**/

    public JList getTrajetNonFixeList() {
        return trajetNonFixeList;
    }

    public void setTrajetNonFixeList(JList trajetNonFixeList) {
        this.trajetNonFixeList = trajetNonFixeList;
    }

    public JList getTrajetFixeList() {
        return trajetFixeList;
    }

    public void setTrajetFixeList(JList trajetFixeList) {
        this.trajetFixeList = trajetFixeList;
    }

    public ArrayList<TrajetFixe> getTrajetFixe() {
        return trajetFixe;
    }

    public void setTrajetFixe(ArrayList<TrajetFixe> trajetFixe) {
        this.trajetFixe = trajetFixe;
    }

    public ArrayList<TrajetNonFixe> getTrajetNonFixe() {
        return trajetNonFixe;
    }

    public void setTrajetNonFixe(ArrayList<TrajetNonFixe> trajetNonFixe) {
        this.trajetNonFixe = trajetNonFixe;
    }

    public DefaultListModel getModelTrajetFixe() {
        return modelTrajetFixe;
    }

    public void setModelTrajetFixe(DefaultListModel modelTrajetFixe) {
        this.modelTrajetFixe = modelTrajetFixe;
    }

    public DefaultListModel getModelTrajetNonFixe() {
        return modelTrajetNonFixe;
    }

    public void setModelTrajetNonFixe(DefaultListModel modelTrajetNonFixe) {
        this.modelTrajetNonFixe = modelTrajetNonFixe;
    }

    public JTextField getNombreHeureMinText() {
        return nombreHeureMinText;
    }

    public void setNombreHeureMinText(JTextField nombreHeureMinText) {
        this.nombreHeureMinText = nombreHeureMinText;
    }

    public JTextField getNombreHeureMaxText() {
        return nombreHeureMaxText;
    }

    public void setNombreHeureMaxText(JTextField nombreHeureMaxText) {
        this.nombreHeureMaxText = nombreHeureMaxText;
    }

    public JTextField getCoutHoraireJourText() {
        return coutHoraireJourText;
    }

    public void setCoutHoraireJourText(JTextField coutHoraireJourText) {
        this.coutHoraireJourText = coutHoraireJourText;
    }

    public JTextField getCoutHoraireNuitText() {
        return coutHoraireNuitText;
    }

    public void setCoutHoraireNuitText(JTextField coutHoraireNuitText) {
        this.coutHoraireNuitText = coutHoraireNuitText;
    }

    public JTextField getCoutHottelerieText() {
        return coutHottelerieText;
    }

    public void setCoutHottelerieText(JTextField coutHottelerieText) {
        this.coutHottelerieText = coutHottelerieText;
    }

    public JTextField getNombreHeureConduiteMaximumText() {
        return nombreHeureConduiteMaximumText;
    }

    public void setNombreHeureConduiteMaximumText(JTextField nombreHeureConduiteMaximumText) {
        this.nombreHeureConduiteMaximumText = nombreHeureConduiteMaximumText;
    }

    public JTextField getTempsReposJournalierText() {
        return tempsReposJournalierText;
    }

    public void setTempsReposJournalierText(JTextField tempsReposJournalierText) {
        this.tempsReposJournalierText = tempsReposJournalierText;
    }

    public JTextField getNombreHeuresConduiteJournaliereText() {
        return nombreHeuresConduiteJournaliereText;
    }

    public void setNombreHeuresConduiteJournaliereText(JTextField nombreHeuresConduiteJournaliereText) {
        this.nombreHeuresConduiteJournaliereText = nombreHeuresConduiteJournaliereText;
    }

    public JTextField getNombreHeuresHebdomadaireText() {
        return nombreHeuresHebdomadaireText;
    }

    public void setNombreHeuresHebdomadaireText(JTextField nombreHeuresHebdomadaireText) {
        this.nombreHeuresHebdomadaireText = nombreHeuresHebdomadaireText;
    }

    public JTextField getDureeReposHebdomadaireText() {
        return dureeReposHebdomadaireText;
    }

    public void setDureeReposHebdomadaireText(JTextField dureeReposHebdomadaireText) {
        this.dureeReposHebdomadaireText = dureeReposHebdomadaireText;
    }

    public JButton getResolutionButton() {
        return resolutionButton;
    }

    public void setResolutionButton(JButton resolutionButton) {
        this.resolutionButton = resolutionButton;
    }

    public Plannification getPlannification() {
        return plannification;
    }

    public void setPlannification(Plannification plannification) {
        this.plannification = plannification;
    }
}
