package whz.informatik.coffeeshop.common;

import org.springframework.beans.BeanUtils;
import whz.informatik.coffeeshop.shop.domain.*;
import whz.informatik.coffeeshop.shop.service.dto.*;

import java.util.ArrayList;
import java.util.List;

public class DTOUtils {
    public static CustomerDTO createDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO,"addressList","warrantyList");
        List<AddressDTO> addressDTOList = new ArrayList<>();
        customer.getAddressList().forEach(address -> addressDTOList.add(DTOUtils.createDTO(address)));
        customerDTO.setAddressList(addressDTOList);
        List<WarrantyDTO> warrantyDTOList = new ArrayList<>();
        customer.getWarrantyList().forEach(warranty -> warrantyDTOList.add(DTOUtils.createDTO(warranty)));
        customerDTO.setWarrantyList(warrantyDTOList);
        return customerDTO;
    }

    public static AddressDTO createDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        BeanUtils.copyProperties(address,addressDTO,"customers");
        List<Long> customerIdList = new ArrayList<>();
        address.getAllCustomer().forEach(customer -> customerIdList.add(customer.getId()));
        addressDTO.setCustomerIdList(customerIdList);
        return addressDTO;
    }

    public static ShoppingCartDTO createDTO(ShoppingCart shoppingCart) {
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        BeanUtils.copyProperties(shoppingCart,shoppingCartDTO,"customer","items");
        shoppingCartDTO.setId(shoppingCart.getId());
        shoppingCartDTO.setCustomer(createDTO(shoppingCart.getCustomer()));
        List<ItemDTO> itemDTOList = new ArrayList<>();
        shoppingCart.getItems().forEach(item -> itemDTOList.add(createDTO(item)));
        shoppingCartDTO.setItems(itemDTOList);
        return shoppingCartDTO;
    }

    public static ItemDTO createDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        BeanUtils.copyProperties(item,itemDTO,"product");
        itemDTO.setProduct(createDTO(item.getProduct()));
        return itemDTO;
    }

    public static ProductDTO createDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product,productDTO,"productType");
        productDTO.setProductType(createDTO(product.getProductType()));
        return productDTO;
    }

    public static ProductTypeDTO createDTO(ProductType productType) {
        ProductTypeDTO productTypeDTO = new ProductTypeDTO();
        BeanUtils.copyProperties(productType, productTypeDTO);
        return productTypeDTO;
    }

    public static WarrantyDTO createDTO(Warranty warranty) {
        WarrantyDTO warrantyDTO = new WarrantyDTO();
        BeanUtils.copyProperties(warranty,warrantyDTO,"customer", "item");
        warrantyDTO.setCustomer(DTOUtils.createDTO(warranty.getCustomer()));
        warrantyDTO.setItem(DTOUtils.createDTO(warranty.getItem()));
        return warrantyDTO;
    }
}
