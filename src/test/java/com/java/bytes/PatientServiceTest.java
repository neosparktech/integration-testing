package com.java.bytes;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.java.bytes.patientServices.AppointmentServices;
import com.java.bytes.patientServices.PatientDTO;
import com.java.bytes.patientServices.PatientServices;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

	@InjectMocks
	private PatientServices patientService;

	@Mock
	private AppointmentServices appointmentService;

	@Test
	@DisplayName("Test appointment booking with appointment Service returns true")
	public void test_book_appointments_with_appointment_service_true() {
		PatientDTO testPatient = PatientDTO.builder().firstName("John").lastName("Doe").dateOfBirth(LocalDate.now()).build();
		Mockito.when(appointmentService.bookAppointment(Mockito.any(PatientDTO.class), Mockito.any(LocalDateTime.class)))
				.thenReturn(true);

		assertTrue(
				"ok".equalsIgnoreCase(patientService.bookAppointments(testPatient, LocalDateTime.now())));
		
		ArgumentCaptor<PatientDTO> normalizedPatientCaptor = ArgumentCaptor.forClass(PatientDTO.class);
		ArgumentCaptor<LocalDateTime> appointmentLocalDateTimeCaptor = ArgumentCaptor.forClass(LocalDateTime.class);

		Mockito.verify(appointmentService, times(1)).bookAppointment(normalizedPatientCaptor.capture(),
				appointmentLocalDateTimeCaptor.capture());
		PatientDTO capturedPatient = normalizedPatientCaptor.getValue();
		assertTrue("JOHN".equals(capturedPatient.getFirstName()));
		assertTrue("DOE".equals(capturedPatient.getLastName()));
		assertTrue(testPatient.getDateOfBirth().equals(capturedPatient.getDateOfBirth()));
		


	}

	@Test
	@DisplayName("Test appointment booking with appointment Service returns false")
	public void test_book_appointments_with_appointment_service_false() {
		PatientDTO testPatient = PatientDTO.builder().firstName("John").lastName("Doe").dateOfBirth(LocalDate.now()).build();
		Mockito.when(appointmentService.bookAppointment(Mockito.any(PatientDTO.class), Mockito.any(LocalDateTime.class)))
				.thenReturn(false);
		assertTrue("failure"
				.equalsIgnoreCase(patientService.bookAppointments(testPatient, LocalDateTime.now())));
	}
	


	@Test
	@DisplayName("Throw exception appointment booking with empty patient")
	public void test_book_appointments_with_null_Patient() {
		PatientDTO testPatient = PatientDTO.builder().firstName(null).lastName(null).dateOfBirth(null).build();
		assertThrows(IllegalArgumentException.class, () -> patientService
				.bookAppointments(testPatient, LocalDateTime.now()));

	}
	
	

}
