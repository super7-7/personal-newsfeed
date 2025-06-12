package sparta.dailystagram.userDto;

import sparta.dailystagram.entity.User;

public class UserInfoResponseDto {

    // 속성
    private Long id;
    private String email;
    private String userName;

    // 생성자
    public UserInfoResponseDto() {}

    public UserInfoResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.userName = user.getUserName();
    }

    // 기능(게터,세터)
    public Long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getUserName() {
        return userName;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
