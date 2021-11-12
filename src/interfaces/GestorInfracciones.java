package interfaces;

import java.time.LocalDateTime;

import inspector.Inspector;

public interface GestorInfracciones {

	Boolean tieneEstacionamientoVigente(String patente, LocalDateTime tiempoConsulta);

	void altaInfraccion(String patente, Inspector inspector);

}
