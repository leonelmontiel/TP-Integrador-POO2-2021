package entidad;

import estacionamiento.Estacionamiento;
import sem.SEM;

public interface Entidad {

	void actualizarEstacionamientoIniciado(SEM sem, Estacionamiento estIniciado);

	void actualizarEstacionamientoFinalizado(SEM sem, Estacionamiento estFinalizado);

}
