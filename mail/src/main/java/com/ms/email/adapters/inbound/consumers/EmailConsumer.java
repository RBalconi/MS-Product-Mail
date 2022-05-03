package com.ms.email.adapters.inbound.consumers;

import com.ms.email.application.domain.Email;
import com.ms.email.application.domain.enumerations.Situation;
import com.ms.email.application.ports.EmailServicePort;
import com.shopms.rabbitmq.dtos.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailConsumer {

    @Autowired
    public EmailServicePort emailServicePort;

//    @RabbitListener(queues = "${spring.rabbitmq.queue}")
//    public void listen(@Payload EmailDto emailDto) {
//        var email = new Email();
//        BeanUtils.copyProperties(emailDto, email);
//        emailServicePort.sendEmail(email);
//        log.info("Email sent to " + email.getEmailTo() + " with Status " + email.getSituation().name());
//    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload MessageDto message) {
        var email = new Email();
        email.setEmailFrom("r_balconi@outlook.com");
        email.setEmailTo("rbalconi.dev@gmail.com");
        email.setSubject("Teste");
        email.setSituation(Situation.SENT);
        email.setBody(message.getMessage());
        emailServicePort.sendEmail(email);
        log.info("Email sent to " + email.getEmailTo() + " with Status " + email.getSituation().name());
        System.out.println(message);
    }



//    @RabbitListener(queues = "${spring.rabbitmq.queue}")
//    public void listen(Object object) {
//        System.out.println(object);
//
//    }

}
