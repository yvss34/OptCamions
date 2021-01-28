package vue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NouveauTrajetFixe extends JFrame{

    private JPanel mainPanel;

    private JLabel villeDepartLabel;
    private JComboBox villeDepartComboBox;

    private JLabel villeArriveeLabel;
    private JComboBox villeArriveeComboBox;

    private JLabel tempsDeConduiteLabel;

    private JLabel villeStopLabel;
    private JComboBox villeStopComboBox;
    private JList villeStopList;
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

    public NouveauTrajetFixe(String titre){
        super(titre);
        this.setContentPane(mainPanel);
        this.pack();

        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Creation de l'objet Trajet fixe

            }
        });
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }


}
