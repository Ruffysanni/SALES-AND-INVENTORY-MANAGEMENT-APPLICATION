package com.INGRYD.INGRYD_CRM.service;
import com.INGRYD.INGRYD_CRM.model.Product;
import com.INGRYD.INGRYD_CRM.repository.ProductRepository;
import jakarta.mail.MessagingException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {
    final ProductRepository productRepository;
    final MessageService messageService;

    public ProductService(ProductRepository productRepository, MessageService messageService) {
        this.productRepository = productRepository;
        this.messageService = messageService;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product getProductById(Long id){
        return productRepository.findById(id).get();
    }
    @ConditionalOnProperty(value = "notification.role", havingValue = "ADMIN,SALES_REP,CUSTOMER")
    public Product postNewProduct(Product product) throws MessagingException {
        messageService.sendNewProductNotification(STR."This is to notify the arrival of a new product: Product Name: \{product.getProductName()}\nProduct Description: \{product.getDescription()}\nProduct Category: \{product.getCategory()}");
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

    public void deleteProduct(Product product,  Long id){
        // Check if tem to be update is available in the database
        Product productFromDb = productRepository.findById(id).get();
        // Delete the new item from the repository
        productRepository.delete(product);
    }
}
