package entidad;

import estacionamiento.Estacionamiento;
import registroDeCompra.RegistroDeRecargaCelular;
import sem.Consultable;

public interface Entidad {

	void actualizarEstacionamientoIniciado(Consultable sistemaCentral, Estacionamiento estIniciado);

	void actualizarEstacionamientoFinalizado(Consultable sistemaCentral, Estacionamiento estFinalizado);

	void actualizarRecargaDeCredito(Consultable sistemaCentral, RegistroDeRecargaCelular registro);

}
