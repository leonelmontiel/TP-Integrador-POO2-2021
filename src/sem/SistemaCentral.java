package sem;

import java.time.LocalTime;

import estacionamiento.Estacionamiento;
import registroDeCompra.RegistroDeCompraPuntual;
import registroDeCompra.RegistroDeRecargaCelular;

public interface SistemaCentral {

	public Float getPrecioPorHora();

	public LocalTime getHoraInicio();
	
	public LocalTime getHoraCierre();

	Float getCosto(Estacionamiento estacionamiento);
	
	void iniciarEstacionamiento(Estacionamiento estacionamiento);
	
	void finalizarEstacionamiento(Estacionamiento estacionamiento);

	void generarEstacionamientoPuntual(RegistroDeCompraPuntual registroCompraPuntual);

	void generarRecarga(RegistroDeRecargaCelular registroDeRecargaCelular);
	
	void finalizarTodosLosEstacionamientos();

}
