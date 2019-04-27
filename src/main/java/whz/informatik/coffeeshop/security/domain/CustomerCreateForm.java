package whz.informatik.coffeeshop.security.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Form for Customer creation
 * holds info for User creation as well
 */
public class CustomerCreateForm {
    /** Customer Requirements **/
    // loginName
    @NotEmpty
    private String firstName = "";
    @NotEmpty
    private String lastName = "";

    /** User Requirements **/
    @NotEmpty
    private String loginName = "";
    @NotEmpty
    private String email = "";
    @NotEmpty
    private String password = "";
    @NotEmpty
    private String passwordRepeated = "";
    @NotNull
    private Role role = Role.USER;

    /** Address Requirements **/
    @NotEmpty
    private String street = "";
    @NotEmpty
    private String housenumber = "";
    @NotEmpty
    private String town = "";
    @NotEmpty
    private String zipCode = "";

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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPasswordRepeated() {
        return passwordRepeated;
    }
    public void setPasswordRepeated(String passwordRepeated) {
        this.passwordRepeated = passwordRepeated;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getHousenumber() {
        return housenumber;
    }
    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }
    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "CustomerCreateForm{" +
                    "email='" + email.replaceFirst("@.+", "@***") + "' " +
                    "password='***' " +
                    "passwordRepeated='***' " +
                    "role=" + role + ' ' +
                    "loginName='" + loginName + "' " +
                    "firstName='" + firstName + "' " +
                    "lastName='" + lastName + "' " +
                    "street='" + street + "' " +
                    "housenumber='" + housenumber + "' " +
                    "zipCode='" + zipCode + "' " +
                    "town='" + town + "'" +
                '}';

    }
}
