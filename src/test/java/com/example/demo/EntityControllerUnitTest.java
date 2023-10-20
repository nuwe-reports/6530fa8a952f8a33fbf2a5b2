package com.example.demo;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.demo.controllers.*;
import com.example.demo.repositories.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.entities.*;

class EntityControllerUnitTest {

	@Nested
	@WebMvcTest(DoctorController.class)
	class DoctorControllerUnitTest {

		@MockBean
		private DoctorRepository doctorRepository;

		@Autowired
		private MockMvc mockMvc;

		@Autowired
		ObjectMapper objectMapper;

		Doctor doctor1 = new Doctor("John", "Doe", 35, "johndoe@example.com");
		Doctor doctor2 = new Doctor("Jane", "Smith", 40, "janesmith@example.com");
		List<Doctor> doctors = Arrays.asList(doctor1, doctor2);

		@Test
		void testGetAllDoctors() throws Exception {
			

			when(doctorRepository.findAll()).thenReturn(doctors);

			mockMvc.perform(get("/api/doctors")).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$[0].firstName").value("John")).andExpect(jsonPath("$[0].age").value(35))
					.andExpect(jsonPath("$[1].lastName").value("Smith"));
		}

		@Test
		void testGetDoctorById() throws Exception {

			long id = 100;

			when(doctorRepository.findById(id)).thenReturn(Optional.of(doctor1));

			mockMvc.perform(get("/api/doctors/{id}", id)).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("lastName").value("Doe"));
		}

		@Test
		void testGetDoctorByIdNotPresent() throws Exception {

			long id = 100;

			when(doctorRepository.findById(id)).thenReturn(Optional.empty());

			mockMvc.perform(get("/api/doctors/{id}", id)).andExpect(status().isNotFound());
		}

		@Test
		void testCreateDoctor() throws Exception {

			String doctor1Json = objectMapper.writeValueAsString(doctor1);

			when(doctorRepository.save(doctor1)).thenReturn(doctor1);

			mockMvc.perform(post("/api/doctor").contentType(MediaType.APPLICATION_JSON).content(doctor1Json))
					.andExpect(status().isCreated()).andExpect(jsonPath("age").value(35))
					.andExpect(jsonPath("lastName").value("Doe"));

		}

		@Test
		void testDeleteDoctor() throws Exception {

			long id = 100;
			doctor1.setId(id);

			when(doctorRepository.findById(id)).thenReturn(Optional.of(doctor1));

			mockMvc.perform(delete("/api/doctors/{id}", id)).andExpect(status().isOk());
		}

		@Test
		void testDeleteDoctorNotPresent() throws Exception {

			long id = 100;

			when(doctorRepository.findById(id)).thenReturn(Optional.empty());

			mockMvc.perform(delete("/api/doctors/{id}", id)).andExpect(status().isNotFound());
		}

