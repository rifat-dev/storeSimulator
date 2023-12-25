package com.rifat.storeSimulator.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseItemDTO {
    
    private String productName;
    private int quantity;

    public PurchaseItemDTO(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

}
