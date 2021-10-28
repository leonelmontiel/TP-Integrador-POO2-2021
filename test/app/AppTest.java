package app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {

	private App app;

	@BeforeEach
	void setUp() throws Exception {
		this.app = new App(1150625347);
	}

	@Test
	void testGetNumero() {
		int numEsperado = 1150625347;
		int numObtenido = this.app.getNumero();
		assertEquals(numEsperado, numObtenido);
	}

}
