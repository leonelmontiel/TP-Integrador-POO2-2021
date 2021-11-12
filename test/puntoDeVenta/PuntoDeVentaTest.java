package puntoDeVenta;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.APP;
import app.Pantalla;
import interfaces.GestorRegistros;

class PuntoDeVentaTest {
	
	PuntoDeVenta punto;
	GestorRegistros gestorRegistros;
	APP app;

	@BeforeEach
	void setUp() throws Exception {
		this.gestorRegistros = mock(GestorRegistros.class);
		this.app = mock(APP.class);
		this.punto = new PuntoDeVenta(this.gestorRegistros);
	}

	@Test
	void testGenerarRecarga80Pesos() {
		//SetUP
		Float monto = 80f;
		this.punto.generarRecarga(this.app, monto);
		//Verify
		verify(this.gestorRegistros).generarRecarga(this.punto, 1, this.app, monto);
	}
	
	@Test
	void testGenerarCompraPuntualPor2Horas() {
		//SetUP
		String patente = "AB 123 CD";
		Integer horasCompradas = 2;
		this.punto.generarCompraPuntual(patente, horasCompradas);
		//Verify
		verify(this.gestorRegistros).generarCompraPuntual(this.punto, 1, patente,
				horasCompradas);
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
