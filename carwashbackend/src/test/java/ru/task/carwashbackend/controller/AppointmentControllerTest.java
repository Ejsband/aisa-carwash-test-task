package ru.task.carwashbackend.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.task.carwashbackend.entity.Appointment;
import ru.task.carwashbackend.service.AppointmentService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppointmentControllerTest {

    @InjectMocks
    private AppointmentController appointmentController;

    @Mock
    private AppointmentService appointmentService;

    private List<Appointment> appointmentList;


    @BeforeEach
    public void setup() {
        appointmentList = new ArrayList<>();
        Appointment appointment = new Appointment();
        appointmentList.add(appointment);
    }


    @Test
    public void testGetAppointments() {
        when(appointmentService.findAll()).thenReturn(appointmentList);
        ResponseEntity<?> response = appointmentController.getAppointments();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof List);
        verify(appointmentService, times(1)).findAll();
    }
}