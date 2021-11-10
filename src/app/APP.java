package app;

import java.time.LocalTime;

import estacionamiento.Estacionamiento;
import sem.SEM;

public class APP implements MovementSensor {

	private int numero;
	private SEM sistema;
	private EstadoAPP estado;
	private AsistenciaAlUsuario asistenciaAlUsuario;
	private Pantalla pantanlla;

	public APP(int numero, SEM sistema) {
		this.numero = numero;
		this.sistema = sistema;
		this.estado = new SinEstacionamiento();
		this.asistenciaAlUsuario = AsistenciaAlUsuario.DESACTIVADA;
	}

	public int getNumero() {
		return this.numero;
	}

	public void iniciarEstacionamiento(String patente) {
		this.estado.iniciarEstacionamiento(this, patente);		
	}

	public boolean saldoEsMayorOIgualAPrecioPorHora() {
		return this.sistema.getSaldo(this) >= this.sistema.getPrecioPorHora();
	}

	public void finalizarEstacionamiento() {
		// SI NO TIENE SALDO SUFICIENTE, FINALIZA EL ESTACIONAMIENTO PERO QUEDA SALDO NEGATIVO
		// SI NO LO FINALIZA, SIGUE ABIERTO HASTA QUE SE CIERRA SOLO A LAS 20H
		// se env�a a s� misma para que el sistema la vincule con la patente y as� finalice el estacionamiento registrado
		this.estado.finalizarEstacionamiento(this);
	}

	public Float getSaldo() {
		// se env�a a s� misma para consultar en el registro del SEM cu�nto saldo tiene disponible
		return this.sistema.getSaldo(this);
	}

	public LocalTime getHoraMaxima(Estacionamiento estacionamiento) {
		// consulta al sistema la hora m�xima registrada para el estacionamiento dado
		//this.sistema.getHoraMaxima(estacionamiento);
		return null;
	}

	public Boolean tieneEstacionamiento() {
		return this.estado.tieneEstacionamiento();
	}

	void finalizarEstacionamientoSeguro() {
		this.sistema.finalizarEstacionamiento(this);		
	}

	void setEstado(EstadoAPP nuevoEstado) {
		this.estado = nuevoEstado;		
	}

	void iniciarEstacionamientoSeguro(String patente) {
		this.sistema.iniciarEstacionamiento(patente, this);		
	}
	
	@Override
	public void driving() {
		this.estado.alertaFinEstacionamiento(this);
	}
	
	@Override
	public void walking() {
		this.estado.alertaInicioEstacionamiento(this);
	}

	//esto se utiliza para emular lo que se le muestra al usuario de la app en la pantalla
	Pantalla getPantalla() {
		return this.pantanlla;
	}

	void setPantalla(Pantalla pantalla) {
		this.pantanlla = pantalla;
	}

	public AsistenciaAlUsuario getAsistenciaAlUsuario() {
		return this.asistenciaAlUsuario;
	}

	public void activarAsistenciaAlUsuario() {
		this.asistenciaAlUsuario = AsistenciaAlUsuario.ACTIVADA;
	}

	public void desactivarAsistenciaAlUsuario() {
		this.asistenciaAlUsuario = AsistenciaAlUsuario.DESACTIVADA;
	}

	public SEM getSistema() {
		return this.sistema;
	}
}
