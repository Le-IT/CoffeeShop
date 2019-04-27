package whz.informatik.coffeeshop.security.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import whz.informatik.coffeeshop.common.DTOUtils;
import whz.informatik.coffeeshop.security.domain.CustomerCreateForm;
import whz.informatik.coffeeshop.security.domain.Role;
import whz.informatik.coffeeshop.security.domain.User;
import whz.informatik.coffeeshop.security.domain.UserRepository;
import whz.informatik.coffeeshop.security.service.dto.UserDTO;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDTO getUserDTOById(long id) {
        log.debug("Getting user by id={} as dto", id);
        User user = userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format(">>> User=%s not found", id)));
        return DTOUtils.createDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsersDTO() {
        log.debug("Getting all users as dto");
        List<User> targetListOrigin = userRepository.findAllByOrderByLoginNameAsc();
        List<UserDTO> targetList= new ArrayList<>();
        targetListOrigin.forEach(user -> targetList.add(DTOUtils.createDTO(user)));
        return targetList;
    }

    @Override
    public Collection<UserDTO> getAllUsersWithRoleDTO(Role role) {
        log.debug("Getting all users by role={} as dto", role.name());
        List<User> targetListOrigin = userRepository.findAllByRoleOrderByLoginNameAsc(role);
        List<UserDTO> targetList = new ArrayList<>();
        targetListOrigin.forEach(user -> targetList.add(DTOUtils.createDTO(user)));
        return targetList;
    }

    @Override
    public User getUserById(long id) {
        log.debug("Getting user by id={}", id);
        User user = userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format(">>> User=%s not found", id)));
        return user;
    }

    @Override
    public Collection<User> getAllUsers() {
        log.debug("Getting all users");
        return userRepository.findAllByOrderByLoginNameAsc();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        log.debug("Getting user by email={}", email.replaceFirst("@.*", "@***"));
        return userRepository.findOneByEmail(email);
    }

    @Override
    public Optional<User> getUserByLoginName(String loginName) {
        log.debug("Getting user by login=" + loginName);
        return userRepository.findOneByLoginName(loginName);
    }

    @Override
    public boolean existsByLoginName(String loginName) {
        return userRepository.existsByLoginName(loginName);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User create(CustomerCreateForm form) {
        log.debug("Creating user with loginName=" + form.getLoginName());
        User user = new User();
        user.setEmail(form.getEmail());
        user.setLoginName(form.getLoginName());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRole(form.getRole());
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteById(long userId) {
        userRepository.deleteById(userId);
    }


}
