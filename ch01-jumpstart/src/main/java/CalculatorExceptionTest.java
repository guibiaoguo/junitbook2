/**
 * Created by shentong on 2016/12/20.
 */
public class CalculatorExceptionTest {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        double result = calculator.add(10,50);
        if(result != 60) {
            System.out.println("Bad result: " + result);
        }
    }

    public void testAdd() {
        Calculator calculator = new Calculator();
        double result = calculator.add(10,50);
        if (result != 60) {
//            throw new llegalStateException("Bad result: " + result);
        }
    }
}
