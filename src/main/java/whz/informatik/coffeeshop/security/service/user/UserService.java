package whz.informatik.coffeeshop.security.service.user;

import whz.informatik.coffeeshop.security.domain.CustomerCreateForm;
import whz.informatik.coffeeshop.security.domain.Role;
import whz.informatik.coffeeshop.security.domain.User;
import whz.informatik.coffeeshop.security.service.dto.UserDTO;

import java.util.Collection;
import java.util.Optional;

/**
 * Interface for functionality of the UserService
 * more or less CRUD for User and UserDTO
 */
public interface UserService {
    /**
     * find User with given id
     * @param id
     * @return userDTO
     */
    UserDTO getUserDTOById(long id);

    /**
     * get all registered users as collection of DTOs
     * @return userDTOs
     */
    Collection<UserDTO> getAllUsersDTO();

    /**
     * get all registered users with given role (in sense of role-based access control)
     * @param role
     * @return users
     */
    Collection<UserDTO> getAllUsersWithRoleDTO(Role role);

    /**
     * find User with given id
     * @param id
     * @return user or null
     */
    User getUserById(long id);

    /**
     * get all registered users
     * @return users
     */
    Collection<User> getAllUsers();

    /**
     * find User with given email
     * @param email
     * @return optional_user
     */
    Optional<User> getUserByEmail(String email);

    /**
     * find User with given login
     * @param loginName
     * @return optional_user
     */
    Optional<User> getUserByLoginName(String loginName);

    /**
     * checks whether a user with given login exists or not
     * @param loginName
     * @return true if user with given login exists
     */
    boolean existsByLoginName(String loginName);

    /**
     * checks whether a user with given email exists or not
     * @param email
     * @return true if user with given email exists
     */
    boolean existsByEmail(String email);

    /**
     * create a user by form
     * @param form
     * @return user
     */
    User create(CustomerCreateForm form);

    /**
     * delete user
     * @param user
     */
    void delete(User user);

    /**
     * delete user with given userId
     * @param userId
     */
    void deleteById(long userId);
}
