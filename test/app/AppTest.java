package app;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sem.SEM;

class AppTest {

	private App app;
	private Integer numero;
	private String patente;
	private SEM sistema;

	@BeforeEach
	void setUp() throws Exception {
		//SetUp
		this.numero = 1150625347;
		this.patente = "A13F45";
		
		this.sistema = spy(SEM.class);
		this.app = new App(this.numero, this.sistema);
		
		
		// config mocks
	}

	@Test
	void testGetNumero() {
		//Excercise
		int numObtenido = this.app.getNumero();
		//Verify
		assertEquals(this.numero, numObtenido);
	}
	
	@Test
	void testIniciarEstacionamiento() {
		//Excercise
		this.app.iniciarEstacionamiento(this.patente);
		//Verify
		verify(this.sistema, times(1)).iniciarApp(patente, app);
	}
	
	@Test
	void testFinalizarEstacionamiento() {
		//Excercise
		this.app.finalizarEstacionamiento();
		//Verify
		verify(this.sistema, times(1)).finalizarApp(app);
	}

}
