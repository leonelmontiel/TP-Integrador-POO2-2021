package registroDeCompra;

import java.time.LocalDate;
import java.time.LocalTime;

import app.App;

public class RegistroDeCompra {

	private Integer nroControl;
	private LocalDate fecha;
	private LocalTime hora;
	private App celular;

	public RegistroDeCompra(Integer nroControl, LocalDate fecha, LocalTime hora, App celular) {
		this.nroControl = nroControl;
		this.fecha = fecha;
		this.hora = hora;
		this.celular = celular;
	}

	public Integer getNroControl() {
		return this.nroControl;
	}

	public LocalDate getFecha() {
		return this.fecha;
	}

	public LocalTime getHora() {
		return this.hora;
	}

	public App getCelular() {
		return this.celular;
	}

}
