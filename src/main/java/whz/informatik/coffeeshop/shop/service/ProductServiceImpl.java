package whz.informatik.coffeeshop.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.informatik.coffeeshop.common.DTOUtils;
import whz.informatik.coffeeshop.shop.domain.Product;
import whz.informatik.coffeeshop.shop.domain.repository.ProductRepository;
import whz.informatik.coffeeshop.shop.service.dto.ProductDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<ProductDTO> getAllDTO() {
        List<Product> targetListOrigin = getAll();
        List<ProductDTO> targetList = new ArrayList<>();
        targetListOrigin.forEach(product -> targetList.add(DTOUtils.createDTO(product)));
        return targetList;
    }

    @Override
    public Optional<ProductDTO> getDTOById(long id) {
        Optional<Product> optionalProduct = getById(id);
        if(optionalProduct.isPresent()) {
            ProductDTO productDTO = DTOUtils.createDTO(optionalProduct.get());
            return Optional.of(productDTO);
        } return Optional.empty();
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> getByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void removeProduct(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void removeProduct(Product product) {
        productRepository.delete(product);
    }
}
