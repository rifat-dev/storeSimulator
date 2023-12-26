package com.rifat.storeSimulator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rifat.storeSimulator.DTO.AvailableQuantityProductsInStoreDTO;
import com.rifat.storeSimulator.DTO.CartForComparisonDTO;
import com.rifat.storeSimulator.DTO.CartPurchaseDTO;
import com.rifat.storeSimulator.DTO.PurchaseItemDTO;
import com.rifat.storeSimulator.model.Product;
import com.rifat.storeSimulator.model.ProductStore;
import com.rifat.storeSimulator.model.Store;
import com.rifat.storeSimulator.repository.ProductStoreRepository;
import com.rifat.storeSimulator.repository.StoreRepository;

@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final ProductStoreRepository productStoreRepository;
    private final ProductService productService;
    
    @Autowired
    public StoreService(StoreRepository storeRepository, ProductStoreRepository productStoreRepository, ProductService productService) {
        this.storeRepository = storeRepository;
        this.productStoreRepository = productStoreRepository;
        this.productService = productService;
    }

    public Optional<ProductStore> changeProductQuantity(String storeName, String productName, int diffQuantity) {
        Optional<Store> optCurStore = this.storeRepository.findByName(storeName);
        Optional<Product> optCurProduct = this.productService.getProductByName(productName);
        if (optCurStore.isEmpty() || optCurProduct.isEmpty()) {
            return null;
        }

        UUID curStoreId = optCurStore.get().getId();
        UUID curProductId = optCurProduct.get().getId();

        Optional<ProductStore> optCurProductStore = this.productStoreRepository.findByStoreIdAndProductId(curStoreId, curProductId);
        ProductStore curProductStore = null;
        if (optCurProductStore.isPresent()) {
            curProductStore = optCurProductStore.get();
            int curQuantity = curProductStore.getQuantity();

            if (diffQuantity + curQuantity < 0) {
                return null;
            } else {
                curProductStore.setQuantity(diffQuantity + curQuantity);
            }

            this.productStoreRepository.save(curProductStore);
        } 
        return Optional.ofNullable(curProductStore);
    }

    public Optional<ProductStore> changeProductPrice(String storeName, String productName, int diffPrice) {
        Optional<Store> optCurStore = this.storeRepository.findByName(storeName);
        Optional<Product> optCurProduct = this.productService.getProductByName(productName);
        if (optCurStore.isEmpty() || optCurProduct.isEmpty()) {
            return null;
        }

        UUID curStoreId = optCurStore.get().getId();
        UUID curProductId = optCurProduct.get().getId();

        Optional<ProductStore> optCurProductStore = this.productStoreRepository.findByStoreIdAndProductId(curStoreId, curProductId);
        ProductStore curProductStore = null;
        if (optCurProductStore.isPresent()) {
            curProductStore = optCurProductStore.get();
            double curPrice = curProductStore.getPrice();

            if (diffPrice + curPrice < 0) {
                return null;
            } else {
                curProductStore.setPrice(diffPrice + curPrice);
            }

            this.productStoreRepository.save(curProductStore);
        } 
        return Optional.ofNullable(curProductStore);
    }

    public Optional<ProductStore> findStoreWithCheapestProduct(String productName) {
        Optional<Product> optCurProduct = this.productService.getProductByName(productName);
        if (optCurProduct.isEmpty()) {
            return null;
        }

        UUID curProductId = optCurProduct.get().getId();

        return this.productStoreRepository.findTopByProductIdOrderByPriceAsc(curProductId);
    }

    public List<AvailableQuantityProductsInStoreDTO> findQuantityProductsStoreMoney(String storeName, double amount) {
        Optional<Store> optCurStore = this.storeRepository.findByName(storeName);
        if (optCurStore.isEmpty()) {
            return null;
        }

        UUID curStoreId = optCurStore.get().getId();
        List<ProductStore> listOfAllProducts = this.productStoreRepository.findByStoreId(curStoreId);
        List<AvailableQuantityProductsInStoreDTO> result = new ArrayList<>();

        for (ProductStore curItem: listOfAllProducts) {
            double pricePerItem = curItem.getPrice();
            int maxQuantity = (int) (amount / pricePerItem);

            if (maxQuantity > curItem.getQuantity()) {
                AvailableQuantityProductsInStoreDTO curItemOfList = 
                    new AvailableQuantityProductsInStoreDTO(
                        storeName, 
                        curItem.getQuantity(), 
                        curItem.getQuantity() * pricePerItem
                    );
                result.add(curItemOfList);
            } else if (maxQuantity > 0) {
                AvailableQuantityProductsInStoreDTO curItemOfList = 
                    new AvailableQuantityProductsInStoreDTO(
                        storeName, 
                        maxQuantity, 
                        maxQuantity * pricePerItem
                    );
                result.add(curItemOfList);
            } else {
                continue;
            }
        }

        return result;
    }

    public double buyBatchOfProducts(CartPurchaseDTO cartPurchaseDTO, boolean changeQuantity) {
        double totalCost = 0.0;
        String curStoreName = cartPurchaseDTO.getStoreName();
        Optional<Store> optCurStore = this.storeRepository.findByName(curStoreName);

        if (optCurStore.isEmpty()) {
            return 0.0;
        }
        
        UUID curStoreId = optCurStore.get().getId();
        List<PurchaseItemDTO> itemsToBuy = cartPurchaseDTO.getItemsToBuy();

        for (PurchaseItemDTO purchaseItem : itemsToBuy) {
            String curProductName = purchaseItem.getProductName();
            Optional<Product> optCurProduct = this.productService.getProductByName(curProductName);
            if (optCurProduct.isEmpty()) {
                continue;
            }
            UUID curProductId = optCurProduct.get().getId();
            int curQuantity = purchaseItem.getQuantity();
            Optional<ProductStore> optCurProductStore = this.productStoreRepository.findByStoreIdAndProductId(curStoreId, curProductId);
            if (optCurProductStore.isEmpty()) {
                continue;
            }
            ProductStore curProductStore = optCurProductStore.get();
            if (curProductStore.getQuantity() < curQuantity) {
                curQuantity = curProductStore.getQuantity();
            }
            if (changeQuantity) {
                this.changeProductQuantity(curStoreName, curProductName, -curQuantity);
            }
            totalCost += curQuantity * curProductStore.getPrice();
        }

        return totalCost;
    }

    public Store findBatchOfProducts(CartForComparisonDTO cartForComparisonDTO) {
        List<PurchaseItemDTO> itemsToBuy = cartForComparisonDTO.getItemsToBuy();
        List<Store> storesList = storeRepository.findAll();
        Store returnStore = new Store();
        double minConst = Double.MAX_VALUE;

        for (Store curStore : storesList) {
            UUID storeUUID = curStore.getId();
            double totalCost = 0.0;

            for (PurchaseItemDTO purchaseItem : itemsToBuy) {
                String curProductName = purchaseItem.getProductName();
                Optional<Product> optCurProduct = this.productService.getProductByName(curProductName);
                if (optCurProduct.isEmpty()) {
                    break;
                }
                UUID curProductId = optCurProduct.get().getId();
                int curQuantity = purchaseItem.getQuantity();
                Optional<ProductStore> optCurProductStore = this.productStoreRepository.findByStoreIdAndProductId(storeUUID, curProductId);
                if (optCurProductStore.isEmpty()) {
                    break;
                }
                ProductStore curProductStore = optCurProductStore.get();
                if (curProductStore.getQuantity() < curQuantity) {
                    break;
                }
                totalCost += curQuantity * curProductStore.getPrice();
            }

            if (totalCost < minConst) {
                minConst = totalCost;
                returnStore = curStore;
            }
        }

        return returnStore;
    }

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }
}
