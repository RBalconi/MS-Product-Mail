package com.ms.email.application.ports;

import com.ms.email.application.domain.Email;

public interface EmailServicePort {
    Email sendEmail(Email email);
    Email saveEmail(Email email);
//    List<Email> findAll(PageInfo pageInfo);
//    Optional<Email> findById(UUID id);
}
