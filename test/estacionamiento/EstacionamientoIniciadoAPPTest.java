package estacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.APP;

class EstacionamientoIniciadoAPPTest extends EstacionamientoTest {

	private APP aplicacion;
	
	@BeforeEach
	void setUp() throws Exception {

		this.aplicacion = mock(APP.class);
		this.patente = "AA 325 AA";
		this.estacionamiento = new EstacionamientoIniciadoAPP(this.aplicacion, this.horaInicio, this.patente);
		
	}

	@Test
	void testTieneUnaAPPAsociada() {
		APP aplicacionEsperada = this.aplicacion;
		assertEquals(aplicacionEsperada, ((EstacionamientoIniciadoAPP) this.estacionamiento).getApp());
	}
	
	@Test
	void testEstacionamientoIniciadoAPPEstaVigente() {
		//setup
		LocalDateTime hoy = LocalDateTime.of(2021, 10, 14, 14, 0);
		
		assertTrue(this.estacionamiento.estaVigente(hoy));
	}

}
