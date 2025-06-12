package sparta.dailystagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.dailystagram.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
