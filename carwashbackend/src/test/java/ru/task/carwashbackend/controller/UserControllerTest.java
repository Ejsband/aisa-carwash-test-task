package ru.task.carwashbackend.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.task.carwashbackend.entity.User;
import ru.task.carwashbackend.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private UUID uuid;
    private List<User> userList;

    @BeforeEach
    public void setup() {
        uuid = UUID.randomUUID();
        userList = new ArrayList<>();
        User user = new User();
        userList.add(user);
    }

    @Test
    void getUsers() {
        when(userService.findAll()).thenReturn(userList);
        ResponseEntity<?> response = userController.getUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);
        verify(userService, times(1)).findAll();
    }

    @Test
    void deleteUser() {
        when(userService.deleteById(any(UUID.class))).thenReturn(true);
        ResponseEntity<?> response = userController.deleteUser(uuid.toString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService, times(1)).deleteById(any(UUID.class));
    }

    @Test
    void updateUser() {
        when(userService.update(any(UUID.class))).thenReturn(true);
        ResponseEntity<?> response = userController.updateUser(uuid.toString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService, times(1)).update(any(UUID.class));
    }
}