package testMonomialOperation;

import modelClasses.Monomial;
import modelClasses.MonomialDouble;
import modelClasses.MonomialInt;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonIntegrateTest {
    private static Double epsilon=0.01;

    @ParameterizedTest
    @MethodSource("provideInput")
    void testIntegrationOperation(Monomial m1, Number coef, int degree){
        Monomial result=m1.integrateMonomial();
        if( result instanceof MonomialInt){
            Integer expected=(Integer)result.getCoefficient();
            assertTrue((int)coef==expected && degree==result.getDegree());
        }else if(result instanceof MonomialDouble){
            Double expected=(Double)result.getCoefficient();
            assertTrue(Math.abs((double)coef-expected)<=epsilon && degree==result.getDegree());
        }
    }

    private static List<Arguments> provideInput(){
        List<Arguments> argumentsList=new ArrayList<>();
        argumentsList.add(Arguments.of(new MonomialInt(4,2),1.33,3));
        argumentsList.add(Arguments.of(new MonomialInt(-4,2),-1.33,3));
        argumentsList.add(Arguments.of(new MonomialInt(3,2),1,3));
        argumentsList.add(Arguments.of(new MonomialInt(0,2),0,0));
        argumentsList.add(Arguments.of(new MonomialInt(0,0),0,0));
        argumentsList.add(Arguments.of(new MonomialInt(-5,4),-1,5));
        return  argumentsList;
    }
}
