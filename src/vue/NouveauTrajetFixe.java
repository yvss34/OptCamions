package vue;

import javax.swing.*;

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

    public NouveauTrajetFixe(String titre){
        super(titre);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new NouveauTrajetFixe("Nouveau trajet fixe");
        frame.setVisible(true);
    }

}
