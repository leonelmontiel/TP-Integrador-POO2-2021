package interfaces;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.APP;
import puntoDeVenta.PuntoDeVenta;
import registroDeCompra.RegistroDeCompra;

class GestorRegistrosImplTest {
	
	private GestorRegistrosImpl gestor;
	private SistemaCentral sistema;
	private RegistroDeCompra registro;
	private PuntoDeVenta puntoDeVenta;
	private String patente;
	private APP app;
	
	@BeforeEach
	void setUp() throws Exception {
		//mock 
		this.sistema = mock(SistemaCentral.class);
		this.gestor = new GestorRegistrosImpl(this.sistema);
	}

	@Test
	void testAlmacenarRegistro() {
		//configuracion de mocks
		this.registro = mock(RegistroDeCompra.class);
		List<RegistroDeCompra> registrosSpy = spy(new ArrayList<RegistroDeCompra>());
		
		//setup
		this.gestor.setRegistros(registrosSpy);
		
		//exercise
		this.gestor.almacenar(this.registro);
		
		//verify
		verify(registrosSpy).add(this.registro);
	}
	
	@Test
	void testGenerarCompraPuntual() {
		//mock 
		this.puntoDeVenta = mock(PuntoDeVenta.class);
		this.patente = "abc 123";
		
		//exercise
		this.gestor.generarCompraPuntual(this.puntoDeVenta, 1, this.patente, 2);
		
		//verify
		assertTrue(this.gestor.tieneAlmacenadoRegistro(1));
	}
	
	@Test
	void testGenerarRecarga() {
		//mock
		this.puntoDeVenta = mock(PuntoDeVenta.class);
		this.app = mock(APP.class);
		
		//exercise
		this.gestor.generarRecarga(this.puntoDeVenta, 2, this.app, 80f);
		
		//verify
		assertTrue(this.gestor.tieneAlmacenadoRegistro(2));
	}

}
