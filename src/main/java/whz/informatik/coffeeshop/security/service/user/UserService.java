package whz.informatik.coffeeshop.security.service.user;

import whz.informatik.coffeeshop.security.domain.CustomerCreateForm;
import whz.informatik.coffeeshop.security.domain.Role;
import whz.informatik.coffeeshop.security.domain.User;
import whz.informatik.coffeeshop.security.service.dto.UserDTO;

import java.util.Collection;
import java.util.Optional;

public interface UserService {
    UserDTO getUserDTOById(long id);
    Collection<UserDTO> getAllUsersDTO();
    Collection<UserDTO> getAllUsersWithRoleDTO(Role role);
    User getUserById(long id);
    Collection<User> getAllUsers();
    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByLoginName(String loginName);
    boolean existsByLoginName(String loginName);
    boolean existsByEmail(String email);
    User create(CustomerCreateForm form);
    void delete(User user);
    void deleteById(long userId);
}
