package ru.task.carwashbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.task.carwashbackend.repository.ServiceJpaRepository;
import ru.task.carwashbackend.service.ServiceService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {

    private final ServiceJpaRepository serviceJpaRepository;

    @Override
    public List<ru.task.carwashbackend.entity.Service> findAll() {
        try {
            return serviceJpaRepository.findAll();
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Error finding all services.", e);
        }
    }

    @Override
    public Optional<ru.task.carwashbackend.entity.Service> findById(UUID id) {
        try {
            return serviceJpaRepository.findById(id);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Error finding service with id: " + id, e);
        }
    }

    @Override
    public void save(String serviceName) {
        try {
            serviceJpaRepository.save(new ru.task.carwashbackend.entity.Service(UUID.randomUUID(), serviceName));
        } catch(DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving service with name: " + serviceName, e);
        }
    }

    @Override
    public Boolean deleteById(UUID id) {
        try {
            Optional<ru.task.carwashbackend.entity.Service> serviceOptional = findById(id);
            serviceOptional.ifPresent(serviceJpaRepository::delete);
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean update(ru.task.carwashbackend.entity.Service service) {
        try {
            serviceJpaRepository.save(service);
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
}
