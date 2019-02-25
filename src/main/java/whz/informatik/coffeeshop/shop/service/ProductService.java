package whz.informatik.coffeeshop.shop.service;

import whz.informatik.coffeeshop.shop.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAll();
    Optional<Product> getById(long id);
    Optional<Product> getByName(String name);
    Product addProduct(Product product);
    void removeProduct(long id);
    void removeProduct(Product product);
}
