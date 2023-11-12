package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.example.demo.entities.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestInstance(Lifecycle.PER_CLASS)
class EntityUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    private Doctor d1;

    private Patient p1;

    private Room r1;

    private Appointment a1;
    private Appointment a2;
    private Appointment a3;

    @BeforeEach
    void setUp() {

        d1 = new Doctor("DoctorFirstName", "DoctorLastName", 45, "doctor@example.com");
        entityManager.persistAndFlush(d1);

        p1 = new Patient("PatientFirstName", "PatientLastName", 60, "patient@example.com");
        entityManager.persistAndFlush(p1);

        r1 = new Room("example_room");
        entityManager.persistAndFlush(r1);

        a1 = new Appointment(p1, d1, r1, LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        a2 = new Appointment(p1, d1, r1, LocalDateTime.of(2023, 4, 10, 10, 30), LocalDateTime.now().plusHours(2));
        a3 = new Appointment(p1, d1, r1, LocalDateTime.of(2023, 4, 10, 10, 30), LocalDateTime.now().plusHours(3));
        entityManager.persistAndFlush(a1);
        entityManager.persistAndFlush(a2);
        entityManager.persistAndFlush(a3);
    }

    @Test
    void testDoctorEntityPersistence() {

        Doctor retrievedDoctor = entityManager.find(Doctor.class, d1.getId());

        assertThat(retrievedDoctor).isNotNull();
        assertThat(retrievedDoctor.getFirstName()).isEqualTo("DoctorFirstName");
        assertThat(retrievedDoctor.getLastName()).isEqualTo("DoctorLastName");
        assertThat(retrievedDoctor.getAge()).isEqualTo(45);
        assertThat(retrievedDoctor.getEmail()).isEqualTo("doctor@example.com");
    }

    @Test
    void testPatientEntityPersistence() {

        Patient retrievedPatient = entityManager.find(Patient.class, p1.getId());

        assertThat(retrievedPatient).isNotNull();
        assertThat(retrievedPatient.getFirstName()).isEqualTo("PatientFirstName");
        assertThat(retrievedPatient.getLastName()).isEqualTo("PatientLastName");
        assertThat(retrievedPatient.getAge()).isEqualTo(60);
        assertThat(retrievedPatient.getEmail()).isEqualTo("patient@example.com");
    }

    @Test
    void testRoomEntityPersistence() {

        Room retrievedRoom = entityManager.find(Room.class, r1.getRoomName());

        assertThat(retrievedRoom).isNotNull();
        assertThat(retrievedRoom.getRoomName()).isEqualTo("example_room");
    }

    @Test
    void testAppointmentEntityPersistence() {

        Appointment retrievedAppointment = entityManager.find(Appointment.class, a2.getId());

        assertThat(retrievedAppointment).isNotNull();
        assertThat(retrievedAppointment.getDoctor().getFirstName()).isEqualTo("DoctorFirstName");
        assertThat(retrievedAppointment.getPatient().getLastName()).isEqualTo("PatientLastName");
        assertThat(retrievedAppointment.getRoom().getRoomName()).isEqualTo("example_room");
        assertThat(retrievedAppointment.getStartsAt()).isEqualTo(LocalDateTime.of(2023, 4, 10, 10, 30));
    }

    @Test
    void testAppointmentEntityOverlapsMethod() {

        Appointment retrievedAppointment1 = entityManager.find(Appointment.class, a1.getId());
        Appointment retrievedAppointment2 = entityManager.find(Appointment.class, a3.getId());
        Appointment retrievedAppointment3 = entityManager.find(Appointment.class, a3.getId());

        assertThat(retrievedAppointment1.overlaps(retrievedAppointment2)).isFalse();
        assertThat(retrievedAppointment2.overlaps(retrievedAppointment3)).isTrue();
    }
}