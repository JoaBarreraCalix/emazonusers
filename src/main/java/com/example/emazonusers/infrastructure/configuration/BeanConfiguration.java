//infrastructure.configuration.BeanConfiguration
package com.example.emazonusers.infrastructure.configuration;

import com.example.emazonusers.domain.api.IUserServicePort;

import com.example.emazonusers.domain.spi.IUserPersistencePort;
import com.example.emazonusers.domain.usecase.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public IUserServicePort userServicePort(IUserPersistencePort userPersistencePort) {
        return new UserUseCase(userPersistencePort);
    }

}
