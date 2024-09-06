package com.example.emazonusers.infrastructure.out.jpa.adapter;

import com.example.emazonusers.domain.model.User;
import com.example.emazonusers.infrastructure.out.jpa.entity.UserEntity;
import com.example.emazonusers.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.example.emazonusers.infrastructure.out.jpa.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserJpaAdapterTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    @InjectMocks
    private UserJpaAdapter userJpaAdapter;

    private User user;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User(1L, "John", "Doe", 123456789L, "+571234567890", null, "john.doe@example.com", "password123", 2L);
        userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setFirstName("John");
        userEntity.setLastName("Doe");
        userEntity.setDocumentId(123456789L);
        userEntity.setEmail("john.doe@example.com");
    }

    @Test
    void registerUser_successful() {
        when(userEntityMapper.toEntity(user)).thenReturn(userEntity);

        userJpaAdapter.registerUser(user);

        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    void findUserByDocumentId_successful() {
        when(userRepository.findByDocumentId(user.getDocumentId())).thenReturn(Optional.of(userEntity));
        when(userEntityMapper.toDomain(userEntity)).thenReturn(user);

        Optional<User> result = userJpaAdapter.findUserByDocumentId(user.getDocumentId());

        assertEquals(Optional.of(user), result);
    }

    @Test
    void findUserByEmail_successful() {
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(userEntity));
        when(userEntityMapper.toDomain(userEntity)).thenReturn(user);

        Optional<User> result = userJpaAdapter.findUserByEmail(user.getEmail());

        assertEquals(Optional.of(user), result);
    }
}
