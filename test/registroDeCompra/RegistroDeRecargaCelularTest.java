package registroDeCompra;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegistroDeRecargaCelularTest extends RegistroDeCompraTest{

	private Float monto;

	@BeforeEach
	void setUp() throws Exception {
		this.monto = 800f;
		this.registroDeCompra = new RegistroDeRecargaCelular(this.nroControl, this.fecha, this.hora, this.celular, this.monto);
	}

	@Test
	void test() {
		assertEquals(this.monto, ((RegistroDeRecargaCelular) this.registroDeCompra).getMontoRecarga(), 0);
	}

}
