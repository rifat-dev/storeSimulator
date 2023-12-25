package com.rifat.storeSimulator.DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartPurchaseDTO {

    private String storeName;
    private List<PurchaseItemDTO> itemsToBuy;

    public CartPurchaseDTO(String storeName, List<PurchaseItemDTO> itemsToBuy) {
        this.storeName = storeName;
        this.itemsToBuy = itemsToBuy;
    }
    
}
