package sparta.dailystagram.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sparta.dailystagram.entity.User;
import sparta.dailystagram.loginDto.LoginErrorResponseDto;
import sparta.dailystagram.loginDto.LoginRequestDto;
import sparta.dailystagram.loginDto.LoginResponseDto;
import sparta.dailystagram.service.LoginService;
import sparta.dailystagram.service.UserService;
import sparta.dailystagram.userDto.UserInfoResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest httpServletRequest, HttpServletResponse response) {

        try {
            // 로그인 검증
            User user = loginService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());

            // 세션 저장
            HttpSession session = httpServletRequest.getSession(true);
            session.setAttribute("loginUser", user);
            session.setAttribute("userEmail", user.getEmail());

            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(60 * 60); // 1시간
            response.addCookie(cookie);

            // 응답
            LoginResponseDto responseDto = new LoginResponseDto(user, 200, "로그인 성공");
            return ResponseEntity.ok(responseDto);

        } catch (IllegalArgumentException e) {
            // 예외 응답 DTO 구성
            LoginErrorResponseDto errorResponse = new LoginErrorResponseDto(400, e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
