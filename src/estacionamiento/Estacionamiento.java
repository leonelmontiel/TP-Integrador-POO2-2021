package estacionamiento;

import java.time.LocalTime;

public abstract class Estacionamiento {

	private LocalTime horaInicio;

	public Estacionamiento(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}
	
	public LocalTime getHoraInicio() {
		return this.horaInicio;
	}

}
