package app;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sem.SEM;

class AppTest {

	private APP aPP;
	private Integer numero;
	private String patente;
	private SEM sistema;

	@BeforeEach
	void setUp() throws Exception {
		//SetUp
		this.numero = 1150625347;
		this.patente = "A13F45";
		
		this.sistema = spy(SEM.class);
		this.aPP = new APP(this.numero, this.sistema);
		
		
		// config mocks
	}

	@Test
	void testGetNumero() {
		//Excercise
		int numObtenido = this.aPP.getNumero();
		//Verify
		assertEquals(this.numero, numObtenido);
	}
	
	@Test
	void testIniciarEstacionamiento() {
		//Excercise
		this.aPP.iniciarEstacionamiento(this.patente);
		//Verify
		verify(this.sistema, times(1)).iniciarApp(patente, aPP);
	}
	
	@Test
	void testFinalizarEstacionamiento() {
		//Excercise
		this.aPP.finalizarEstacionamiento();
		//Verify
		verify(this.sistema, times(1)).finalizarApp(aPP);
	}

	@Test
	void testGetSaldo() {
		//setUp
		Float saldoDeseado = 580f;
		when(this.sistema.getSaldoDe(aPP)).thenReturn(saldoDeseado);
		//Excercice
		Float saldoObtenido = this.aPP.getSaldo();
		//Verify
		assertEquals(saldoDeseado, saldoObtenido);
		verify(this.sistema, times(1)).getSaldoDe(aPP);
	}
}
