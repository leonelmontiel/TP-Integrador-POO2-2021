package registroDeCompra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import app.App;
import puntoDeVenta.PuntoDeVenta;

abstract class RegistroDeCompraTest {
	
	protected RegistroDeCompra registroDeCompra; // SUT
	protected PuntoDeVenta puntoDeVenta = mock(PuntoDeVenta.class); // DOC
	protected Integer nroControl; // = 2000548
	protected LocalDate fecha; //= LocalDate.of(2021, 01, 25)
	protected LocalTime hora; // = LocalTime.of(15, 00)
	protected App celular = mock(App.class); // DOC
	

	@Test
	void testGetPuntoDeVenta() {
		assertEquals(this.puntoDeVenta, this.registroDeCompra.getPuntoDeVenta());
	}
	
	@Test
	void testGetNroDeControl() {		
		assertEquals(this.nroControl, this.registroDeCompra.getNroControl());
	}
	
	@Test
	void testGetFecha() {		
		assertEquals(this.fecha, this.registroDeCompra.getFecha());
	}
	
	@Test
	void testGetHora() {		
		assertEquals(this.hora, this.registroDeCompra.getHora());
	}
	
	@Test
	void testGetCelular() {
		assertEquals(this.celular, this.registroDeCompra.getCelular());
	}

}
