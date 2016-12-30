import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by shentong on 2016/12/20.
 */
@RunWith(value = Parameterized.class)
public class ParameterizedTest {

    private double expected;
    private double valueOne;
    private double valueTwo;

    @Parameterized.Parameters
    public static Collection<Integer[]> getTestParameters() {
        Collection c = Arrays.asList(new Integer[][]{ {2,1,1},
                {3,2,1},
                {5,4,1},
                {4,3,1}});
        return c;
    }

    public ParameterizedTest(double expected,double valueOne,double valueTwo) {
        this.expected = expected;
        this.valueOne = valueOne;
        this.valueTwo = valueTwo;
    }

    @Test
    public void sum() {
        Calculator calculator  = new Calculator();
        assertEquals(expected,calculator.add(valueOne,valueTwo),0);
    }
}