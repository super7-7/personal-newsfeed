package sparta.dailystagram.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import sparta.dailystagram.diaryDto.DiaryRequestDto;
import sparta.dailystagram.diaryDto.DiaryUpdateRequestDto;
import sparta.dailystagram.diaryDto.DiaryUpdateResponseDto;
import sparta.dailystagram.entity.Diary;
import sparta.dailystagram.entity.User;
import sparta.dailystagram.repository.DiaryRepository;

import java.util.Optional;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    /**
    * 게시물 생성 서비스
     */
    public Diary createDiary(DiaryRequestDto requestDto) {
        // 필수값 검증
        if (requestDto.getEmail() == null || requestDto.getUserName() == null ||
                requestDto.getTitle() == null || requestDto.getContent() == null) {
            throw new IllegalArgumentException("게시글 입력 항목 중 빈 내용이 있습니다.");
        }

        // Diary 엔티티 생성
        Diary diary = new Diary(
                requestDto.getEmail(),
                requestDto.getUserName(),
                requestDto.getTitle(),
                requestDto.getContent(),
                requestDto.getImage() // null이어도 무방
        );

        // 저장 후 반환
        return diaryRepository.save(diary);
    }

    /**
     *  게시물 수정 서비스
     */
    @Transactional
    public DiaryUpdateResponseDto updateDiary(Long diaryId, DiaryUpdateRequestDto updateRequestDto, HttpSession session) {
        // 1. 세션에서 사용자 이메일 꺼내기
        String userEmail = (String) session.getAttribute("userEmail");

        if (userEmail == null) {
            throw new AccessDeniedException("로그인이 필요합니다.");
        }

        // 2. 수정할 데이터 준비
        String title = updateRequestDto.getTitle();
        String content = updateRequestDto.getContent();
        String image = updateRequestDto.getImage();

        // 3. 게시글 조회
        Optional<Diary> diaryOptional = diaryRepository.findById(diaryId);
        if (diaryOptional.isEmpty()) {
            throw new EntityNotFoundException("해당 게시글을 찾을 수 없습니다.");
        }

        Diary diary = diaryOptional.get();

        // 4. 권한 확인 (작성자와 로그인 유저가 동일한지 확인)
        if (!diary.getEmail().equals(userEmail)) {
            throw new AccessDeniedException("본인 게시글만 수정 가능합니다.");
        }

        // 5. 게시글 내용 수정
        diary.updateDiary(title, content, image); // 이 안에서 updatedAt도 갱신된다면 OK

        // 6. 수정 결과 반환 (DTO 생성)
        DiaryUpdateResponseDto responseDto = new DiaryUpdateResponseDto(
                diary.getId(),
                diary.getEmail(),
                diary.getUserName(),
                diary.getImage(),
                diary.getTitle(),
                diary.getContent(),
                diary.getCreatedAt(),
                diary.getUpdatedAt()
        );
        return responseDto;
    }
}
