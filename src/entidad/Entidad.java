package entidad;

import estacionamiento.Estacionamiento;
import interfaces.SistemaCentral;
import registroDeCompra.RegistroDeRecargaCelular;

public interface Entidad {

	void actualizarEstacionamientoIniciado(SistemaCentral sistemaCentral, Estacionamiento estIniciado);

	void actualizarEstacionamientoFinalizado(SistemaCentral sistemaCentral, Estacionamiento estFinalizado);

	void actualizarRecargaDeCredito(SistemaCentral sistemaCentral, RegistroDeRecargaCelular registro);

}
