package entidad;

import estacionamiento.Estacionamiento;
import registroDeCompra.RegistroDeRecargaCelular;
import sem.SistemaCentral;

public interface Entidad {

	void actualizarEstacionamientoIniciado(SistemaCentral sistemaCentral, Estacionamiento estIniciado);

	void actualizarEstacionamientoFinalizado(SistemaCentral sistemaCentral, Estacionamiento estFinalizado);

	void actualizarRecargaDeCredito(SistemaCentral sistemaCentral, RegistroDeRecargaCelular registro);

}
