import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Positive;
import net.jqwik.api.lifecycle.BeforeTry;
import org.dlpk.Exercicio2.MathFunctions;
import org.dlpk.Exercicio2.MathLogger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static net.jqwik.api.state.Action.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class MathFunctionsPropertyTest {

    MathLogger logger;
    MathFunctions mathFunctions;

    @BeforeTry
    void mockOrderService() {
        logger = mock(MathLogger.class);
        doNothing().when(logger).log(anyString(), any());
        mathFunctions = new MathFunctions(logger);
    }

    @Property
    void multiplyByTwoSemprePar(@ForAll int num) {
        int result = mathFunctions.MultiplyByTwo(num);
        assertEquals(num * 2, result);
    }

    @Property
    void generateMultiplicationTableMultiplos(@ForAll("zeroExclusiveInt") int num,
                                              @ForAll @IntRange(min=1, max=100) int limit) {
        int[] multiples = mathFunctions.GenerateMultiplicationTable(num, limit);
        for (int mult : multiples) {
            assertEquals(mult % num, 0);
        }

    }

    @Property
    void isPrime(@ForAll @IntRange(min=-1024, max=1024) int num) {
        if (mathFunctions.IsPrime(num)) {
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (i < -1 || i > 1 && Math.abs( i ) != num)
                    assertNotEquals(0, num % i);
            }
        }

    }

    @Property
    void calcAverage(@ForAll("intArrProvider") Integer[] nums) {
        //https://stackoverflow.com/a/23945015 - mapear para int
        int[] nums_correto = Arrays.stream(nums).mapToInt(i->i).toArray();
        double avg = mathFunctions.CalculateAverage(nums_correto);
        assertTrue( avg < nums[nums.length-1] && avg > nums[0] );
    }

    @Provide
    Arbitrary<Integer> zeroExclusiveInt() {
        return Arbitraries.integers().between(-1000, 1000)
                .filter(i -> i != 0);
    }

    @Provide
    Arbitrary<Integer[]> intArrProvider() {
        //https://stackoverflow.com/a/60024478
        Arbitrary<Integer> integerArbitrary = Arbitraries.integers().between(-1000, 1000);
        return integerArbitrary
                .array(Integer[].class).ofMinSize(2).ofMaxSize(400)
                .map(anArray -> {
                    Arrays.sort(anArray);
                    return anArray;});
    }


}
