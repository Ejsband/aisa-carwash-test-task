package ru.task.carwashbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.task.carwashbackend.entity.Appointment;
import ru.task.carwashbackend.entity.User;

import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    List<Appointment> findByUserName(String name);
}
