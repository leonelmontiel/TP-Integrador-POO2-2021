package interfaces;

import java.time.LocalTime;

import estacionamiento.Estacionamiento;

public class SistemaCentral {

	private LocalTime horaCierre;
	private Float precioPorHora;
	
	public SistemaCentral(LocalTime horaCierre, Float precioPorHora) {
		this.horaCierre = horaCierre;
		this.precioPorHora = precioPorHora;
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

	public void notificarEstacionamientoFinalizado(Estacionamiento estacionamientoAFinalizar) {
		// TODO Auto-generated method stub
		
	}

}
