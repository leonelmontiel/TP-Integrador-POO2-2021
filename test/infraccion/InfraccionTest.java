package infraccion;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inspector.Inspector;
import zona.Zona;

class InfraccionTest {
	Infraccion infraccion;
	Inspector inspector;
	Zona zona;
	String patente;
	LocalDate fecha;
	LocalTime hora;

	@BeforeEach
	void setUp() throws Exception {
		this.inspector = mock(Inspector.class);
		this.zona = mock(Zona.class);
		this.patente = "AB 123 CD";
		this.fecha = LocalDate.of(2021, 11, 11);
		this.hora = LocalTime.of(14, 0);
		this.infraccion = new Infraccion(this.patente, this.inspector, this.fecha, this.hora, this.zona);
	}

	@Test
	void testGetInspector() {
		assertEquals(this.inspector, this.infraccion.getInspector());
	}
	
	@Test
	void testGetZona() {
		assertEquals(this.zona, this.infraccion.getzona());
	}
	
	@Test
	void testGetPatente() {
		assertEquals(this.patente, this.infraccion.getPatente());
	}
	
	@Test
	void testGetFecha() {
		assertEquals(this.fecha, this.infraccion.getFecha());
	}
	
	@Test
	void testGetHora() {
		assertEquals(this.hora, this.infraccion.getHora());
	}

}
