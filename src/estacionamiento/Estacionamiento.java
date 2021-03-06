package estacionamiento;

import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract class Estacionamiento {

	private LocalTime horaInicio;
	private LocalTime horaFin;
	private String patente; 

	public Estacionamiento(LocalTime horaInicio, LocalTime horaFin, String patente) {
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.patente = patente;
	}
	
	public LocalTime getHoraInicio() {
		return this.horaInicio;
	}
	
	public LocalTime getHoraFin() {
		return this.horaFin;
	}
	
	protected void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}
	
	public String getPatente() {
		return this.patente;
	}
	
	public abstract Boolean estaVigente(LocalDateTime momentoConsulta);

	public abstract Integer getDuracion();

}
