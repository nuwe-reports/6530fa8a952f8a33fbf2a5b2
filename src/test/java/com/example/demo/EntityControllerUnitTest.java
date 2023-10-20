package com.example.demo;

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
import com.example.demo.entities.*;

class EntityControllerUnitTest {

	@Nested
	@WebMvcTest(DoctorController.class)
	class DoctorControllerUnitTest {

		@MockBean
		private DoctorRepository doctorRepository;

		@Autowired
		private MockMvc mockMvc;

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
		void testGetDoctorByIdPresent() throws Exception {

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

			mockMvc.perform(get("/api/doctors/{id}", id))
			.andExpect(status().isNotFound());

		}
		
		@Test
		void testCreateDoctor() throws Exception {
			long id = 100;
			
			mockMvc.perform(post("/api/doctors/{id}", id));
		}
		
		@Test
		void testDeleteDoctor() throws Exception {
			long id = 100;
			
			mockMvc.perform(post("/api/doctors/{id}", id));
		}
		
		@Test
		void testDeleteAllDoctors() throws Exception {
			long id = 100;
			
			mockMvc.perform(post("/api/doctors", id));
		}
	}

	@Nested
	@WebMvcTest(PatientController.class)
	class PatientControllerUnitTest {

		@MockBean
		private PatientRepository patientRepository;

		@Autowired
		private MockMvc mockMvc;

		@Test
		void testgetAllPatients() throws Exception {

			Patient patient1 = new Patient("Jim", "Smith", 35, "jonah@example.com");
			Patient patient2 = new Patient("Joana", "Jander", 40, "joanajander58@example.com");
			List<Patient> patients = Arrays.asList(patient1, patient2);

			when(patientRepository.findAll()).thenReturn(patients);

			mockMvc.perform(get("/api/patients")).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$[0].firstName").value("Jim")).andExpect(jsonPath("$[1].age").value(40))
					.andExpect(jsonPath("$[1].lastName").value("Jander"));

		}
		
		@Test
		void testGetPatientById() throws Exception {

			long id = 100;

			when(patientRepository.findById(id)).thenReturn(Optional.of(doctor1));

			mockMvc.perform(get("/api/doctors/{id}", id)).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("lastName").value("Doe"));
		}

		@Test
		void testGetPatientByIdNotPresent() throws Exception {

			long id = 100;

			when(patientRepository.findById(id)).thenReturn(Optional.empty());

			mockMvc.perform(get("/api/doctors/{id}", id))
			.andExpect(status().isNotFound());

		}
		
		@Test
		void testCreatepatient() throws Exception {
			long id = 100;
			
			mockMvc.perform(post("/api/patients/{id}", id));
		}
		
		@Test
		void testDeletePatient() throws Exception {
			long id = 100;
			
			mockMvc.perform(post("/api/doctors/{id}", id));
		}
		
		@Test
		void testDeleteAllPatients() throws Exception {
			long id = 100;
			
			mockMvc.perform(post("/api/doctors", id));
		}
	}

	@Nested
	@WebMvcTest(RoomController.class)
	class RoomControllerUnitTest {

		@MockBean
		private RoomRepository roomRepository;

		@Autowired
		private MockMvc mockMvc;

		@Test
		void testgetAllRooms() throws Exception {

			Room room1 = new Room("room_uno");
			Room room2 = new Room("room_dos");
			List<Room> rooms = Arrays.asList(room1, room2);

			when(roomRepository.findAll()).thenReturn(rooms);

			mockMvc.perform(get("/api/rooms")).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$[0].roomName").value("room_uno"))
					.andExpect(jsonPath("$[1].roomName").value("room_dos"));

		}
		@Test
		void testGetRoomByRoomName() throws Exception {

			long id = 100;

			when(patientRepository.findById(id)).thenReturn(Optional.of(doctor1));

			mockMvc.perform(get("/api/doctors/{id}", id)).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("lastName").value("Doe"));
		}

		@Test
		void testGetRoomByRoomNameNotPresent() throws Exception {

			long id = 100;

			when(patientRepository.findById(id)).thenReturn(Optional.empty());

			mockMvc.perform(get("/api/doctors/{id}", id))
			.andExpect(status().isNotFound());

		}
		
		@Test
		void testCreateRoom() throws Exception {
			long id = 100;
			
			mockMvc.perform(post("/api/patients/{id}", id));
		}
		
		@Test
		void testDeleteRoom() throws Exception {
			long id = 100;
			
			mockMvc.perform(post("/api/doctors/{id}", id));
		}
		
		@Test
		void testDeleteAllRooms() throws Exception {
			long id = 100;
			
			mockMvc.perform(post("/api/doctors", id));
		}
	}
}
