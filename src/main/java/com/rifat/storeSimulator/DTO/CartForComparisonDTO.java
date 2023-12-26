package com.rifat.storeSimulator.DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartForComparisonDTO {
    
    private List<PurchaseItemDTO> itemsToBuy;

    public CartForComparisonDTO(List<PurchaseItemDTO> itemsToBuy) {
        this.itemsToBuy = itemsToBuy;
    }
}
