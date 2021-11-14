package sem;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import entidad.Entidad;
import estacionamiento.Estacionamiento;
import registroDeCompra.RegistroDeCompraPuntual;
import registroDeCompra.RegistroDeRecargaCelular;
import zona.Zona;

public class SistemaCentralImpl implements SistemaCentral {

	private LocalTime horaInicio;
	private LocalTime horaCierre;
	private Float precioPorHora;
	private List<Zona> zonas;
	private List<Entidad> entidades;
	private GestorEstacionamiento gestorEstacionamientos;
	private GestorAPP gestorAPP;
	
	public SistemaCentralImpl(LocalTime horaInicio, LocalTime horaCierre, Float precioPorHora, List<Zona> zonas) {
		this.horaInicio = horaInicio;
		this.horaCierre = horaCierre;
		this.precioPorHora = precioPorHora;
		this.zonas = zonas;
		this.entidades = new ArrayList<Entidad>();
	}

	@Override
	public LocalTime getHoraInicio() {
		return this.horaInicio;
	}
	
	@Override
	public LocalTime getHoraCierre() {
		return this.horaCierre;
	}

	@Override
	public Float getPrecioPorHora() {
		return this.precioPorHora;
	}

	void setGestorAPP(GestorAPP gestorAPP) {
		this.gestorAPP = gestorAPP;		
	}
	
	void setGestorEstacionamientos(GestorEstacionamiento gestorEstacionamientos) {
		this.gestorEstacionamientos = gestorEstacionamientos;		
	}
	
	void setEntidades(List<Entidad> listaEntidades) {
		this.entidades = listaEntidades;
	}

	@Override
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
