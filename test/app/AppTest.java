package app;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import interfaces.GestorAPP;

class AppTest {

	private APP app;
	private Integer numero;
	private String patente;
	private GestorAPP sistema;
	private Pantalla pantalla;

	@BeforeEach
	void setUp() throws Exception {
		//SetUp
		this.numero = 1150625347;
		this.patente = "A13F45";
		
		this.sistema = mock(GestorAPP.class);
		this.pantalla = mock(Pantalla.class);
		this.app = new APP(this.numero, this.sistema);

	}

	@Test
	void testGetNumero() {
		//Excercise
		int numObtenido = this.app.getNumero();
		//Verify
		assertEquals(this.numero, numObtenido);
	}
	
	@Test
	void testIniciarEstacionamientoOK() {
		//setUp
		this.pantalla = mock(Pantalla.class);
		when(this.sistema.tieneSaldoSuficiente(this.app)).thenReturn(true);

		//setup
		this.app.setPantalla(this.pantalla);
		
		//Excercise
		this.app.iniciarEstacionamiento(this.patente);
		//Verify
		verify(this.sistema).tieneSaldoSuficiente(this.app);
		verify(this.sistema, times(1)).iniciarEstacionamiento(this.patente, this.app);
	}

	@Test
	void testNoTieneSaldoParaIniciarEstacionamiento() {
		//setUp
		Float saldoDisponible = 20f;
		when(this.sistema.getSaldo(this.app)).thenReturn(saldoDisponible);
		this.app.setPantalla(this.pantalla);
		//Excercise
		this.app.iniciarEstacionamiento(this.patente);
		//Verify
		verify(this.sistema, never()).iniciarEstacionamiento(this.patente, this.app);
		verify(this.pantalla).mostrar("No tenes saldo suficiente para iniciar este estacionamiento.");
	}
	
	@Test
	void testGetSaldo() {
		//setUp
		Float saldoDeseado = 580f;
		when(this.sistema.getSaldo(this.app)).thenReturn(saldoDeseado);
		//Excercice
		Float saldoObtenido = this.app.getSaldo();
		//Verify
		assertEquals(saldoDeseado, saldoObtenido);
		verify(this.sistema, times(1)).getSaldo(this.app);
	}
	
	@Test
	void testNoTieneEstacioamientoIniciado() {
		assertFalse(this.app.tieneEstacionamiento());
	}
	
	@Test
	void testTieneEstacioamientoIniciado() {
		//Config Mock
		this.pantalla = mock(Pantalla.class);
		when(this.sistema.tieneSaldoSuficiente(this.app)).thenReturn(true);
		
		//setup
		this.app.setPantalla(this.pantalla);
		
		//Excercise
		this.app.iniciarEstacionamiento(this.patente);
		//Verify
		assertTrue(this.app.tieneEstacionamiento());
	}
	
	@Test
	void testAPPSinEstacioamientoNoLoFinaliza() {
		//Excercise
		this.app.finalizarEstacionamiento();
		//Verify
		verify(this.sistema, never()).finalizarEstacionamiento(this.app);
	}
		
	@Test
	void testAPPConEstacionamientoLoFinaliza() {
		//Config Mock
		this.pantalla = mock(Pantalla.class);
		when(this.sistema.tieneSaldoSuficiente(this.app)).thenReturn(true);
		//setup
		this.app.setPantalla(this.pantalla);
		
		//Excercise
		this.app.iniciarEstacionamiento(this.patente);
		this.app.finalizarEstacionamiento();
		//Verify
		verify(this.sistema).tieneSaldoSuficiente(this.app);
		verify(this.sistema, times(1)).finalizarEstacionamiento(this.app);
	}
	
	@Test
	void testAPPConEstacionamientoNoLoInicia() {
		//Config Mock
		this.pantalla = mock(Pantalla.class);
		when(this.sistema.tieneSaldoSuficiente(this.app)).thenReturn(true);

		//setup
		this.app.setPantalla(this.pantalla);
		
		//Excercise
		this.app.iniciarEstacionamiento(this.patente);
		this.app.iniciarEstacionamiento(this.patente);
		//Verify
		verify(this.sistema).tieneSaldoSuficiente(this.app);
		verify(this.sistema, times(1)).iniciarEstacionamiento(this.patente, this.app);
	}

