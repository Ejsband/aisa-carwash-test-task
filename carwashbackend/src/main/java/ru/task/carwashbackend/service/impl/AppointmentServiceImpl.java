package ru.task.carwashbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.task.carwashbackend.entity.Appointment;
import ru.task.carwashbackend.repository.AppointmentRepository;
import ru.task.carwashbackend.service.AppointmentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private  final AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> findAll() {
        try {
            return appointmentRepository.findAll();
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve appointments", e);
        }
    }

    @Override
    public List<Appointment> findByUserName(String name) {
        try {
            return appointmentRepository.findByUserName(name);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve appointments", e);
        }
    }

    @Override
    public Appointment save(Appointment appointment) {
        try {
            return appointmentRepository.save(appointment);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve appointments", e);
        }
    }
}
