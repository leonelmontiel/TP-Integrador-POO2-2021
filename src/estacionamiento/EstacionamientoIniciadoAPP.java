package estacionamiento;

import java.time.LocalDate;
import java.time.LocalTime;

import app.APP;

public class EstacionamientoIniciadoAPP extends Estacionamiento {

	private APP app;

	public EstacionamientoIniciadoAPP(APP aplicacion, LocalTime horaInicio, String patente) {
		super(horaInicio, patente);
		this.app = aplicacion;
	}

	public APP getApp() {
		return this.app;
	}
	
	public LocalTime getHoraMaxima() {
		//todo: hay que implementar como se calcula en base al saldo en la app
		return null;
	}
	
	@Override
	public Boolean estaVigente(LocalDate fechaConsulta) {
		return true;
	}

}
