package estacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import registroDeCompra.RegistroCompraPuntual;

class EstacionamientoPuntualTest extends EstacionamientoTest {

	private LocalTime horaFin;
	private RegistroCompraPuntual compraPuntual;
	
	@BeforeEach
	void setUp() throws Exception {
		this.horaInicio = LocalTime.of(12, 0);
		this.horaFin = LocalTime.of(15, 0);
		this.patente = "AA 325 AA";
		this.compraPuntual = mock(RegistroCompraPuntual.class);
		this.estacionamiento = new EstacionamientoPuntual(this.horaInicio, this.horaFin, this.patente, 
				this.compraPuntual);
	}
	
	@Test
	void testUnEstacionamientoPuntualTieneUnaHoraDeFinalizacion() {
		LocalTime horaEsperada = this.horaFin;
		assertEquals(horaEsperada, ((EstacionamientoPuntual) this.estacionamiento).getHoraFin());
	}
	
	@Test
	void testUnEstacionamientoPuntualTieneUnRegistroDeCompraPuntualAsociado() {
		RegistroCompraPuntual registroEsperado = this.compraPuntual;
		assertEquals(registroEsperado, ((EstacionamientoPuntual) this.estacionamiento).getRegistroCompraPuntual());
	}
	

}
