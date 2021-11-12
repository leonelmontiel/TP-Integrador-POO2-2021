package interfaces;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import estacionamiento.Estacionamiento;
import estacionamiento.EstacionamientoPuntual;
import registroDeCompra.RegistroDeCompraPuntual;

public class GestorEstacionamientoImpl implements GestorEstacionamiento {

	private List<Estacionamiento> estacionamientos;
	private SistemaCentral sistema;

	@Override
	public Boolean tieneEstacionamientoVigente(String patente, LocalDateTime momentoConsulta) {
		return this.estacionamientos.stream()
				.anyMatch(estacionamiento -> estacionamiento.getPatente().equals(patente) &&
						estacionamiento.estaVigente(momentoConsulta));
	}

	@Override
	public Float getCosto(Estacionamiento estacionamiento) {
		return estacionamiento.getDuracion() * this.sistema.getPrecioPorHora();
	}

	@Override
	public void generarEstacionamiento(RegistroDeCompraPuntual registroDeCompraPuntual) {
		LocalTime horaInicio = registroDeCompraPuntual.getHora();
		LocalTime horaFin = horaInicio.plusHours(registroDeCompraPuntual.getHorasCompradas());
		Estacionamiento estacionamiento = new EstacionamientoPuntual(horaInicio, horaFin, registroDeCompraPuntual);
		
		this.registrarEstacionamiento(estacionamiento);
		registroDeCompraPuntual.notificarCompraExitosa();
	}

	@Override
	public void registrarEstacionamiento(Estacionamiento estacionamiento) {
		this.estacionamientos.add(estacionamiento);
	}

}
