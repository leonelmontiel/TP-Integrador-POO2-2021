package sem;

import java.time.LocalDateTime;
import java.util.List;

import infraccion.Infraccion;
import inspector.Inspector;

public interface GestorInfracciones {

	public List<Infraccion> getInfracciones();	
	
	public Boolean tieneInfracciones(String patente);
	
	public Boolean tieneEstacionamientoVigente(String patente, LocalDateTime tiempoConsulta);

	public void altaInfraccion(String patente, Inspector inspector);

}
