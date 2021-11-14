package sem;

import estacionamiento.Estacionamiento;
import registroDeCompra.RegistroDeCompraPuntual;
import registroDeCompra.RegistroDeRecargaCelular;

public interface SistemaCentral extends Consultable{
	
	public void iniciarEstacionamiento(Estacionamiento estacionamiento);
	
	public void finalizarEstacionamiento(Estacionamiento estacionamiento);

	public void generarEstacionamientoPuntual(RegistroDeCompraPuntual registroCompraPuntual);

	public void generarRecarga(RegistroDeRecargaCelular registroDeRecargaCelular);

}
