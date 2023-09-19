package ru.task.carwashbackend.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.task.carwashbackend.entity.User;
import ru.task.carwashbackend.repository.UserJpaRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserJpaRepository userJpaRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void findAllTest() {
        when(userJpaRepository.findAll()).thenReturn(new ArrayList<>());
        userService.findAll();
        verify(userJpaRepository).findAll();
    }

    @Test
    void findByIdTest() {
        UUID uuid = UUID.randomUUID();
        when(userJpaRepository.findById(uuid)).thenReturn(Optional.empty());
        userService.findById(uuid);
        verify(userJpaRepository).findById(uuid);
    }

    @Test
    void saveTest() {
        User user = new User(); // replace with an actual User object
        userService.save(user);
        verify(userJpaRepository).save(user);
    }

    @Test
    void deleteByIdTest() {
        UUID uuid = UUID.randomUUID();
        when(userJpaRepository.findById(uuid)).thenReturn(Optional.empty());
        userService.deleteById(uuid);
        verify(userJpaRepository).findById(uuid);
        verify(userJpaRepository, never()).delete(any());
    }

    @Test
    void updateTest() {
        UUID uuid = UUID.randomUUID();
        when(userJpaRepository.findById(uuid)).thenReturn(Optional.empty());
        userService.update(uuid);
        verify(userJpaRepository).findById(uuid);
        verify(userJpaRepository, never()).save(any());
    }
}