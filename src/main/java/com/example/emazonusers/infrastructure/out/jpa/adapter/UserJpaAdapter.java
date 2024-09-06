package com.example.emazonusers.infrastructure.out.jpa.adapter;

import com.example.emazonusers.domain.model.User;
import com.example.emazonusers.domain.spi.IUserPersistencePort;
import com.example.emazonusers.infrastructure.out.jpa.entity.UserEntity;
import com.example.emazonusers.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.example.emazonusers.infrastructure.out.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public void registerUser(User user) {
        UserEntity userEntity = userEntityMapper.toEntity(user);
        userRepository.save(userEntity);
    }

    @Override
    public Optional<User> findUserByDocumentId(Long documentId) {
        return userRepository.findByDocumentId(documentId).map(userEntityMapper::toDomain);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email).map(userEntityMapper::toDomain);
    }
}
