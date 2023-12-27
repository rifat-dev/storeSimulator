package com.rifat.storeSimulator.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewProductStoreDTO {
    
    private String store;
    private String product;
    private double price;
    private int quantity;


    public NewProductStoreDTO() {
    }

    public NewProductStoreDTO(String store, String product, double price, int quantity) {
        this.store = store;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

}
