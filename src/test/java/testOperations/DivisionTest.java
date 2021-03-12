package testOperations;

import modelClasses.ModelCalc;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DivisionTest {
    private ModelCalc model=new ModelCalc();

    @ParameterizedTest
    @MethodSource("provideInput")
    void testDivisionOperation(String p1, String p2, String expectedCat, String expectedRest){
        model.patternMonomialList(p1,1);
        model.patternMonomialList(p2,2);
        try {
            model.divPolynomial();
            assertEquals(expectedCat,model.getCat());
            assertEquals(expectedRest,model.getRest());
        }catch (Exception exc){
            assertEquals("Impartirea la 0 nu e posibila",exc.getMessage());
        }

    }

    private static List<Arguments> provideInput(){
        List<Arguments> argumentsList=new ArrayList<>();
        argumentsList.add(Arguments.of("","x","0","0"));
        argumentsList.add(Arguments.of("","","",""));
        argumentsList.add(Arguments.of("X^3+X^4","","",""));
        argumentsList.add(Arguments.of("2","3","+0.67","0"));
        argumentsList.add(Arguments.of("2","-3","-0.67", "0"));
        argumentsList.add(Arguments.of("3","X","0","+3"));
        argumentsList.add(Arguments.of("x^3+X^5+4*X^3","1", "+X^5+5X^3","0"));
        argumentsList.add(Arguments.of("X^2+2X^3","X^3+x","+2","+X^2-2X"));
        argumentsList.add(Arguments.of("X^2","x^2+x-x","+1","0"));
        argumentsList.add(Arguments.of("5X^4-X","5x^3-2X^3","+1.67X","-X"));
        argumentsList.add(Arguments.of("X-X^6","X^3+2X^5-X^4+X","-0.5X-0.25","+0.25X^4+0.25X^3+0.5X^2+1.25X"));
        argumentsList.add(Arguments.of("X^8","-X+X^2+1","+X^6+X^5-X^3-X^2+1","+X-1"));
        return  argumentsList;
    }
}
