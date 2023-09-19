package ru.task.carwashbackend.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.task.carwashbackend.entity.Service;
import ru.task.carwashbackend.repository.ServiceJpaRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceServiceImplTest {

    @Mock
    private ServiceJpaRepository serviceJpaRepository;

    @InjectMocks
    private ServiceServiceImpl serviceService;

    private Service service;
    private UUID id;

    @BeforeEach
    public void setUp() {
        id = UUID.randomUUID();
        service = new Service(id, "test");
    }

    @Test
    void findAllTest() {
        when(serviceJpaRepository.findAll()).thenReturn(new ArrayList<>());

        serviceService.findAll();

        verify(serviceJpaRepository).findAll();
    }

    @Test
    void findByIdTest() {
        UUID uuid = UUID.randomUUID();
        when(serviceJpaRepository.findById(uuid)).thenReturn(Optional.empty());

        serviceService.findById(uuid);

        verify(serviceJpaRepository).findById(uuid);
    }

    @Test
    void saveTest() {
        String serviceName = "testService";
        serviceService.save(serviceName);

        verify(serviceJpaRepository).save(any());
    }

    @Test
    void deleteByIdTest() {
        UUID uuid = UUID.randomUUID();
        when(serviceJpaRepository.findById(uuid)).thenReturn(Optional.empty());

        serviceService.deleteById(uuid);

        verify(serviceJpaRepository).findById(uuid);
        verify(serviceJpaRepository, never()).delete(any());
    }

    @Test
    public void testUpdate() {
        serviceService.update(service);
        verify(serviceJpaRepository, times(1)).save(any(Service.class));
    }

}