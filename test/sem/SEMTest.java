package sem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import app.APP;
import entidad.Entidad;
import estacionamiento.Estacionamiento;
import infraccion.Infraccion;
import inspector.Inspector;
import registroDeCompra.RegistroDeCompra;
import registroDeCompra.RegistroDeCompraPuntual;
import registroDeCompra.RegistroDeRecargaCelular;

class SEMTest {

	private SEM sem;
	private LocalTime horaInicio;
	private LocalTime horaCierre;
	private Float precioPorHora;
	
	private APP app;
	private Estacionamiento estacionamiento;
	private Inspector inspector;
	private Estacionamiento otroEstacionamiento;
	private Entidad entidad;
	private Entidad otraEntidad;
	private RegistroDeCompra registro;
	
	@BeforeEach
	void setUp() throws Exception {
		//mocks
		this.app = mock(APP.class);
		this.estacionamiento = mock(Estacionamiento.class);
		this.otroEstacionamiento = mock(Estacionamiento.class);
		this.inspector = mock(Inspector.class);
		this.entidad = mock(Entidad.class);
		this.otraEntidad = mock(Entidad.class);
		
		this.horaInicio = LocalTime.of(7, 0);
		this.horaCierre = LocalTime.of(20, 0);
		this.precioPorHora = 40f;
		
		this.sem = new SEM(this.horaInicio, this.horaCierre, this.precioPorHora);
		
	}

	@Test
	void testHoraInicio() {
		LocalTime horaEsperada = this.horaInicio;
		
		assertEquals(horaEsperada, this.sem.getHoraInicio());
	}
	
	@Test
	void testHoraCierre() {
		LocalTime horaEsperada = this.horaCierre;
		
		assertEquals(horaEsperada, this.sem.getHoraCierre());
	}
	
