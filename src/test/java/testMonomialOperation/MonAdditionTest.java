package testMonomialOperation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import model.Monomial;
import model.MonomialInt;
import model.MonomialDouble;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonAdditionTest {
    private static Double epsilon=0.00001;

    @ParameterizedTest
    @MethodSource("provideInput")
    void testAdditionOperation(Monomial m1, Monomial m2, Number coef, int degree){
        Monomial result=m1.addMonomial(m2);
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
        argumentsList.add(Arguments.of(new MonomialInt(4,2),new MonomialInt(5,2),9,2));
        argumentsList.add(Arguments.of(new MonomialInt(-4,2),new MonomialInt(4,2),0,0));
        argumentsList.add(Arguments.of(new MonomialInt(1,2),new MonomialInt(-3,2),-2,2));
        argumentsList.add(Arguments.of(new MonomialInt(0,2),new MonomialInt(5,2),5,2));
        argumentsList.add(Arguments.of(new MonomialInt(0,0),new MonomialInt(0,1),0,0));
        argumentsList.add(Arguments.of(new MonomialDouble(5.3,4),
                                                  new MonomialDouble(2.4,4),7.7,4));
        return  argumentsList;
    }

}
