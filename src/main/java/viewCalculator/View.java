package viewCalculator;

import javax.swing.*;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import modelClasses.*;

public class View extends JFrame {

    Font f1 = new Font("Serif", Font.BOLD, 15);
    Font outputFont=new Font("TimesNewRoman", Font.PLAIN, 15);
    Font f2 = new Font("SansSerif", Font.BOLD, 30);
    Font fDigit = new Font("Serif", Font.BOLD, 25);
    Color bgColor =new Color(16, 15, 16, 255);
    private  ModelCalc model;
    private  JTextField firstPolynom = new JTextField();
    private  JTextField secondPolynom = new JTextField();
    private final JLabel secondPolynomLabel = new JLabel("Second Polynomial: ");
    private final JLabel resultLabel = new JLabel("RESULT ");
    private JTextField resultField = new JTextField();
    private final JLabel catLabel = new JLabel("Quotient: ");
    private final JLabel restLabel = new JLabel("Remainder: ");
    private  JTextField catText = new JTextField();
    private  JTextField restText = new JTextField();
    private final JRadioButton btMultiply2 = new JRadioButton("  *  Multiplication");
    private final JRadioButton btIntegration2 = new JRadioButton("   ∫  Integration");
    private final JRadioButton btSub2 = new JRadioButton("   -   Subtraction");
    private final JRadioButton btDiv2 = new JRadioButton("   /   Division ");
    private final JRadioButton btDerivate2 = new JRadioButton("  d  Derivative");
    private final JRadioButton btAdd2 = new JRadioButton("  +  Addition");
    private final ButtonGroup groupOperation;
    private final JButton btZero = new JButton("0");
    private final JButton btOne = new JButton("1");
    private final JButton btTwo = new JButton("2");
    private final JButton btThree = new JButton("3");
    private final JButton btFour = new JButton("4");
    private final JButton btFive = new JButton("5");
    private final JButton btSix = new JButton("6");
    private final JButton btSeven = new JButton("7");
    private final JButton btEight = new JButton("8");
    private final JButton btNine = new JButton("9");
    private final JButton btClear = new JButton("C");
    private final JButton btResult = new JButton("=");
    private final JButton btDel = new JButton("Del");
    private final JButton btMultiply = new JButton("*");
    private final JButton btSub = new JButton("-");
    private final JButton btAdd = new JButton("+");
    private final JButton btPow = new JButton("^");
    private final JButton btX = new JButton("X");

