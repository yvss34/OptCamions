package vue;

import javax.swing.*;

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
    private JButton nouveauTrajetButton;
    private JButton nouveauTrajetFixeButton;
    private JList trajetNonFixeList;
    private JList trajetFixeList;
    private JButton resolutionButton;

    public InterfacePrincipale(String titre){
        super(titre);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new InterfacePrincipale("Symone");
        frame.setVisible(true);
    }
}
