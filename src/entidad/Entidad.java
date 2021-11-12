package entidad;

import estacionamiento.Estacionamiento;
import registroDeCompra.RegistroDeRecargaCelular;
import sem.SEM;

public interface Entidad {

	void actualizarEstacionamientoIniciado(SEM sem, Estacionamiento estIniciado);

	void actualizarEstacionamientoFinalizado(SEM sem, Estacionamiento estFinalizado);

	void actualizarRecargaDeCredito(SEM sem, RegistroDeRecargaCelular registro);

}
