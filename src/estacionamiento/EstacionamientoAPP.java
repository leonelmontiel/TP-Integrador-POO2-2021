package estacionamiento;

import java.time.LocalDateTime;
import java.time.LocalTime;

import app.APP;

public class EstacionamientoAPP extends Estacionamiento {

	private APP app;

	public EstacionamientoAPP(APP aplicacion, LocalTime horaInicio, String patente) {
		super(horaInicio, null, patente);
		this.app = aplicacion;
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
	
	public LocalTime getHoraMaxima() {
		//todo: hay que implementar como se calcula en base al saldo en la app
		return this.app.getHoraMaxima(this);
	}
	
	@Override
	public Boolean estaVigente(LocalDateTime momentoConsulta) {
		return (this.getHoraFin() == null) ? true : false;
	}

}
