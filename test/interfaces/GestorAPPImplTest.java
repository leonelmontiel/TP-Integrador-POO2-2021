package interfaces;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.APP;
import estacionamiento.EstacionamientoAPP;
import registroDeCompra.RegistroDeRecargaCelular;

class GestorAPPImplTest {

	private GestorAppImpl gestor;
	private SistemaCentral sistema;
	private APP app;
	private RegistroDeRecargaCelular registro;
	private RegistroDeRecargaCelular otroRegistro;
	private EstacionamientoAPP estacionamiento;
	private String patente;
	
	@BeforeEach
	void setUp() throws Exception {
		//mock
		this.sistema = mock(SistemaCentral.class);
		
		this.gestor = new GestorAppImpl(this.sistema);
		
	}

	@Test
	void testRegistrarAPP() {
		//configuracion de mocks
		Map<APP, Float> usuariosAPPSpy = spy(new HashMap<APP, Float>());
		this.app = mock(APP.class);
		
		//setup
		this.gestor.setUsuariosAPP(usuariosAPPSpy);
		
		//exercise
		this.gestor.regitrarAPP(this.app);
		
		//verify
		verify(usuariosAPPSpy).put(this.app, 0f);
	}
	
	@Test
	void testSoloRegistraUnaVezAUnaAPPRegistrarAPP() {
		//configuracion de mocks
		Map<APP, Float> usuariosAPPSpy = spy(new HashMap<APP, Float>());
		this.app = mock(APP.class);
		
		//setup
		this.gestor.setUsuariosAPP(usuariosAPPSpy);
		
		//exercise
		this.gestor.regitrarAPP(this.app);
		this.gestor.regitrarAPP(this.app);
		
		//verify
		verify(usuariosAPPSpy, times(1)).put(this.app, 0f);
	}
	
	@Test
	void testGetSaldo() {
		//setup
		this.gestor.regitrarAPP(this.app);
		
		assertEquals(0f, this.gestor.getSaldo(this.app));
	}
	
	@Test
	void testGetHoraMaximaRetornaHoraCierre() {
		//configuracion de mocks
		LocalTime diecinueveHoras = LocalTime.of(19, 0);
		@SuppressWarnings("unchecked")
		Map<APP, Float> usuarios = mock(Map.class);
		this.app = mock(APP.class);
		when(this.sistema.getHoraCierre()).thenReturn(LocalTime.of(20, 0));
		when(this.sistema.getPrecioPorHora()).thenReturn(40f);
		when(usuarios.get(this.app)).thenReturn(120f);
		
		this.gestor.setUsuariosAPP(usuarios);
		
		//verify
		assertEquals(this.sistema.getHoraCierre(), this.gestor.getHoraMaxima(this.app, diecinueveHoras));
		verify(this.sistema).getHoraCierre();
		verify(this.sistema).getPrecioPorHora();
		verify(usuarios).get(this.app);
	}
	
	@Test
	void testRecargaDeSaldo() {
		//configuracion de mocks
		Float montoRecarga = 80f;
		this.app = mock(APP.class);
		this.registro = mock(RegistroDeRecargaCelular.class);
		when(registro.getApp()).thenReturn(this.app);
		when(registro.getMontoRecarga()).thenReturn(montoRecarga);
		
		//exercise
		this.gestor.regitrarAPP(this.app);
		this.gestor.recargarSaldo(this.registro);
		
		//verify
		assertEquals(montoRecarga, this.gestor.getSaldo(this.app));
		verify(this.registro, times(2)).getApp();
		verify(this.registro).getMontoRecarga();
	}
	
	@Test
	void testRecargaDeSaldoIncrementaElSaldoExistente() {
		//configuracion de mocks
		Float montoRecarga = 80f;
		Float montoRecarga2 = 20f;
		Float montoEsperado = 100f;
		this.app = mock(APP.class);
		this.registro = mock(RegistroDeRecargaCelular.class);
		this.otroRegistro = mock(RegistroDeRecargaCelular.class);
		when(registro.getApp()).thenReturn(this.app);
		when(registro.getMontoRecarga()).thenReturn(montoRecarga);
		when(otroRegistro.getApp()).thenReturn(this.app);
		when(otroRegistro.getMontoRecarga()).thenReturn(montoRecarga2);
		
		
		//exercise
		this.gestor.regitrarAPP(this.app);
		this.gestor.recargarSaldo(this.registro);
		this.gestor.recargarSaldo(this.otroRegistro);
		
		//verify
		assertEquals(montoEsperado, this.gestor.getSaldo(this.app));
		verify(this.registro, times(2)).getApp();
		verify(this.registro).getMontoRecarga();
		verify(this.otroRegistro, times(2)).getApp();
		verify(this.otroRegistro).getMontoRecarga();
	}
	
