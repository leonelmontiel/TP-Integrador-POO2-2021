package interfaces;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import estacionamiento.Estacionamiento;
import registroDeCompra.RegistroDeCompraPuntual;

class GestorEstacionamientoImplTest {

	private GestorEstacionamientoImpl gestor;
	private SistemaCentral sistema;
	private Estacionamiento estacionamiento;
	private String patente;
	private RegistroDeCompraPuntual registro;
	
	@BeforeEach
	void setUp() throws Exception {
		//mock
		this.sistema = mock(SistemaCentral.class);
		
		this.gestor = new GestorEstacionamientoImpl(this.sistema);
	}

	@Test
	void testRegistrarEstacionamiento() {
		//configuracion de mocks
		List<Estacionamiento> estacionamientosSpy = spy(new ArrayList<Estacionamiento>());
		this.estacionamiento = mock(Estacionamiento.class);
		
		//setup
		this.gestor.setEstacionamientos(estacionamientosSpy);
		
		//exercise
		this.gestor.registrarEstacionamiento(this.estacionamiento);
		
		//verify
		verify(estacionamientosSpy).add(this.estacionamiento);
	}
	
	@Test
	void testTieneEstacionamientoVigente() {
		//configuracion de mocks
		LocalDateTime momentoConsulta = LocalDateTime.now();
		this.patente = "ABC 123";
		this.estacionamiento = mock(Estacionamiento.class);
		when(this.estacionamiento.getPatente()).thenReturn(this.patente);
		when(this.estacionamiento.estaVigente(momentoConsulta)).thenReturn(true);
		
		//setup
		List<Estacionamiento> estacionamientos = Arrays.asList(this.estacionamiento);
		this.gestor.setEstacionamientos(estacionamientos);
		
		
		//verify
		assertTrue(this.gestor.tieneEstacionamientoVigente(this.patente, momentoConsulta));
		verify(this.estacionamiento).getPatente();
		verify(this.estacionamiento).estaVigente(momentoConsulta);
	}
	
	@Test
	void testNoTieneEstacionamientoVigente() {
		//configuracion de mocks
		LocalDateTime momentoConsulta = LocalDateTime.now();
		this.patente = "ABC 123";
		this.estacionamiento = mock(Estacionamiento.class);
		when(this.estacionamiento.getPatente()).thenReturn("ACB 321");
		when(this.estacionamiento.estaVigente(momentoConsulta)).thenReturn(true);
		
		//setup
		List<Estacionamiento> estacionamientos = Arrays.asList(this.estacionamiento);
		this.gestor.setEstacionamientos(estacionamientos);
		
		
		//verify
		assertFalse(this.gestor.tieneEstacionamientoVigente(this.patente, momentoConsulta));
		verify(this.estacionamiento).getPatente();
		verify(this.estacionamiento).estaVigente(momentoConsulta);
	}
	
	@Test
	void testGetCosto() {
		//configuracion de mocks
		this.estacionamiento = mock(Estacionamiento.class);
		when(this.estacionamiento.getDuracion()).thenReturn(3);
		when(this.sistema.getPrecioPorHora()).thenReturn(40f);
		
		//setup
		Float montoEsperado = 120f;
		
		//verify
		assertEquals(montoEsperado, this.gestor.getCosto(this.estacionamiento));
		verify(this.estacionamiento).getDuracion();
		verify(this.sistema).getPrecioPorHora();
	}

	@Test
	void testGenerarEstacionamiento() {
		//configuracion de mocks
		LocalTime horaCompra = LocalTime.of(12, 0);
		LocalDate fechaCompra = LocalDate.of(2021, 12, 10);
		LocalDateTime momentoConsulta = LocalDateTime.of(2021, 12, 10, 13, 0); 
		this.patente = "ABC 123";
		this.registro = mock(RegistroDeCompraPuntual.class); 
		when(this.registro.getHora()).thenReturn(horaCompra);
		when(this.registro.getFecha()).thenReturn(fechaCompra);
		when(this.registro.getHorasCompradas()).thenReturn(2);
		when(this.registro.getPatente()).thenReturn(this.patente);

		//exercise
		this.gestor.generarEstacionamiento(this.registro);
		
		//verify
		assertTrue(this.gestor.tieneEstacionamientoVigente(this.patente, momentoConsulta));
		verify(this.registro).getHora();
		verify(this.registro).getFecha();
		verify(this.registro).getHorasCompradas();
		verify(this.registro).getPatente();
	}
}
