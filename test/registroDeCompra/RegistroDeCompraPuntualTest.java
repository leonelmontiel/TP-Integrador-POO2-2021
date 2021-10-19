package registroDeCompra;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegistroDeCompraPuntualTest extends RegistroDeCompraTest{

	private Integer horasCompradas;

	@BeforeEach
	void setUp() throws Exception {
		this.horasCompradas = 4;
		this.nroControl = 2000548;
		this.hora = LocalTime.of(15, 00);
		this.registroDeCompra = new RegistroDeCompraPuntual(this.nroControl, this.fecha, this.hora, this.celular, this.horasCompradas);
	}

	@Test
	void testGetHorasCompradas() {
		assertEquals(this.horasCompradas, ((RegistroDeCompraPuntual) this.registroDeCompra).getHorasCompradas());
	}

}
