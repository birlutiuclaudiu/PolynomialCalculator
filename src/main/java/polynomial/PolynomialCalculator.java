//Birlutiu Claudiu-Andrei
package polynomial;
import controller.ControllerCalc;
import view.*;
import model.*;

public class PolynomialCalculator {

    public static void main(String[] args){
        ModelCalc model=new ModelCalc();
        View view=new View(model);
        ControllerCalc controller=new ControllerCalc(view,model);
        view.setVisible(true);
    }
}