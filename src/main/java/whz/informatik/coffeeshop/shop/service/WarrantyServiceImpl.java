package whz.informatik.coffeeshop.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.informatik.coffeeshop.common.DTOUtils;
import whz.informatik.coffeeshop.shop.domain.*;
import whz.informatik.coffeeshop.shop.domain.repository.ShoppingOrderRepository;
import whz.informatik.coffeeshop.shop.domain.repository.WarrantyRepository;
import whz.informatik.coffeeshop.shop.service.dto.ShoppingOrderDTO;
import whz.informatik.coffeeshop.shop.service.dto.WarrantyDTO;

import java.time.Instant;
import java.util.*;

@Service
public class WarrantyServiceImpl implements WarrantyService{

    private WarrantyRepository warrantyRepository;
    private ShoppingOrderService shoppingOrderService;
    private ShoppingCartService shoppingCartService;
    private CustomerService customerService;


    @Autowired
    public WarrantyServiceImpl(WarrantyRepository warrantyRepository,
                               ShoppingOrderRepository shoppingOrderRepository,
                               ShoppingCartService shoppingCartService,
                               CustomerService customerService,
                               ProductService productService) {
        this.warrantyRepository = warrantyRepository;
        this.shoppingOrderService = shoppingOrderService;
        this.shoppingCartService = shoppingCartService;
        this.customerService = customerService;

    }


    @Override
    public Optional<Warranty> getWarrantyById(long warrentyId) {
        return warrantyRepository.findById(warrentyId);
    }

    // FIXME
    @Override
    public Warranty createWarrantyForOrderedProducts(Customer customer) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR,2);
        Date endDate = cal.getTime();
        for(ShoppingOrder shoppingOrder: customer.getOrders()){
           for(Item item: shoppingOrder.getItems()){
               if(item.getProduct().getProductType().isWithWarranty() && !customer.hasWarranty(item,endDate)){
                   Warranty warranty = new Warranty();
                   warranty.setup(endDate,item,customer);
                   warrantyRepository.save(warranty);
                   customer.getWarrantyList().add(warranty);
                   update(warranty);
                   customerService.updateCustomer(customer);
                   System.out.println("Groesse WarrantyListCustomer"+customer.getWarrantyList().size());
               }
           }
        }
        return null;
    }

    @Override
    public List<Warranty> getWarrantysByCustomer(Customer customer) {
        return customer.getWarrantyList();
    }

    @Override
    public Optional<WarrantyDTO> getWarrentyDTOById(long warrantyId) {
        Optional<Warranty> optionalWarranty = getWarrantyById(warrantyId);
        if(optionalWarranty.isPresent()){
            WarrantyDTO warrantyDTO = DTOUtils.createDTO(optionalWarranty.get());
            return Optional.of(warrantyDTO);
        }return Optional.empty();
    }

    @Override
    public List<WarrantyDTO> getWarrantysDTOByCustomer(Customer customer) {
        List<Warranty> targetListOrigin = getWarrantysByCustomer(customer);
        List<WarrantyDTO> targetList = new ArrayList<>();
        targetListOrigin.forEach(warranty -> targetList.add(DTOUtils.createDTO(warranty)));
        return targetList;
    }

    @Override
    public Warranty update(Warranty warranty) {
        if(warrantyRepository.existsById(warranty.getId())){
            return warrantyRepository.save(warranty);
        }
        return null;
    }
}
