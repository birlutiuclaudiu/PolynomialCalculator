package testOperations;

import modelClasses.ModelCalc;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {
    private ModelCalc model = new ModelCalc();

    @ParameterizedTest
    @MethodSource("provideInput")
    void testIntegrationOperation(String p1, String expectedResult) {
        model.patternMonomialList(p1, 1);
        model.integratePolynomial();
        assertEquals(expectedResult, model.getResult());
    }

    private static List<Arguments> provideInput() {
        List<Arguments> argumentsList = new ArrayList<>();
        argumentsList.add(Arguments.of("", "0"));
        argumentsList.add(Arguments.of("4", "+4X"));
        argumentsList.add(Arguments.of("2X", "+X^2"));
        argumentsList.add(Arguments.of("X", "+0.5X^2"));
        argumentsList.add(Arguments.of("2X^2", "+0.67X^3"));
        argumentsList.add(Arguments.of("-3X^3+5X", "-0.75X^4+2.5X^2"));
        argumentsList.add(Arguments.of("x^3+4x+5", "+0.25X^4+2X^2+5X"));
        argumentsList.add(Arguments.of("x^3+X^5-4*X^3", "+0.17X^6-0.75X^4"));
        argumentsList.add(Arguments.of("-2X^6+X^5+2X^4+X^3", "-0.29X^7+0.17X^6+0.4X^5+0.25X^4"));
        argumentsList.add(Arguments.of("X^2+3+4+5x-X^2", "+2.5X^2+7X"));
        argumentsList.add(Arguments.of("5x^5-2X^3", "+0.83X^6-0.5X^4"));
        argumentsList.add(Arguments.of("X^3+2X^5-X^4+X+100", "+0.33X^6-0.2X^5+0.25X^4+0.5X^2+100X"));
        argumentsList.add(Arguments.of("-2x^3+3*x^2-5x^4+3x+1+0", "-X^5-0.5X^4+X^3+1.5X^2+X"));
        return argumentsList;
    }

}
