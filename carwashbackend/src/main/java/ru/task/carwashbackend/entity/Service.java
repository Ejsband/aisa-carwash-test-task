package ru.task.carwashbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
public class Service {

    @Id
    @Column(name = "id")
    private UUID serviceId;
    @Column(name = "name")
    private String serviceName;

    public Service(UUID serviceId, String serviceName) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
    }
}
