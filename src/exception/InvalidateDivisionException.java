package exception;

/**
 * 사용자 정의 예외
 * 나누기 연산자에서 분모에 0이 들어오는 경우의 예외처리
 */
public class InvalidateDivisionException extends RuntimeException {
    public InvalidateDivisionException(String message) {
        super(message);
    }
}

