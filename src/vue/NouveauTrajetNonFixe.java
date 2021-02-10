package vue;

import modele.Jour;
import modele.Trajet;
import modele.TrajetNonFixe;
import modele.Ville;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class NouveauTrajetNonFixe extends JFrame{

    /**Composants de la vue**/
    private JPanel mainPanel;

    private JLabel villeDepartLabel;
    private JComboBox villeDepartComboBox;

    private JLabel villeArriveeLabel;
    private JComboBox villeArriveeComboBox;

    private JLabel tempsDeConduiteLabel;
    private JLabel tempsSousTrajetLabel;

    private JLabel villeStopLabel;
    private JComboBox villeStopComboBox;
    private JButton villeStopButton;

    private JLabel frequenceParSemaineLabel;
    private JLabel fenetreTempsDepartLabel;
    private JLabel nombreTrajetMinLabel;
    private JLabel nombreJoursMinLabel;

    private JButton annulerButton;
    private JButton validerButton;

    private JTextField fenetreTempsDepartText;
    private JTextField tempsSousTrajetText;
    private JTextField tempsDeConduiteText;
    private JTextField frequenceParSemaineText;
    private JTextField nombreTrajetMinText;
    private JTextField nombreJoursMinText;

    private JList villeStopList;
    private DefaultListModel model;

    private boolean checkerValider;
    private boolean checherAjoutVilleStop;
    private JLabel messageErrorLabel;

    InterfacePrincipale interfacePrincipale;
    ArrayList<Ville> villes;

    /**Les elements a envoyer**/
    TrajetNonFixe trajet;
    HashMap<Ville,Double> hashMapVilleStops = new HashMap<Ville,Double>();

    public NouveauTrajetNonFixe(String titre, InterfacePrincipale interfacePrincipale){
        super(titre);
        this.setContentPane(mainPanel);
        this.pack();

        this.interfacePrincipale = interfacePrincipale;

        villes = new ArrayList<Ville>();
        villes = Ville.getVilles();
        for(Ville ville:villes){
            villeDepartComboBox.addItem(ville);
            villeArriveeComboBox.addItem(ville);
            villeStopComboBox.addItem(ville);
        }


        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkerValider = true;

                /**Verification**/
                if(villeDepartComboBox.getSelectedItem() == null){
                    checkerValider = false;
                    villeDepartLabel.setText("Ville départ *");
                }

                if(villeArriveeComboBox.getSelectedItem() == null){
                    checkerValider = false;
                    villeArriveeLabel.setText("Ville arrivée *");
                }

                try
                {
                    double tempsDeConduite = Double.parseDouble(tempsDeConduiteText.getText());
                } catch (NumberFormatException ex){
                    checkerValider = false;
                    tempsDeConduiteText.setText("?");
                }

                try
                {
                    int frequenceParSemaine = Integer.parseInt(frequenceParSemaineText.getText());
                    if(frequenceParSemaine > 6){
                        checkerValider = false;
                        frequenceParSemaineText.setText("IMPOSSIBLE");
                    }
                } catch (NumberFormatException ex) {
                    checkerValider = false;
                    frequenceParSemaineText.setText("?");

                }

                try
                {
                    int nombreTrajetMin = Integer.parseInt(nombreTrajetMinText.getText());
                    if(nombreTrajetMin>2){
                        checkerValider = false;
                        nombreTrajetMinText.setText("IMPOSSIBLE");
                    }
                } catch (NumberFormatException ex) {
                    checkerValider = false;
                    nombreTrajetMinText.setText("?");

                }

                if(nombreJoursMinText.getText().length() != 0){
                    try
                    {
                        int nombreJoursMin = Integer.parseInt(nombreJoursMinText.getText());
                    } catch (NumberFormatException ex) {
                        checkerValider = false;
                        nombreJoursMinText.setText("?");

                    }
                }else{
                    nombreJoursMinText.setText("0");
                }

                try
                {
                    int nombreJoursMin = Integer.parseInt(nombreJoursMinText.getText());
                    int frequenceParSemaine = Integer.parseInt(frequenceParSemaineText.getText());

                    if(7-frequenceParSemaine*(1+nombreJoursMin)+1<nombreJoursMin){
                        checkerValider = false;
                        frequenceParSemaineText.setText("IMPOSSIBLE");
                        nombreJoursMinText.setText("IMPOSSIBLE");
                    }
                } catch (NumberFormatException ex) {

                }

                try
                {
                    int nombreJoursMin = Integer.parseInt(nombreJoursMinText.getText());
                    int nombreTrajetMin = Integer.parseInt(nombreTrajetMinText.getText());

                    if(2-nombreTrajetMin*(1+nombreJoursMin)+1<nombreJoursMin){
                        checkerValider = false;
                        nombreJoursMinText.setText("IMPOSSIBLE");
                        nombreTrajetMinText.setText("IMPOSSIBLE");
                    }
                } catch (NumberFormatException ex) {

                }

                try
                {
                    String[] fenetreTempsDepart = fenetreTempsDepartText.getText().split(";");

                    for (String fenetre : fenetreTempsDepart){
                        String[] tempsDepart = fenetre.split("-");

                        if(tempsDepart.length!=2){
                            checkerValider = false;
                            fenetreTempsDepartText.setText("?");
                        }
                        else{
                            double heureArrivee = Double.parseDouble(tempsDepart[0]);
                            double heureDepart = Double.parseDouble(tempsDepart[1]);

                            if((int)heureArrivee >24||(int)heureArrivee<0 || (int)heureDepart >24||(int)heureDepart<0){
                                checkerValider = false;
                                fenetreTempsDepartText.setText("?");
                            }
                            if(heureArrivee - (int)heureArrivee > 0.6 ||heureArrivee - (int)heureArrivee < 0||heureDepart - (int)heureDepart >0.6||heureDepart - (int)heureDepart < 0){
                                checkerValider = false;
                                fenetreTempsDepartText.setText("?");
                            }
                        }


                    }
                }catch (NumberFormatException ex) {
                    checkerValider = false;
                    fenetreTempsDepartText.setText("?");

                }

                if(villeDepartComboBox.getSelectedItem() == villeArriveeComboBox.getSelectedItem()){
                    checkerValider = false;
                    villeArriveeLabel.setText("Ville arrivée ***");
                }

                    /**Creation de l'objet Trajet non fixe**/

                    if(checkerValider == true){
                        double tempsDeConduite = Double.parseDouble(tempsDeConduiteText.getText());
                        int frequenceParSemaine = Integer.parseInt(frequenceParSemaineText.getText());
                        int nombreTrajetMin = Integer.parseInt(nombreTrajetMinText.getText());
                        int nombreJoursMin = Integer.parseInt(nombreJoursMinText.getText());
                        String[] fenetreTempsDepart = fenetreTempsDepartText.getText().split(";");

                        if(hashMapVilleStops.isEmpty() || hashMapVilleStops == null)
                            trajet = new TrajetNonFixe(Trajet.getNbrTrajet(),(Ville)villeDepartComboBox.getSelectedItem(),(Ville)villeArriveeComboBox.getSelectedItem(),tempsDeConduite,null,frequenceParSemaine,fenetreTempsDepart,nombreTrajetMin,nombreJoursMin);
                        else
                            trajet = new TrajetNonFixe(Trajet.getNbrTrajet(),(Ville)villeDepartComboBox.getSelectedItem(),(Ville)villeArriveeComboBox.getSelectedItem(),tempsDeConduite,hashMapVilleStops,frequenceParSemaine,fenetreTempsDepart,nombreTrajetMin,nombreJoursMin);

                        System.out.println(trajet.toString());
                        interfacePrincipale.getTrajetNonFixe().add(trajet);
                        interfacePrincipale.getModelTrajetNonFixe().addElement(trajet);
                        interfacePrincipale.setTrajetNonFixeList(new JList(interfacePrincipale.getModelTrajetNonFixe()));
                    }


            }
        });

        villeStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checherAjoutVilleStop = true;

                if(villeStopComboBox.getSelectedItem() == null){
                    checherAjoutVilleStop = false;
                    villeStopLabel.setText("Ville stop *");
                }

                try{
                    double tempsSousTrajet = Double.parseDouble(tempsSousTrajetText.getText());
                    double tempsDeConduite = Double.parseDouble(tempsDeConduiteText.getText());
                    if((int)tempsSousTrajet >24||(int)tempsSousTrajet<0 || tempsSousTrajet >= tempsDeConduite || tempsSousTrajet - (int)tempsSousTrajet > 60 ||tempsSousTrajet - (int)tempsSousTrajet < 0){
                        checherAjoutVilleStop = false;
                        tempsSousTrajetText.setText("?");
                    }


                }catch (NumberFormatException ex) {
                    checherAjoutVilleStop = false;
                    tempsSousTrajetText.setText("?");
                }

                if(villeStopComboBox.getSelectedItem() == villeArriveeComboBox.getSelectedItem() || villeStopComboBox.getSelectedItem() == villeDepartComboBox.getSelectedItem()){
                    checherAjoutVilleStop = false;
                    villeStopLabel.setText("Ville stop ***");
                }

                if(checherAjoutVilleStop == true){

                    try{
                        double tempsSousTrajet = Double.parseDouble(tempsSousTrajetText.getText());
                        hashMapVilleStops.put((Ville)villeStopComboBox.getSelectedItem(),tempsSousTrajet);

                        ArrayList<String> villeStop = new ArrayList<String>();
                        villeStop.add(villeStopComboBox.getSelectedItem().toString());
                        villeStop.add(tempsSousTrajetText.getText());
                        model.addElement(villeStop);

                        villeStopList = new JList(model);
                    }catch (NumberFormatException ex) {
                        checherAjoutVilleStop = false;
                        tempsSousTrajetText.setText("?");
                    }

                }

            }

        });
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        model = new DefaultListModel();
        villeStopList = new JList(model);
    }

    /** Getters & Setters **/

    public JComboBox getVilleDepartComboBox() {
        return villeDepartComboBox;
    }

    public void setVilleDepartComboBox(JComboBox villeDepartComboBox) {
        this.villeDepartComboBox = villeDepartComboBox;
    }

    public JComboBox getVilleArriveeComboBox() {
        return villeArriveeComboBox;
    }

    public void setVilleArriveeComboBox(JComboBox villeArriveeComboBox) {
        this.villeArriveeComboBox = villeArriveeComboBox;
    }

    public JComboBox getVilleStopComboBox() {
        return villeStopComboBox;
    }

    public void setVilleStopComboBox(JComboBox villeStopComboBox) {
        this.villeStopComboBox = villeStopComboBox;
    }

    public JButton getValiderButton() {
        return validerButton;
    }

    public void setValiderButton(JButton validerButton) {
        this.validerButton = validerButton;
    }

    public JTextField getFenetreTempsDepartText() {
        return fenetreTempsDepartText;
    }

    public void setFenetreTempsDepartText(JTextField fenetreTempsDepartText) {
        this.fenetreTempsDepartText = fenetreTempsDepartText;
    }

    public JTextField getTempsSousTrajetText() {
        return tempsSousTrajetText;
    }

    public void setTempsSousTrajetText(JTextField tempsSousTrajetText) {
        this.tempsSousTrajetText = tempsSousTrajetText;
    }

    public JTextField getTempsDeConduiteText() {
        return tempsDeConduiteText;
    }

    public void setTempsDeConduiteText(JTextField tempsDeConduiteText) {
        this.tempsDeConduiteText = tempsDeConduiteText;
    }

    public JTextField getFrequenceParSemaineText() {
        return frequenceParSemaineText;
    }

    public void setFrequenceParSemaineText(JTextField frequenceParSemaineText) {
        this.frequenceParSemaineText = frequenceParSemaineText;
    }

    public JTextField getNombreTrajetMinText() {
        return nombreTrajetMinText;
    }

    public void setNombreTrajetMinText(JTextField nombreTrajetMinText) {
        this.nombreTrajetMinText = nombreTrajetMinText;
    }

    public JTextField getNombreJoursMinText() {
        return nombreJoursMinText;
    }

    public void setNombreJoursMinText(JTextField nombreJoursMinText) {
        this.nombreJoursMinText = nombreJoursMinText;
    }

    public TrajetNonFixe getTrajet() {
        return trajet;
    }

    public void setTrajet(TrajetNonFixe trajet) {
        this.trajet = trajet;
    }

    public ArrayList<Ville> getVilles() {
        return villes;
    }

    public void setVilles(ArrayList<Ville> villes) {
        this.villes = villes;
    }
}
