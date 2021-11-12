package registroDeCompra;

import java.time.LocalDate;
import java.time.LocalTime;

import puntoDeVenta.PuntoDeVenta;

public abstract class RegistroDeCompra {

	private Integer nroControl;
	private LocalDate fecha;
	private LocalTime hora;
	private PuntoDeVenta puntoDeVenta;

	public RegistroDeCompra(PuntoDeVenta punto, Integer nroControl, LocalDate fecha, LocalTime hora) {
		this.puntoDeVenta = punto;
		this.nroControl = nroControl;
		this.fecha = fecha;
		this.hora = hora;
	}

	public Integer getNroControl() {
		return this.nroControl;
	}

	public LocalDate getFecha() {
		return this.fecha;
	}

	// se asume que en el momento de compra se realiza tanto la recarga de celular como el inicio del estacionamiento comprado
	public LocalTime getHora() {
		return this.hora;
	}

	public PuntoDeVenta getPuntoDeVenta() {
		return this.puntoDeVenta;
	}

}
