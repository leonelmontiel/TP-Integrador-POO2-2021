package estacionamiento;

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
	public Boolean estaVigente() {
		return null;
	}
}
