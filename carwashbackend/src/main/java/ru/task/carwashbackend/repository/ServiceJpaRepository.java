package ru.task.carwashbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.task.carwashbackend.entity.Service;

import java.util.UUID;

public interface ServiceJpaRepository extends JpaRepository<Service, UUID> {
}
