import calculator.CalculatorV1;
import calculator.CalculatorV2;
import exception.InvalidateDivisionException;
import exception.InvalidateOperatorException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class App {

    static BufferedReader bufferedReader;
    static CalculatorV2 calculator;

    static {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        calculator = new CalculatorV2();
    }

    static void menu() throws IOException {
        System.out.println("=== 계산기 메뉴 ===");
        System.out.println("1. 계산하기");
        System.out.println("2. 계산 이력 보기");
        System.out.println("3. 이력 지우기");
        System.out.println("0. 종료");
        System.out.printf("선택: ");
        int cmd = Integer.parseInt(bufferedReader.readLine());//명령어 를 저장하는 변수
        if (cmd == 1) {  //1.계산하기

            Number num1;//첫 번째 숫자.
            Number num2;//두 번째 숫자.
            String operator; //연산자 +, -, *, /,
            Number result;//결과

            System.out.println("=== Java 계산기 ===");
            System.out.printf("첫 번째 숫자를 입력 하세요: ");
            while (true) {   //숫자가 아닌값을 입력 받았을때의 예외처리
                try {
                    num1 = inputNumber();
                    break; //숫자를 제대로 입력 받았다면
                } catch (NumberFormatException e) {
                    System.out.printf("숫자를 입력해주세요 : ");
                }
            }
            System.out.printf("연산자를 입력하세요 (+, -, *, /, %%, ^, sqrt): ");
            while (true) {   //연산자가 아닌값을 입력 받았을때의 예외처리
                try {
                    operator = bufferedReader.readLine();
                    validateOperator(operator);
                    break; //연산자 입력이 잘되었다면
                } catch (InvalidateOperatorException e) {
                    System.out.printf("지원하지 않는 연산자입니다\n");
                    System.out.printf("연산자를 입력하세요 (+, -, *, /, %%, ^, sqrt): ");
                }
            }
            if (operator.equals("sqrt")) {   //sqrt 연산 : 매개변수가 1개만 필요한경우
                result = calculator.calculate(num1, operator);
                calculator.save(result);// Calculator 컬렉션 에 결과 저장
                System.out.printf("결과: %s %s = %s\n", String.valueOf(num1), operator, String.valueOf(result));
            } else {     //그 이외의 연산 : 매개변수가 2개 필요한 경우 +, -, *, /, %, ^,
                System.out.printf("두 번째 숫자를 입력 하세요: ");
                while (true) {
                    try {
                        num2 = inputNumber();
                        if (operator.equals("/")) {
                            validateDivisionNum(num2);
                        }
                        break;
                    } catch (NumberFormatException e) {//1.숫자가 아닌값을 입력 받았을때의 예외처리
                        System.out.printf("숫자를 입력해주세요 : ");
                    } catch (InvalidateDivisionException e) {//2.나누기 연산인 경우 입력값이 0일경우에 예외처리
                        System.out.printf("0으로 나눌 수 없습니다\n");
                        System.out.printf("숫자를 입력해주세요 : ");
                    }
                }
                result = calculator.calculate(num1, num2, operator);
                calculator.save(result);// Calculator 컬렉션 에 결과 저장
                System.out.printf("결과: %s %s %s = %s\n",String.valueOf(num1), operator, String.valueOf(num2), String.valueOf(result));
            }
            //Step 2: 연산 로직 구현
            while (true) {
                System.out.printf("이전 결과(%s) 를 사용하시겠습니까? (y/n): ", String.valueOf(result));
                String sw = bufferedReader.readLine();
                if (sw.equals("n")) {
                    System.out.println("계산기를 종료합니다.");
                    break;
                } else {
                    //계속 연산
                    num1 = result;
                    System.out.printf("연산자를 입력하세요 (+, -, *, /, %%, ^, sqrt): ");
                    while (true) {   //숫자가 아닌값을 입력 받았을때의 예외처리
                        try {
                            operator = bufferedReader.readLine();
                            validateOperator(operator);
                            break; //숫자를 제대로 입력 받았다면
                        } catch (InvalidateOperatorException e) {
                            System.out.printf("지원하지 않는 연산자입니다\n");
                            System.out.printf("연산자를 입력하세요 (+, -, *, /, %%, ^, sqrt): ");
                        }
                    }
                    if (operator.equals("sqrt")) {   //sqrt 연산 : 매개변수가 1개만 필요한경우
                        result = calculator.calculate(num1, operator);
                        calculator.save(result);// Calculator 컬렉션 에 결과 저장
                        System.out.printf("결과: %s %s = %s\n", String.valueOf(num1), operator, String.valueOf(result));
                    } else {    //그 이외의 연산 : 매개변수가 2개 필요한 경우 +, -, *, /, %, ^,
                        System.out.printf("숫자를 입력 하세요: ");
                        while (true) {
                            try {
                                num2 = inputNumber();
                                if (operator.equals("/")) {
                                    validateDivisionNum(num2);
                                }
                                break;
                            } catch (NumberFormatException e) { //숫자가 아닌값을 입력 받았을때의 예외처리
                                System.out.printf("숫자를 입력해주세요 : ");
                            } catch (InvalidateDivisionException e) { //2.나누기 연산인 경우 입력값이 0일경우에 예외처리
                                System.out.printf("0으로 나눌 수 없습니다\n");
                                System.out.printf("숫자를 입력해주세요 : ");
                            }
                        }
                        result = calculator.calculate(num1, num2, operator);
                        calculator.save(result);// Calculator 컬렉션 에 결과 저장
                        System.out.printf("결과: %s %s %s = %s\n",String.valueOf(num1), operator, String.valueOf(num2), String.valueOf(result));
                    }
                }
            }
        } else if (cmd == 2) {  //2. 계산 이력 보기
            System.out.printf("=== <계산 이력> ===\n");
            Queue<Number> instance = calculator.getResultQueueInstance();
            int count = 1;
            for (Number v : instance) {
                System.out.printf("%d 번째 계산 결과 =  %f", count++,v );
            }
        } else if (cmd == 3) {  //3. 이력 지우기
            Number remove = calculator.remove();
            System.out.println("sucessful!");
        }else { //0. 종료
            System.out.println("=== 계산기를 종료합니다 ===");
            System.exit(1);
        }
    }

    /**
     * 연산자 검증 메서드
     * @param operator : 연산자 문자열
     * @throws InvalidateOperatorException operator 입력이 +, -, *, /, %%, ^, sqrt 가 아니라면 연산자가 아니므로 예외를 던짐
     */
    public static void validateOperator(String operator) throws InvalidateOperatorException {
        if (!(operator.equals("+") || operator.equals("-") ||
                operator.equals("*") || operator.equals("/") ||
                operator.equals("%") || operator.equals("^") ||
                operator.equals("sqrt"))
        ) {
            throw new InvalidateOperatorException("잘못된 연산자입니다: " + operator);
        }
    }
    /**
     * == ArithmeticException
     * @param divsionNum 분모에 들어오는 숫자
     * @throws InvalidateDivisionException 0이라면 예외를 던짐
     */
    public static void validateDivisionNum(Number divsionNum) throws InvalidateDivisionException {
        if (divsionNum.intValue() == 0) {
            throw new InvalidateDivisionException("잘못된 입력입니다: " + divsionNum);
        }
    }

    /**
     * 입력 함수.
     * Double 형과 Integer 형을 둘다 입력받은후
     * @return Number 형으로 리턴
     * @throws IOException
     */
    public static Number inputNumber() throws IOException {
        String input= bufferedReader.readLine();
        if (input.contains(".") || input.contains("e") || input.contains("E")) {
            // 소수점이 있으면 Double
             return Double.valueOf(input);
        } else {
            // 정수면 Integer
            return Integer.valueOf(input);
        }
    }
}
