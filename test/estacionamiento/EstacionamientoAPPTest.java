package estacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.APP;

class EstacionamientoAPPTest extends EstacionamientoTest {

	private APP aplicacion;
	
	@BeforeEach
	void setUp() throws Exception {

		this.aplicacion = mock(APP.class);
		this.patente = "AA 325 AA";
		this.horaInicio = LocalTime.of(18, 0);
		this.horaFin = LocalTime.of(20, 0);
		this.estacionamiento = new EstacionamientoAPP(this.aplicacion, this.patente, this.horaInicio);
		
	}

	@Test
	void testTieneUnaAPPAsociada() {
		APP aplicacionEsperada = this.aplicacion;
		assertEquals(aplicacionEsperada, ((EstacionamientoAPP) this.estacionamiento).getApp());
	}
	
	@Test
	void testEstacionamientoAPPEstaVigente() {
		//setup
		LocalDateTime hoy = LocalDateTime.of(2021, 10, 14, 14, 0);
		
		assertTrue(this.estacionamiento.estaVigente(hoy));
	}
	
	@Test
	void testEstacionamientoAPPNoEstaVigente() {
		//setup
		LocalDateTime hoy = LocalDateTime.of(2021, 10, 14, 14, 0);
		
		((EstacionamientoAPP) this.estacionamiento).finalizar();
		
		assertFalse(this.estacionamiento.estaVigente(hoy));		
	}
	
	@Test
	void testGetDuracionEstacionamientoAppFinalizado() {
		//setup
		this.estacionamiento.setHoraFin(this.horaFin);

		assertEquals(2, ((EstacionamientoAPP) this.estacionamiento).getDuracion());
	}

	@Test
	void testGetDuracionEstacionamientoNoFinalizadoLanzaError() {
		//este test se realizo para alcanzar el 100% de testing
		assertThrows(RuntimeException.class, ()-> this.estacionamiento.getDuracion());
	}
}
