package vue;

import javax.swing.*;

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

    public NouveauTrajetNonFixe(String titre){
        super(titre);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new NouveauTrajetNonFixe("Nouveau trajet non fixe");
        frame.setVisible(true);
    }

}
