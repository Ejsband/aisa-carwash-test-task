package ru.task.carwashbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.task.carwashbackend.entity.User;
import ru.task.carwashbackend.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "API для управления пользователями")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Все пользователи",
            description = "Получение списка всех доступных пользователей.",
            tags = {"users", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @CrossOrigin
    @GetMapping("/")
    public ResponseEntity<?> getUsers() {
        List<User> users = userService.findAll();
        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Добавление пользователя",
            description = "Добавление нового клиента автомойки или нового администратора автомойки.",
            tags = {"users", "post"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @CrossOrigin
    @PostMapping(value = "/")
    public ResponseEntity<?> saveUser(@RequestBody Map<String, Object> data) {
        userService.save(new User(UUID.randomUUID(),
                (String) data.get("name"),
                (String) data.get("userPassword"),
                (String) data.get("userRole")
        ));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
            summary = "Удаление пользователя",
            description = "Пользователь получил услугу и больше не вернется или администратор был уволен.",
            tags = {"users", "delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") String id) {
        return userService.deleteById(UUID.fromString(id))
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(
            summary = "Обновление данных пользователя",
            description = "Администратор поменял фамилию или пользователь сменил домашний адрес.",
            tags = {"users", "put"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "id") String id) {
        return userService.update(UUID.fromString(id))
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(
            summary = "Один пользователь",
            description = "Получение пользователя по имени.",
            tags = {"users", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = User.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @CrossOrigin
    @GetMapping("/{name}")
    public ResponseEntity<?> getUserByName(@PathVariable String name) {
        System.out.println(name);
        User user = userService.findByName(name);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
