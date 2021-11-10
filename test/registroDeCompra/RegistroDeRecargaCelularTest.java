package registroDeCompra;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.APP;

class RegistroDeRecargaCelularTest extends RegistroDeCompraTest{

	private Float monto;
	private APP celular;

	@BeforeEach
	void setUp() throws Exception {
		this.nroControl = 5001448;
		this.hora = LocalTime.of(21, 30);
		this.monto = 800f;
		this.celular = mock(APP.class);
		this.registroDeCompra = new RegistroDeRecargaCelular(this.puntoDeVenta, this.nroControl, this.fecha, this.hora, this.celular, this.monto);
	}

	@Test
	void test() {
		assertEquals(this.monto, ((RegistroDeRecargaCelular) this.registroDeCompra).getMontoRecarga(), 0);
	}
	
	@Test
	void testGetCelular() {
		assertEquals(this.celular, ((RegistroDeRecargaCelular) this.registroDeCompra).getCelular());
	}

}
