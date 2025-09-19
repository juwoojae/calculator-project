package exception;

/**
 * 사용자 정의 예외
 * 연산자 입력에서 연산자 가아닌 다른 문자가 들어올 경우에 예외처리
 */
public class InvalidateOperatorException extends RuntimeException {
    public InvalidateOperatorException(String message) {
        super(message);
    }
}

