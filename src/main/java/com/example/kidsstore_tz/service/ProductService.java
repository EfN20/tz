package com.example.kidsstore_tz.service;

import com.example.kidsstore_tz.domain.Product;
import com.example.kidsstore_tz.exception.NotFoundException;
import com.example.kidsstore_tz.repository.ProductRepository;
import com.example.kidsstore_tz.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.getProductById(id).orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(int id, Product product) {
        boolean exists = productRepository.existsById(id);
        if (exists) {
            product.setId(id);
            return productRepository.save(product);
        } throw new NotFoundException("Product not found");
    }

    @Override
    public boolean deleteProductById(int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        } throw new NotFoundException("Product not found");
    }
}
