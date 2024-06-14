package com.INGRYD.INGRYD_CRM.service;
import com.INGRYD.INGRYD_CRM.model.Product;
import com.INGRYD.INGRYD_CRM.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {
    final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product getProductById(Long id){
        return productRepository.findById(id).get();
    }
    public Product postNewProduct(Product product){
        return productRepository.save(product);
    }
    public Product updateProduct(Product productToUpdate, Long id){
        // Check if product to be update is available in the database
        Product productFromDb = productRepository.findById(id).get();

        // Update the new item with the existing one
        productToUpdate.setProductName(productFromDb.getProductName());
        productToUpdate.setCategory(productFromDb.getCategory());
        productToUpdate.setDescription(productFromDb.getDescription());
        productToUpdate.setPrice(productFromDb.getPrice());
        return productRepository.save(productToUpdate);
    }

    public void deleteProduct( Long id){
        // Check if tem to be update is available in the database
        Product productFromDb = productRepository.findById(id).get();
        // Delete the new item from the repository
        if(productFromDb != null){
            productRepository.deleteById(id);
        }
    }
}
