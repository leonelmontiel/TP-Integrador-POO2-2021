package interfaces;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.APP;
import registroDeCompra.RegistroDeRecargaCelular;

class GestorAPPImplTest {

	private GestorAppImpl gestor;
	private APP app;
	private RegistroDeRecargaCelular registro;
	private RegistroDeRecargaCelular otroRegistro;
	
	@BeforeEach
	void setUp() throws Exception {
		this.gestor = new GestorAppImpl();
		
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
	
	

}
