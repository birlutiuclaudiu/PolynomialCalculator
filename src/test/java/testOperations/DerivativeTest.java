package testOperations;

import modelClasses.ModelCalc;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DerivativeTest {
    private ModelCalc model=new ModelCalc();

    @ParameterizedTest
    @MethodSource("provideInput")
    void testDerivativeOperation(String p1, String expectedResult){
        model.patternMonomialList(p1,1);
        model.dervPolynomial();
        assertEquals(expectedResult,model.getResult());
    }
    private static List<Arguments> provideInput(){
        List<Arguments> argumentsList=new ArrayList<>();
        argumentsList.add(Arguments.of("","0"));
        argumentsList.add(Arguments.of("4","0"));
        argumentsList.add(Arguments.of("2X","+2"));
        argumentsList.add(Arguments.of("X","+1"));
        argumentsList.add(Arguments.of("2X^2","+4X"));
        argumentsList.add(Arguments.of("-3X^3+5X","-9X^2+5"));
        argumentsList.add(Arguments.of("x^3+4x+5","+3X^2+4"));
        argumentsList.add(Arguments.of("x^3+X^5-4*X^3", "+5X^4-9X^2"));
        argumentsList.add(Arguments.of("-2X^6+X^5+2X^4+X^3","-12X^5+5X^4+8X^3+3X^2"));
        argumentsList.add(Arguments.of("X^2+3+4+5x-X^2","+5"));
        argumentsList.add(Arguments.of("5x^5-2X^3","+25X^4-6X^2"));
        argumentsList.add(Arguments.of("X^3+2X^5-X^4+X+100","+10X^4-4X^3+3X^2+1"));
        argumentsList.add(Arguments.of("-2x^3+3*x^2-5x^4+3x+1+0","-20X^3-6X^2+6X+3"));
        return  argumentsList;
    }

}
