package sem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entidad.Entidad;
import estacionamiento.Estacionamiento;
import registroDeCompra.RegistroDeCompra;
import registroDeCompra.RegistroDeCompraPuntual;
import registroDeCompra.RegistroDeRecargaCelular;
import zona.Zona;

class SistemaCentralTest {

	private SistemaCentralImpl sistema;
	private LocalTime horaInicio;
	private LocalTime horaCierre;
	private Float precioPorHora;
	private List<Zona> zonas;
	
	private Estacionamiento estacionamiento;
	private RegistroDeCompra registro;
	private Entidad entidad;
	private Entidad otraEntidad;
	private GestorEstacionamiento gestorEstacionamiento;
	private GestorAPP gestorAPP;
	
	@BeforeEach
	void setUp() throws Exception {
		this.horaInicio = LocalTime.of(7, 0);
		this.horaCierre = LocalTime.of(20, 0);
		this.precioPorHora = 40f;
		this.zonas = new ArrayList<Zona>();
		this.sistema = new SistemaCentralImpl(this.horaInicio, this.horaCierre, this.precioPorHora, this.zonas);
	}

	@Test
	void testHoraInicio() {
		LocalTime horaEsperada = this.horaInicio;
		
		assertEquals(horaEsperada, this.sistema.getHoraInicio());
	}
	
	@Test
	void testHoraCierre() {
		LocalTime horaEsperada = this.horaCierre;
		
		assertEquals(horaEsperada, this.sistema.getHoraCierre());
	}
	
	@Test
	void testPrecioPorHora() {
		Float precioEsperado = 40f;
		
		assertEquals(precioEsperado, this.sistema.getPrecioPorHora());
	}
	
	@Test
	void testGetZonas() {
		List<Zona> zonasEsperadas = this.zonas;
		
		assertEquals(zonasEsperadas, this.sistema.getZonas());
	}
	
	@Test
	void testGetCosto() {
		//configuracion de mocks
		this.estacionamiento = mock(Estacionamiento.class);
		when(this.estacionamiento.getDuracion()).thenReturn(3);
		Float costoEsperado = 120f;
		
		assertEquals(costoEsperado, this.sistema.getCosto(this.estacionamiento));
	}
	
	@Test
	void testGenerarRecarga() {
		//configuracion de mocks
		this.gestorAPP = mock(GestorAPP.class);
		this.registro = mock(RegistroDeRecargaCelular.class);

		//setup
		this.sistema.setGestorAPP(this.gestorAPP);
		
		//exercise
		this.sistema.generarRecarga((RegistroDeRecargaCelular) this.registro);
		
		//verify
		verify(this.gestorAPP).recargarSaldo((RegistroDeRecargaCelular) this.registro);
	}
	
	@Test
	void testGenerarCompraPuntual() {
		//configuracion de mocks
		this.registro = mock(RegistroDeCompraPuntual.class);
		this.gestorEstacionamiento = mock(GestorEstacionamiento.class);
		
		//setup
		this.sistema.setGestorEstacionamientos(this.gestorEstacionamiento);
		
		//exercise
		this.sistema.generarEstacionamientoPuntual((RegistroDeCompraPuntual) this.registro);
		
		//verify
		verify(this.gestorEstacionamiento).generarEstacionamiento((RegistroDeCompraPuntual) this.registro);
	}
	
	@Test
	void testSuscribirEntidad() {
		//configuracion de mocks
		this.entidad = mock(Entidad.class);
		
		//setup
		List<Entidad> entidadesSpy = spy(new ArrayList<Entidad>());
		this.sistema.setEntidades(entidadesSpy);
		
		//exercise
		this.sistema.suscribir(this.entidad);
		
		//verify
		verify(entidadesSpy).add(this.entidad);
	}
	
	@Test
	void testNoSeSuscribeDosVecesLaMismaEntidad() {
		//configuracion de mocks
		this.entidad = mock(Entidad.class);
		
		//setup
		List<Entidad> entidadesSpy = spy(new ArrayList<Entidad>());
		this.sistema.setEntidades(entidadesSpy);
		
		//exercise
		this.sistema.suscribir(this.entidad);
		this.sistema.suscribir(this.entidad);
		
		//verify
		verify(entidadesSpy, times(1)).add(this.entidad);		
	}