	@Test
	void testFinalizarUnEstacionamientoDecrementaElSaldoDeLaAPPAsociada() {
		Float saldoInicial = 120f;
		Float saldoEsperado = 80f;
		//configuracion de mocks
		this.estacionamiento = mock(EstacionamientoAPP.class);
		this.registro = mock(RegistroDeRecargaCelular.class);
		this.app = mock(APP.class);
	
		when(this.estacionamiento.getApp()).thenReturn(this.app);
		when(this.estacionamiento.getHoraInicio()).thenReturn(LocalTime.of(12, 0));
		when(this.estacionamiento.getHoraFin()).thenReturn(LocalTime.of(13, 0));
		when(this.estacionamiento.getDuracion()).thenReturn(1);
		when(this.sistema.getCosto(this.estacionamiento)).thenReturn(40f);
		when(this.registro.getApp()).thenReturn(this.app);
		when(this.registro.getMontoRecarga()).thenReturn(saldoInicial);
		
		@SuppressWarnings("unchecked")
		Map<APP, EstacionamientoAPP> estacionamientosMock = mock(Map.class);
		when(estacionamientosMock.get(this.app)).thenReturn(this.estacionamiento);
		
		//setup
		this.gestor.regitrarAPP(this.app);
		this.gestor.recargarSaldo(this.registro);
		this.gestor.setEstacionamientos(estacionamientosMock);
		
		//exercise
		this.gestor.finalizarEstacionamiento(this.app);
		
		//verify
		assertEquals(saldoEsperado, this.gestor.getSaldo(this.app));
		verify(this.estacionamiento, atLeast(1)).getHoraInicio();
		verify(this.estacionamiento, atLeast(1)).getHoraFin();
		verify(this.estacionamiento).getDuracion();
		verify(this.sistema).getCosto(this.estacionamiento);
		verify(this.registro, atLeast(1)).getApp();
		verify(this.registro).getMontoRecarga();
	}
	
	@Test
	void testIniciarEstacionamiento() {
		this.patente = "ABC 123";
		this.app = mock(APP.class);
		@SuppressWarnings("unchecked")
		Map<APP, Float> usuarios = mock(Map.class);
		
		when(this.sistema.getHoraCierre()).thenReturn(LocalTime.of(20, 0));
		when(this.sistema.getPrecioPorHora()).thenReturn(40f);
		when(usuarios.get(this.app)).thenReturn(80f);
		
		//setup
		this.gestor.setUsuariosAPP(usuarios);
		
		this.gestor.iniciarEstacionamiento(this.patente, this.app);
		
		assertTrue(this.gestor.tieneEstacionamientoIniciado(this.app));
		
		verify(this.sistema, atLeast(1)).getHoraCierre();
		verify(this.sistema, atLeast(1)).getPrecioPorHora();
		verify(usuarios, atLeast(1)).get(this.app);
	}

	@Test
	void testIniciarEstacionamientoSinSaldo() {
		this.patente = "ABC 123";
		this.app = mock(APP.class);
		@SuppressWarnings("unchecked")
		Map<APP, Float> usuarios = mock(Map.class);
		
		when(this.sistema.getHoraCierre()).thenReturn(LocalTime.of(20, 0));
		when(this.sistema.getPrecioPorHora()).thenReturn(40f);
		when(usuarios.get(this.app)).thenReturn(20f);
		
		//setup
		this.gestor.setUsuariosAPP(usuarios);
		
		this.gestor.iniciarEstacionamiento(this.patente, this.app);
		
		assertFalse(this.gestor.tieneEstacionamientoIniciado(this.app));
		
		verify(this.sistema, atLeast(1)).getPrecioPorHora();
		verify(this.app).notificarAlUsuario("No dispone de saldo suficiente");
		verify(usuarios, atLeast(1)).get(this.app);
	}

}
