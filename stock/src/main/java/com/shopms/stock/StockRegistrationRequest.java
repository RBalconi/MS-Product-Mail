package com.shopms.stock;

import java.math.BigDecimal;

public record StockRegistrationRequest(
        String name,
        Integer quantity,
        BigDecimal price) {
}
