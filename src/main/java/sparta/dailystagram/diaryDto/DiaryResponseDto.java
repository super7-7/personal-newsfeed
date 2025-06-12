package sparta.dailystagram.diaryDto;

import sparta.dailystagram.entity.Diary;
import java.time.LocalDateTime;

public class DiaryResponseDto {
    private Long id;
    private String email;
    private String userName;
    private String title;
    private String content;
    private String image;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 생성자
    public DiaryResponseDto(Diary diary) {
        this.id = diary.getId();
        this.email = diary.getEmail();
        this.userName = diary.getUserName();
        this.title = diary.getTitle();
        this.content = diary.getContent();
        this.image = diary.getImage();
        this.createdAt = diary.getCreatedAt();
        this.updatedAt = diary.getUpdatedAt();
    }

    // 게터
    public Long getId() {
        return id;
    }
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
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
