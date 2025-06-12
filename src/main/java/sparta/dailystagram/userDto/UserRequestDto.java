package sparta.dailystagram.userDto;

import org.springframework.web.multipart.MultipartFile;

public class UserRequestDto {
    // 속성
    private String userName;
    private String email;
    private String password;
    private String checkPassword;

    // 생성자
    public UserRequestDto() {}

    public UserRequestDto(String userName, String email, String password,  String checkPassword) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.checkPassword = checkPassword;

    }

    // 기능(게터,세터)
    public String getUserName() {
        return userName;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getCheckPassword() {
        return checkPassword;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }
}

