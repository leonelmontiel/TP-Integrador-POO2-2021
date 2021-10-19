package registroDeCompra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.App;

class registroDeCompraTest {

	private RegistroDeCompra registroDeCompra; // SUT
	private Integer nroControl;
	private LocalDate fecha;
	private LocalTime hora;
	private App celular = mock(App.class); // mock

	@BeforeEach
	void setUp() throws Exception {
		this.nroControl = 2000548;
		this.fecha = LocalDate.of(2021, 01, 25);
		this.hora = LocalTime.of(15, 00);
		this.registroDeCompra = new RegistroDeCompra(nroControl, fecha, hora, celular);
	}

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
