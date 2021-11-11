package inspector;

import java.time.LocalDateTime;

import sem.SEM;
import zona.Zona;

public class Inspector {

	private SEM sistema;
	private Zona zona;	

	public Inspector(SEM sem, Zona zona) {
		this.sistema = sem;
		this.zona = zona;
	}
	
	public SEM getSistema() {
		return this.sistema;
	}

	public Zona getZona() {
		return this.zona;
	}

	public Boolean verificarVigencia(String patente, LocalDateTime tiempoConsulta) {
		// recibe el momento en el que se realiza la consulta como colaborador externo, porque es necesario para determinar si
		// el estacionamiento está vigente
		return this.sistema.tieneEstacionamientoVigente(patente, tiempoConsulta);		
	}

	public void altaInfraccion(String patente) {
		this.sistema.altaInfraccion(patente, this);
	}

}
