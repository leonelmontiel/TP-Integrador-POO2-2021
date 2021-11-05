package estacionamiento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import app.APP;
import registroDeCompra.RegistroCompraPuntual;

public class EstacionamientoPuntual extends Estacionamiento {

	private RegistroCompraPuntual registroCompraPuntual;

	public EstacionamientoPuntual(LocalTime horaInicio, LocalTime horaFin, String patente, 
			RegistroCompraPuntual compraPuntual) {
		super(horaInicio, horaFin, patente);
		this.registroCompraPuntual = compraPuntual;
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

	@Override
	public APP getApp() {
		return null;
	}

	@Override
	public void finalizar() {
		
	}
}
