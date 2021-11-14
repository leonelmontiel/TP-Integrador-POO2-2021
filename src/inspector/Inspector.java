package inspector;

import java.time.LocalDateTime;

import sem.GestorInfracciones;
import zona.Zona;

public class Inspector {

	private GestorInfracciones sistema;
	private Zona zona;	

	public Inspector(GestorInfracciones sistema, Zona zona) {
		this.sistema = sistema;
		this.zona = zona;
	}
	
	public GestorInfracciones getSistema() {
		return this.sistema;
	}

	public Zona getZona() {
		return this.zona;
	}

	/**@implNote 
	 * recibe el momento en el que se realiza la consulta como colaborador externo, porque es necesario para determinar si
	 * el estacionamiento está vigente*/
	public Boolean verificarVigencia(String patente, LocalDateTime tiempoConsulta) {
		return this.sistema.tieneEstacionamientoVigente(patente, tiempoConsulta);		
	}

	public void altaInfraccion(String patente) {
		this.sistema.altaInfraccion(patente, this);
	}

}
