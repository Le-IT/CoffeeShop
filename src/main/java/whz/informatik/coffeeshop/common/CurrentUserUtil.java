package whz.informatik.coffeeshop.common;

import org.springframework.ui.Model;
import whz.informatik.coffeeshop.security.domain.CurrentUser;
import whz.informatik.coffeeshop.security.domain.Role;

/**
 * Class for easier access to the currentUser,
 * as it is necessary really often
 */
public class CurrentUserUtil {

    /**
     * Get Current User and setup model with attributes `userFrom` of type String
     * and `isAdmin` of type bool
     * @param model - ViewModel
     * @return from - username of the user that is currently logged in
     */
    public static String getCurrentUser(Model model) {
        CurrentUser currentUser = (CurrentUser) model.asMap().get("currentUser");
        if(currentUser == null || currentUser.getUser() == null) return "";
        String from = currentUser.getLoginName();
        model.addAttribute("userFrom", from);
        if(currentUser.getRole() == Role.ADMIN)
            model.addAttribute("isAdmin",true);
        return from;
    }

    /**
     * Get Current User and setup model with attributes `userFrom` of type String
     * and `isAdmin` of type bool
     * @param model - ViewModel
     * @return fromId - id of the user that is currently logged in
     */
    public static long getCurrentUserId(Model model) {
        CurrentUser currentUser = (CurrentUser) model.asMap().get("currentUser");
        if(currentUser == null || currentUser.getUser() == null) return 0;
        Long currentUserId = currentUser.getId();
        model.addAttribute("userFrom", currentUser.getLoginName());
        if(currentUser.getRole() == Role.ADMIN)
            model.addAttribute("isAdmin",true);
        return currentUserId;
    }
}
