package registroDeCompra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sem.SEM;

class RegistroDeCompraPuntualTest extends RegistroDeCompraTest{

	private Integer horasCompradas;
	private String patente;
	
	@BeforeEach
	void setUp() throws Exception {
		this.horasCompradas = 4;
		this.nroControl = 2000548;
		this.hora = LocalTime.of(15, 00);
		this.patente = "ABC 123";
		this.registroDeCompra = new RegistroDeCompraPuntual(this.puntoDeVenta, this.nroControl, this.fecha, this.hora, this.patente, this.horasCompradas);
		mock(SEM.class);
	}

	@Test
	void testGetHorasCompradas() {
		assertEquals(this.horasCompradas, ((RegistroDeCompraPuntual) this.registroDeCompra).getHorasCompradas());
	}
	
	@Test
	void testGetPatente() {
		String patenteObtenida = ((RegistroDeCompraPuntual) this.registroDeCompra).getPatente();
		assertEquals(patenteObtenida, this.patente);
	}
	
	@Test
	void testNotificarCompraExitosa() {
		//Exercise
		((RegistroDeCompraPuntual) this.registroDeCompra).notificarCompraExitosa();
		//Verify
		verify(this.puntoDeVenta).notificarCompraExitosa();
	}

}
