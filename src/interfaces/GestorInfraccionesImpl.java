package interfaces;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import infraccion.Infraccion;
import inspector.Inspector;

public class GestorInfraccionesImpl implements GestorInfracciones {

	private GestorEstacionamiento gestorEstacionamiento;
	private List<Infraccion> infracciones;

	public GestorInfraccionesImpl(GestorEstacionamiento gestorEstacionamiento) {
		this.gestorEstacionamiento = gestorEstacionamiento;
		this.infracciones = new ArrayList<Infraccion>();
	}
	
	public List<Infraccion> getInfracciones(){
		return this.infracciones;
	}
	
	void setInfracciones(List<Infraccion> infracciones) {
		this.infracciones = infracciones;
	}
	
	public Boolean tieneInfracciones(String patente) {
		return !this.infracciones.stream()
				.filter(infraccion -> infraccion.getPatente().equals(patente)).toList().isEmpty();
	}
	
	@Override
	public Boolean tieneEstacionamientoVigente(String patente, LocalDateTime momentoConsulta) {
		return this.gestorEstacionamiento.tieneEstacionamientoVigente(patente, momentoConsulta);
	}

	@Override
	public void altaInfraccion(String patente, Inspector inspector) {
		LocalDate fecha = LocalDate.now();
		LocalTime hora = LocalTime.now();
		Infraccion infraccion = new Infraccion(patente, inspector, fecha, hora, inspector.getZona());
		
		this.infracciones.add(infraccion);
	}

}
