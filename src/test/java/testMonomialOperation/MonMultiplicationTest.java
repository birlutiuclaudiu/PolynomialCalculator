package testMonomialOperation;

import model.Monomial;
import model.MonomialDouble;
import model.MonomialInt;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonMultiplicationTest {
    private static Double epsilon=0.00001;

    @ParameterizedTest
    @MethodSource("provideInput")
    void testAdditionOperation(Monomial m1, Monomial m2, int coef, int degree){
    Monomial result=m1.mulMonomial(m2);
        if( result instanceof MonomialInt){
        Integer expected=(Integer)result.getCoefficient();
        assertTrue(coef==expected && degree==result.getDegree());
    }else if(result instanceof MonomialDouble){
        Double expected=(Double)result.getCoefficient();
        assertTrue(Math.abs(coef-expected)<=epsilon && degree==result.getDegree());
    }
}

    private static List<Arguments> provideInput() {
        List<Arguments> argumentsList = new ArrayList<>();
        argumentsList.add(Arguments.of(new MonomialInt(4, 2), new MonomialInt(5, 2), 20, 4));
        argumentsList.add(Arguments.of(new MonomialInt(-4, 2), new MonomialInt(4, 2), -16, 4));
        argumentsList.add(Arguments.of(new MonomialInt(1, 2), new MonomialInt(-3, 2), -3, 4));
        argumentsList.add(Arguments.of(new MonomialInt(0, 2), new MonomialInt(5, 2), 0, 0));
        argumentsList.add(Arguments.of(new MonomialInt(0, 0), new MonomialInt(0, 1), 0, 0));
        argumentsList.add(Arguments.of(new MonomialInt(5, 0), new MonomialInt(8, 0), 40, 0));
        return argumentsList;
    }
}
