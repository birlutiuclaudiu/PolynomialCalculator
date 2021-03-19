package testMonomialOperation;

import model.Monomial;
import model.MonomialInt;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonDerivativeTest {
    private static Double epsilon=0.00001;

    @ParameterizedTest
    @MethodSource("provideInput")
    void testDerivativeOperation(Monomial m1, int coef, int degree){
        Monomial result=m1.dervMonomial();
        Integer expected=(Integer)result.getCoefficient();
        assertTrue(coef==expected && degree==result.getDegree());

    }

    private static List<Arguments> provideInput(){
        List<Arguments> argumentsList=new ArrayList<>();
        argumentsList.add(Arguments.of(new MonomialInt(4,2),8,1));
        argumentsList.add(Arguments.of(new MonomialInt(-4,2),-8,1));
        argumentsList.add(Arguments.of(new MonomialInt(3,1),3,0));
        argumentsList.add(Arguments.of(new MonomialInt(5,0),0,0));
        argumentsList.add(Arguments.of(new MonomialInt(0,0),0,0));
        argumentsList.add(Arguments.of(new MonomialInt(-5,4),-20,3));
        return  argumentsList;
    }

}
