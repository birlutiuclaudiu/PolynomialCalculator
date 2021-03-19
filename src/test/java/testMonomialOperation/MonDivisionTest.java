package testMonomialOperation;

import model.Monomial;
import model.MonomialDouble;
import model.MonomialInt;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonDivisionTest {

    private static Double epsilon=0.01;

    @ParameterizedTest
    @MethodSource("provideInput")
    void testDivisionOperation(Monomial m1, Monomial m2, Number coef, Integer degree){
        try{
            Monomial result=m1.divMonomial(m2);
            if( result instanceof MonomialInt){
                Integer expected=(Integer)result.getCoefficient();
                assertTrue((int)coef==expected && degree==result.getDegree());
            }else if(result instanceof MonomialDouble){
                Double expected=(Double)result.getCoefficient();
                assertTrue(Math.abs((Double)coef-expected)<=epsilon && degree==result.getDegree());
            }
        }catch(ArithmeticException exc){
             assertTrue(1==1);
        }

    }
    private static List<Arguments> provideInput() {
        List<Arguments> argumentsList = new ArrayList<>();
        argumentsList.add(Arguments.of(new MonomialInt(4, 2), new MonomialInt(5, 2), 0.8, 0));
        argumentsList.add(Arguments.of(new MonomialInt(-4, 2), new MonomialInt(4, 2), -1, 0));
        argumentsList.add(Arguments.of(new MonomialInt(1, 10), new MonomialInt(-3, 2), -0.33, 8));
        argumentsList.add(Arguments.of(new MonomialInt(0, 5), new MonomialInt(5, 2), 0, 0));
        argumentsList.add(Arguments.of(new MonomialInt(0, 0), new MonomialInt(0, 1), 0, 0));
        argumentsList.add(Arguments.of(new MonomialInt(5, 0), new MonomialInt(8, 0), 0.63, 0));
        return argumentsList;
    }
}
