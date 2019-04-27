package whz.informatik.coffeeshop.security.service.dto;

import whz.informatik.coffeeshop.security.domain.Role;

/**
 * DTO for User for more efficient networking
 */
public class UserDTO {
    private Long id;
    private String loginName;
    private String email;
    private Role role;

    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}
