package testOperations;

import modelClasses.ModelCalc;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiplicationTest {
    private ModelCalc model=new ModelCalc();

    @ParameterizedTest
    @MethodSource("provideInput")
    void testMultiplicationOperation(String p1, String p2, String expectedResult){
        model.patternMonomialList(p1,1);
        model.patternMonomialList(p2,2);
        model.multiplyPolynomial();
        assertEquals(expectedResult,model.getResult());
    }

    private static List<Arguments> provideInput(){
        List<Arguments> argumentsList=new ArrayList<>();
        argumentsList.add(Arguments.of("","x","0"));
        argumentsList.add(Arguments.of("","","0"));
        argumentsList.add(Arguments.of("2","3","+6"));
        argumentsList.add(Arguments.of("2","-3","-6"));
        argumentsList.add(Arguments.of("3","X","+3X"));
        argumentsList.add(Arguments.of("x^3+4x+5","", "0"));
        argumentsList.add(Arguments.of("1","x^3+X^5+4*X^3", "+X^5+5X^3"));
        argumentsList.add(Arguments.of("X^2+2X^3","X^3+x","+2X^6+X^5+2X^4+X^3"));
        argumentsList.add(Arguments.of("X^2","x^2+x-x","+X^4"));
        argumentsList.add(Arguments.of("2X^4+X","5x^5-2X^3","+10X^9-4X^7+5X^6-2X^4"));
        argumentsList.add(Arguments.of("X-X^5","X^3+2X^5-X^4+X","-2X^10+X^9-X^8+X^6-X^5+X^4+X^2"));
        argumentsList.add(Arguments.of("-2x^3+3*x^2-5x^4+3x+1+0","5*x^2-3X^3+4X-4","+15X^7-19X^6-39X^5+18X^4+32X^3"+
                                                                         "+5X^2-8X-4"));
        return  argumentsList;
    }
}
