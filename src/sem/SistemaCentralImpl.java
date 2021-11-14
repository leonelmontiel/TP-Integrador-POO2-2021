package sem;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import entidad.Entidad;
import estacionamiento.Estacionamiento;
import registroDeCompra.RegistroDeCompraPuntual;
import registroDeCompra.RegistroDeRecargaCelular;
import zona.Zona;

public class SistemaCentralImpl implements SistemaCentral, SistemaObservable, Administrable {

	private LocalTime horaInicio;
	private LocalTime horaCierre;
	private Float precioPorHora;
	private List<Zona> zonas;
	private List<Entidad> entidades;
	private GestorEstacionamiento gestorEstacionamientos;
	private GestorAPP gestorAPP;
	
	public SistemaCentralImpl(LocalTime horaInicio, LocalTime horaCierre, Float precioPorHora, 
			List<Zona> zonas, GestorEstacionamiento gestorEstacionamientos, GestorAPP gestorAPP) {
		this.setHoraInicio(horaInicio);
		this.setHoraCierre(horaCierre);
		this.setPrecioPorHora(precioPorHora);
		this.zonas = zonas;
		this.entidades = new ArrayList<Entidad>();
		this.gestorEstacionamientos = gestorEstacionamientos;
		this.gestorAPP = gestorAPP;
	}

	@Override
	public LocalTime getHoraInicio() {
		return this.horaInicio;
	}
	
	@Override
	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;	
	}
	
	@Override
	public void setHoraCierre(LocalTime horaCierre) {
		this.horaCierre = horaCierre;
	}
	
	@Override
	public LocalTime getHoraCierre() {
		return this.horaCierre;
	}

	@Override
	public Float getPrecioPorHora() {
		return this.precioPorHora;
	}
	
	@Override
	public void setPrecioPorHora(Float nuevoPrecio) {
		this.precioPorHora = nuevoPrecio;
	}
	
	void setEntidades(List<Entidad> listaEntidades) {
		this.entidades = listaEntidades;
	}

	public List<Zona> getZonas() {
		return this.zonas;
	}

	@Override
	public Float getCosto(Estacionamiento estacionamiento) {
		return estacionamiento.getDuracion() * this.precioPorHora;
	}
	
	@Override
	public void generarRecarga(RegistroDeRecargaCelular registroDeRecargaCelular) {
		this.gestorAPP.recargarSaldo(registroDeRecargaCelular);
		this.notificarRecargaDeCredito(registroDeRecargaCelular);
	}
	
	@Override
	public void iniciarEstacionamiento(Estacionamiento estacionamiento) {
		//este mensaje se recibe por parte de ambos gestores (app y estacionamientos)
		this.gestorEstacionamientos.registrarEstacionamiento(estacionamiento);
		this.notificarEstacionamientoIniciado(estacionamiento);
	}

	@Override
	public void finalizarEstacionamiento(Estacionamiento estacionamiento) {
		//mismo caso que iniciar estacionamiento
		this.notificarEstacionamientoFinalizado(estacionamiento);
	}
	
	@Override
	public void generarEstacionamientoPuntual(RegistroDeCompraPuntual registroDeCompraPuntual) {
		this.gestorEstacionamientos.generarEstacionamiento(registroDeCompraPuntual);
	}
	
	private Boolean estaSuscripto(Entidad entidad) {
		return this.entidades.contains(entidad);
	}

	@Override
	public void suscribir(Entidad entidad) {
		if (!estaSuscripto(entidad)) {
			this.entidades.add(entidad);
		}		
	}

	@Override
	public void desuscribir(Entidad entidad) {
		if (estaSuscripto(entidad)) {
			this.entidades.remove(entidad);
		}				
	}
	
	/**
	 * @implNote
	 * si bien los mensajes de notificar son publicos siempre son llamados por el mismo sistema central
	 * dado que es su responsabilidad realizar la notificacion en cada caso
	 */
	@Override
	public void notificarEstacionamientoIniciado(Estacionamiento estacionamiento) {
		this.entidades.stream().forEach(entidad -> entidad.actualizarEstacionamientoIniciado(this, estacionamiento));
	}

	@Override
	public void notificarEstacionamientoFinalizado(Estacionamiento estacionamiento) {
		this.entidades.stream().forEach(entidad -> entidad.actualizarEstacionamientoFinalizado(this, estacionamiento));
	}

	@Override
	public void notificarRecargaDeCredito(RegistroDeRecargaCelular registroDeRecargaCelular) {
		this.entidades.stream().forEach(entidad -> entidad.actualizarRecargaDeCredito(this, registroDeRecargaCelular));
	}
	
	@Override
	public void finalizarTodosLosEstacionamientos() {
		this.gestorAPP.finalizarTodosLosEstacionamientos();
	}
	
}
