package estacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.APP;

class EstacionamientoFinalizadoAPPTest extends EstacionamientoTest {

	private APP aplicacion;
	private LocalTime horaFin;
	private Float costo;
	
	@BeforeEach
	void setUp() throws Exception {
	
		this.aplicacion = mock(APP.class);
		this.horaInicio = LocalTime.of(12, 0);
		this.horaFin = LocalTime.of(15, 0);
		this.patente = "AA 325 AA";
		this.costo = 120f;
		this.estacionamiento = new EstacionamientoFinalizadoAPP(this.aplicacion, this.horaInicio, 
				this.horaFin, this.patente, this.costo);
		
	}
	
	@Test
	void testTieneUnaAPPAsociada() {
		APP aplicacionEsperada = this.aplicacion;
		assertEquals(aplicacionEsperada, ((EstacionamientoFinalizadoAPP) this.estacionamiento).getApp());
	}

	@Test
	void testEstacionamientoFinalizadoAPPTieneUnaHoraDeFinalizacion() {
		LocalTime horaEsperada = this.horaFin;
		assertEquals(horaEsperada, ((EstacionamientoFinalizadoAPP) this.estacionamiento).getHoraFin());
	}
	
	@Test
	void testGetCosto() {
		Float costoEsperado = this.costo;
		assertEquals(costoEsperado, ((EstacionamientoFinalizadoAPP) this.estacionamiento).getCosto());
	}

	@Test
	void testEstacionamientoFinalizadoAPPNoEstaVigente() {
		//setup
		LocalDate hoy = LocalDate.of(2021, 10, 14);

		assertFalse(this.estacionamiento.estaVigente(hoy));
	}

}
