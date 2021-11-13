package inspector;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import interfaces.GestorInfracciones;
import zona.Zona;

class InspectorTest {
	
	Inspector inspector; //SUT
	Zona zona; //DOC
	String patente; //DOC
	GestorInfracciones sistema; //DOC

	@BeforeEach
	void setUp() throws Exception {
		this.zona = mock(Zona.class);
		this.patente = "AB 123 CD";
		this.sistema = mock(GestorInfracciones.class);
		this.inspector = new Inspector(this.sistema, this.zona);
	}

	@Test
	void testGetSistema() {
		assertEquals(this.sistema, this.inspector.getSistema());
	}
	
	@Test
	void testGetZona() {
		assertEquals(this.zona, this.inspector.getZona());
	}
	
	@Test
	void testVerificarVigenciaDeInfraccionOK() {
		//SetUp
		LocalDateTime tiempoConsulta = LocalDateTime.of(2021, 11, 11, 19, 0);
		// Config Mock
		when(this.sistema.tieneEstacionamientoVigente(this.patente, tiempoConsulta)).thenReturn(true);
		//Verify
		assertTrue(this.inspector.verificarVigencia(this.patente, tiempoConsulta));
		verify(this.sistema).tieneEstacionamientoVigente(this.patente, tiempoConsulta);
	}
	
	@Test
	void testVerificarVigenciaDeInfraccionFalso() {
		//SetUp
		LocalDateTime tiempoConsulta = LocalDateTime.of(2021, 11, 11, 19, 0);
		// Config Mock
		when(this.sistema.tieneEstacionamientoVigente(this.patente, tiempoConsulta)).thenReturn(false);
		//Verify
		assertFalse(this.inspector.verificarVigencia(this.patente, tiempoConsulta));
		verify(this.sistema).tieneEstacionamientoVigente(this.patente, tiempoConsulta);
	}
	
	@Test
	void testDarDeAltaInfraccion() {
		//Exercise
		this.inspector.altaInfraccion(this.patente);
		//Verify
		verify(this.sistema).altaInfraccion(this.patente, this.inspector);
	}

}
