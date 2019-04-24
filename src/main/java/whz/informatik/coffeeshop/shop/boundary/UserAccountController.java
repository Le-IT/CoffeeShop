package whz.informatik.coffeeshop.shop.boundary;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import whz.informatik.coffeeshop.common.CurrentUserUtil;
import whz.informatik.coffeeshop.shop.domain.Address;
import whz.informatik.coffeeshop.shop.domain.Customer;
import whz.informatik.coffeeshop.shop.domain.Warranty;
import whz.informatik.coffeeshop.shop.service.AddressService;
import whz.informatik.coffeeshop.shop.service.CustomerService;
import whz.informatik.coffeeshop.shop.service.dto.AddressDTO;
import whz.informatik.coffeeshop.shop.service.dto.CustomerDTO;

import java.util.List;

@Controller
public class UserAccountController {

    private Logger log = LoggerFactory.getLogger(UserAccountController.class);

    private CustomerService customerService;
    private AddressService addressService;

    @Autowired
    public UserAccountController(CustomerService customerService, AddressService addressService) {
        this.customerService = customerService;
        this.addressService = addressService;
    }

    @RequestMapping(value = {"/profile"})
    public String showAccountPage(@RequestParam("id") long userId, Model model) {
        String username = CurrentUserUtil.getCurrentUser(model);
        CustomerDTO customer = customerService.getDTOByLoginName(username).get();
        model.addAttribute("currentCustomer", customer);
        return "userAccount";
    }

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
    @RequestMapping(value= "/showWarranties")
    public String handleShowWarranties(Model model){
        String from = CurrentUserUtil.getCurrentUser(model);
        String url = "/profile?id="+CurrentUserUtil.getCurrentUserId(model);
        Customer customer = customerService.getByLoginName(from).get();

        List<Warranty> warranties = customer.getWarrantyList();
        model.addAttribute("warrantyList", warranties);

        return "warranties";
    }



}
