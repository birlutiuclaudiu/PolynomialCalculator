package controllerCalculator;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import viewCalculator.*;
import modelClasses.*;

//rol in gestiunea evenimentelor ce se petrec la nivelul interfeței grafice.
public class ControllerCalc {
    private View view;
    private ModelCalc model;
    private boolean enableOperation = false;

    public ControllerCalc(View view, ModelCalc model) {
        //setează view-ul și model-ul pe care le controlează
        this.view = view;
        this.model = model;

        //se adaugă listener-i pentru evenimentle din interfața grafică
        //se va feace apelul metodelor din view cu obiecte ce implementează intefata ActionListener
        //aceste obiecte sunt de tipul unei clase interne definte în cadrul clasei ControllerCalc
        view.addAddListener(new ButtonListener("+"));
        view.addSubListener(new ButtonListener("-"));
        view.addMultiplyListener(new ButtonListener("*"));
        view.addXListener(new ButtonListener("X"));
        view.addPowListener(new ButtonListener("^"));

        view.addDelListener(new DelListener());
        view.addClearListener(new ClearListener());
        view.addEqualListener(new EqualListener());

        view.addOneListener(new ButtonListener("1"));
        view.addTwoListener(new ButtonListener("2"));
        view.addThreeListener(new ButtonListener("3"));
        view.addFourListener(new ButtonListener("4"));
        view.addFiveListener(new ButtonListener("5"));
        view.addSixListener(new ButtonListener("6"));
        view.addSevenListener(new ButtonListener("7"));
        view.addEightListener(new ButtonListener("8"));
        view.addNineListener(new ButtonListener("9"));
        view.addZeroListener(new ButtonListener("0"));

        view.addTextFieldsActionListener(new TextFieldListener());
        view.addRadioButtonListener(new RadioButtonListener());
    }

    //preia textul din căsuța selectată, află poziția cursorului, inserează valoarea butonului
    // apăsat după cursor și setează poziția acestuia după caracterul inserat
    //cu substring se imparte tesxtul în doua pentru adauga noul caracter
    public class ButtonListener implements ActionListener {
        private String inputString;

        public ButtonListener(String s) {
            this.inputString = s;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String a = "";
            String b = "";
            if (view.getTextFieldSelected() == 0) {
                String s = view.getFirstPolInput();
                int caretPosition = view.getPositionText();
                if (s.length() > 0)
                    a = s.substring(0, caretPosition);
                if (s.length() > caretPosition)
                    b = s.substring(caretPosition);
                view.setFirstPolynom(a + inputString + b);
                view.setPositionText(caretPosition + 1);
            } else if (view.getTextFieldSelected() == 1) {
                String s = view.getSecondPolInput();
                int caretPosition = view.getPositionText();
                if (s.length() > 0)
                    a = s.substring(0, caretPosition);
                if (s.length() > caretPosition)
                    b = s.substring(caretPosition);
                view.setSecondPolynom(a + inputString + b);
                view.setPositionText(caretPosition + 1);
            } else {
                return;
            }
        }
    }

   //operația inversă celei descrise mai sus. se va sterge caracterul de dinainte cursorului
    public class DelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String a = "";
            String b = "";
            int caretPosition = view.getPositionText();
            if (view.getTextFieldSelected() == 0 && view.getFirstPolInput().length() > 0 && caretPosition > 0) {
                a = view.getFirstPolInput().substring(0, caretPosition - 1);
                b = view.getFirstPolInput().substring(caretPosition);
                view.setFirstPolynom(a + b);
                view.setPositionText(caretPosition - 1);
            } else if (view.getTextFieldSelected() == 1 && view.getSecondPolInput().length() > 0 && caretPosition > 0) {
                a = view.getSecondPolInput().substring(0, caretPosition - 1);
                b = view.getSecondPolInput().substring(caretPosition);
                view.setSecondPolynom(a + b);
                view.setPositionText(caretPosition - 1);
            }
        }
    }

    //seterge tot continutul din text-field-ul slelectat
    public class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getTextFieldSelected() == 0) {
                view.setFirstPolynom("");
            } else if (view.getTextFieldSelected() == 1) {
                view.setSecondPolynom("");
            }
        }
    }

    //decrie logica răspunsului la apăsare butonului de egal
    public class EqualListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!enableOperation) {
                view.showError("Date introduse invalide! Respectati indicatiile");
                return;
            }
            try {
                model.patternMonomialList(view.getFirstPolInput(), 1);
                model.patternMonomialList(view.getSecondPolInput(), 2);
            } catch (NumberFormatException exc) {
                view.showError(exc.getMessage());
                return;
            }
            switch (view.isSelectedOperation()){
                case 0:  model.addPolynomial(); break;
                case 1:  model.subPolynomial(); break;
                case 2:  model.multiplyPolynomial(); break;
                case 3:
                    try {
                    model.divPolynomial();
                    view.setResult2Text();
                    } catch (Exception exc) {
                    view.showError(exc.getMessage());
                    }
                    break;
                case 4:  model.dervPolynomial(); break;
                default:  model.integratePolynomial(); break;
            }
            if (view.isSelectedOperation() != 3)
                view.setResultLabel();
        }
    }






    //pentru corectitudine input;
    // text fieldul corespuzator va fi colorat verde sau rosu in functie de corectitudine
    // în stringul folosit pentru pattern am încercat să includ cat mai multe cazuri neacceptate
    //se setează și enableOperation pentru a anula efectuarea operațiilor când se apasă butonul de egal
    public class TextFieldListener implements CaretListener {
        @Override
        public void caretUpdate(CaretEvent e) {
            Pattern pattern = Pattern.compile("([^Xx\\-+\\*\\^\\d]|(?:[+\\-\\^][+\\-\\*\\^])|(?:[+-\\^][0]+[1-9])" +
                    "|(?:[Xx][\\*\\d])|(?:\\d\\*\\d)|(?:\\d\\^)|(?:\\^\\d\\*)|(?:[xX][Xx])|(?:\\^\\d+[Xx])|(?:[Xx]\\^[Xx]))");
            Matcher matcher = pattern.matcher(view.getFirstPolInput());
            boolean a, b;
            if (matcher.find()) {
                view.getTextFieldFirstPol().setBackground(new Color(246, 71, 71));
                a = false;
            } else {
                view.getTextFieldFirstPol().setBackground(new Color(152, 253, 152, 255));
                a = true;
            }
            Matcher matcherSecond = pattern.matcher(view.getSecondPolInput());
            if (matcherSecond.find()) {
                view.getTextFieldSecondPol().setBackground(new Color(246, 71, 71));
                b = false;
            } else {
                view.getTextFieldSecondPol().setBackground(new Color(152, 251, 152));
                b = true;
            }

            enableOperation= a && b;
        }
    }

    //listener pentru butoanele JRadio pentru selecția operației de executa
    //Daca operație e derivare sau integrare, text-boxul celui de-al doilea polinom va dispăerea
    //se va colora textul operașiei selectate cu roșu
    public class RadioButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(view.isSelectedOperation()==4 || view.isSelectedOperation()==5){
                view.setSecondPolynom("");
                view.enableSecondPol(false);
            }else{
                view.enableSecondPol(true);
            }
            view.setColorOperation();
        }
    }
}


