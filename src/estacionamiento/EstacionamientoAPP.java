package estacionamiento;

import java.time.LocalDateTime;
import java.time.LocalTime;

import app.APP;

public class EstacionamientoAPP extends Estacionamiento {

	private static final String ESTACIONAMIENTO_VIGENTE = "El estacionamiento se encuentra vigente";
	private APP app;

	public EstacionamientoAPP(APP aplicacion, String patente, LocalTime horaInicio) {
		super(horaInicio, null, patente);
		this.app = aplicacion;
	}
	
	public APP getApp() {
		return this.app;
	}
	
	public void finalizar() {
		this.setHoraFin(LocalTime.now());
	}
	
	@Override
	public Boolean estaVigente(LocalDateTime momentoConsulta) {
		return this.estaVigente();
	}

	private Boolean estaVigente() {
		return (this.getHoraFin() == null) ? true : false;
	}

	@Override
	public Integer getDuracion() {
		this.asegurarFinalizado();
		return	this.getHoraFin().getHour() - this.getHoraInicio().getHour();
	}

	/**@implNote
	 * el sistema no admite que se le solicite la duracion a un estacionamiento no finalizado.
	 * En caso de que se fuerse dicha situacion debe lanzar error */
	private void asegurarFinalizado() {
		if(this.estaVigente()) throw new RuntimeException(ESTACIONAMIENTO_VIGENTE);
	}
}
