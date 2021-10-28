package estacionamiento;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import registroDeCompra.RegistroCompraPuntual;

class EstacionamientoPuntualTest extends EstacionamientoTest {

	private RegistroCompraPuntual compraPuntual;
	
	@BeforeEach
	void setUp() throws Exception {
		
		this.horaInicio = LocalTime.of(12, 0);
		this.horaFin = LocalTime.of(15, 0);
		this.patente = "AA 325 AA";
		this.compraPuntual = mock(RegistroCompraPuntual.class);
		this.estacionamiento = new EstacionamientoPuntual(this.horaInicio, this.horaFin, this.patente, 
				this.compraPuntual);
		
	}
	
	@Test
	void testUnEstacionamientoPuntualTieneUnaHoraDeFinalizacion() {
		LocalTime horaEsperada = this.horaFin;
		assertEquals(horaEsperada, ((EstacionamientoPuntual) this.estacionamiento).getHoraFin());
	}
	
	@Test
	void testUnEstacionamientoPuntualTieneUnRegistroDeCompraPuntualAsociado() {
		RegistroCompraPuntual registroEsperado = this.compraPuntual;
		assertEquals(registroEsperado, ((EstacionamientoPuntual) this.estacionamiento).getRegistroCompraPuntual());
	}
	
	@Test
	void testUnEstacionamientoPuntualEstaVigenteSiEsDelDia() {
		//setup
		LocalDateTime hoy = LocalDateTime.of(2021, 10, 14, 14, 0);
		LocalDate fechaCompra = LocalDate.of(2021, 10, 14); 
		
		//configuracion de mock
		when(this.compraPuntual.getFecha()).thenReturn(fechaCompra);
		
		assertTrue(this.estacionamiento.estaVigente(hoy));
		
		//verify
		verify(this.compraPuntual, atLeast(1)).getFecha();
	}
	
	@Test
	void testUnEstacionamientoPuntualNoEstaVigenteSiNoSeConsultaElDiaDeSuCompra() {
		//setup
		LocalDateTime hoy = LocalDateTime.of(2021, 10, 14, 14, 0);
		LocalDate otroDia = LocalDate.of(2021, 10, 30);
		
		//configuracion de mock
		when(this.compraPuntual.getFecha()).thenReturn(otroDia);
		
		assertFalse(this.estacionamiento.estaVigente(hoy));
		
		//verify
		verify(this.compraPuntual, atLeast(1)).getFecha();
	}

	@Test
	void testUnEstacionamientoPuntualEstaVigenteSiSeConsultaDentroDeSuHorarioInicioFin() {
		//setup
		LocalDateTime hoy = LocalDateTime.of(2021, 10, 14, 14, 0);
		LocalDate fechaCompra = LocalDate.of(2021, 10, 14); 
		
		//configuracion de mock
		when(this.compraPuntual.getFecha()).thenReturn(fechaCompra);
		
		assertTrue(this.estacionamiento.estaVigente(hoy));
		
		//verify
		verify(this.compraPuntual, atLeast(1)).getFecha();
	}
	
	@Test
	void testUnEstacionamientoPuntualNoEstaVigenteSiSeConsultaFueraDeSuHorarioInicioFin() {
		//setup
		LocalDateTime hoy = LocalDateTime.of(2021, 10, 14, 17, 0);
		LocalDate fechaCompra = LocalDate.of(2021, 10, 14); 
		
		//configuracion de mock
		when(this.compraPuntual.getFecha()).thenReturn(fechaCompra);
		
		assertFalse(this.estacionamiento.estaVigente(hoy));
		
		//verify
		verify(this.compraPuntual, atLeast(1)).getFecha();
	}


}
