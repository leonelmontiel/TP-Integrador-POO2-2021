package estacionamiento;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EstacionamientoPuntualTest extends EstacionamientoTest {

	private String patente;
	
	@BeforeEach
	void setUp() throws Exception {
		this.horaInicio = LocalTime.now();
		this.patente = "AA 325 AA";
		this.estacionamiento = new EstacionamientoPuntual(this.horaInicio, this.patente);
	}

	@Test
	void testUnEstacionamientoPuntualTieneUnaPatenteAsociada() {
		String patenteEsperada = this.patente;
		assertEquals(patenteEsperada, ((EstacionamientoPuntual) this.estacionamiento).getPatente());
	}
	
	@Test
	void testOtroRandom() {
		
	}

}
