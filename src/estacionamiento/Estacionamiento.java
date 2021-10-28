package estacionamiento;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Estacionamiento {

	private LocalTime horaInicio;
	private String patente;

	public Estacionamiento(LocalTime horaInicio, String patente) {
		this.horaInicio = horaInicio;
		this.patente = patente;
	}
	
	public LocalTime getHoraInicio() {
		return this.horaInicio;
	}
	
	public String getPatente() {
		return this.patente;
	}
	
	public abstract Boolean estaVigente(LocalDate fechaConsulta);

}
