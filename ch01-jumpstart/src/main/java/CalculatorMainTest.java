/**
 * Created by shentong on 2016/12/20.
 */
public class CalculatorMainTest {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        double result = calculator.add(10,50);
        if(result != 60) {
            System.out.println("Bad result: " + result)
            ;
        }
    }
}
