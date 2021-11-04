package sem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import app.APP;
import estacionamiento.Estacionamiento;

class SEMTest {

	private SEM sem;
	private LocalTime horaInicio;
	private LocalTime horaCierre;
	private Float precioPorHora;
	
	private APP app;
	
	@BeforeEach
	void setUp() throws Exception {
		//mocks
		this.app = mock(APP.class);
		
		this.horaInicio = LocalTime.of(7, 0);
		this.horaCierre = LocalTime.of(20, 0);
		this.precioPorHora = 40f;
		
		this.sem = new SEM(this.horaInicio, this.horaCierre, this.precioPorHora);
		
	}

	@Test
	void testHoraInicio() {
		LocalTime horaEsperada = this.horaInicio;
		
		assertEquals(horaEsperada, this.sem.getHoraInicio());
	}
	
	@Test
	void testHoraCierre() {
		LocalTime horaEsperada = this.horaCierre;
		
		assertEquals(horaEsperada, this.sem.getHoraCierre());
	}
	
	@Test
	void testPrecioPorHora() {
		Float precioEsperado = 40f;
		
		assertEquals(precioEsperado, this.sem.getPrecioPorHora());
	}

	@Test
	void testIniciarEstacionamiento() {
		//setup 
		List<Estacionamiento> estacionamientosSpy = spy(new ArrayList<Estacionamiento>());
		this.sem.setEstacionamientos(estacionamientosSpy);
		
		Estacionamiento estacionamientoNuevo = this.sem.iniciarEstacionamiento("abc 123", this.app);
		
		//verify
		verify(estacionamientosSpy).add(estacionamientoNuevo);
	}
	
}
