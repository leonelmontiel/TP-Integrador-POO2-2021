package app;

import sem.SEM;

public class App {

	private int numero;
	private SEM sistema;

	public App(int numero, SEM sistema) {
		this.numero = numero;
		this.sistema = sistema;
	}

	public int getNumero() {
		return this.numero;
	}

	public void iniciarEstacionamiento(String patente) {
		// envía la patente para iniciar el estacionamiento y a su vez a la app misma para que el sistema los vincule
		this.sistema.iniciarApp(patente, this);
	}

	public void finalizarEstacionamiento() {
		// se envía a sí misma para que el sistema la vincule con la patente y así finalice el estacionamiento registrado
		this.sistema.finalizarApp(this);
	}

}
