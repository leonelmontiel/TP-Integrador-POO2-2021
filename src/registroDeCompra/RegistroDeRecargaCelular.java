package registroDeCompra;

import java.time.LocalDate;
import java.time.LocalTime;

import app.APP;
import puntoDeVenta.PuntoDeVenta;

public class RegistroDeRecargaCelular extends RegistroDeCompra {

	private Float montoRecarga;
	private APP celular;

	public RegistroDeRecargaCelular(PuntoDeVenta punto, Integer nroControl, LocalDate fecha, LocalTime hora, APP celular, Float montoRecarga) {
		super(punto, nroControl, fecha, hora);
		this.celular = celular;
		this.montoRecarga = montoRecarga;
	}

	public Float getMontoRecarga() {
		return this.montoRecarga;
	}

	public APP getCelular() {
		return this.celular;
	}

}
