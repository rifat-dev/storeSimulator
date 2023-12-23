package com.rifat.storeSimulator.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rifat.storeSimulator.model.ProductStore;

public interface ProductStoreRepository extends JpaRepository<ProductStore, UUID> {

}
