package testOperations;

import modelClasses.ModelCalc;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubtractionTest {
    private ModelCalc model=new ModelCalc();

    @ParameterizedTest
    @MethodSource("provideInput")
    void testSubtractionOperation(String p1, String p2, String expectedResult){
        model.patternMonomialList(p1,1);
        model.patternMonomialList(p2,2);
        model.subPolynomial();
        assertEquals(expectedResult,model.getResult());
    }

    private static List<Arguments> provideInput(){
        List<Arguments> argumentsList=new ArrayList<>();
        argumentsList.add(Arguments.of("","x","-X"));
        argumentsList.add(Arguments.of("","","0"));
        argumentsList.add(Arguments.of("2","3","-1"));
        argumentsList.add(Arguments.of("2","-3","+5"));
        argumentsList.add(Arguments.of("3","X","-X+3"));
        argumentsList.add(Arguments.of("X^2+2X^3","X^3+x","+X^3+X^2-X"));
        argumentsList.add(Arguments.of("X^2","x^2+x-x","0"));
        argumentsList.add(Arguments.of("X^4","x^5-X^4","-X^5+2X^4"));
        argumentsList.add(Arguments.of("X-X^5","X^3+2X^5-X^4+X","-3X^5+X^4-X^3"));
        argumentsList.add(Arguments.of("X^4-2X^3+X^5","-X^5-X^4+2X^3","+2X^5+2X^4-4X^3"));
        argumentsList.add(Arguments.of("2x^3+3*x^2+5x^4+3x+1+0","5*x^2-3X^3+4X-4","+5X^4+5X^3-2X^2-X+5"));
        return  argumentsList;
    }
}
