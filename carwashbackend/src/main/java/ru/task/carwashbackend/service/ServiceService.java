package ru.task.carwashbackend.service;

import ru.task.carwashbackend.entity.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServiceService {
    List<Service> findAll();
    Optional<Service> findById(UUID id);
    void save(String serviceName);
    Boolean deleteById(UUID id);
    Boolean update(Service service);

}
