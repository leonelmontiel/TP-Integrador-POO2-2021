package estacionamiento;

import java.time.LocalDateTime;
import java.time.LocalTime;

import app.APP;

public class EstacionamientoFinalizadoAPP extends Estacionamiento {

	private APP aplicacion;
	private LocalTime horaFin;
	private Float costo;

	public EstacionamientoFinalizadoAPP(APP aplicacion, LocalTime horaInicio, LocalTime horaFin, String patente, Float costo) {
		super(horaInicio, patente);
		this.aplicacion = aplicacion;
		this.horaFin = horaFin;
		this.costo = costo;
	}
	
	public APP getApp() {
		return this.aplicacion;
	}

	public LocalTime getHoraFin() {
		return this.horaFin;
	}

	public Float getCosto() {
		return this.costo;
	}

	@Override
	public Boolean estaVigente(LocalDateTime momentoConsulta) {
		return false;
	}

}
