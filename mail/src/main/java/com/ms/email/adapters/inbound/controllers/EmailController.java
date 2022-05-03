package com.ms.email.adapters.inbound.controllers;

import com.ms.email.adapters.dtos.EmailDto;
import com.ms.email.adapters.outbound.persistence.entities.EmailEntity;
import com.ms.email.application.domain.Email;
import com.ms.email.application.ports.EmailServicePort;
import com.ms.email.application.services.EmailServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EmailController {

    @Autowired
    private EmailServicePort emailServicePort;

    @PostMapping("/send-email")
    public ResponseEntity<Email> sendEmail(@RequestBody @Valid EmailDto emailDto) {
        var email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        emailServicePort.sendEmail(email);
        return new ResponseEntity<>(email, HttpStatus.CREATED);
    }
}
