package pt2021_30226_birlutiu_claudiu_assignment_1;

import controllerCalculator.*;
import viewCalculator.*;
import modelClasses.*;

public class PolynomialCalculator {

    public static void main(String[] args){
        ModelCalc model=new ModelCalc();
        View view=new View(model);
        ControllerCalc controller=new ControllerCalc(view,model);
        view.setVisible(true);
    }
}