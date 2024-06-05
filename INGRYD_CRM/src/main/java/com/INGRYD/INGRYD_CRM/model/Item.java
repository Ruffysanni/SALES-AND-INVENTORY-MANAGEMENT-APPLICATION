//package com.INGRYD.INGRYD_CRM.model;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//
//@Entity(name = "_items_table")
//public class Item {
//    /*
//        -	item_id (Primary Key)
//        -	sale_id (Foreign key to sale)
//        -	product_id (Foreign key to product)
//        -	quantity
//        -	unitPrice
//    * */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long item_id;
//
//    @NotBlank
//    @NotNull
//    private Long sale_id;
//    @NotBlank
//    @NotNull
//    private Long product_id;
//
//    @NotBlank
//    @NotNull
//    private Double quantity;
//
//    @NotBlank
//    @NotNull
//    private Double unitPrice;
//
//    @NotBlank
//    @NotNull
//    private Sale sale;
//
//    @NotBlank
//    @NotNull
//
//    private Product product;
//
//    public Item() {
//    }
//
//    public Item(Long item_id, Long sale_id, Long product_id, Double quantity, Double unitPrice, Sale sale, Product product) {
//        this.item_id = item_id;
//        this.sale_id = sale_id;
//        this.product_id = product_id;
//        this.quantity = quantity;
//        this.unitPrice = unitPrice;
//        this.sale = sale;
//        this.product = product;
//    }
//
//    public Long getSale_id() {
//        return sale_id;
//    }
//
//    public Long getItem_id() {
//        return item_id;
//    }
//
////    public void setSale_id(Long sale_id) {
////        this.sale_id = sale_id;
////    }
//
//    //    public void setProduct_id(Long product_id) {
////        this.product_id = product_id;
////    }
//    public Long getProduct_id() {
//        return product_id;
//    }
//
//    public Double getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Double quantity) {
//        this.quantity = quantity;
//    }
//
//    public Double getUnitPrice() {
//        return unitPrice;
//    }
//
//    public void setUnitPrice(Double unitPrice) {
//        this.unitPrice = unitPrice;
//    }
//
//}
