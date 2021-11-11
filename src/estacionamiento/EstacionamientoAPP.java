package estacionamiento;

import java.time.LocalDateTime;
import java.time.LocalTime;

import app.APP;

public class EstacionamientoAPP extends Estacionamiento {

	private APP app;
	private String patente;

	public EstacionamientoAPP(APP aplicacion, String patente, LocalTime horaInicio) {
		super(horaInicio, null);
		this.app = aplicacion;
		this.patente = patente;
	}
	
	public void finalizar() {
		this.setHoraFin(LocalTime.now());
	}

	public APP getApp() {
		return this.app;
	}

	public Float getCosto() {
		//ver como calcularlo si no esta finalizado
		return null;
	}
	
	@Override
	public Boolean estaVigente(LocalDateTime momentoConsulta) {
		return (this.getHoraFin() == null) ? true : false;
	}

	@Override
	public String getPatente() {
		return this.patente;
	}

}
