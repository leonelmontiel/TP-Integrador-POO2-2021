package sem;

import java.time.LocalTime;

public interface Consultable {

	public Float getPrecioPorHora();

	public LocalTime getHoraInicio();
	
	public LocalTime getHoraCierre();

}
