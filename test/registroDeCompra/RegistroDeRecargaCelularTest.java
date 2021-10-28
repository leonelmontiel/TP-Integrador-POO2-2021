package registroDeCompra;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegistroDeRecargaCelularTest extends RegistroDeCompraTest{

	private Float monto;

	@BeforeEach
	void setUp() throws Exception {
		this.nroControl = 5001448;
		this.hora = LocalTime.of(21, 30);
		this.monto = 800f;
		this.registroDeCompra = new RegistroDeRecargaCelular(this.puntoDeVenta, this.nroControl, this.fecha, this.hora, this.celular, this.monto);
	}

	@Test
	void test() {
		assertEquals(this.monto, ((RegistroDeRecargaCelular) this.registroDeCompra).getMontoRecarga(), 0);
	}

}
