package sparta.dailystagram.diaryDto;

public class DiaryUpdateRequestDto {

    // 속성
    private String title;
    private String content;
    private String image;

    // 생성자
    public DiaryUpdateRequestDto() {}

    public DiaryUpdateRequestDto(String title, String content, String image) {
        this.title = title;
        this.content = content;
        this.image = image;
    }
    // 기능(게터,세터)
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getImage() {
        return image;
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
