package estacionamiento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import registroDeCompra.RegistroDeCompraPuntual;

public class EstacionamientoPuntual extends Estacionamiento {

	private RegistroDeCompraPuntual registroCompraPuntual;

	public EstacionamientoPuntual(LocalTime horaInicio, LocalTime horaFin, String patente,
			RegistroDeCompraPuntual compraPuntual) {
		super(horaInicio, horaFin, patente);
		this.registroCompraPuntual = compraPuntual;
	}

	public RegistroDeCompraPuntual getRegistroCompraPuntual() {
		return this.registroCompraPuntual;
	}

	@Override
	public Boolean estaVigente(LocalDateTime momentoConsulta) {
		return this.esFechaDeCompra(momentoConsulta.toLocalDate())
				&& this.esHoraEnVigencia(momentoConsulta.toLocalTime());
	}
	
	private Boolean esFechaDeCompra(LocalDate fechaConsulta) {
		return fechaConsulta.equals(this.getRegistroCompraPuntual().getFecha());
	}

	private Boolean esHoraEnVigencia(LocalTime horaConsulta) {
		return horaConsulta.isBefore(this.getHoraFin()) 
				&& horaConsulta.isAfter(this.getHoraInicio());
	}

	@Override
	public Integer getDuracion() {
		return	this.getHoraFin().getHour() - this.getHoraInicio().getHour();
	}

}
