package app;

import java.time.LocalTime;

import estacionamiento.Estacionamiento;
import sem.SEM;

public class APP {

	private int numero;
	private SEM sistema;

	public APP(int numero, SEM sistema) {
		this.numero = numero;
		this.sistema = sistema;
	}

	public int getNumero() {
		return this.numero;
	}

	public void iniciarEstacionamiento(String patente) {
		// EVALUAR SI TIENE SALDO SUFICIENTE PARA LA PRIMERA HORA DE ESTACIONAMIENTO $40
		if (saldoEsMayorOIgualAPrecioPorHora()) {
			// env�a la patente para iniciar el estacionamiento y a su vez a la app misma para que el sistema los vincule
			this.sistema.iniciarEstacionamiento(patente, this);
		} else {
			System.out.println("No ten�s saldo suficiente para iniciar este estacionamiento. El precio por hora es: " + "$" + this.sistema.getPrecioPorHora());
		}
		
	}

	public boolean saldoEsMayorOIgualAPrecioPorHora() {
		return this.sistema.getSaldo(this) >= this.sistema.getPrecioPorHora();
	}

	public void finalizarEstacionamiento() {
		// SI NO TIENE SALDO SUFICIENTE, FINALIZA EL ESTACIONAMIENTO PERO QUEDA SALDO NEGATIVO
		// SI NO LO FINALIZA, SIGUE ABIERTO HASTA QUE SE CIERRA SOLO A LAS 20H
		// se env�a a s� misma para que el sistema la vincule con la patente y as� finalice el estacionamiento registrado
		this.sistema.finalizarEstacionamiento(this);
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
}
