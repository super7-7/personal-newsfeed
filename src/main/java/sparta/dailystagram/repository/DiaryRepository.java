package sparta.dailystagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.dailystagram.entity.Diary;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    // ㄴ기본 crud 메서드 제공
}