	@Test
	void testAPPSinEstacionamientoInicialmenteNoAvisaSobreCambiosDeMovimiento() {
		//setup
		this.app.setPantalla(this.pantalla);
		//exercise
		this.app.driving();
		this.app.walking();
		
		//verify
		verify(this.pantalla, never()).mostrar("Alerta debe iniciar estacionamiento");
		verify(this.pantalla, never()).mostrar("Alerta debe finalizar estacionamiento");
	}
	
	@Test
	void testAPPConEstacionamientoInicialmenteNoAvisaSobreCambiosDeMovimiento() {
		//setup
		this.app.setPantalla(this.pantalla);
	
		//exercise
		this.app.iniciarEstacionamiento(this.patente);
		this.app.driving();
		
		//verify
		verify(this.pantalla, never()).mostrar("Alerta debe finalizar estacionamiento");
	}
	
	@Test
	void testAPPConAsistenciaAlUsuarioActivadaAlertaCambioDeMovimiento() {
		//setup
		when(this.sistema.tieneSaldoSuficiente(this.app)).thenReturn(true);
		this.app.activarAsistenciaAlUsuario();
		this.app.setPantalla(this.pantalla);
		
		//exercise 
		this.app.walking();
		this.app.iniciarEstacionamiento(this.patente);
		this.app.driving();
		
		//verify
		verify(this.sistema).tieneSaldoSuficiente(this.app);
		verify(this.pantalla).mostrar("Alerta debe iniciar estacionamiento");
		verify(this.pantalla).mostrar("Alerta debe finalizar estacionamiento");
	}

	@Test
	void testAPPConAsistenciaAlUsuarioActivadaAlertaSoloElCambioDeMovimiento() {
		//setup
		when(this.sistema.tieneSaldoSuficiente(this.app)).thenReturn(true);
		this.app.activarAsistenciaAlUsuario();
		this.app.setPantalla(this.pantalla);
		
		//exercise 
		this.app.walking();
		this.app.iniciarEstacionamiento(this.patente);
		this.app.walking();
		
		//verify
		verify(this.sistema).tieneSaldoSuficiente(this.app);
		verify(this.pantalla, times(1)).mostrar("Alerta debe iniciar estacionamiento");
	}
	
	@Test
	void testAPPdesactivaAsistenciaAlUsuario() {
		AsistenciaAlUsuario esperada = AsistenciaAlUsuario.DESACTIVADA;
		
		//exercise
		this.app.activarAsistenciaAlUsuario();
		this.app.desactivarAsistenciaAlUsuario();
		
		assertEquals(esperada, this.app.getAsistenciaAlUsuario());
	}
	
	@Test
	void testAppEnModoAutomaticoIniciaSolaUnEstacionamiento() {
		//setup
		when(this.sistema.tieneSaldoSuficiente(this.app)).thenReturn(true);
		this.app.setPantalla(this.pantalla);
		
		//se presupone que al activar el modo automatico se debe configurar la patente que utiliza para
		//iniciar los estacionamientos
		this.app.activarModoAutomatico(this.patente);
		
		this.app.walking();

		//verify
		verify(this.sistema).tieneSaldoSuficiente(this.app);
		verify(this.sistema).iniciarEstacionamiento(this.patente, this.app);	
		verify(this.pantalla).mostrar("Se inicio un estacionamiento en forma automatica");
	}
	
	@Test
	void testAppEnModoAutomaticoFinalizaSolaUnEstacionamiento() {
		//setup
		when(this.sistema.tieneSaldoSuficiente(this.app)).thenReturn(true);
		this.app.setPantalla(this.pantalla);
		
		//se presupone que al activar el modo automatico se debe configurar la patente que utiliza para
		//iniciar los estacionamientos
		this.app.activarModoAutomatico(this.patente);
		
		this.app.walking();

		this.app.driving();
		
		//verify
		verify(this.sistema).tieneSaldoSuficiente(this.app);
		verify(this.sistema).finalizarEstacionamiento(this.app);
		verify(this.pantalla).mostrar("Se finalizo el estacionamiento en forma automatica");
	}

}
