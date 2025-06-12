package sparta.dailystagram.diaryDto;

public class DiaryRequestDto {

    // 속성
    private String email;
    private String userName;
    private String title;
    private String content;
    private String image;

    // 생성자
    public DiaryRequestDto() {}

    public DiaryRequestDto(String email, String userName, String title, String content, String image) {
        this.email = email;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.image = image;
    }

    // 기능(게터, 세터)

    public String getEmail() {
        return email;
    }
    public String getUserName() {
        return userName;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getImage() {
        return image;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setImage(String image) {
        this.image = image;
    }
}
