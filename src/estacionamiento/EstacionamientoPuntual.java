package estacionamiento;

import java.time.LocalTime;

public class EstacionamientoPuntual extends Estacionamiento {

	private String patente;

	public EstacionamientoPuntual(LocalTime horaInicio, String patente) {
		super(horaInicio);
		this.patente = patente;
	}

	public String getPatente() {
		return this.patente;
	}
}