		@Test
		void testDeleteAllDoctors() throws Exception {

			doNothing().when(doctorRepository).deleteAll();

			mockMvc.perform(delete("/api/doctors")).andExpect(status().isOk());
		}
	}

	@Nested
	@WebMvcTest(PatientController.class)
	class PatientControllerUnitTest {

		@MockBean
		private PatientRepository patientRepository;

		@Autowired
		private MockMvc mockMvc;

		@Autowired
		ObjectMapper objectMapper;

		Patient patient1 = new Patient("Jim", "Smith", 35, "jonah@example.com");
		Patient patient2 = new Patient("Joana", "Jander", 40, "joanajander58@example.com");
		List<Patient> patients = Arrays.asList(patient1, patient2);

		@Test
		void testgetAllPatients() throws Exception {


			when(patientRepository.findAll()).thenReturn(patients);

			mockMvc.perform(get("/api/patients")).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$[0].firstName").value("Jim"))
					.andExpect(jsonPath("$[1].age").value(40))
					.andExpect(jsonPath("$[1].lastName").value("Jander"));
		}

		@Test
		void testGetPatientById() throws Exception {

			long id = 100;

			when(patientRepository.findById(id)).thenReturn(Optional.of(patient1));

			mockMvc.perform(get("/api/patients/{id}", id)).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("lastName").value("Smith"));
		}

		@Test
		void testGetPatientByIdNotPresent() throws Exception {

			long id = 100;

			when(patientRepository.findById(id)).thenReturn(Optional.empty());

			mockMvc.perform(get("/api/patients/{id}", id)).andExpect(status().isNotFound());

		}

		@Test
		void testCreatePatient() throws Exception {

			String patient1json = objectMapper.writeValueAsString(patient1);

			when(patientRepository.save(patient1)).thenReturn(patient1);

			mockMvc.perform(post("/api/patient").contentType(MediaType.APPLICATION_JSON).content(patient1json))
					.andExpect(status().isCreated()).andExpect(jsonPath("age").value(35))
					.andExpect(jsonPath("lastName").value("Smith"));
		}

		@Test
		void testDeletePatient() throws Exception {

			long id = 100;
			patient1.setId(id);

			when(patientRepository.findById(id)).thenReturn(Optional.of(patient1));

			mockMvc.perform(delete("/api/patients/{id}", id)).andExpect(status().isOk());
		}

		@Test
		void testDeletePatientNotPresent() throws Exception {

			long id = 100;

			when(patientRepository.findById(id)).thenReturn(Optional.empty());

			mockMvc.perform(delete("/api/patients/{id}", id)).andExpect(status().isNotFound());
		}

		@Test
		void testDeleteAllPatients() throws Exception {

			doNothing().when(patientRepository).deleteAll();

			mockMvc.perform(delete("/api/patients")).andExpect(status().isOk());
		}
	}

	@Nested
	@WebMvcTest(RoomController.class)
	class RoomControllerUnitTest {

		@MockBean
		private RoomRepository roomRepository;

		@Autowired
		private MockMvc mockMvc;

		@Autowired
		ObjectMapper objectMapper;

		Room room1 = new Room("room_uno");
		Room room2 = new Room("room_dos");
		List<Room> rooms = Arrays.asList(room1, room2);

		@Test
		void testgetAllRooms() throws Exception {

			when(roomRepository.findAll()).thenReturn(rooms);

			mockMvc.perform(get("/api/rooms")).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$[0].roomName").value("room_uno"))
					.andExpect(jsonPath("$[1].roomName").value("room_dos"));

		}

		@Test
		void testGetRoomByRoomName() throws Exception {


			when(roomRepository.findByRoomName("room_uno"))
			.thenReturn(Optional.of(room1));

			mockMvc.perform(get("/api/rooms/{roomName}", "room_uno"))
			.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("roomName").value("room_uno"));
		}

		@Test
		void testGetRoomByRoomNameNotPresent() throws Exception {

			when(roomRepository.findByRoomName("room_uno"))
			.thenReturn(Optional.empty());

			mockMvc.perform(get("/api/rooms/{roomName}", "room_uno"))
			.andExpect(status().isNotFound());

		}

		@Test
		void testCreateRoom() throws Exception {

			String room1json = objectMapper.writeValueAsString(room1);

			when(roomRepository.save(room1)).thenReturn(room1);

			mockMvc.perform(post("/api/room").contentType(MediaType.APPLICATION_JSON).content(room1json))
					.andExpect(status().isCreated()).andExpect(jsonPath("roomName").value("room_uno"));
		}

		@Test
		void testDeleteRoom() throws Exception {


			when(roomRepository.findByRoomName("room_dos")).thenReturn(Optional.of(room2));

			mockMvc.perform(delete("/api/rooms/{roomName}", "room_dos"))
			.andExpect(status().isOk());
		}

		void testDeleteRoomNotPresent() throws Exception {

			when(roomRepository.findByRoomName("room_dos"))
			.thenReturn(Optional.empty());

			mockMvc.perform(delete("/api/rooms/{roomName}", "room_dos"))
			.andExpect(status().isNotFound());
		}

		@Test
		void testDeleteAllRooms() throws Exception {

			doNothing().when(roomRepository).deleteAll();

			mockMvc.perform(delete("/api/rooms")).andExpect(status().isOk());
		}
	}
}
