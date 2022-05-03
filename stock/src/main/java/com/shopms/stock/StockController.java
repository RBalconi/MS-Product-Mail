package com.shopms.stock;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("v1/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping("{productId}&{quantity}")
    public StockResponse registerProductInStock(@PathVariable("productId") String productId,
                                                @PathVariable("quantity") Integer quantity) {

        log.info("StockController.registerProductInStock() {} with {} qtt", productId, quantity);
        stockService.registerProduct(productId, quantity);
        return new StockResponse(productId, quantity);
    }
}
