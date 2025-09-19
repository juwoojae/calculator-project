import calculator.Calculator;

import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        double sqrt = calculator.calculate(4, "sqrt");
        calculator.save(sqrt);
        double plus = calculator.calculate(1, 9, "+");
        calculator.save(plus);
        Queue<Double> resultQueueInstance = calculator.getResultQueueInstance();
        System.out.println("resultQueueInstance = " + resultQueueInstance);

        System.out.println(calculator.calculate(2,1,"-"));
        System.out.println(calculator.calculate(10,3,"*"));
        System.out.println(calculator.calculate(10,2,"/"));
        System.out.println(calculator.calculate(10,6,"%"));
        System.out.println(calculator.calculate(10,2,"^"));
        calculator.remove();
        resultQueueInstance = calculator.getResultQueueInstance();
        System.out.println("resultQueueInstance = " + resultQueueInstance);
    }
}
