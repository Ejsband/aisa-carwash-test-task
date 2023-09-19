package ru.task.carwashbackend.service;

import ru.task.carwashbackend.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> findAll();
    List<Appointment> findByUserName(String name);
    Appointment save(Appointment appointment);
}
