package entidad;

import estacionamiento.Estacionamiento;
import interfaces.SistemaCentral;
import registroDeCompra.RegistroDeRecargaCelular;
import sem.SEM;

public interface Entidad {

	void actualizarEstacionamientoIniciado(SEM sem, Estacionamiento estIniciado);

	void actualizarEstacionamientoFinalizado(SistemaCentral sistemaCentral, Estacionamiento estFinalizado);

	void actualizarRecargaDeCredito(SistemaCentral sistemaCentral, RegistroDeRecargaCelular registro);

}
