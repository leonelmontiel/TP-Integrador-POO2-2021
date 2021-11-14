package sem;

import java.time.LocalTime;
import java.util.List;

import entidad.Entidad;
import estacionamiento.Estacionamiento;
import registroDeCompra.RegistroDeCompraPuntual;
import registroDeCompra.RegistroDeRecargaCelular;
import zona.Zona;

public interface SistemaCentral {

	public Float getPrecioPorHora();

	public LocalTime getHoraInicio();
	
	public LocalTime getHoraCierre();
	
	public List<Zona> getZonas();

	public Float getCosto(Estacionamiento estacionamiento);
	
	public void iniciarEstacionamiento(Estacionamiento estacionamiento);
	
	public void finalizarEstacionamiento(Estacionamiento estacionamiento);

	public void generarEstacionamientoPuntual(RegistroDeCompraPuntual registroCompraPuntual);

	public void generarRecarga(RegistroDeRecargaCelular registroDeRecargaCelular);
	
	public void finalizarTodosLosEstacionamientos();
	
	public void suscribir(Entidad entidad);
	
	public void desuscribir(Entidad entidad);
	
	public void notificarEstacionamientoIniciado(Estacionamiento estacionamiento);
	
	public void notificarEstacionamientoFinalizado(Estacionamiento estacionamiento);

	public void notificarRecargaDeCredito(RegistroDeRecargaCelular registroDeRecargaCelular);

}
