package com.example.kidsstore_tz.service.interfaces;

import com.example.kidsstore_tz.domain.Product;

import java.util.List;

public interface IProductService {

    List<Product> getAllProducts();

    Product getProductById(int id);

    Product addProduct(Product product);

    Product updateProduct(int id, Product product);

    boolean deleteProductById(int id);

}
