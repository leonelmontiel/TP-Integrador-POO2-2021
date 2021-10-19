package registroDeCompra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import app.App;

abstract class RegistroDeCompraTest {
	
	protected Integer nroControl; // = 2000548
	protected LocalDate fecha; //= LocalDate.of(2021, 01, 25)
	protected LocalTime hora; // = LocalTime.of(15, 00)
	protected App celular = mock(App.class);
	protected RegistroDeCompra registroDeCompra; // SUT

	@Test
	void testGetNroDeControl() {		
		assertEquals(nroControl, this.registroDeCompra.getNroControl());
	}
	
	@Test
	void testGetFecha() {		
		assertEquals(fecha, this.registroDeCompra.getFecha());
	}
	
	@Test
	void testGetHora() {		
		assertEquals(hora, this.registroDeCompra.getHora());
	}
	
	@Test
	void testGetCelular() {
		assertEquals(celular, this.registroDeCompra.getCelular());
	}

}
