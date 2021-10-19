package estacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

abstract class EstacionamientoTest {

	protected Estacionamiento estacionamiento;
	protected LocalTime horaInicio;
	protected LocalTime horaFin;
	protected String patente;

	@Test
	void testUnEstacionamientoTieneUnaHoraDeInicio() {
		LocalTime horaEsperada = this.horaInicio;
		assertEquals(horaEsperada, this.estacionamiento.getHoraInicio());
	}
	
	@Test
	void testUnEstacionamientoTieneUnaPatenteAsociada() {
		String patenteEsperada = this.patente;
		assertEquals(patenteEsperada, this.estacionamiento.getPatente());
	}
	
}
