package com.rifat.storeSimulator.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvailableQuantityProductsInStoreDTO {

    private String productName;
    private int quantity;
    private double totalCost;

    public AvailableQuantityProductsInStoreDTO(String productName, int quantity, double totalCost) {
        this.productName = productName;
        this.quantity = quantity;
        this.totalCost = totalCost;
    }

}
