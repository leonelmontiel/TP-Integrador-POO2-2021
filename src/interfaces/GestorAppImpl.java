package interfaces;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import app.APP;
import estacionamiento.Estacionamiento;
import estacionamiento.EstacionamientoAPP;
import registroDeCompra.RegistroDeRecargaCelular;

public class GestorAppImpl implements GestorAPP {

	private Map<APP, Float> usuariosAPP;
	private Map<APP, EstacionamientoAPP> estacionamientoAPP;
	private SistemaCentral sistema;

	public GestorAppImpl(SistemaCentral sistema) {
		this.usuariosAPP = new HashMap<APP, Float>();
		this.estacionamientoAPP = new HashMap<APP, EstacionamientoAPP>();
		this.sistema = sistema;
	}
	
	void setUsuariosAPP(Map<APP, Float> usuariosAPP) {
		this.usuariosAPP = usuariosAPP;
	}

	void setEstacionamientos(Map<APP, EstacionamientoAPP> estacionamientos) {
		this.estacionamientoAPP = estacionamientos;
	}
	
	public void regitrarAPP(APP app) {
		if(!this.tieneRegistradoApp(app)) {			
			this.usuariosAPP.put(app, 0f);
		}
	}

	private Boolean tieneRegistradoApp(APP app) {
		return this.usuariosAPP.containsKey(app);
	}
	
	@Override
	public void recargarSaldo(RegistroDeRecargaCelular registroDeRecargaCelular) {
		Float nuevoSaldo = this.getNuevoSaldo(registroDeRecargaCelular); 
		this.usuariosAPP.replace(registroDeRecargaCelular.getApp(), nuevoSaldo);
	}

	private Float getNuevoSaldo(RegistroDeRecargaCelular registroDeRecargaCelular) {
		return this.getSaldo(registroDeRecargaCelular.getApp()) + registroDeRecargaCelular.getMontoRecarga();
	}

	@Override
	public Float getSaldo(APP app) {
		return this.usuariosAPP.get(app);
	}

	@Override
	public void finalizarEstacionamiento(APP app) {
		Estacionamiento estacionamientoAFinalizar = this.popEstacionamiento(app);
		
		Float costo = this.sistema.getCosto(estacionamientoAFinalizar);
		String notificacion = "Iniciado " + estacionamientoAFinalizar.getHoraInicio() + 
				" Finalizado " + estacionamientoAFinalizar.getHoraFin() + " con una duracion de " +
				estacionamientoAFinalizar.getDuracion() + " y un costo de " + 
				costo;
		
		this.decrementarSaldo(app, costo);
		this.sistema.finalizarEstacionamiento(estacionamientoAFinalizar);
		app.notificarAlUsuario(notificacion);
		
	}

	private void decrementarSaldo(APP app, Float costo) {
		Float nuevoSaldo = this.usuariosAPP.get(app) - costo;
		this.usuariosAPP.replace(app, nuevoSaldo);
	}

	private Estacionamiento popEstacionamiento(APP app) {
		Estacionamiento estacionamiento = this.estacionamientoAPP.get(app);
		this.estacionamientoAPP.remove(app);
		return estacionamiento;
	}

	@Override
	public void iniciarEstacionamiento(String patente, APP app) {
		if(this.tieneSaldoSuficiente(app)) {
			LocalTime horaInicio = LocalTime.now();
			EstacionamientoAPP nuevoEstacionamiento = new EstacionamientoAPP(app, patente, horaInicio);
			String notificacion = "Estacionamiento iniciado a las " + nuevoEstacionamiento.getHoraInicio() +
					" valido hasta las " + this.getHoraMaxima(app, horaInicio) + " hs.";
			
			this.sistema.iniciarEstacionamiento(nuevoEstacionamiento);
			this.registrarEstacionamiento(app, nuevoEstacionamiento);
			app.notificarAlUsuario(notificacion);
			
		} else {
			String notificacionSinCredito = "No dispone de saldo suficiente";
			app.notificarAlUsuario(notificacionSinCredito);
		}
		
	}

	private void registrarEstacionamiento(APP app, EstacionamientoAPP nuevoEstacionamiento) {
		this.estacionamientoAPP.put(app, nuevoEstacionamiento);
	}

	public LocalTime getHoraMaxima(APP app, LocalTime hora) {
		Integer horasQuePuedeComprar = this.getHorasQuePuedeComprar(app);
		LocalTime potencialHoraFin = hora.plusHours(horasQuePuedeComprar); 
		
		return (potencialHoraFin.isAfter(this.sistema.getHoraCierre())) ? this.sistema.getHoraCierre()
				: potencialHoraFin;
	}

	private Integer getHorasQuePuedeComprar(APP app) {
		return (int) (this.getSaldo(app) / 
				this.sistema.getPrecioPorHora());
	}

	@Override
	public Boolean tieneSaldoSuficiente(APP app) {
		return this.getSaldo(app) >= this.sistema.getPrecioPorHora();
	}

	public Boolean tieneEstacionamientoIniciado(APP app) {
		return this.estacionamientoAPP.containsKey(app);
	}

}
