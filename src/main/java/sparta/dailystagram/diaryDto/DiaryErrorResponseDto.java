package sparta.dailystagram.diaryDto;

public class DiaryErrorResponseDto {
    private int status;
    private String message;

    public DiaryErrorResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

