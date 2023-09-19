package ru.task.carwashbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.task.carwashbackend.entity.User;
import ru.task.carwashbackend.repository.UserJpaRepository;
import ru.task.carwashbackend.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;

    @Override
    public List<User> findAll() {
        try {
            return userJpaRepository.findAll();
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve all users", e);
        }
    }

    @Override
    public Optional<User> findById(UUID id) {
        try {
            return userJpaRepository.findById(id);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve user by id", e);
        }
    }

    @Override
    public void save(User user) {
        try {
            userJpaRepository.save(user);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save user", e);
        }
    }

    @Override
    public Boolean deleteById(UUID id) {
        Optional<User> userOptional = findById(id);
        try {
            userOptional.ifPresent(userJpaRepository::delete);
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean update(UUID id) {
        Optional<User> userOptional = findById(id);
        try {
            userOptional.ifPresent(userJpaRepository::save);
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User findByName(String name) {
        return userJpaRepository.findByName(name);
    }
}
