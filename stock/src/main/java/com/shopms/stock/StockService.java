package com.shopms.stock;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public void registerProduct(String productId, int quantity) {
        Long idProduct = Long.parseLong(productId);
        Stock stock = Stock.builder()
                .name("test")
                .idProduct(idProduct)
                .quantity(quantity)
                .build();

        stockRepository.insert(stock);
    }

}
