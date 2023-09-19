package ru.task.carwashbackend.service;

import ru.task.carwashbackend.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    List<User> findAll();
    Optional<User> findById(UUID id);
    void save(User user);
    Boolean deleteById(UUID id);
    Boolean update(UUID id);
    User findByName(String name);
}
