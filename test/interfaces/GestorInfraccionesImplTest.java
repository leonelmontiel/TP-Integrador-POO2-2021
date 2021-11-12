package interfaces;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import infraccion.Infraccion;
import inspector.Inspector;

class GestorInfraccionesImplTest {
	
	private GestorInfraccionesImpl gestor;
	private GestorEstacionamiento gestorEstacionamiento;
	private Infraccion infraccion;
	private String patente;
	private Inspector inspector;

	@BeforeEach
	void setUp() throws Exception {
		//mock
		this.gestorEstacionamiento = mock(GestorEstacionamiento.class);
		this.gestor = new GestorInfraccionesImpl(this.gestorEstacionamiento);
		this.patente = "ABC 123";
	}

	@Test
	void testGetInfracciones() {
		//setup
		List<Infraccion> infracciones = new ArrayList<Infraccion>();
		this.gestor.setInfracciones(infracciones);
		
		//verify
		assertEquals(infracciones, this.gestor.getInfracciones());
	}
	
	@Test
	void testPatenteTieneInfracciones() {
		//mock
		this.infraccion = mock(Infraccion.class);
		when(this.infraccion.getPatente()).thenReturn(this.patente);
		//setup
		List<Infraccion> infracciones = Arrays.asList(this.infraccion);
		this.gestor.setInfracciones(infracciones);
		
		//verify
		assertTrue(this.gestor.tieneInfracciones(this.patente));
		verify(this.infraccion).getPatente();
	}

	@Test
	void testPatenteNoTieneInfracciones() {
		//mock
		this.infraccion = mock(Infraccion.class);
		when(this.infraccion.getPatente()).thenReturn("ACB 123");
		//setup
		List<Infraccion> infracciones = Arrays.asList(this.infraccion);
		this.gestor.setInfracciones(infracciones);
		
		//verify
		assertFalse(this.gestor.tieneInfracciones(this.patente));
		verify(this.infraccion).getPatente();
	}
	
	@Test
	void testPatenteTieneEstacionamientoVigente() {
		//mock
		LocalDateTime momentoConsulta = LocalDateTime.now();
		when(this.gestorEstacionamiento.tieneEstacionamientoVigente(this.patente, momentoConsulta))
			.thenReturn(true);
		
		//verify
		assertTrue(this.gestor.tieneEstacionamientoVigente(this.patente, momentoConsulta));
		verify(this.gestorEstacionamiento).tieneEstacionamientoVigente(this.patente, momentoConsulta);
	}
	
	@Test
	void testPatenteNoTieneEstacionamientoVigente() {
		//mock
		LocalDateTime momentoConsulta = LocalDateTime.now();
		when(this.gestorEstacionamiento.tieneEstacionamientoVigente(this.patente, momentoConsulta))
			.thenReturn(false);
		
		//verify
		assertFalse(this.gestor.tieneEstacionamientoVigente(this.patente, momentoConsulta));
		verify(this.gestorEstacionamiento).tieneEstacionamientoVigente(this.patente, momentoConsulta);
	}
	
	@Test
	void testAltaInfraccion() {
		//dummy mock
		this.inspector = mock(Inspector.class);
		
		//verify condiciones previas al alta infraccion
		assertFalse(this.gestor.tieneInfracciones(this.patente));
		
		//setup
		this.gestor.altaInfraccion(this.patente, this.inspector);
		
		//verify
		assertTrue(this.gestor.tieneInfracciones(this.patente));
	}

}
