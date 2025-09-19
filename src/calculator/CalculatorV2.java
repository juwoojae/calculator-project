package calculator;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * CalculatorV1 에서 제네릭을 이용한 version + 추가메서드 구현
 * 도전과제 추가내용
 */
public class CalculatorV2 {
    /**
     * 컬렉션 필드에 직접 접근하지 못하도록 수정 (캡슐화)
     */
    private final static Queue<Number> resultQueue = new LinkedList<>();

    /**
     * 메서드 오버로딩 (제곱근 연산의 경우)
     *
     * @param num1      : 파라메터가 1개만 필요
     * @param opperator : 연산자 문자열
     * @param <T>       Number 의 자식 (래퍼클래스) 를 타입변수로 받고
     *         Integer (op)= Integer 반환
     *        Double (op)  = Double 반환
     */
    public <T extends Number> Number calculate(T num1, String opperator) {
        double result = Math.sqrt(num1.doubleValue());     //double > int 이므로 double 로 저장후 형변환
        if (num1 instanceof Integer)
            return (int) result;
        else {
            return  fommating(result);
        }
    }
    /**
     * 메서드 오버로딩 (제곱근 연산 이외의 경우)
     *
     * @param num1, num2      : 파라메터가 2 개 필요
     * @param opperator : 연산자 문자열
     * @param <T>       Number 의 자식 (래퍼클래스) 를 타입변수로 받고
     *        Integer (op) Integer = Integer 반환
     *        Integer (op) Double = Double 반환
     *        Double (op) Double = Double 반환
     *        예외 케이스 / 연산의 경우
     *        Integer (op) Integer = Double 이 되는 케이스가 있음
     *        mod 연산을 이용해서 형변환을 할지말지 결정
     */
    public <T extends Number> Number calculate(T num1, T num2, String opperator) {
        double result;
        if (opperator.equals(Operator.PLUS.getSymbol())) {
            result = num1.doubleValue() + num2.doubleValue();
            if (num1 instanceof Integer && num2 instanceof Integer) //T 가 Integer인 경우 형변환후 리턴
                return (int) result;
            else { //T 가 Double 형인 경우 그대로 리턴
                return  fommating(result);
            }
        } else if (opperator.equals(Operator.MINUS.getSymbol())) {
            result = num1.doubleValue() - num2.doubleValue();
            if (num1 instanceof Integer && num2 instanceof Integer)
                return (int) result;
            else {
                return  fommating(result);
            }
        } else if (opperator.equals(Operator.MULTIPLY.getSymbol())) {
            result = num1.doubleValue() * num2.doubleValue();
            if (num1 instanceof Integer && num2 instanceof Integer)
                return (int) result;
            else {
                return  fommating(result);
            }
        }else if (opperator.equals(Operator.DIVIDE.getSymbol())) {
            result = num1.doubleValue() / num2.doubleValue();
            if (num1 instanceof Integer && num2 instanceof Integer)
                // Integer (op) Integer = Double 이 되는 케이스가 있음 mod 연산을 이용해서 형변환을 할지말지 결정
                if(num1.doubleValue() % num2.doubleValue()>0){ //num1%num2>0 이라면 나누어 떨어지지 않으므로 Doble 형 그대로 리턴
                    return  fommating(result);
                }else { //나누어 떨어지는 경우에는 int 로 형변환
                    return (int) result;
                }
            else {
                return  fommating(result);
            }
        }
        else if (opperator.equals(Operator.MODULER.getSymbol())){
            result = num1.doubleValue() % num2.doubleValue();
            if (num1 instanceof Integer && num2 instanceof Integer)
                return (int) result;
            else {
                return  fommating(result);
            }
        } else {  // (opperator.equals(Operator.POW.getSymbol()))
            result = Math.pow(num1.doubleValue(), num2.doubleValue());
        if (num1 instanceof Integer && num2 instanceof Integer)
            return (int) result;
        else {
            return fommating(result);
        }
    }

    }

    /**
     * Calculator 클래스에 저장된 연산 결과들 중 가장 먼저 저장된 데이터를 삭제하는 기능을 가진 메서드를 구현
     *
     * @return : 삭제된 값 & 먼저 계산된 연산
     */
    public Number remove() {
        return resultQueue.poll();
    }

    /**
     * resultQueue 에 연산한 값을 저장하는 용도 메서드
     *
     * @return : 저장된 값
     */
    public Number save(Number value) {
        resultQueue.offer(value);
        return value;
    }

    /**
     * resultQueue 를 얕은 복사로 반환하는 메서드 -> 외부에서 변경해주는것을 방지
     */
    public Queue<Number> getResultQueueInstance() {
        return new LinkedList<>(resultQueue);  //얕은 복사
    }

    /**
     * 포멧팅 메서드
     * Number 를 매개변수로 받아서 포멧팅후 다시 Number 형으로 리턴
     */
    public Number fommating(Number value){
        return Double.parseDouble(String.format("%.2f", value.doubleValue()));
    }
    /**
     *  도전과제
     *  stream 과 lambda를 활용해서
     *  저장된 연산 결과들 중에서 compare 보다 큰 결과 값들을 필터링하는 메서드
     */
    public List<Number> ConversionResultQueue(Number compare){
        List<Number> conversion = getResultQueueInstance().stream()
                .filter(num -> num.doubleValue()>compare.doubleValue())
                .collect(Collectors.toList());
        return conversion;
    }
}

