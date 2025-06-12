package sparta.dailystagram.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import sparta.dailystagram.diaryDto.*;
import sparta.dailystagram.entity.Diary;
import sparta.dailystagram.service.DiaryService;

@RestController
@RequestMapping("/api/diaries")
public class DiaryController {

    // 속성
    private final DiaryService diaryService;

    // 생성자
    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    /**
     * 게시물 생성 API
     */
    @PostMapping
    public ResponseEntity<?> createDiaryAPI(@RequestBody DiaryRequestDto diaryRequestDto, HttpSession session) {
        String userEmail = (String) session.getAttribute("userEmail");

        if (userEmail == null) {
            return ResponseEntity.status(401).body(new DiaryErrorResponseDto(401, "로그인이 필요합니다."));
        }

        diaryRequestDto.setEmail(userEmail); // 세션 이메일로 덮어쓰기

        try {
            Diary createdDiary = diaryService.createDiary(diaryRequestDto);
            return ResponseEntity.status(201).body(new DiaryResponseDto(createdDiary));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new DiaryErrorResponseDto(400, e.getMessage()));
        }
    }

    /**
     * 게시물 수정 API
     */
    private static final Logger logger = LoggerFactory.getLogger(DiaryController.class);

    @PatchMapping("{id}")
    public ResponseEntity<?> updateDiaryAPI(@PathVariable("id") Long diaryId,
                                            @RequestBody DiaryUpdateRequestDto updateRequestDto,
                                            HttpSession session) {

        try {
            DiaryUpdateResponseDto updatedDiary = diaryService.updateDiary(diaryId, updateRequestDto, session);
            return ResponseEntity.ok(updatedDiary);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(new DiaryErrorResponseDto(404, e.getMessage()));
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(403).body(new DiaryErrorResponseDto(403, e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new DiaryErrorResponseDto(400, e.getMessage()));
        } catch (Exception e) {
            // 혹시 모를 예외 대비 (선택 사항)
            return ResponseEntity.status(500).body(new DiaryErrorResponseDto(500, "서버 오류가 발생했습니다."));
        }
    }
}
