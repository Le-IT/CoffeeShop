package whz.informatik.coffeeshop.shop.service;

import whz.informatik.coffeeshop.shop.domain.Product;
import whz.informatik.coffeeshop.shop.service.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service-Interface to provide functionality to controllers to
 * handle Product-Persistence
 */
public interface ProductService {
    /**
     * get all Products as DTOs
     * @return productDTOs
     */
    List<ProductDTO> getAllDTO();

    /**
     * find Product with given ID
     * @param id
     * @return productDTO_optional
     */
    Optional<ProductDTO> getDTOById(long id);

    /**
     * get all Products
     * @return products
     */
    List<Product> getAll();

    /**
     * find Product with given ID
     * @param id
     * @return product_optional
     */
    Optional<Product> getById(long id);

    /**
     * find Product with given name
     * @param name
     * @return product_optional
     */
    Optional<Product> getByName(String name);

    /**
     * persist the given Product in the database
     * @param product
     * @return Product for further usage
     */
    Product addProduct(Product product);

    /**
     * delete Product with given id from database
     * @param id
     */
    void removeProduct(long id);

    /**
     * delete given PRoduct from database
     * @param product
     */
    void removeProduct(Product product);
}
