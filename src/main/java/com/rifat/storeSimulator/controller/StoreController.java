package com.rifat.storeSimulator.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rifat.storeSimulator.DTO.AvailableQuantityProductsInStoreDTO;
import com.rifat.storeSimulator.DTO.CartForComparisonDTO;
import com.rifat.storeSimulator.DTO.CartPurchaseDTO;
import com.rifat.storeSimulator.model.ProductStore;
import com.rifat.storeSimulator.model.Store;
import com.rifat.storeSimulator.service.StoreService;

@RestController
@RequestMapping("api/store")
public class StoreController {
    
    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/changeProductQuantity")
    public ResponseEntity<Optional<ProductStore>> changeProductQuantity(
            @RequestParam String storeName,
            @RequestParam String productName,
            @RequestParam int diffQuantity) {
        Optional<ProductStore> result = storeService.changeProductQuantity(storeName, productName, diffQuantity);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/changeProductPrice")
    public ResponseEntity<Optional<ProductStore>> changeProductPrice(
            @RequestParam String storeName,
            @RequestParam String productName,
            @RequestParam int diffPrice) {
        Optional<ProductStore> result = storeService.changeProductPrice(storeName, productName, diffPrice);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/findStoreWithCheapestProduct")
    public ResponseEntity<Optional<ProductStore>> findStoreWithCheapestProduct(
            @RequestParam String productName) {
        Optional<ProductStore> result = storeService.findStoreWithCheapestProduct(productName);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/findQuantityProductsStoreMoney")
    public ResponseEntity<List<AvailableQuantityProductsInStoreDTO>> findQuantityProductsStoreMoney(
            @RequestParam String storeName,
            @RequestParam double amount) {
        List<AvailableQuantityProductsInStoreDTO> result = storeService.findQuantityProductsStoreMoney(storeName, amount);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/buyBatchOfProducts")
    public ResponseEntity<Double> buyBatchOfProducts(
            @RequestBody CartPurchaseDTO cartPurchaseDTO,
            @RequestParam boolean changeQuantity) {
        double result = storeService.buyBatchOfProducts(cartPurchaseDTO, changeQuantity);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/findCheapestBatchOfProducts")
    public ResponseEntity<Store> findCheapestBatchOfProducts(
            @RequestBody CartForComparisonDTO cartForComparisonDTO) {
        Store result = storeService.findBatchOfProducts(cartForComparisonDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAllStores")
    public ResponseEntity<List<Store>> getAllStores() {
        List<Store> result = storeService.getAllStores();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/saveStore")
    public ResponseEntity<Store> saveStore(@RequestBody Store store) {
        Store savedStore = storeService.saveStore(store);
        return new ResponseEntity<>(savedStore, HttpStatus.CREATED);
    }
}
