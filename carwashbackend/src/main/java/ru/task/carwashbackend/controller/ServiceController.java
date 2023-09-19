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
import ru.task.carwashbackend.entity.Service;
import ru.task.carwashbackend.service.ServiceService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/services")
@Tag(name = "Услуги", description = "API для управления услугами автомойки.")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceService serviceService;

    @Operation(
            summary = "Все услуги",
            description = "Получение списка всех доступных услуг в автомойке.",
            tags = {"services", "get"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Service.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @CrossOrigin
    @GetMapping
    public ResponseEntity<?> getServices() {
        List<Service> services = serviceService.findAll();
        return services != null && !services.isEmpty()
                ? new ResponseEntity<>(services, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Добавление услуги",
            description = "Добавление новой услуги в автомойке.",
            tags = {"services", "post"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> saveService(@RequestBody Map<String, Object> data) {
        serviceService.save((String) data.get("serviceName"));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
            summary = "Удаление услуги",
            description = "Удаление непопулярных услуг в автомойке.",
            tags = {"services", "delete"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteService(@PathVariable(name = "id") String id) {
        return serviceService.deleteById(UUID.fromString(id))
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @Operation(
            summary = "Обновление услуги",
            description = "Изменение предоставляемых услуг в автомойке.",
            tags = {"services", "put"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @CrossOrigin
    @PutMapping
    public ResponseEntity<?> updateService(@RequestBody Map<String, Object> data) {
        String id = data.get("serviceId").toString();
        System.out.println("id");
        Service service = new Service(UUID.fromString(id), (String) data.get("serviceName"));
        return serviceService.update(service)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
