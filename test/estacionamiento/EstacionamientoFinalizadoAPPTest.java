package estacionamiento;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EstacionamientoFinalizadoAPPTest extends EstacionamientoTest {

	private LocalTime horaFin;
	private Float costo;
	
	@BeforeEach
	void setUp() throws Exception {
	
		this.horaInicio = LocalTime.of(12, 0);
		this.horaFin = LocalTime.of(15, 0);
		this.patente = "AA 325 AA";
		this.costo = 120f;
		this.estacionamiento = new EstacionamientoFinalizadoAPP(this.horaInicio, this.horaFin, this.patente, 
				this.costo);
		
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
		assertFalse(this.estacionamiento.estaVigente());
	}

}
