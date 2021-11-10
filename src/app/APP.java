package app;

import java.time.LocalTime;

import estacionamiento.Estacionamiento;
import sem.SEM;

public class APP {

	private int numero;
	private SEM sistema;
	private EstadoAPP estado;

	public APP(int numero, SEM sistema) {
		this.numero = numero;
		this.sistema = sistema;
		this.estado = new SinEstacionamiento();
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
}
