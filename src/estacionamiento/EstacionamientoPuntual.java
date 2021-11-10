package estacionamiento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import app.APP;
import registroDeCompra.RegistroDeCompraPuntual;

public class EstacionamientoPuntual extends Estacionamiento {

	private RegistroDeCompraPuntual registroCompraPuntual;

	public EstacionamientoPuntual(LocalTime horaInicio, LocalTime horaFin, RegistroDeCompraPuntual compraPuntual) {
		super(horaInicio, horaFin);
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
	public APP getApp() {
		return null;
	}

	@Override
	public void finalizar() {
		
	}

	@Override
	public String getPatente() {
		return this.getRegistroCompraPuntual().getPatente();
	}
}
