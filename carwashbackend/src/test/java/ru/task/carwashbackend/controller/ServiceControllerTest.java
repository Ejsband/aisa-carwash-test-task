package ru.task.carwashbackend.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.task.carwashbackend.entity.Service;
import ru.task.carwashbackend.service.ServiceService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceControllerTest {

    @Mock
    private ServiceService serviceService;

    @InjectMocks
    private ServiceController serviceController;

    private UUID uuid;
    private List<Service> serviceList;

    @BeforeEach
    public void setup() {
        uuid = UUID.randomUUID();
        serviceList = new ArrayList<>();
        Service service = new Service();
        serviceList.add(service);
    }

    @Test
    void getServices() {
        when(serviceService.findAll()).thenReturn(serviceList);
        ResponseEntity<?> response = serviceController.getServices();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);
        verify(serviceService, times(1)).findAll();
    }

    @Test
    void saveService() {
        Map<String, Object> map = new HashMap<>();
        map.put("serviceName", "test");
        ResponseEntity<?> response = serviceController.saveService(map);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(serviceService, times(1)).save(anyString());
    }

    @Test
    void deleteService() {
        when(serviceService.deleteById(any(UUID.class))).thenReturn(true);
        ResponseEntity<?> response = serviceController.deleteService(uuid.toString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(serviceService, times(1)).deleteById(any(UUID.class));
    }

    @Test
    public void testUpdateService() {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("serviceId", UUID.randomUUID().toString());
        requestData.put("serviceName", "testName");
        when(serviceService.update(any(Service.class))).thenReturn(true);
        ResponseEntity<?> responseEntity = serviceController.updateService(requestData);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(serviceService, times(1)).update(any());
    }
}