    public View(ModelCalc model) {
        this.model = model;
        this.setPreferredSize(new Dimension(750, 900));
        this.setMinimumSize(new Dimension(750, 900));

        //fac doua panel-uri; unul pentru calcule si unul pt informatii
        JTabbedPane tabbedPane = new JTabbedPane();
        //un panel pentru informatii
        JPanel infoPanel = new InfoPanel();

        //pentru a dezactiva focusul cand se da click pe buton
        this.disableFocus();

        //panel-ul principal la care se adauga celelate panel-uri: input, result, opertions, butoane cifre si semne
        JPanel mainPanel = new JPanel();
        JLabel titleCalc = new JLabel("Polynomial Calculator");
        titleCalc.setFont(new Font("Serif", Font.BOLD, 30));

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(titleCalc);      titleCalc.setForeground(new Color(255,255,255));
        titleCalc.setAlignmentX(0.5f);

        JPanel panelInput = new JPanel();
        panelInput.setLayout(new GridLayout(2, 1));
        panelInput.setPreferredSize(new Dimension(this.getWidth(), 65));

        JPanel firstPanelPolynomial = new JPanel();
        JLabel l1 = new JLabel("First Polynomial: ");
        l1.setFont(f1); l1.setForeground(new Color(255,255,255));
        firstPanelPolynomial.add(l1);
        firstPolynom.setPreferredSize(new Dimension(2 * this.getWidth() / 3, 30));
        firstPanelPolynomial.add(firstPolynom);  firstPolynom.setFont(outputFont);

        JPanel secondPanelPolynomial = new JPanel();
        secondPolynomLabel.setFont(f1);  secondPolynomLabel.setForeground(new Color(255,255,255));
        secondPanelPolynomial.add(secondPolynomLabel);
        secondPolynom.setPreferredSize(new Dimension(2 * this.getWidth() / 3, 30));
        secondPanelPolynomial.add(secondPolynom); secondPolynom.setFont(outputFont);

        panelInput.add(firstPanelPolynomial);
        panelInput.add(secondPanelPolynomial);

        //pentru panoul de rezultate
        JPanel resultPanel = new JPanel();
        resultPanel.add(resultLabel);
        resultLabel.setFont(fDigit);
        resultPanel.add(resultField);  resultField.setFont(outputFont); resultField.setEditable(false);
        resultField.setPreferredSize(new Dimension(3 * this.getWidth() / 4, 30));
        resultPanel.add(catLabel);
        resultPanel.add(catText);
        catText.setPreferredSize(new Dimension(3 * this.getWidth() / 4, 30));
        resultPanel.add(restLabel);
        resultPanel.add(restText);
        restText.setPreferredSize(new Dimension(3 * this.getWidth() / 4, 30));
        restText.setVisible(false); restText.setFont(outputFont);
        restLabel.setVisible(false);
        catText.setVisible(false);  catText.setFont(outputFont);
        catLabel.setVisible(false);

        //panoul de operatii mateamtice pe polinoame
        JPanel operationPanel = new JPanel();
        operationPanel.setPreferredSize(new Dimension(2 * this.getWidth() / 3, 100));
        operationPanel.setLayout(new GridLayout(3, 2, 6, 4));
        operationPanel.add(btAdd2);
        btAdd2.setFont(f2);                 btAdd2.setForeground(new Color(246, 0, 8));
        btAdd2.setSelected(true);
        operationPanel.add(btSub2);
        btSub2.setFont(f2);                 btSub2.setForeground(Color.white);
        operationPanel.add(btMultiply2);
        btMultiply2.setFont(f2);            btMultiply2.setForeground(Color.white);
        operationPanel.add(btDiv2);
        btDiv2.setFont(f2);                 btDiv2.setForeground(Color.white);
        operationPanel.add(btDerivate2);
        btDerivate2.setFont(f2);            btDerivate2.setForeground(Color.white);
        operationPanel.add(btIntegration2);
        btIntegration2.setFont(f2);         btIntegration2.setForeground(Color.white);
        groupOperation = new ButtonGroup();
        groupOperation.add(btAdd2);         btAdd2.setBackground(bgColor);
        groupOperation.add(btSub2);         btSub2.setBackground(bgColor);
        groupOperation.add(btMultiply2);    btMultiply2.setBackground(bgColor);
        groupOperation.add(btDiv2);         btDiv2.setBackground(bgColor);
        groupOperation.add(btDerivate2);    btDerivate2.setBackground(bgColor);
        groupOperation.add(btIntegration2); btIntegration2.setBackground(bgColor);


        //panourile pentru cifre si semne
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
        userPanel.setPreferredSize(new Dimension(2 * this.getWidth() / 3, 100));
        JPanel numberPanel = new JPanel();
        numberPanel.setLayout(new GridLayout(3, 3, 3, 2));
        numberPanel.add(btOne);
        numberPanel.add(btTwo);
        numberPanel.add(btThree);
        numberPanel.add(btFour);
        numberPanel.add(btFive);
        numberPanel.add(btSix);
        numberPanel.add(btSeven);
        numberPanel.add(btEight);
        numberPanel.add(btNine);


        JPanel signPanel2 = new JPanel();
        signPanel2.setLayout(new GridLayout(3, 3, 3, 3));
        signPanel2.add(btZero);
        btZero.setFont(fDigit);
        signPanel2.add(btAdd);
        btAdd.setBackground(new Color(255, 184, 77));
        signPanel2.add(btSub);
        btSub.setBackground(new Color(255, 184, 77));
        signPanel2.add(btMultiply);
        btMultiply.setBackground(new Color(255, 184, 77));
        signPanel2.add(btX);
        btX.setBackground(new Color(255, 184, 77));
        signPanel2.add(btPow);
        btPow.setBackground(new Color(255, 184, 77));
        signPanel2.add(btDel);
        btDel.setBackground(new Color(230, 138, 0, 252));
        signPanel2.add(btClear);
        btClear.setBackground(new Color(230, 138, 0));
        signPanel2.add(btResult);
        btResult.setBackground(new Color(51, 204, 0));
        userPanel.add(numberPanel);
        userPanel.add(signPanel2);

        //setare culori panel-uri
        resultPanel.setBackground(new Color(0, 255, 24, 255));
        secondPanelPolynomial.setBackground(bgColor);
        firstPanelPolynomial.setBackground(bgColor);
        operationPanel.setBackground(bgColor);
        mainPanel.setBackground(bgColor);
        signPanel2.setBackground(new Color(155, 160, 230));
        numberPanel.setBackground(new Color(30, 20, 100));

        mainPanel.add(panelInput);
        mainPanel.add(resultPanel);
        mainPanel.add(operationPanel);
        mainPanel.add(userPanel);
        tabbedPane.add("New Calculus", mainPanel);
        tabbedPane.add("Info", infoPanel);


        this.setContentPane(tabbedPane);
        this.pack();
        this.setTitle("Polynomial Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //listener for "signs"
    public void addAddListener(ActionListener e) {
        this.btAdd.addActionListener(e);
    }
    public void addSubListener(ActionListener e) {
        this.btSub.addActionListener(e);
    }
    public void addMultiplyListener(ActionListener e) {
        this.btMultiply.addActionListener(e);
    }
    public void addXListener(ActionListener e) {
        this.btX.addActionListener(e);
    }
    public void addPowListener(ActionListener e) {
        this.btPow.addActionListener(e);
    }
    public void addDelListener(ActionListener e) {
        this.btDel.addActionListener(e);
    }
    public void addClearListener(ActionListener e) {
        this.btClear.addActionListener(e);
    }
    public void addEqualListener(ActionListener e) {
        this.btResult.addActionListener(e);
    }
    //listener for digits
    public void addOneListener(ActionListener e) {
        this.btOne.addActionListener(e);
    }
    public void addTwoListener(ActionListener e) {
        this.btTwo.addActionListener(e);
    }
    public void addThreeListener(ActionListener e) { this.btThree.addActionListener(e); }
    public void addFourListener(ActionListener e) { this.btFour.addActionListener(e); }
    public void addFiveListener(ActionListener e) { this.btFive.addActionListener(e); }
    public void addSixListener(ActionListener e) { this.btSix.addActionListener(e); }
    public void addSevenListener(ActionListener e) {
        this.btSeven.addActionListener(e);
    }
    public void addEightListener(ActionListener e) {
        this.btEight.addActionListener(e);
    }
    public void addNineListener(ActionListener e) {
        this.btNine.addActionListener(e);
    }
    public void addZeroListener(ActionListener e) {
        this.btZero.addActionListener(e);
    }

    //returneaza numarul butonului selectat pentru operatia pe polinoame
    public int isSelectedOperation() {
        int i = 0;
        for (Iterator<AbstractButton> buttons = groupOperation.getElements().asIterator(); buttons.hasNext(); ) {
            AbstractButton button = buttons.next();
            if (button.isSelected()) {
                return i;
            }
            i++;
        }
        return -1;
    }
    //returneaza in care casuta text se afla cursorul
    public int getTextFieldSelected() {
        if (firstPolynom.isFocusOwner()) {
            return 0;
        } else if (secondPolynom.isFocusOwner()) {
            return 1;
        } else
            return 2;
    }

    //returneaza pozitia cursorului in text field
    public int getPositionText() {
        if (firstPolynom.isFocusOwner())
            return firstPolynom.getCaretPosition();
        else if (secondPolynom.isFocusOwner()) {
            return secondPolynom.getCaretPosition();
        }
        return -1;
    }
    //seteaza pozitia cursorului in text field; folositor pentru stergere
    public void setPositionText(int caretPosition) {
        if (firstPolynom.isFocusOwner())
            firstPolynom.setCaretPosition(caretPosition);
        else if (secondPolynom.isFocusOwner()) {
            secondPolynom.setCaretPosition(caretPosition);
        }
    }
    //gettere si settere pentru inputul celor doua polinoame;
    public String getFirstPolInput() {
        return (String) firstPolynom.getText();
    }
    public void setFirstPolynom(String s) {
        firstPolynom.setText(s);
    }
    public String getSecondPolInput() {
        return (String) secondPolynom.getText();
    }
    public void setSecondPolynom(String s) {
        secondPolynom.setText(s);
    }

    //listener pentru modificari ce au loc in text field-uri; folositor pentru colorarea casutei
    public void addTextFieldsActionListener(CaretListener e) {
        this.firstPolynom.addCaretListener(e);
        this.secondPolynom.addCaretListener(e);
    }
    //pentru a colora casutele controller seteaza culoare pentru JTextField corespunzator
    public JTextField getTextFieldFirstPol() {
        return firstPolynom;
    }
    public JTextField getTextFieldSecondPol() {
        return secondPolynom;
    }
    public void addRadioButtonListener(ActionListener e){
        for (Iterator<AbstractButton> buttons = groupOperation.getElements().asIterator(); buttons.hasNext(); ) {
            AbstractButton button = buttons.next();
            button.addActionListener(e);
            }
    }

    //pentru selectia operatiei
    public void setColorOperation(){
        for (Iterator<AbstractButton> buttons = groupOperation.getElements().asIterator(); buttons.hasNext(); ) {
            AbstractButton button = buttons.next();
            if (button.isSelected()) {
                button.setForeground(new Color(246, 0, 8));
            }
            else{
                button.setForeground(Color.white);
            }
        }
    }
    //pentru a exlude sau include al doilea polinom in functie de operatie
    public void enableSecondPol(boolean choice){
        this.secondPolynom.setVisible(choice);
        this.secondPolynomLabel.setVisible(choice);
    }


    //metode pentru manipularea afisarii rezultatului/ rezultatelor
    public void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
    public void setResultLabel() {
        restLabel.setVisible(false);
        catLabel.setVisible(false);
        restText.setVisible(false);
        catText.setVisible(false);
        resultLabel.setVisible(true);
        resultField.setVisible(true);
        this.resultField.setText(model.getResult());
    }
    //pentru impartire unde este nevoie de doua casute pentru cat si rest
    public void setResult2Text() {
        restLabel.setVisible(true);
        catLabel.setVisible(true);
        restText.setVisible(true);
        catText.setVisible(true);
        resultLabel.setVisible(false);
        resultField.setVisible(false);
        restText.setText(model.getRest());
        catText.setText(model.getCat());
    }

    //metoda ce deazativeaza focus-ul pe un buton cand este apasat
    private void disableFocus(){
        //ca sa nu le scriu de 2 ori l-am adaugat intr-o lista de butoane
        ArrayList<AbstractButton> buttonsList = new ArrayList<>();
        buttonsList.add(btOne);buttonsList.add(btTwo);buttonsList.add(btThree);buttonsList.add(btFour);
        buttonsList.add(btFive);buttonsList.add(btSix);buttonsList.add(btSeven);buttonsList.add(btEight);
        buttonsList.add(btNine);buttonsList.add(btZero);buttonsList.add(btAdd);buttonsList.add(btSub);
        buttonsList.add(btMultiply);buttonsList.add(btX);buttonsList.add(btPow);buttonsList.add(btDel);
        buttonsList.add(btResult);buttonsList.add(btAdd2);buttonsList.add(btMultiply2);buttonsList.add(btDiv2);
        buttonsList.add(btIntegration2);buttonsList.add(btDerivate2);buttonsList.add(btDiv2);buttonsList.add(btSub2);
        buttonsList.add(btClear);
        for (AbstractButton e : buttonsList) {
            e.setFocusable(false);
            e.setFont(fDigit);
        }
    }
}
