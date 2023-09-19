package ru.task.carwashbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
public class Appointment {

    @Id
    @Column(name = "id")
    private UUID appointmentId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id", insertable=false, updatable=false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "service_id", insertable=false, updatable=false)
    private Service service;

    @Column(name = "day")
    private String date;

}
