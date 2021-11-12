package puntoDeVenta;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.APP;
import app.Pantalla;
import registroDeCompra.RegistroDeCompra;
import sem.SEM;

class PuntoDeVentaTest {
	
	PuntoDeVenta punto;
	SEM sem;
	APP app;

	@BeforeEach
	void setUp() throws Exception {
		this.sem = mock(SEM.class);
		this.app = mock(APP.class);
		this.punto = new PuntoDeVenta(this.sem);
	}

	@Test
	void testGenerarRecarga80Pesos() {
		//SetUP
		Float monto = 80f;
		RegistroDeCompra registro = this.punto.generarRecarga(this.app, monto);
		//Verify
		verify(this.sem).almacenar(registro);
	}
	
	@Test
	void testGenerarCompraPuntualPor2Horas() {
		//SetUP
		String patente = "AB 123 CD";
		Integer horasCompradas = 2;
		RegistroDeCompra registro = this.punto.generarCompraPuntual(patente, horasCompradas);
		//Verify
		verify(this.sem).almacenar(registro);
	}
	
	@Test
	void testNotificarCompraExitosa() {
		//Config Mock
		Pantalla pantalla = mock(Pantalla.class);
		this.punto.setPantalla(pantalla);
		this.punto.notificarCompraExitosa();
		//Verify
		verify(pantalla).mostrar("Compra realizada con éxito");
	}

}
