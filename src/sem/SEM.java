package sem;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import app.APP;
import estacionamiento.Estacionamiento;
import estacionamiento.EstacionamientoAPP;

public class SEM {

	private LocalTime horaInicio;
	private LocalTime horaCierre;
	private Float precioPorHora;
	private List<Estacionamiento> estacionamientos;

	public SEM(LocalTime horaInicio, LocalTime horaCierre, Float precioPorHora) {
		this.horaInicio = horaInicio;
		this.horaCierre = horaCierre;
		this.precioPorHora = precioPorHora;
		this.estacionamientos = new ArrayList<Estacionamiento>();
	}

	public LocalTime getHoraInicio() {
		return this.horaInicio;
	}

	public LocalTime getHoraCierre() {
		return this.horaCierre;
	}

	public Float getPrecioPorHora() {
		return this.precioPorHora;
	}

	void setEstacionamientos(List<Estacionamiento> estacionamientos) {
		this.estacionamientos = estacionamientos;
	}

	public Estacionamiento iniciarEstacionamiento(String patente, APP app) {
		LocalTime horaInicio = LocalTime.now(); 
		EstacionamientoAPP nuevoEstacionamiento = new EstacionamientoAPP(app, horaInicio, patente);
		
		this.estacionamientos.add(nuevoEstacionamiento);
		
		return nuevoEstacionamiento;
	}

}
