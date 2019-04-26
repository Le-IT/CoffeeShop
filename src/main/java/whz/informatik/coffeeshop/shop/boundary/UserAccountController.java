package whz.informatik.coffeeshop.shop.boundary;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import whz.informatik.coffeeshop.common.CurrentUserUtil;
import whz.informatik.coffeeshop.security.domain.CustomerCreateForm;
import whz.informatik.coffeeshop.security.service.dto.UserDTO;
import whz.informatik.coffeeshop.security.service.user.UserService;
import whz.informatik.coffeeshop.shop.domain.Address;
import whz.informatik.coffeeshop.shop.domain.Customer;
import whz.informatik.coffeeshop.shop.domain.Warranty;
import whz.informatik.coffeeshop.shop.service.AddressService;
import whz.informatik.coffeeshop.shop.service.CustomerService;
import whz.informatik.coffeeshop.shop.service.dto.AddressDTO;
import whz.informatik.coffeeshop.shop.service.dto.CustomerDTO;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class UserAccountController {

    private Logger log = LoggerFactory.getLogger(UserAccountController.class);

    private UserService userService;
    private CustomerService customerService;
    private AddressService addressService;

    @Autowired
    public UserAccountController(UserService userService,
                                 CustomerService customerService,
                                 AddressService addressService) {
        this.userService = userService;
        this.customerService = customerService;
        this.addressService = addressService;
    }

    @PreAuthorize("#userId == principal.id or hasAuthority('ADMIN')")
    @RequestMapping(value = {"/profile"})
    public String showAccountPage(@RequestParam("id") long userId, Model model) {
        long fromid = CurrentUserUtil.getCurrentUserId(model);

        UserDTO user = userService.getUserDTOById(userId);
        Optional<CustomerDTO> ocustomer = customerService.getDTOByLoginName(user.getLoginName());

        if(ocustomer.isPresent())
            model.addAttribute("currentCustomer", ocustomer.get());
        else
            return "redirect:/";


        if (user.getId() != fromid)
            model.addAttribute("userid", user.getId());

        return "userAccount";
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping (value = "/addAdress", method = RequestMethod.POST)
    public String handleAddAddress(@RequestParam String street,
                                @RequestParam String housenumber,
                                @RequestParam String town,
                                @RequestParam String zipCode,
                                Model model){

        String from = CurrentUserUtil.getCurrentUser(model);
        Customer customer = customerService.getByLoginName(from).get();
        Address newAddress = addressService.createAddress(street, housenumber, zipCode, town);
        customer.addAddress(newAddress);
        customerService.updateCustomer(customer);
        String url = "/profile?id="+CurrentUserUtil.getCurrentUserId(model);
        return "redirect:"+url;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping (value = "/deleteAddress", method = RequestMethod.POST)
    public String handleDeleteAddress(@RequestParam long addressId, Model model){
        System.out.println("ADDRESSID: "+ addressId);
        String from = CurrentUserUtil.getCurrentUser(model);
        String url = "/profile?id="+CurrentUserUtil.getCurrentUserId(model);
        Customer customer = customerService.getByLoginName(from).get();
        for(Address address: customer.getAddressList()){
            System.out.println(address);
        }
        if(customer.getAddressList().size()>=1 && addressService.getById(addressId).isPresent()) {
            Address addressToDelete = addressService.getById(addressId).get();
            customer.removeAddress(addressToDelete);
            customerService.updateCustomer(customer);
        }
        return "redirect:"+url;
    }

    // TODO update the object instead of delete/create
    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping (value = "/updateAddress", method = RequestMethod.POST)
    public String handleUpdateAddress(@RequestParam String street,
                                   @RequestParam String housenumber,
                                   @RequestParam String town,
                                   @RequestParam String zipCode,
                                   @RequestParam long addressId,
                                   Model model){

        String from = CurrentUserUtil.getCurrentUser(model);
        Customer customer = customerService.getByLoginName(from).get();
        Address newAddress = addressService.createAddress(street, housenumber, zipCode, town);
        customer.addAddress(newAddress);
        customerService.updateCustomer(customer);
        String url = "/profile?id="+CurrentUserUtil.getCurrentUserId(model);
        return handleDeleteAddress(addressId,model);
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value= "/showWarranties")
    public String handleShowWarranties(Model model){
        String from = CurrentUserUtil.getCurrentUser(model);
        String url = "/profile?id="+CurrentUserUtil.getCurrentUserId(model);
        Customer customer = customerService.getByLoginName(from).get();

        List<Warranty> warranties = customer.getWarrantyList();
        model.addAttribute("warrantyList", warranties);

        model.addAttribute("currentCustomer", customer);
        return "warranties";
    }


    @RequestMapping(value = "/users/create", method = RequestMethod.POST)
    public RedirectView handleUserCreateForm(@Valid @ModelAttribute("newCustomerForm") CustomerCreateForm form, BindingResult bindingResult,
                                             HttpServletRequest request, RedirectAttributes redir) {
        log.info("Processing users create form= " + form);
        log.info("Processing users create bindingResult= " + bindingResult.toString().replace("\n", ","));
        String referer = request.getHeader("Referer");

        RedirectView rv = new RedirectView();
        rv.setUrl(referer);

        if (bindingResult.hasErrors()) {
            log.debug("Error creating new Customer: " + bindingResult.getGlobalError().getDefaultMessage());
            redir.addFlashAttribute("error", bindingResult.getGlobalError().getDefaultMessage());
            return rv;
        }

        userService.create(form);
        customerService.createCustomer(form);

        if (referer.contains("register")) {
            rv.setContextRelative(true);
            rv.setUrl("/login");
        }
        return rv;
    }

    @PreAuthorize("#userid == principal.id or hasAuthority('ADMIN')")
    @RequestMapping(value = "/users/delete", method = RequestMethod.POST)
    public String handleUserDelete(@RequestParam Long userid, Model model) {
        String from = CurrentUserUtil.getCurrentUser(model);
        log.debug("Processing user delete request on user with id=" + userid);
        UserDTO userDTO = userService.getUserDTOById(userid);
        boolean isAdmin = model.asMap().containsKey("isAdmin") ? (boolean) model.asMap().get("isAdmin") : false;
        if(userDTO.getLoginName().equals(from) && isAdmin) {
            log.info("User delete request denied, admins can't be deleted (not even by their own(at least not in this way))");
            return "redirect:/users_managed";
        }
        Optional<Customer> customer = customerService.getByLoginName(userDTO.getLoginName());
        if(customer.isPresent()) {
            log.debug("Deleting Customer with id=" + customer.get().getId());
            customerService.removeCustomer(customer.get());
        }
        userService.deleteById(userid);
        log.debug("User deleted with id=" + userid);
        if(from == userDTO.getLoginName()) {
            log.debug("Logged out");
            return "redirect:/logout";
        }
        return "redirect:/users_managed";
    }
}
