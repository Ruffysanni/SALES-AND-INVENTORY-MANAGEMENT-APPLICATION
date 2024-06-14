package com.INGRYD.INGRYD_CRM.controller;
import com.INGRYD.INGRYD_CRM.model.Product;
import com.INGRYD.INGRYD_CRM.service.ProductService;

//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/allProducts")
    public List<Product> getAllItems(){
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Product getItemById(@PathVariable Long productId){
        return productService.getProductById(productId);
    }

    @PostMapping
    //public Product postNewProduct(@RequestBody @Valid Product product){
    public Product postNewProduct(@RequestBody Product product){
        return productService.postNewProduct(product);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@RequestBody Product product, @PathVariable Long productId){
        return productService.updateProduct(product, productId);
    }

    @DeleteMapping("/product/{productId}")
    public void deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
//    public void deleteProduct(@RequestBody Product product,@PathVariable Long productId){
//        productService.deleteProduct(product, productId);
    }
}
