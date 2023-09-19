package ru.task.carwashbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.task.carwashbackend.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<User, UUID> {
    User findByName(String name);
}
