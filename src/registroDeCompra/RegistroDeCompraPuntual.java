package registroDeCompra;

import java.time.LocalDate;
import java.time.LocalTime;

import app.App;

public class RegistroDeCompraPuntual extends RegistroDeCompra {

	private Integer horasCompradas;

	public RegistroDeCompraPuntual(Integer nroControl, LocalDate fecha, LocalTime hora, App celular, Integer horasCompradas) {
		super(nroControl, fecha, hora, celular);
		this.horasCompradas = horasCompradas;
	}

	public Integer getHorasCompradas() {
		return horasCompradas;
	}

}
