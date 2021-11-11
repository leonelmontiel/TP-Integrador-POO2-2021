package zona;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inspector.Inspector;
import puntoDeVenta.PuntoDeVenta;

class ZonaTest {
	
	Zona zona;
	Inspector inspector;
	List<PuntoDeVenta> puntosDeVenta;
	PuntoDeVenta puntoDeVenta;

	@BeforeEach
	void setUp() throws Exception {
		this.inspector = mock(Inspector.class);
		this.zona = new Zona(this.inspector);
		this.puntoDeVenta = mock(PuntoDeVenta.class);
	}

	@Test
	void testGetInspector() {
		assertEquals(this.inspector, this.zona.getInspector());
	}
	
	@Test
	void testGetPuntoDeVenta() {
		//SetUP
		this.zona.setPuntosDeVenta(this.puntosDeVenta);
		//Verify
		assertEquals(this.puntosDeVenta, this.zona.getPuntosDeVenta());
	}
	
	@Test
	void testRegistrarPuntoDeVenta() {
		//Config Spy
		this.puntosDeVenta = spy(new ArrayList<PuntoDeVenta>());
		//SetUP
		this.zona.setPuntosDeVenta(this.puntosDeVenta);
		//Exercise
		this.zona.registrar(this.puntoDeVenta);
		//Verify
		verify(this.puntosDeVenta).add(this.puntoDeVenta);
	}
	
	@Test
	void testNoRegistrarPuntoDeVentaIguales() {
		//Config Spy
		this.puntosDeVenta = spy(new ArrayList<PuntoDeVenta>());
		//SetUP
		this.zona.setPuntosDeVenta(this.puntosDeVenta);
		//Exercise
		this.zona.registrar(this.puntoDeVenta);
		this.zona.registrar(this.puntoDeVenta);
		//Verify
		verify(this.puntosDeVenta, times(1)).add(this.puntoDeVenta);
	}
	
	@Test
	void testDesvincular() {
		//Config Spy
		this.puntosDeVenta = spy(new ArrayList<PuntoDeVenta>());
		//SetUP
		this.zona.setPuntosDeVenta(this.puntosDeVenta);
		//Exercise
		this.zona.registrar(this.puntoDeVenta);
		this.zona.desvincular(this.puntoDeVenta);
		//Verify
		verify(this.puntosDeVenta).add(this.puntoDeVenta);
		verify(this.puntosDeVenta).remove(this.puntoDeVenta);		
	}
	
	@Test
	void testNoPuedeDesvincular() {
		//Config Spy
		this.puntosDeVenta = spy(new ArrayList<PuntoDeVenta>());
		//SetUP
		this.zona.setPuntosDeVenta(this.puntosDeVenta);
		//Exercise
		this.zona.desvincular(this.puntoDeVenta);
		//Verify
		verify(this.puntosDeVenta, never()).remove(this.puntoDeVenta);		
	}

}
