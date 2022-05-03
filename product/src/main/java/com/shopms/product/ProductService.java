package com.shopms.product;

//import com.shopms.amqp.RabbitMQMessageProducer;
import com.shopms.product.amqp.RabbitMQService;
import com.shopms.rabbitmq.dtos.MessageDto;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
//@ComponentScan("com.shopms.amqp")
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RabbitMQService rabbitMQService;

    public void registerProduct(ProductRegistrationRequest request) {
        Product product = Product.builder()
                .name(request.name())
                .quantity(request.quantity())
                .build();

        productRepository.saveAndFlush(product);

        StockResponse stockResponse = setStockRegister(product);

        if (stockResponse == null) {
            throw new RuntimeException("Stock service is not return a response");
        }

        sendToDataMailQueue(product, stockResponse);
    }

    private StockResponse setStockRegister(Product product) {
        return restTemplate.getForObject(
                // FIXME: parameter
                "http://STOCK:8081/v1/stock/{productId}&{quantity}",
                StockResponse.class,
                product.getId(),
                product.getQuantity()
        );
    }

    private void sendToDataMailQueue(Product product, StockResponse stockResponse) {
        MessageDto messageDto = new MessageDto(
                String.format("A new product '%s' has been registered. With %s units \n TEST SYSTEM",
                product.getName(),
                stockResponse.getQuantity()));

        rabbitMQService.sendMessage(messageDto);
    }
}
