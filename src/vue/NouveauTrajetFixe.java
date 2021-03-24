package vue;

import modele.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class NouveauTrajetFixe extends JFrame{

    /**Composants de la vue**/
    private JPanel mainPanel;

    private JLabel villeDepartLabel;
    private JComboBox villeDepartComboBox;

    private JLabel villeArriveeLabel;
    private JComboBox villeArriveeComboBox;

    private JLabel tempsDeConduiteLabel;

    private JLabel villeStopLabel;
    private JComboBox villeStopComboBox;
    private JLabel tempsSousTrajetLabel;
    private JButton villeStopButton;

    private JLabel jourDeDepartLabel;
    private JComboBox jourDeDepartComboBox;

    private JLabel heureDeDepartLabel;

    private JButton annulerButton;
    private JButton validerButton;
    private JTextField tempsSousTrajetText;
    private JTextField heureDeDepartText;
    private JTextField tempsDeConduiteText;
    private JLabel messageErrorLabel;
    private JList villeStopList;

    private DefaultListModel model;
    private boolean checkerValider;
    private boolean checherAjoutVilleStop;

    ArrayList<Ville> villes;

    InterfacePrincipale interfacePrincipale;

    /**Les elements a envoyer**/
    TrajetFixe trajet;
    HashMap<Ville,Double> hashMapVilleStops = new HashMap<Ville,Double>();

    public NouveauTrajetFixe(String titre, InterfacePrincipale interfacePrincipale){
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

        ArrayList<Jour> jours = new ArrayList<Jour>();
        jours = Jour.jourDeLaSemaine();
        for(Jour jour: jours){
            jourDeDepartComboBox.addItem(jour);
        }

        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkerValider =true;
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

                if(jourDeDepartComboBox.getSelectedItem() == null){
                    checkerValider = false;
                    jourDeDepartLabel.setText("Jour de départ *");
                }

                try{
                    double heureDepart = Double.parseDouble(heureDeDepartText.getText());
                    if((int)heureDepart >24||(int)heureDepart<0 ||  heureDepart - (int)heureDepart > 0.6 ||heureDepart - (int)heureDepart < 0){
                        checkerValider = false;
                        heureDeDepartText.setText("?");
                    }


                }catch (NumberFormatException ex) {
                    checkerValider = false;
                    heureDeDepartText.setText("?");
                }



                /**Creation de l'objet Trajet fixe**/

                if(checkerValider == true){
                    double tempsDeConduite = Double.parseDouble(tempsDeConduiteText.getText());
                    double heureDepart = Double.parseDouble(heureDeDepartText.getText());

                    if(hashMapVilleStops.isEmpty() || hashMapVilleStops==null)
                        trajet = new TrajetFixe(interfacePrincipale.getNbrTrajet(),(Ville)villeDepartComboBox.getSelectedItem(),(Ville)villeArriveeComboBox.getSelectedItem(),tempsDeConduite,null,(Jour)jourDeDepartComboBox.getSelectedItem(), LocalTime.of((int)heureDepart, (int) ((heureDepart-(int)heureDepart)*100)));
                    else
                        trajet = new TrajetFixe(interfacePrincipale.getNbrTrajet(),(Ville)villeDepartComboBox.getSelectedItem(),(Ville)villeArriveeComboBox.getSelectedItem(),tempsDeConduite,hashMapVilleStops,(Jour)jourDeDepartComboBox.getSelectedItem(),LocalTime.of((int)heureDepart,(int) ((heureDepart-(int)heureDepart)*100)));

                    System.out.println(trajet.toString());
                    interfacePrincipale.setNbrTrajet(interfacePrincipale.getNbrTrajet()+1);
                    interfacePrincipale.getTrajetFixe().add(trajet);
                    interfacePrincipale.getModelTrajetFixe().addElement(trajet);
                    interfacePrincipale.setTrajetFixeList(new JList(interfacePrincipale.getModelTrajetFixe()));
                }

            }
        });
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
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

    /*** Getters & Setters ***/

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

    public JComboBox getJourDeDepartComboBox() {
        return jourDeDepartComboBox;
    }

    public void setJourDeDepartComboBox(JComboBox jourDeDepartComboBox) {
        this.jourDeDepartComboBox = jourDeDepartComboBox;
    }

    public JButton getValiderButton() {
        return validerButton;
    }

    public void setValiderButton(JButton validerButton) {
        this.validerButton = validerButton;
    }

    public JTextField getTempsSousTrajetText() {
        return tempsSousTrajetText;
    }

    public void setTempsSousTrajetText(JTextField tempsSousTrajetText) {
        this.tempsSousTrajetText = tempsSousTrajetText;
    }

    public JTextField getHeureDeDepartText() {
        return heureDeDepartText;
    }

    public void setHeureDeDepartText(JTextField heureDeDepartText) {
        this.heureDeDepartText = heureDeDepartText;
    }

    public JTextField getTempsDeConduiteText() {
        return tempsDeConduiteText;
    }

    public void setTempsDeConduiteText(JTextField tempsDeConduiteText) {
        this.tempsDeConduiteText = tempsDeConduiteText;
    }

    public TrajetFixe getTrajet() {
        return trajet;
    }

    public void setTrajet(TrajetFixe trajet) {
        this.trajet = trajet;
    }

    public ArrayList<Ville> getVilles() {
        return villes;
    }

    public void setVilles(ArrayList<Ville> villes) {
        this.villes = villes;
    }
}
