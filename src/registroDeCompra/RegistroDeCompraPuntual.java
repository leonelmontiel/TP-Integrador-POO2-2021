package registroDeCompra;

import java.time.LocalDate;
import java.time.LocalTime;

import puntoDeVenta.PuntoDeVenta;

public class RegistroDeCompraPuntual extends RegistroDeCompra {

	private String patente;
	private Integer horasCompradas;

	public RegistroDeCompraPuntual(PuntoDeVenta punto, Integer nroControl, LocalDate fecha, LocalTime hora, String patente, Integer horasCompradas) {
		super(punto, nroControl, fecha, hora);
		this.patente = patente;
		this.horasCompradas = horasCompradas;
	}

	public Integer getHorasCompradas() {
		return this.horasCompradas;
	}

	public String getPatente() {
		return this.patente;
	}

}
