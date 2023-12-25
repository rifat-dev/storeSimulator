package com.rifat.storeSimulator.repository;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rifat.storeSimulator.model.ProductStore;

public interface ProductStoreRepository extends JpaRepository<ProductStore, UUID> {

    Optional<ProductStore> findByStoreIdAndProductId(UUID storeId, UUID productId);

    Optional<ProductStore> findTopByProductIdOrderByPriceAsc(UUID productId);

    List<ProductStore> findByStoreId(UUID storeId);

}
