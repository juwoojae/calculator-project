package calculator;

import java.util.LinkedList;
import java.util.Queue;
public class Calculator {
    /**
     * 컬렉션 필드에 직접 접근하지 못하도록 수정 (캡슐화)
     */
    private final static Queue<Double> resultQueue = new LinkedList<>();

    public double calculate(double num1, String opperator) {
        return Math.sqrt(num1);
    }

    public  double calculate(double num1,double num2, String opperator) {
        if (opperator.equals(Operator.PLUS.getSymbol()))
            return num1 + num2;
        else if (opperator.equals(Operator.MINUS.getSymbol()))
            return num1 - num2;
        else if (opperator.equals(Operator.MULTIPLY.getSymbol()))
            return num1 * num2;
        else if (opperator.equals(Operator.DIVIDE.getSymbol()))
            return num1 / num2;
        else if (opperator.equals(Operator.MODULER.getSymbol()))
            return num1 % num2;
        else // (opperator.equals(Operator.POW.getSymbol()))
            return Math.pow(num1, num2);
    }

    /**
     * Calculator 클래스에 저장된 연산 결과들 중 가장 먼저 저장된 데이터를 삭제하는 기능을 가진 메서드를 구현
     * @return : 삭제된 값 & 먼저 계산된 연산
     */
    public double remove() {
        return resultQueue.poll();
    }

    /**
     * resultQueue 에 연산한 값을 저장하는 용도 메서드
     * @return : 저장된 값
     */
    public double save(double value) {
        resultQueue.offer(value);
        return value;
    }

    /**
     * resultQueue 를 얕은 복사로 반환하는 메서드 -> 외부에서 변경해주는것을 방지
     */
    public Queue<Double> getResultQueueInstance() {
        return new LinkedList<>(resultQueue);  //얕은 복사
    }
}

