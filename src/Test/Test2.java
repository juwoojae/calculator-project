package Test;

import calculator.CalculatorV2;

import java.util.Queue;

public class Test2 {
    public static void main(String[] args) {
        CalculatorV2 calculatorV2 = new CalculatorV2();
        Number sqrt = calculatorV2.calculate(4, "sqrt");
        Number plus = calculatorV2.calculate(1, 9, "+");

        calculatorV2.save(sqrt);
        calculatorV2.save(plus);

        Queue<Number> resultQueueInstance = calculatorV2.getResultQueueInstance();
        System.out.println("resultQueueInstance = " + resultQueueInstance);

        Number minus = calculatorV2.calculate(2.1, 1, "-");
        Number multimply = calculatorV2.calculate(10.1, 3, "*");
        //예외 케이스 Integer / Integer 해서 double 이 생기는 경우가 있을수 있다
        //나누어 떨어지지 않는경우
        Number division = calculatorV2.calculate(10, 4, "/");
        //나누어 떨어지는 경우
        division = calculatorV2.calculate(10, 2, "/");
        Number mod = calculatorV2.calculate(10, 4, "%");
        Number pow = calculatorV2.calculate(10, 2, "^");
        System.out.println("minus = " + minus);
        System.out.println("multimply = " + multimply);
        System.out.println("division = " + division);
        System.out.println("mod = " + mod);
        System.out.println("pow = " + pow);

        calculatorV2.save(minus);
        calculatorV2.save(multimply);
        calculatorV2.save(division);
        calculatorV2.save(mod);
        calculatorV2.save(pow);
        // calculator.remove();
        resultQueueInstance = calculatorV2.getResultQueueInstance();
        System.out.println("resultQueueInstance = " + resultQueueInstance);
    }
}
