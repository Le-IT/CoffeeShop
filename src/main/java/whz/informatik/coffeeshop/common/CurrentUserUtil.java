package whz.informatik.coffeeshop.common;

import org.springframework.ui.Model;
import whz.informatik.coffeeshop.security.domain.CurrentUser;
import whz.informatik.coffeeshop.security.domain.Role;

public class CurrentUserUtil {

    public static String getCurrentUser(Model model) {
        CurrentUser currentUser = (CurrentUser) model.asMap().get("currentUser");
        if(currentUser == null || currentUser.getUser() == null) return "";
        String from = currentUser.getLoginName();
        model.addAttribute("userFrom", from);
        if(currentUser.getRole() == Role.ADMIN)
            model.addAttribute("isAdmin",true);
        return from;
    }
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
