package viewCalculator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InfoPanel extends JPanel {
    Font f=new Font("SansSerif",Font.BOLD,15);

    Font f1=new Font("SansSerif",Font.BOLD,12);
    public InfoPanel() {
        this.setPreferredSize(new Dimension(750, 900));
        this.setMinimumSize(new Dimension(750, 900));
        JPanel info1 = new JPanel();
        info1.setLayout(new GridLayout(4,1));
        JLabel l1=new JLabel("Un polinom valid se poate scrie sub urmatoarele forme ale monoamelor");
        l1.setFont(f);
        info1.add(l1);
        JPanel monoameValide = new JPanel();
        monoameValide.setLayout(new GridLayout(8 , 2));
        monoameValide.setAlignmentX(JLabel.CENTER);
        monoameValide.setFont(f);
        monoameValide.add(new JLabel("+a*X^b"));
        monoameValide.add(new JLabel("-a*X^b"));
        monoameValide.add(new JLabel("+a*X"));
        monoameValide.add(new JLabel("-a*X"));
        monoameValide.add(new JLabel("+aX^b"));
        monoameValide.add(new JLabel("-aX^b"));
        monoameValide.add(new JLabel("+aX"));
        monoameValide.add(new JLabel("-aX"));
        monoameValide.add(new JLabel("+X"));
        monoameValide.add(new JLabel("-X"));
        monoameValide.add(new JLabel("+X^b"));
        monoameValide.add(new JLabel("-X^b"));
        monoameValide.add(new JLabel("aX^b"));
        monoameValide.add(new JLabel("+a"));
        monoameValide.add(new JLabel("-a"));
        monoameValide.add(new JLabel("a"));
        JLabel l2=new JLabel("Nu conteaza daca e x sau X; a si b numere naturale");
        l2.setFont(f);
        info1.add(monoameValide);
        info1.add(l2);
        ArrayList<JLabel> rulesLabels=new ArrayList<>();
        rulesLabels.add(new JLabel("Introduceti coeficienti si grade intregi"));
        rulesLabels.add(new JLabel("Un intreg este de lungime maxim 9"));
        rulesLabels.add(new JLabel("Casutele celor doua polinome sunt rosii=> s-a introdus ceva invalid"));
        rulesLabels.add(new JLabel("Calculatorul va afisa rezultatul doar in momentul in care cele doua polinoame sunt " +
                "introduse corect"));
        rulesLabels.add(new JLabel("Derivarea si integrarea se fac doar pe primul polinom, dar ambele casute sa fie" +
                "verzi"));
        rulesLabels.add(new JLabel("Nu conteaza ordinea monoamnelor"));
        rulesLabels.add(new JLabel("Se pot introduce mai multe monoame de acelasi grad"));
        rulesLabels.add(new JLabel("Nu se pun spatii"));
        rulesLabels.add(new JLabel("Polinom valid: 5*X^4+4x+5X^2+3x+x-x+4-0-56X"));
        JPanel rulesPanel=new JPanel();
        rulesPanel.setLayout(new GridLayout(rulesLabels.size(),1));
        rulesPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        for(JLabel rule: rulesLabels){
            rule.setFont(f1);
            rulesPanel.add(rule);
        }
        info1.add(rulesPanel);
        this.add(info1);
        Color background=new Color(222, 194, 164);
        info1.setBackground(background);
        monoameValide.setBackground(background);
        rulesPanel.setBackground(new Color(152, 199, 108));
        info1.setBackground(background);
        this.setBackground(background);


    }
}
