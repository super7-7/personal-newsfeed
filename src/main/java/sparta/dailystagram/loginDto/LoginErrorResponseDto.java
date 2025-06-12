package sparta.dailystagram.loginDto;

public class LoginErrorResponseDto {

    // 속성
    private int status;
    private String message;

    // 생성자
    public LoginErrorResponseDto() {}

    public LoginErrorResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }

    // 기능(게터,세터)
    public int getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
