package com.INGRYD.INGRYD_CRM.controller;

import com.INGRYD.INGRYD_CRM.model.Item;
import com.INGRYD.INGRYD_CRM.model.Product;
import com.INGRYD.INGRYD_CRM.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/allProducts")
    public List<Product> getAllItems(){
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Product getItemById(Long productId){
        return productService.getProductById(productId);
    }

    @PostMapping
    public Product postNewProduct(Product product){
        return productService.postNewProduct(product);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(Product product, Long productId){
        return productService.updateProduct(product, productId);
    }

    @DeleteMapping("/product/{productId}")
    public void deleteProduct(Product product, Long productId){
        productService.deleteProduct(product, productId);
    }
}
