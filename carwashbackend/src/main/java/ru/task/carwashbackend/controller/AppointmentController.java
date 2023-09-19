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
import ru.task.carwashbackend.entity.Appointment;
import ru.task.carwashbackend.service.AppointmentService;
import ru.task.carwashbackend.service.ServiceService;
import ru.task.carwashbackend.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@Tag(name = "Записи", description = "API для управления записями в автомойку.")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final UserService userService;
    private final ServiceService serviceService;

    @Operation(
            summary = "Все записи",
            description = "Получение списка записей в автомойку.",
            tags = {"appointments", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Appointment.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @CrossOrigin
    @GetMapping
    public ResponseEntity<?> getAppointments() {
        List<Appointment> appointments = appointmentService.findAll();
        return appointments != null && !appointments.isEmpty()
                ? new ResponseEntity<>(appointments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Записи пользователя",
            description = "Получение списка записей пользователя в автомойку.",
            tags = {"appointments", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Appointment.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @CrossOrigin
    @GetMapping("/{userName}")
    public ResponseEntity<?> getAppointmentsByUserName(@PathVariable String userName) {
        List<Appointment> appointments = appointmentService.findByUserName(userName);
        return appointments != null && !appointments.isEmpty()
                ? new ResponseEntity<>(appointments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @Operation(
//            summary = "Сохранени записи",
//            description = "Сохранение записи пользователя.",
//            tags = {"appointments", "post"})
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema())}),
//            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
//            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
//    @CrossOrigin
//    @PostMapping
//    public ResponseEntity<?> saveService(@RequestBody Map<String, Object> data) {
//        System.out.println((String) data.get("userName"));
//        System.out.println((String) data.get("serviceId"));
//        String name = (String) data.get("userName");
//        UUID id = UUID.fromString((String) data.get("serviceId"));
//        User user = userService.findByName(name);
//        Service service = serviceService.findById(id).get();
//        System.out.println(service.getServiceName());
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
}
