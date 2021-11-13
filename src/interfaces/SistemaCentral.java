package interfaces;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import entidad.Entidad;
import estacionamiento.Estacionamiento;
import registroDeCompra.RegistroDeCompraPuntual;
import registroDeCompra.RegistroDeRecargaCelular;
import zona.Zona;

public class SistemaCentral {

	private LocalTime horaInicio;
	private LocalTime horaCierre;
	private Float precioPorHora;
	private List<Zona> zonas;
	private List<Entidad> entidades;
	private GestorEstacionamiento gestorEstacionamientos;
	private GestorAPP gestorAPP;
	
	public SistemaCentral(LocalTime horaInicio, LocalTime horaCierre, Float precioPorHora, List<Zona> zonas) {
		this.horaInicio = horaInicio;
		this.horaCierre = horaCierre;
		this.precioPorHora = precioPorHora;
		this.zonas = zonas;
		this.entidades = new ArrayList<Entidad>();
	}

	public LocalTime getHoraInicio() {
		return this.horaInicio;
	}
	
	public LocalTime getHoraCierre() {
		return this.horaCierre;
	}

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
	
	public List<Zona> getZonas() {
		return this.zonas;
	}

	public Float getCosto(Estacionamiento estacionamiento) {
		return estacionamiento.getDuracion() * this.precioPorHora;
	}
	
	public void generarRecarga(RegistroDeRecargaCelular registroDeRecargaCelular) {
		this.gestorAPP.recargarSaldo(registroDeRecargaCelular);
		this.notificarRecargaDeCredito(registroDeRecargaCelular);
	}
	
	public void iniciarEstacionamiento(Estacionamiento estacionamiento) {
		//este mensaje se recibe por parte de ambos gestores (app y estacionamientos)
		this.gestorEstacionamientos.registrarEstacionamiento(estacionamiento);
		this.notificarEstacionamientoIniciado(estacionamiento);
	}
	
	public void finalizarEstacionamiento(Estacionamiento estacionamiento) {
		//mismo caso que iniciar estacionamiento
		this.notificarEstacionamientoFinalizado(estacionamiento);
	}
	
	public void generarEstacionamientoPuntual(RegistroDeCompraPuntual registroDeCompraPuntual) {
		this.gestorEstacionamientos.generarEstacionamiento(registroDeCompraPuntual);
	}
	
	Boolean estaSuscripto(Entidad entidad) {
		return this.entidades.contains(entidad);
	}

	public void suscribir(Entidad entidad) {
		if (!estaSuscripto(entidad)) {
			this.entidades.add(entidad);
		}		
	}

	public void desuscribir(Entidad entidad) {
		if (estaSuscripto(entidad)) {
			this.entidades.remove(entidad);
		}				
	}
	
	private void notificarEstacionamientoIniciado(Estacionamiento estacionamiento) {
		this.entidades.stream().forEach(entidad -> entidad.actualizarEstacionamientoIniciado(this, estacionamiento));
	}

	private void notificarEstacionamientoFinalizado(Estacionamiento estacionamiento) {
		this.entidades.stream().forEach(entidad -> entidad.actualizarEstacionamientoFinalizado(this, estacionamiento));
	}

	private void notificarRecargaDeCredito(RegistroDeRecargaCelular registroDeRecargaCelular) {
		this.entidades.stream().forEach(entidad -> entidad.actualizarRecargaDeCredito(this, registroDeRecargaCelular));
	}
	
}
