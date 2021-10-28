package estacionamiento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import registroDeCompra.RegistroCompraPuntual;

public class EstacionamientoPuntual extends Estacionamiento {

	private LocalTime horaFin;
	private RegistroCompraPuntual registroCompraPuntual;

	public EstacionamientoPuntual(LocalTime horaInicio, LocalTime horaFin, String patente, 
			RegistroCompraPuntual compraPuntual) {
		super(horaInicio, patente);
		this.horaFin = horaFin;
		this.registroCompraPuntual = compraPuntual;
	}

	public LocalTime getHoraFin() {
		return this.horaFin;
	}
	
	public RegistroCompraPuntual getRegistroCompraPuntual() {
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
}
