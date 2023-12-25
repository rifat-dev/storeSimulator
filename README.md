# Симулятор магазина 🏪
Ещё не сделано
<p>
    <strong>ООП ЛАБОРАТОРНАЯ РАБОТА №3</strong> <br>
    ...
    <strong>java 17, spring boot 3.2.0</strong> и <strong>Spring Data JPA</strong>.
</p>

## Установка и запуск

```bash
# Step 1: Clone the repository
git clone 

# Step 2: Navigate to the project directory
cd 

# Step 3: Running container
docker-compose up -d

# Step 4: Enable execution
chmod +x 

# Step 5: Run script


# Step 6: Access the application
Open browser and go to http://localhost:8080
```

## Структура работы в виде дерева:
```
./src
├── main
│   ├── java
│   │   └── com
│   │       └── rifat
│   │           └── storeSimulator
│   │               ├── DTO
│   │               │   ├── AvailableQuantityProductsInStoreDTO.java
│   │               │   ├── CartPurchaseDTO.java
│   │               │   └── PurchaseItemDTO.java
│   │               ├── StoreSimulatorApplication.java
│   │               ├── config
│   │               ├── controller
│   │               │   ├── ProductController.java
│   │               │   ├── StoreController.java
│   │               │   └── UserController.java
│   │               ├── model
│   │               │   ├── Product.java
│   │               │   ├── ProductStore.java
│   │               │   ├── Store.java
│   │               │   ├── User.java
│   │               │   └── UserRole.java
│   │               ├── repository
│   │               │   ├── ProductRepository.java
│   │               │   ├── ProductStoreRepository.java
│   │               │   ├── StoreRepository.java
│   │               │   └── UserRepository.java
│   │               └── service
│   │                   ├── ProductService.java
│   │                   ├── StoreService.java
│   │                   └── UserService.java
│   └── resources
│       ├── application.properties
│       ├── static
│       └── templates
│           └── hello.html
└── test
    └── java
        └── com
            └── rifat
                └── storeSimulator
                    └── StoreSimulatorApplicationTests.java
```

<p class="note">
    ITMO University<br>
    Saint Petersburg Autumn 2023
</p>