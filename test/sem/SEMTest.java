package sem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
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
	private Estacionamiento estacionamiento;
	
	@BeforeEach
	void setUp() throws Exception {
		//mocks
		this.app = mock(APP.class);
		this.estacionamiento = mock(Estacionamiento.class);
		
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
	
	@Test
	void testFinalizarEstacionamiento() {
		//setup 
		List<Estacionamiento> estacionamientosSpy = spy(new ArrayList<Estacionamiento>());
		this.sem.setEstacionamientos(estacionamientosSpy);
		
		Estacionamiento estacionamientoNuevo = this.sem.iniciarEstacionamiento("abc 123", this.app);
		
		//pensar que pasa si no tiene estacionamiento iniciado
		this.sem.finalizarEstacionamiento(this.app);
		
		//verify
		verify(estacionamientosSpy).add(estacionamientoNuevo);
		assertFalse(estacionamientoNuevo.estaVigente(LocalDateTime.now()));
		
	}

	@Test
	void testAppSinRegistrar() {
		assertFalse(this.sem.tieneRegistradoApp(this.app));
	}
	
	@Test
	void testRegistrarApp() {
		//exercise
		this.sem.regitrarAPP(this.app);
	
		//verify
		assertTrue(this.sem.tieneRegistradoApp(this.app));
	}
	
	@Test
	void testGetSaldo() {
		//sutup
		Float saldoEsperado = 0f;
		this.sem.regitrarAPP(this.app);
		
		assertEquals(saldoEsperado, this.sem.getSaldo(this.app));
	}
	
	@Test
	void testRecargarSaldo() {
		//sutup
		Float montoRecarga = 10f;
		Float montoRecarga2 = 90f;
		Float saldoEsperado = 10f;
		Float saldoEsperado2 = 100f;
		this.sem.regitrarAPP(this.app);
		this.sem.recargarSaldo(this.app, montoRecarga);
		
		assertEquals(saldoEsperado, this.sem.getSaldo(this.app));
		
		this.sem.recargarSaldo(this.app, montoRecarga2);
		assertEquals(saldoEsperado2, saldoEsperado2);
	}
	
	@Test
	void testRegistrarEstacionamiento() {
		//setup 
		List<Estacionamiento> estacionamientosSpy = spy(new ArrayList<Estacionamiento>());
		this.sem.setEstacionamientos(estacionamientosSpy);
		
		this.sem.registrarEstacionamiento(this.estacionamiento);
		
		//verify
		verify(estacionamientosSpy).add(this.estacionamiento);
	}
	
	@Test
	void testTieneEstacionamientoVigente() {
		LocalDateTime momentoConsulta = LocalDateTime.now();
		
		//configuracion de mock
		when(this.estacionamiento.getPatente()).thenReturn("abc 123");
		when(this.estacionamiento.estaVigente(momentoConsulta)).thenReturn(true);
		
		//setup
		this.sem.registrarEstacionamiento(this.estacionamiento);
		
		//verify
		assertTrue(this.sem.tieneEstacionamientoVigente("abc 123", momentoConsulta));
		
		verify(this.estacionamiento).getPatente();
		verify(this.estacionamiento).estaVigente(momentoConsulta);
	}
	
}
