package whz.informatik.coffeeshop.security.service.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import whz.informatik.coffeeshop.security.domain.CustomerCreateForm;
import whz.informatik.coffeeshop.security.service.user.UserService;

@Component
public class CustomerCreateFormValidator implements Validator {
    private static final Logger log = LoggerFactory.getLogger(CustomerCreateFormValidator.class);

    private UserService userService;

    @Autowired
    public CustomerCreateFormValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(CustomerCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.debug("Validating {}", target);
        CustomerCreateForm form = (CustomerCreateForm) target;
        validatePasswords(errors, form);
        validateEmail(errors, form);
        validateAddress(errors, form);
    }

    private void validatePasswords(Errors errors, CustomerCreateForm form) {
        if (!form.getPassword().equals(form.getPasswordRepeated()))
            errors.reject("password", "unterschiedliche Passwörter eingegeben! vertippt?");
    }

    private void validateEmail(Errors errors, CustomerCreateForm form) {
        if (userService.existsByLoginName(form.getLoginName()))
            errors.reject("loginName", "Nutzer mit diesem nickname existiert bereits");
        else if (userService.existsByEmail(form.getEmail()))
            errors.reject("email", "Nutzer mit dieser Email existiert bereits");
    }

    private void validateAddress(Errors errors, CustomerCreateForm form) {
        if(form.getZipCode().length()!=5)
            errors.reject("zipCode","Postleitzahl im falschen Format!");
    }


}
