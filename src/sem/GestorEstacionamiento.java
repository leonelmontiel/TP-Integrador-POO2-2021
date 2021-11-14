package sem;

import java.time.LocalDateTime;

import estacionamiento.Estacionamiento;
import registroDeCompra.RegistroDeCompraPuntual;

public interface GestorEstacionamiento {

	public Boolean tieneEstacionamientoVigente(String patente, LocalDateTime momentoConsulta);

	public Float getCosto(Estacionamiento estacionamiento);
	
	public void registrarEstacionamiento(Estacionamiento estacionamiento);

	public void generarEstacionamiento(RegistroDeCompraPuntual registroDeCompraPuntual);

}
