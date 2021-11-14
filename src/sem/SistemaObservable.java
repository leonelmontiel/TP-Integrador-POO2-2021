package sem;

import entidad.Entidad;
import estacionamiento.Estacionamiento;
import registroDeCompra.RegistroDeRecargaCelular;

public interface SistemaObservable {

	public void suscribir(Entidad entidad);
	
	public void desuscribir(Entidad entidad);
	
	public void notificarEstacionamientoIniciado(Estacionamiento estacionamiento);
	
	public void notificarEstacionamientoFinalizado(Estacionamiento estacionamiento);

	public void notificarRecargaDeCredito(RegistroDeRecargaCelular registroDeRecargaCelular);
	
}
