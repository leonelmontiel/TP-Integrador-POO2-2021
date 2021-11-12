package interfaces;

import java.time.LocalDateTime;

import estacionamiento.Estacionamiento;
import registroDeCompra.RegistroDeCompraPuntual;

public interface GestorEstacionamiento {

	Boolean tieneEstacionamientoVigente(String patente, LocalDateTime momentoConsulta);

	void generarEstacionamiento(RegistroDeCompraPuntual registroDeCompraPuntual);

	Float getCosto(Estacionamiento estacionamientoAFinalizar);

	void notificarEstacionamientoFinalizado(GestorAppImpl gestorAppImpl, Estacionamiento estacionamientoAFinalizar);

}
