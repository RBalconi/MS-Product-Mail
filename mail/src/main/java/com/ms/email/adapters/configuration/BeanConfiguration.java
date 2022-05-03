package com.ms.email.adapters.configuration;

import com.ms.email.MsEmailApplication;
import com.ms.email.application.ports.EmailRepositoryPort;
import com.ms.email.application.ports.EmailServicePort;
import com.ms.email.application.ports.SendEmailServicePort;
import com.ms.email.application.services.EmailServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;

import javax.management.modelmbean.ModelMBean;

@Configuration
@ComponentScan(basePackageClasses = MsEmailApplication.class)
public class BeanConfiguration {

    @Bean
    public EmailServiceImpl emailService(EmailRepositoryPort emailRepositoryPort, SendEmailServicePort sendEmailServicePort) {
        return new EmailServiceImpl(emailRepositoryPort, sendEmailServicePort);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
