package whz.informatik.coffeeshop.security.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findOneByEmail(String email);
    Optional<User> findOneByLoginName(String loginName);
    boolean existsByEmail(String email);
    boolean existsByLoginName(String loginName);

    List<User> findAllByOrderByLoginNameAsc();
    List<User> findAllByRoleOrderByLoginNameAsc(Role role);
}
