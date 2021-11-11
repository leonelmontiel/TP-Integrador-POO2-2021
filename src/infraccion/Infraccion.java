package infraccion;

import java.time.LocalDate;
import java.time.LocalTime;

import inspector.Inspector;
import zona.Zona;

public class Infraccion {

	private String patente;
	private Inspector inspector;
	private LocalDate fecha;
	private LocalTime hora;
	private Zona zona;

	public Infraccion(String patente, Inspector inspector, LocalDate fecha, LocalTime hora, Zona zona) {
		this.patente = patente;
		this.inspector = inspector;
		this.fecha = fecha;
		this.hora = hora;
		this.zona = zona;
	}

	public String getPatente() {
		return this.patente;
	}
	
	public Inspector getInspector() {
		return this.inspector;
	}

	public LocalDate getFecha() {
		return this.fecha;
	}
	
	public LocalTime getHora() {
		return this.hora;
	}
	
	public Zona getzona() {
		return this.zona;
	}

}
