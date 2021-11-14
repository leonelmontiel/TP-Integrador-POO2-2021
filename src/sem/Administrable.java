package sem;

import java.time.LocalTime;

/**
 * @implNote 
 * El administrador del sistema interactua con el mediante esta interfaz agregando una capa de seguridad
 * evitando que otras clases que interactuen puedan modificar los valores de hora inicio, hora cierre,
 * precio por hora, asi como tambien disparar la finalizacion de los estacionamientos cuando no es debido
 */
public interface Administrable {

	public void setHoraInicio(LocalTime horaInicio);
	
	public void setHoraCierre(LocalTime horaCierre);
	
	public void setPrecioPorHora(Float nuevoPrecio);
	
	public void finalizarTodosLosEstacionamientos();
}
