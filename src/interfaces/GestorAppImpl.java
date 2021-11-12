package interfaces;

import java.time.LocalTime;
import java.util.Map;

import app.APP;
import estacionamiento.Estacionamiento;
import estacionamiento.EstacionamientoAPP;
import registroDeCompra.RegistroDeRecargaCelular;

public class GestorAppImpl implements GestorAPP {

	private Map<APP, Float> usuariosAPP;
	private Map<APP, EstacionamientoAPP> estacionamientoAPP;
	private SistemaCentral sistema;

	@Override
	public void recargarSaldo(RegistroDeRecargaCelular registroDeRecargaCelular) {
		Float nuevoSaldo = getNuevoSaldo(registroDeRecargaCelular); 
		this.usuariosAPP.replace(registroDeRecargaCelular.getApp(), nuevoSaldo);
		this.notificarRecargaDeCredito(registroDeRecargaCelular);
	}

	private void notificarRecargaDeCredito(RegistroDeRecargaCelular registroDeRecargaCelular) {
		//pensar una colaboracion con el administrador
		//this.entidades.stream().forEach(ent -> ent.actualizarRecargaDeCredito(this, registro));
	}

	private float getNuevoSaldo(RegistroDeRecargaCelular registroDeRecargaCelular) {
		return this.getSaldo(registroDeRecargaCelular.getApp()) + registroDeRecargaCelular.getMontoRecarga();
	}

	@Override
	public Float getSaldo(APP app) {
		return this.usuariosAPP.get(app);
	}

	@Override
	public void finalizarEstacionamiento(APP app) {
		Estacionamiento estacionamientoAFinalizar = this.popEstacionamiento(app);
		
		estacionamientoAFinalizar.finalizar();
		
		Float costo = this.sistema.getCosto(estacionamientoAFinalizar);
		String notificacion = "Iniciado " + estacionamientoAFinalizar.getHoraInicio() + 
				" Finalizado " + estacionamientoAFinalizar.getHoraFin() + " con una duracion de " +
				estacionamientoAFinalizar.getDuracion() + " y un costo de " + 
				costo;
		
		this.decrementarSaldo(app, costo);
		this.sistema.notificarEstacionamientoFinalizado(estacionamientoAFinalizar);
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
		LocalTime horaInicio = LocalTime.now();
		EstacionamientoAPP nuevoEstacionamiento = new EstacionamientoAPP(app, patente, horaInicio);
		String notificacion = "Estacionamiento iniciado a las " + nuevoEstacionamiento.getHoraInicio() +
				" valido hasta las " + this.getHoraMaxima(app, horaInicio) + " hs.";
		
		this.registrarEstacionamiento(app, nuevoEstacionamiento);
		app.notificarAlUsuario(notificacion);
	}

	private void registrarEstacionamiento(APP app, EstacionamientoAPP nuevoEstacionamiento) {
		this.estacionamientoAPP.put(app, nuevoEstacionamiento);
	}

	private LocalTime getHoraMaxima(APP app, LocalTime hora) {
		Integer horasQuePuedeComprar = this.getHorasQuePuedeComprar(app);
		LocalTime potencialHoraFin = hora.plusHours(horasQuePuedeComprar); 
		
		return (potencialHoraFin.isAfter(this.sistema.getHoraCierre())) ? this.sistema.getHoraCierre()
				: potencialHoraFin;
	}

	private Integer getHorasQuePuedeComprar(APP app) {
		return (int) (this.getSaldo(app) / 
				this.sistema.getPrecioPorHora());
	}
}
