package interfaces;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import app.APP;
import entidad.Entidad;
import estacionamiento.Estacionamiento;
import puntoDeVenta.PuntoDeVenta;
import registroDeCompra.RegistroDeRecargaCelular;

public class SistemaCentral {

	private LocalTime horaInicio;
	private LocalTime horaCierre;
	private Float precioPorHora;
	private GestorRegistros gestorRegistros;
	private List<Entidad> entidades;
	
	public SistemaCentral(LocalTime horaInicio, LocalTime horaCierre, Float precioPorHora) {
		this.horaInicio = horaCierre;
		this.horaCierre = horaCierre;
		this.precioPorHora = precioPorHora;
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

	public Float getCosto(Estacionamiento estacionamiento) {
		return estacionamiento.getDuracion() * this.precioPorHora;
	}

	public void notificarEstacionamientoFinalizado(Estacionamiento estacionamiento) {
		this.entidades.stream().forEach(entidad -> entidad.actualizarEstacionamientoFinalizado(this, estacionamiento));
	}

	public void notificarRecargaDeCredito(RegistroDeRecargaCelular registroDeRecargaCelular) {
		this.entidades.stream().forEach(ent -> ent.actualizarRecargaDeCredito(this, registroDeRecargaCelular));
	}
	
	public void generarRecarga(PuntoDeVenta puntoDeVenta, Integer nroControlRegistro, APP app, Float monto) {
		this.gestorRegistros.generarRecarga(puntoDeVenta, nroControlRegistro, app, monto);
	}

	public void generarCompraPuntual(PuntoDeVenta puntoDeVenta, Integer nroControlRegistro, String patente,
			Integer horasCompradas) {
		this.gestorRegistros.generarCompraPuntual(puntoDeVenta, nroControlRegistro, patente, horasCompradas);
	}
	
}
