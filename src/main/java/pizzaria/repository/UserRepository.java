package pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizzaria.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
