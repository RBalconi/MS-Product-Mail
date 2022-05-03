package com.shopms.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public void registerProduct(@RequestBody ProductRegistrationRequest productRegistrationRequest) {
        log.info("ProductController.registerProduct() {}", productRegistrationRequest);
        productService.registerProduct(productRegistrationRequest);
    }
}
