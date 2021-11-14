package registroDeCompra;

import java.time.LocalDate;
import java.time.LocalTime;

import app.APP;
import puntoDeVenta.PuntoDeVenta;

public class RegistroDeRecargaCelular extends RegistroDeCompra {

	private Float montoRecarga;
	private APP app;

	public RegistroDeRecargaCelular(PuntoDeVenta punto, Integer nroControl, LocalDate fecha, 
			LocalTime hora, APP app, Float montoRecarga) {
		super(punto, nroControl, fecha, hora);
		this.app = app;
		this.montoRecarga = montoRecarga;
	}

	public Float getMontoRecarga() {
		return this.montoRecarga;
	}

	public APP getApp() {
		return this.app;
	}

}
