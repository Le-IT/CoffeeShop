package whz.informatik.coffeeshop.shop.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import whz.informatik.coffeeshop.common.CurrentUserUtil;
import whz.informatik.coffeeshop.shop.domain.Customer;
import whz.informatik.coffeeshop.shop.domain.Item;
import whz.informatik.coffeeshop.shop.domain.ShoppingOrder;
import whz.informatik.coffeeshop.shop.service.CustomerService;
import whz.informatik.coffeeshop.shop.service.ShoppingOrderService;
import whz.informatik.coffeeshop.shop.service.WarrantyService;
import whz.informatik.coffeeshop.shop.service.dto.WarrantyDTO;

import java.util.List;

@Controller
public class WarrantyController {

    private CustomerService customerService;
    private WarrantyService warrantyService;
    private ShoppingOrderService shoppingOrderService;

    @Autowired
    public WarrantyController(CustomerService customerService,
                              WarrantyService warrantyService,
                              ShoppingOrderService shoppingOrderService) {
        this.customerService = customerService;
        this.warrantyService = warrantyService;
        this.shoppingOrderService = shoppingOrderService;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value= "/showWarranties")
    public String handleShowWarranties(Model model){
        String from = CurrentUserUtil.getCurrentUser(model);
        Customer customer = customerService.getByLoginName(from).get();

        List<WarrantyDTO> warranties = warrantyService.getWarrantysDTOByCustomer(customer);
        model.addAttribute("warrantyList", warranties);

        model.addAttribute("currentCustomer", customer);
        return "warranties";
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/warranties/setup")
    public String handleWarrantiesSetup(Model model, @RequestParam long orderId) {
        String from = CurrentUserUtil.getCurrentUser(model);
        Customer customer = customerService.getByLoginName(from).get();
        ShoppingOrder shoppingOrder = shoppingOrderService.getShoppingOrderById(orderId).get();
        for(Item item : shoppingOrder.getItems()){
            if(item.getProduct().getProductType().isWithWarranty()){
                warrantyService.warrantProduct(item, customer);
            }
        }
        return "redirect:/";
    }
}
