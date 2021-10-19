package registroDeCompra;

import java.time.LocalDate;
import java.time.LocalTime;

import app.App;

public class RegistroDeRecargaCelular extends RegistroDeCompra {

	private Float montoRecarga;

	public RegistroDeRecargaCelular(Integer nroControl, LocalDate fecha, LocalTime hora, App celular, Float montoRecarga) {
		super(nroControl, fecha, hora, celular);
		this.montoRecarga = montoRecarga;
	}

	public Float getMontoRecarga() {
		return montoRecarga;
	}

}
