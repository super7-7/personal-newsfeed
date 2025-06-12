package sparta.dailystagram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sparta.dailystagram.service.UserService;
import sparta.dailystagram.userDto.UserRequestDto;

@RestController
@RequestMapping("/api/users") // 기본 URL 경로
@RequiredArgsConstructor
public class UserController {
    // 속성
    private final UserService userService; // UserService 주입

    /**
     * 회원가입 API
     */
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDto userRequestDto) {
        try {
            userService.createUser(userRequestDto);
            return ResponseEntity.status(201).body("회원가입이 정상적으로 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
