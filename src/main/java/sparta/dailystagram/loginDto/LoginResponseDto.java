package sparta.dailystagram.loginDto;

import sparta.dailystagram.entity.User;
import sparta.dailystagram.userDto.UserInfoResponseDto;

import java.io.StringReader;

public class LoginResponseDto {
    // 속성
    private UserInfoResponseDto user;
    private int status;
    private String message;

    // 생성자
    public LoginResponseDto() {}

    public LoginResponseDto(User user, int status, String message) {
        this.user = new UserInfoResponseDto(user);
        this.status = status;
        this.message = message;
    }

    // 기능(게터,세터)
    public UserInfoResponseDto getUser() {
        return user;
    }
    public int getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }

    public void setUser(UserInfoResponseDto user) {
        this.user = user;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
