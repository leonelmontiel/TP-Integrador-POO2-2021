package registroDeCompra;

import java.time.LocalDate;
import java.time.LocalTime;

import app.APP;
import puntoDeVenta.PuntoDeVenta;

public class RegistroDeCompraPuntual extends RegistroDeCompra {

	private Integer horasCompradas;

	public RegistroDeCompraPuntual(PuntoDeVenta punto, Integer nroControl, LocalDate fecha, LocalTime hora, APP celular, Integer horasCompradas) {
		super(punto, nroControl, fecha, hora, celular);
		this.horasCompradas = horasCompradas;
	}

	public Integer getHorasCompradas() {
		return this.horasCompradas;
	}

}
