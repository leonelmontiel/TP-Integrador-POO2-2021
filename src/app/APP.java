package app;

import java.time.LocalTime;

import estacionamiento.Estacionamiento;
import sem.SEM;

public class APP implements MovementSensor {

	private int numero;
	private SEM sistema;
	private Pantalla pantanlla;
	private EstadoAPP estado;
	private AsistenciaAlUsuario asistenciaAlUsuario;

	public APP(int numero, SEM sistema) {
		this.numero = numero;
		this.sistema = sistema;
		this.estado = new SinEstacionamiento();
		this.asistenciaAlUsuario = AsistenciaAlUsuario.DESACTIVADA;
	}

	public int getNumero() {
		return this.numero;
	}
	
	public SEM getSistema() {
		return this.sistema;
	}

	public Float getSaldo() {
		// se env�a a s� misma para consultar en el registro del SEM cu�nto saldo tiene disponible
		return this.sistema.getSaldo(this);
	}
	
	void setPantalla(Pantalla pantalla) {
		this.pantanlla = pantalla;
	}
	
	void setEstado(EstadoAPP nuevoEstado) {
		this.estado = nuevoEstado;		
	}
	
	public AsistenciaAlUsuario getAsistenciaAlUsuario() {
		return this.asistenciaAlUsuario;
	}
	
	public void notificarAlUsuario(String mensaje) {
		this.pantanlla.mostrar(mensaje);
	}
	
	public void iniciarEstacionamiento(String patente) {
		this.estado.iniciarEstacionamiento(this, patente);		
	}
	
	public void finalizarEstacionamiento() {
		// SI NO TIENE SALDO SUFICIENTE, FINALIZA EL ESTACIONAMIENTO PERO QUEDA SALDO NEGATIVO
		// SI NO LO FINALIZA, SIGUE ABIERTO HASTA QUE SE CIERRA SOLO A LAS 20H
		// se env�a a s� misma para que el sistema la vincule con la patente y as� finalice el estacionamiento registrado
		this.estado.finalizarEstacionamiento(this);
	}

	Boolean saldoEsMayorOIgualAPrecioPorHora() {
		return this.sistema.getSaldo(this) >= this.sistema.getPrecioPorHora();
	}

	public LocalTime getHoraMaxima(Estacionamiento estacionamiento) {
		// consulta al sistema la hora m�xima registrada para el estacionamiento dado
		//this.sistema.getHoraMaxima(estacionamiento);
		return null;
	}

	public Boolean tieneEstacionamiento() {
		return this.estado.tieneEstacionamiento();
	}

	@Override
	public void driving() {
		this.estado.alertaFinEstacionamiento(this);
	}
	
	@Override
	public void walking() {
		this.estado.alertaInicioEstacionamiento(this);
	}

	public void activarAsistenciaAlUsuario() {
		this.asistenciaAlUsuario = AsistenciaAlUsuario.ACTIVADA;
	}

	public void desactivarAsistenciaAlUsuario() {
		this.asistenciaAlUsuario = AsistenciaAlUsuario.DESACTIVADA;
	}

	void alertaInicioEstacionamiento() {
		this.asistenciaAlUsuario.alertaInicioEstacionamiento(this);
	}

	void alertaFinEstacionamiento() {
		this.asistenciaAlUsuario.alertaFinEstacionamiento(this);
	}
}
