package registroDeCompra;

import java.time.LocalDate;
import java.time.LocalTime;

import app.App;
import puntoDeVenta.PuntoDeVenta;

public class RegistroDeRecargaCelular extends RegistroDeCompra {

	private Float montoRecarga;

	public RegistroDeRecargaCelular(PuntoDeVenta punto, Integer nroControl, LocalDate fecha, LocalTime hora, App celular, Float montoRecarga) {
		super(punto, nroControl, fecha, hora, celular);
		this.montoRecarga = montoRecarga;
	}

	public Float getMontoRecarga() {
		return montoRecarga;
	}

}
