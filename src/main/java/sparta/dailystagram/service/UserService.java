package sparta.dailystagram.service;

import lombok.RequiredArgsConstructor;
import org.apache.el.stream.StreamELResolverImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sparta.dailystagram.entity.User;
import sparta.dailystagram.repository.UserRepository;
import sparta.dailystagram.userDto.UserRequestDto;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void createUser(UserRequestDto userRequestDto) {
        String email = userRequestDto.getEmail();
        String password = userRequestDto.getPassword();
        String checkPassword = userRequestDto.getCheckPassword();
        String userName = userRequestDto.getUserName();

        // 필수값 확인
        if (email == null || password == null || checkPassword == null || userName == null) {
            throw new IllegalArgumentException("필수 입력값 중 입력하지 않은 항목이 있습니다.");
        }

        // 이메일 중복 확인
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        // 비밀번호 정규식 확인
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=-]).{8,20}$";
        if (!password.matches(passwordPattern)) {
            throw new IllegalArgumentException("비밀번호는 최소 8자(최대 20자), 대소문자, 숫자, 특수문자를 포함해야 합니다.");
        }

        // 비밀번호 확인 일치 여부
        if (!password.equals(checkPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 비밀번호 암호
        String encodedPassword = passwordEncoder.encode(password);

        // 엔티티 생성 및 저장
        User user = new User(userName, email, encodedPassword);
        userRepository.save(user);
    }


}
