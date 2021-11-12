package interfaces;

import java.time.LocalDateTime;

import estacionamiento.Estacionamiento;
import registroDeCompra.RegistroDeCompraPuntual;

public interface GestorEstacionamiento {

	Boolean tieneEstacionamientoVigente(String patente, LocalDateTime momentoConsulta);

	Float getCosto(Estacionamiento estacionamiento);
	
	void registrarEstacionamiento(Estacionamiento estacionamiento);

	void generarEstacionamiento(RegistroDeCompraPuntual registroDeCompraPuntual);

}
