package estacionamiento;

import java.time.LocalDateTime;
import java.time.LocalTime;

import app.APP;

public class EstacionamientoAPP extends Estacionamiento {

	private APP app;

	public EstacionamientoAPP(APP aplicacion, String patente, LocalTime horaInicio) {
		super(horaInicio, null, patente);
		this.app = aplicacion;
	}
	
	public void finalizar() {
		this.setHoraFin(LocalTime.now());
	}

	public APP getApp() {
		return this.app;
	}
	
	@Override
	public Boolean estaVigente(LocalDateTime momentoConsulta) {
		return (this.getHoraFin() == null) ? true : false;
	}

}
