package com.shopms.product;

public record ProductRegistrationRequest(
        String name,
        Integer quantity) {
}
