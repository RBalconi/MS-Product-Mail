package com.ms.email.adapters.outbound.persistence;

import com.ms.email.adapters.outbound.persistence.entities.EmailEntity;
import com.ms.email.application.domain.Email;
import com.ms.email.application.ports.EmailRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PostgresEmailRepository implements EmailRepositoryPort {

    private final SpringDataPostgresEmailRepository emailRepository;

    public PostgresEmailRepository(final SpringDataPostgresEmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @Autowired
    public ModelMapper modelMapper;

    @Override
    public Email save(Email email) {
        var emailEntity = emailRepository.save(modelMapper.map(email, EmailEntity.class));
        return modelMapper.map(emailEntity, Email.class);
    }
}
