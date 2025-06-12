package sparta.dailystagram.diaryDto;

import java.time.LocalDateTime;

public class DiaryUpdateResponseDto {

    private Long id;
    private String email;
    private String userName;
    private String image;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 생성자
    public DiaryUpdateResponseDto(Long id, String email, String userName, String image,
                                  String title, String content,
                                  LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.image = image;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
    public String getImage() {
        return image;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
