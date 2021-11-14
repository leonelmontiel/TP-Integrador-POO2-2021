package sem;

import java.time.LocalTime;

import estacionamiento.Estacionamiento;

public interface Consultable {
	
	public Float getCosto(Estacionamiento estacionamiento);

	public Float getPrecioPorHora();

	public LocalTime getHoraInicio();
	
	public LocalTime getHoraCierre();

}