	@Test
	void testPrecioPorHora() {
		Float precioEsperado = 40f;
		
		assertEquals(precioEsperado, this.sem.getPrecioPorHora());
	}
/*
	@Test
	void testIniciarEstacionamientoYNotificarAlUsuario() {
		//configuracion de mocks
		List<Estacionamiento> estacionamientosSpy = spy(new ArrayList<Estacionamiento>());
		@SuppressWarnings("unchecked")
		Map<APP, Float> usuariosMock = mock(Map.class);
		when(usuariosMock.get(this.app)).thenReturn(80f);
	
		//setup 
		this.sem.setUsuariosAPP(usuariosMock);
		this.sem.setEstacionamientos(estacionamientosSpy);

		//exercise
		Estacionamiento estacionamientoNuevo = this.sem.iniciarEstacionamiento("abc 123", this.app);

		//verify
		verify(estacionamientosSpy).add(estacionamientoNuevo);
	}
	
	@Test
	void testNotificaAlUsuarioCuandoIniciaElEstacionamientoConInformacionDelMismo() {
		//configuracion de mocks
		@SuppressWarnings("unchecked")
		Map<APP, Float> usuariosMock = mock(Map.class);
		when(usuariosMock.get(this.app)).thenReturn(80f);
	
		//setup 
		this.sem.setUsuariosAPP(usuariosMock);

		//exercise
		Estacionamiento estacionamientoNuevo = this.sem.iniciarEstacionamiento("abc 123", this.app);
		
		String notificacionEsperada = "Estacionamiento iniciado a las " 
				+ estacionamientoNuevo.getHoraInicio() +
				" valido hasta las " + this.sem.getHoraMaxima(estacionamientoNuevo) + " hs.";
		
		//verify
		verify(this.app).notificarAlUsuario(notificacionEsperada);		
	}
	
	@Test
	void testFinalizarEstacionamiento() {
		//configuracion de mocks
		List<Estacionamiento> estacionamientosSpy = spy(new ArrayList<Estacionamiento>());
		@SuppressWarnings("unchecked")
		Map<APP, Float> usuariosMock = mock(Map.class);
		when(usuariosMock.get(this.app)).thenReturn(80f);
	
		//setup 
		this.sem.setUsuariosAPP(usuariosMock);
		this.sem.setEstacionamientos(estacionamientosSpy);
		
		Estacionamiento estacionamientoNuevo = this.sem.iniciarEstacionamiento("abc 123", this.app);
		
		//pensar que pasa si no tiene estacionamiento iniciado
		this.sem.finalizarEstacionamiento(this.app);
		
		//verify
		verify(estacionamientosSpy).add(estacionamientoNuevo);
		assertFalse(estacionamientoNuevo.estaVigente(LocalDateTime.now()));
	}
	
	@Test
	void testGetHoraMaxima() {
		//configuracion de mocks
		when(this.estacionamiento.getHoraInicio()).thenReturn(LocalTime.of(19, 0));
		when(this.estacionamiento.getApp()).thenReturn(this.app);
		@SuppressWarnings("unchecked")
		Map<APP, Float> usuariosMock = mock(Map.class);
		when(usuariosMock.get(this.app)).thenReturn(80f);

		//setup 
		LocalTime horaEsperada = this.horaCierre;
		this.sem.setUsuariosAPP(usuariosMock);
		
		//verify
		assertEquals(horaEsperada, this.sem.getHoraMaxima(this.estacionamiento));
		verify(this.estacionamiento).getHoraInicio();
		verify(this.estacionamiento).getApp();
	}
	
	@Test
	void testGetHoraMaximaAntesDelCierre() {
		//configuracion de mocks
		when(this.estacionamiento.getHoraInicio()).thenReturn(LocalTime.of(16, 0));
		when(this.estacionamiento.getApp()).thenReturn(this.app);
		@SuppressWarnings("unchecked")
		Map<APP, Float> usuariosMock = mock(Map.class);
		when(usuariosMock.get(this.app)).thenReturn(80f);

		//setup 
		LocalTime horaEsperada = LocalTime.of(18, 0);
		this.sem.setUsuariosAPP(usuariosMock);
		
		//verify
		assertEquals(horaEsperada, this.sem.getHoraMaxima(this.estacionamiento));
		verify(this.estacionamiento).getHoraInicio();
		verify(this.estacionamiento).getApp();
	}

	@Test
	void testAppSinRegistrar() {
		assertFalse(this.sem.tieneRegistradoApp(this.app));
	}
	
	@Test
	void testRegistrarApp() {
		//setup
		Map<APP, Float> usuariosSpy = spy(new HashMap<APP, Float>());
		this.sem.setUsuariosAPP(usuariosSpy);
		
		//exercise
		this.sem.regitrarAPP(this.app);
	
		//verify
		assertTrue(this.sem.tieneRegistradoApp(this.app));
		verify(usuariosSpy).put(this.app, 0f);
	}
	
	@Test
	void testGetSaldo() {
		//sutup
		Float saldoEsperado = 0f;
		this.sem.regitrarAPP(this.app);
		
		assertEquals(saldoEsperado, this.sem.getSaldo(this.app));
	}
	/*
	@Test
	void testRecargarSaldo() {
		//sutup
		Float montoRecarga = 10f;
		Float montoRecarga2 = 90f;
		Float saldoEsperado = 10f;
		Float saldoEsperado2 = 100f;
		this.sem.regitrarAPP(this.app);
		this.sem.recargarSaldo(this.app, montoRecarga);
		
		assertEquals(saldoEsperado, this.sem.getSaldo(this.app));
		
		this.sem.recargarSaldo(this.app, montoRecarga2);
		
		assertEquals(saldoEsperado2, saldoEsperado2);

	
	@Test
	void testRegistrarEstacionamiento() {
		//setup 
		List<Estacionamiento> estacionamientosSpy = spy(new ArrayList<Estacionamiento>());
		this.sem.setEstacionamientos(estacionamientosSpy);
		
		this.sem.registrarEstacionamiento(this.estacionamiento);
		
		//verify
		verify(estacionamientosSpy).add(this.estacionamiento);
	}
	
	@Test
	void testTieneEstacionamientoVigente() {
		LocalDateTime momentoConsulta = LocalDateTime.now();
		
		//configuracion de mock
		when(this.estacionamiento.getPatente()).thenReturn("abc 123");
		when(this.estacionamiento.estaVigente(momentoConsulta)).thenReturn(true);
		
		//setup
		this.sem.registrarEstacionamiento(this.estacionamiento);
		
		//verify
		assertTrue(this.sem.tieneEstacionamientoVigente("abc 123", momentoConsulta));
		
		verify(this.estacionamiento).getPatente();
		verify(this.estacionamiento).estaVigente(momentoConsulta);
	}
	
	@Test
	void testAltaInfracción() {
		//setup 		
		List<Infraccion> infraccionesSpy = spy(new ArrayList<Infraccion>());
		this.sem.setInfracciones(infraccionesSpy);
		
		Infraccion infraccionNueva = this.sem.altaInfraccion("ABC 123", this.inspector);
		
		//verify
		verify(infraccionesSpy).add(infraccionNueva);
	}
	
	@Test
	void testFinalizarTodosLosEstacionamientos() {
		//SetUp
		List<Estacionamiento> listaEstacionamientos = Arrays.asList(this.estacionamiento, this.otroEstacionamiento);
		this.sem.setEstacionamientos(listaEstacionamientos);
		
		//Excercise
		this.sem.finalizarTodosLosEstacionamientos();		
		
		//Verify
		verify(this.estacionamiento).finalizar();
		verify(this.otroEstacionamiento).finalizar();
	}
	
	@Test
	void testSuscribirEntidad() {
		//setUp
		List<Entidad> listaEntidadesSpy = spy(new ArrayList<Entidad>());
		this.sem.setEntidades(listaEntidadesSpy);
		
		//Excercise
		this.sem.suscribir(this.entidad);
		
		//Verify
		verify(listaEntidadesSpy).add(this.entidad);
	}
	
	@Test
	void testNoPuedeSuscribirEntidadRepetida() {
		//setUp
		List<Entidad> listaEntidadesSpy = spy(Arrays.asList(this.entidad));
		this.sem.setEntidades(listaEntidadesSpy);
		
		//Excercise
		this.sem.suscribir(this.entidad);
		
		//Verify
		verify(listaEntidadesSpy, never()).add(this.entidad);
	}
	
	@Test
	void testDesuscribirEntidad() {
		//setUp
		List<Entidad> listaEntidadesSpy = spy(new ArrayList<Entidad>());
		this.sem.setEntidades(listaEntidadesSpy);
		this.sem.suscribir(this.entidad);
		
		//Excercise
		this.sem.desuscribir(this.entidad);
		
		//Verify
		assertFalse(this.sem.estaSuscripto(this.entidad));
		verify(listaEntidadesSpy).remove(this.entidad);
	}
	
	@Test
	void testNoPuedeDesuscribirEntidadNoEstabaSuscripta() {
		//setUp
		List<Entidad> listaEntidadesSpy = spy(new ArrayList<Entidad>());
		this.sem.setEntidades(listaEntidadesSpy);
		
		//Excercise
		this.sem.suscribir(this.entidad);
		this.sem.desuscribir(this.otraEntidad);//intenta desuscribir una entidad que no es la que está en la lista
		
		//Verify
		assertEquals(1, listaEntidadesSpy.size());
		verify(listaEntidadesSpy).remove(this.otraEntidad); //llama al método pero no surte efecto
	}
	
	@Test
	void testNotificarEstacionamientoIniciado() {
		//configuracion de mocks
		@SuppressWarnings("unchecked")
		Map<APP, Float> usuariosMock = mock(Map.class);
		when(usuariosMock.get(this.app)).thenReturn(80f);
		
		//SetUP
		this.sem.setUsuariosAPP(usuariosMock);
		this.sem.suscribir(this.entidad);
		this.sem.suscribir(this.otraEntidad);
		
		//Excercise
		Estacionamiento estIniciado = this.sem.iniciarEstacionamiento("ABC 123", this.app);

		//Verify
		verify(this.entidad).actualizarEstacionamientoIniciado(this.sem, estIniciado);
		verify(this.otraEntidad).actualizarEstacionamientoIniciado(this.sem, estIniciado);		
	}
	
	@Test
	void testNotificarEstacionamientoFinalizado() {
		//configuracion de mocks
		@SuppressWarnings("unchecked")
		Map<APP, Float> usuariosMock = mock(Map.class);
		when(usuariosMock.get(this.app)).thenReturn(80f);

		//SetUP
		this.sem.setUsuariosAPP(usuariosMock);
		this.sem.suscribir(this.entidad);
		this.sem.suscribir(this.otraEntidad);
		
		//Excercise
		Estacionamiento estFinalizado = this.sem.iniciarEstacionamiento("ABC 123", this.app);
		this.sem.finalizarEstacionamiento(this.app);

		//Verify
		verify(this.entidad).actualizarEstacionamientoFinalizado(this.sem, estFinalizado);
		verify(this.otraEntidad).actualizarEstacionamientoFinalizado(this.sem, estFinalizado);		
	}
	
	
	@Test
	void testNotificarRecargaDeCelular() {
		//Config Mock
		Float monto = 80f;
		this.registro = mock(RegistroDeRecargaCelular.class);
		when(((RegistroDeRecargaCelular) this.registro).getApp()).thenReturn(this.app);
		when(((RegistroDeRecargaCelular) this.registro).getMontoRecarga()).thenReturn(monto);
		@SuppressWarnings("unchecked")
		Map<APP, Float> usuariosMock = mock(Map.class);
		when(usuariosMock.get(this.app)).thenReturn(80f);

		//SetUP
		this.sem.setUsuariosAPP(usuariosMock);
		this.sem.suscribir(this.entidad);
		this.sem.suscribir(this.otraEntidad);
		//Excercise
		this.sem.recargarSaldo((RegistroDeRecargaCelular) this.registro);
		//Verify
		verify(this.entidad).actualizarRecargaDeCredito(this.sem, (RegistroDeRecargaCelular) this.registro);
		verify(this.otraEntidad).actualizarRecargaDeCredito(this.sem, (RegistroDeRecargaCelular) this.registro);
		//IMPLEMENTAR LA NOTIFICACION POR RECARGA DE CELULAR (POSIBLE STRATEGY)
	}
	
	@Test
	void testAlmacenarRegistro() {
		//Config Mock y Spy
		this.registro = mock(RegistroDeCompra.class);
		List<RegistroDeCompra> registrosSpy = spy(new ArrayList<RegistroDeCompra>());
		this.sem.setRegistros(registrosSpy);
		//Exercise
		this.sem.almacenar(this.registro);
		//Verify
		verify(registrosSpy).add(this.registro);		
	}
	
	@Test
	void testGenerarEstacionamiento() {
		//Config Mock
		this.registro = mock(RegistroDeCompraPuntual.class);
		when(this.registro.getHora()).thenReturn(this.horaInicio);
		//Exercise
		this.sem.generarEstacionamiento((RegistroDeCompraPuntual) this.registro);
		//Verify
		((RegistroDeCompraPuntual) verify(this.registro)).notificarCompraExitosa();
	}
	*/
}
