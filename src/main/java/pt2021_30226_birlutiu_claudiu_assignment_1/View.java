package pt2021_30226_birlutiu_claudiu_assignment_1;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.plaf.metal.MetalBorders;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame{

    private JTextField  firstPolynom=new JTextField();
    private JTextField  secondPolynom=new JTextField();


    private JButton btMultiply=new JButton("*");
    private JButton btSub=new JButton("-");
    private JButton btAdd=new JButton("+");

    private JLabel resultLabel=new JLabel("RESULT: ");
    private JLabel catLabel =new JLabel("");
    private JLabel restLabel =new JLabel("");


    private JRadioButton btMultiply2=new JRadioButton("Multiply *");
    private JRadioButton btIntegration2=new JRadioButton("Integration ∫");
    private JRadioButton btSub2=new JRadioButton("Subtraction -");
    private JRadioButton btDiv2=new JRadioButton("Division /");
    private JRadioButton btDerivate2=new JRadioButton("Derivate d");
    private JRadioButton btAdd2=new JRadioButton("     Add +");

    private JButton btZero=new JButton("0");
    private JButton btOne=new JButton("1");
    private JButton btTwo=new JButton("2");
    private JButton btThree=new JButton("3");
    private JButton btFour=new JButton("4");
    private JButton btFive=new JButton("5");
    private JButton btSix=new JButton("6");
    private JButton btSeven=new JButton("7");
    private JButton btEight=new JButton("8");
    private JButton btNine=new JButton("9");
    private JButton btClear=new JButton("C");
    private JButton btResult=new JButton("=");
    private JButton btDel=new JButton("Del");
    private JButton btPoint=new JButton(".");
    private JButton btPow=new JButton("^");
    private JButton btX=new JButton("X");

    Font f1=new Font("TimesNewRoman", Font.TYPE1_FONT, 15);
    Font f2=new Font("TimesNewRoman", Font.CENTER_BASELINE, 30);
    Font fDigit =new Font("Serif", Font.BOLD,25);


    public View() {
        this.setSize(new Dimension(550,600));
        this.setPreferredSize(new Dimension(550,600));
        this.setMinimumSize(new Dimension(600,700));

        JPanel mainPanel=new JPanel();
        JLabel titleCalc = new JLabel("Polynomial Calculator");
        titleCalc.setFont(new Font("Serif", Font.BOLD, 30));

        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.add(titleCalc);
        titleCalc.setAlignmentX(0.5f);
        // mainPanel.setBackground(new Color(0.1f,0.1f,0.2f));


        JPanel panelInput=new JPanel();
        panelInput.setLayout(new GridLayout(2,1));
        panelInput.setPreferredSize(new Dimension(this.getWidth(),90));

        JPanel firstPanelPolynomial=new JPanel();
        JLabel l1=new JLabel("First Polynomial: ");
        l1.setFont(f1);
        firstPanelPolynomial.add(l1);
        firstPolynom.setPreferredSize(new Dimension(2*this.getWidth()/3, 30));
        firstPanelPolynomial.add(firstPolynom);


        JPanel secondPanelPolynomial=new JPanel();
        JLabel l2=new JLabel("Second Polynomial: ");
        l2.setFont(f1);
        secondPanelPolynomial.add(l2);
        secondPolynom.setPreferredSize(new Dimension(2*this.getWidth()/3, 30));
        secondPanelPolynomial.add(secondPolynom);

        panelInput.add(firstPanelPolynomial);
        panelInput.add(secondPanelPolynomial);

        JPanel resultPanel=new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel,BoxLayout.Y_AXIS));
        resultPanel.add(resultLabel); resultLabel.setFont(fDigit);
        resultPanel.add(catLabel);    catLabel.setFont(fDigit);
        resultPanel.add(restLabel);   restLabel.setFont(fDigit);

        JPanel operationPanel=new JPanel();
        operationPanel.setPreferredSize(new Dimension(2*this.getWidth()/3, 100));
        operationPanel.setLayout(new GridLayout(3,2,6,4));
        operationPanel.add(btAdd2);                  btAdd2.setFont(f2);
        operationPanel.add(btSub2);                  btSub2.setFont(f2);
        operationPanel.add(btMultiply2);             btMultiply2.setFont(f2);
        operationPanel.add(btDiv2);                  btDiv2.setFont(f2);
        operationPanel.add(btDerivate2);             btDerivate2.setFont(f2);
        operationPanel.add(btIntegration2);          btIntegration2.setFont(f2);
        ButtonGroup groupOperation=new ButtonGroup();btAdd2.setFont(f2);
        groupOperation.add(btAdd2);
        groupOperation.add(btSub2);
        groupOperation.add(btMultiply2);
        groupOperation.add(btDiv2);
        groupOperation.add(btDerivate2);
        groupOperation.add(btIntegration2);


        JPanel userPanel=new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel,BoxLayout.X_AXIS));
        userPanel.setPreferredSize(new Dimension(2*this.getWidth()/3, 100));
        JPanel numberPanel=new JPanel();
        numberPanel.setLayout(new GridLayout(3,3,3,2));

        numberPanel.add(btOne);    btOne.setFont(fDigit);
        numberPanel.add(btTwo);    btTwo.setFont(fDigit);
        numberPanel.add(btThree);  btThree.setFont(fDigit);
        numberPanel.add(btFour);   btFour.setFont(fDigit);
        numberPanel.add(btFive);   btFive.setFont(fDigit);
        numberPanel.add(btSix);    btSix.setFont(fDigit);
        numberPanel.add(btSeven);  btSeven.setFont(fDigit);
        numberPanel.add(btEight);  btEight.setFont(fDigit);
        numberPanel.add(btNine);   btNine.setFont(fDigit);

        JPanel signPanel2=new JPanel();
        signPanel2.setLayout(new GridLayout(3,3,3,3));
        signPanel2.add(btZero);         btZero.setFont(fDigit);
        signPanel2.add(btAdd);          btAdd.setBackground(new Color(255, 184, 77));
        signPanel2.add(btSub);          btSub.setBackground(new Color(255, 184, 77));
        signPanel2.add(btMultiply);     btMultiply.setBackground(new Color(255, 184, 77));
        signPanel2.add(btX);            btX.setBackground(new Color(255, 184, 77));
        signPanel2.add(btPow);          btPow.setBackground(new Color(255, 184, 77));
        signPanel2.add(btDel);          btDel.setBackground(new Color(230, 138, 0));
        signPanel2.add(btClear);        btClear.setBackground(new Color(230, 138, 0));
        signPanel2.add(btResult);       btResult.setBackground(new Color(51, 204, 0));
        userPanel.add(numberPanel);
        userPanel.add(signPanel2);

        resultPanel.setBackground(new Color(155, 160, 176));
        secondPanelPolynomial.setBackground(new Color(155, 160, 176));
        firstPanelPolynomial.setBackground(new Color(155, 160, 176));
        mainPanel.setBackground(new Color(155, 160, 176));
        signPanel2.setBackground(new Color(155, 160, 230));
        numberPanel.setBackground(new Color(30, 20, 100));

        mainPanel.add(panelInput);
        mainPanel.add(resultPanel);
        mainPanel.add(operationPanel);
        mainPanel.add(userPanel);

        this.setContentPane(mainPanel);
        this.pack();
        this.setTitle("Polynomial Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addAddListener (ActionListener e) {
        this.btAdd.addActionListener(e);
    }
    public void addMultiplyListener (ActionListener e) {
        this.btMultiply.addActionListener(e);
    }

    public void setTotal(String result) {
        this.restLabel.setText("\nREST "+result);

    }

    public String getUserInput() {
        return (String) firstPolynom.getText();
    }

    public void addClearListener (ActionListener e) {
        this.btClear.addActionListener(e);
    }

    public void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

}
