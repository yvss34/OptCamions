package vue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NouveauTrajetNonFixe extends JFrame{

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
    private JList villeStopList;

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
    private JLabel messageErrorLabel;

    private boolean checker;

    public NouveauTrajetNonFixe(String titre){
        super(titre);


        this.setContentPane(mainPanel);
        this.pack();
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checker = true;
                //Verification
                if(villeDepartComboBox.getSelectedItem() == null){
                    checker = false;
                    villeDepartLabel.setText("Ville départ *");
                }

                if(villeArriveeComboBox.getSelectedItem() == null){
                    checker = false;
                    villeArriveeLabel.setText("Ville arrivée *");
                }

                try
                {
                    double tempsDeConduite = Double.parseDouble(tempsDeConduiteText.getText());
                } catch (NumberFormatException ex){
                    checker = false;
                    tempsDeConduiteText.setText("?");
                }

                //Creation de l'objet Trajet non fixe
            }
        });
    }


}
