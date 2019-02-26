package whz.informatik.coffeeshop.shop.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import whz.informatik.coffeeshop.common.CurrentUserUtil;
import whz.informatik.coffeeshop.shop.service.CustomerService;
import whz.informatik.coffeeshop.shop.service.ProductService;
import whz.informatik.coffeeshop.shop.service.dto.CustomerDTO;
import whz.informatik.coffeeshop.shop.service.dto.ProductDTO;

import java.util.List;

@Controller
public class OverviewController {
    private static final Logger log = LoggerFactory.getLogger(OverviewController.class);

    private ProductService productService;
    private CustomerService customerService;


    @Autowired
    public OverviewController(ProductService productService,
                              CustomerService customerService) {
        this.productService = productService;
        this.customerService = customerService;
    }


    @RequestMapping(value = {"/","/products"})
    public String getHomePage(Model model) {
        String from = CurrentUserUtil.getCurrentUser(model);
        log.debug("Processing Request on '/' from user=" + from);
        List<ProductDTO> products = productService.getAllDTO();
        // implement creation and validation of form for item submit
        model.addAttribute("listProducts", products);
        CustomerDTO customerFrom = customerService.getDTOByLoginName(from).orElse(null);
        model.addAttribute("currentCustomer", customerFrom);
        return "home";
    }
}