	@Test
	void testDesuscribirEntidad() {
		//configuracion de mocks
		this.entidad = mock(Entidad.class);
		
		//setup
		List<Entidad> entidadesSpy = spy(new ArrayList<Entidad>());
		this.sistema.setEntidades(entidadesSpy);
		
		//exercise
		this.sistema.suscribir(this.entidad);
		this.sistema.desuscribir(this.entidad);
		
		//verify
		verify(entidadesSpy).add(this.entidad);
		verify(entidadesSpy).remove(this.entidad);
	}
	
	@Test
	void testNoSeDesuscribeUnaEntidadNoSuscripta() {
		//configuracion de mocks
		this.entidad = mock(Entidad.class);
		
		//setup
		List<Entidad> entidadesSpy = spy(new ArrayList<Entidad>());
		this.sistema.setEntidades(entidadesSpy);
		
		//exercise
		this.sistema.desuscribir(this.entidad);
		
		//verify
		verify(entidadesSpy, never()).remove(this.entidad);		
	}
	
	@Test
	void testIniciarEstacionamientoNotificaEstacionamientoIniciado() {
		//configuracion de mocks
		this.entidad = mock(Entidad.class);
		this.otraEntidad = mock(Entidad.class);
		this.gestorEstacionamiento = mock(GestorEstacionamiento.class);
		//dummy
		this.estacionamiento = mock(Estacionamiento.class);

		//setup
		List<Entidad> entidades = Arrays.asList(this.entidad, this.otraEntidad);
		this.sistema.setEntidades(entidades);
		this.sistema.setGestorEstacionamientos(this.gestorEstacionamiento);
		
		//exercise
		this.sistema.iniciarEstacionamiento(this.estacionamiento);
		
		//verify
		verify(this.entidad).actualizarEstacionamientoIniciado(this.sistema, this.estacionamiento);
		verify(this.otraEntidad).actualizarEstacionamientoIniciado(this.sistema, this.estacionamiento);
	}
	
	@Test
	void testFinalizarEstacionamientoNotificaEstacionamientoFinalizado() {
		//configuracion de mocks
		this.entidad = mock(Entidad.class);
		this.otraEntidad = mock(Entidad.class);
		//dummy
		this.estacionamiento = mock(Estacionamiento.class);
		
		//setup
		List<Entidad> entidades = Arrays.asList(this.entidad, this.otraEntidad);
		this.sistema.setEntidades(entidades);
		
		//exercise
		this.sistema.finalizarEstacionamiento(this.estacionamiento);
		
		//verify
		verify(this.entidad).actualizarEstacionamientoFinalizado(this.sistema, this.estacionamiento);
		verify(this.otraEntidad).actualizarEstacionamientoFinalizado(this.sistema, this.estacionamiento);
	}

	@Test
	void testGenerarRecargaNotificaRecargaDeCredito() {
		//configuracion de mocks
		this.entidad = mock(Entidad.class);
		this.otraEntidad = mock(Entidad.class);
		this.gestorAPP = mock(GestorAPP.class);
		//dummy
		this.registro = mock(RegistroDeRecargaCelular.class); 
		
		//setup
		List<Entidad> entidades = Arrays.asList(this.entidad, this.otraEntidad);
		this.sistema.setEntidades(entidades);
		this.sistema.setGestorAPP(this.gestorAPP);
		
		//exercise
		this.sistema.generarRecarga((RegistroDeRecargaCelular) this.registro);
		
		//verify
		verify(this.entidad).actualizarRecargaDeCredito(this.sistema, (RegistroDeRecargaCelular) this.registro);
		verify(this.otraEntidad).actualizarRecargaDeCredito(this.sistema, (RegistroDeRecargaCelular) this.registro);
	}
	
	@Test 
	void testFinalizarTodosLosEstacionamientos() {
		//configuracion de mocks
		this.gestorAPP = mock(GestorAPP.class);
		
		//setup
		this.sistema.setGestorAPP(this.gestorAPP);
		
		//exercise
		this.sistema.finalizarTodosLosEstacionamientos();
		
		//verify
		verify(this.gestorAPP).finalizarTodosLosEstacionamientos();
	}

}
