package com.javaguru.shoppinglist;

public class ProductService {

    private ProductRepository productRepository = new ProductRepository();
    private ProductValidationService validationService = new ProductValidationService();

    public Long saveProduct(Product product) {
        validationService.validate(product);
        Product createdProduct = productRepository.save(product);
        return createdProduct.getId();
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id);
    }

}
