package estacionamiento;

import java.time.LocalTime;

public class EstacionamientoFinalizadoAPP extends Estacionamiento {

	private LocalTime horaFin;
	private Float costo;

	public EstacionamientoFinalizadoAPP(LocalTime horaInicio, LocalTime horaFin, String patente, Float costo) {
		super(horaInicio, patente);
		this.horaFin = horaFin;
		this.costo = costo;
	}

	public LocalTime getHoraFin() {
		return this.horaFin;
	}

	public Float getCosto() {
		return this.costo;
	}

	@Override
	public Boolean estaVigente() {
		return false;
	}

}
