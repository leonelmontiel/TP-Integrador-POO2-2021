package sem;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import estacionamiento.Estacionamiento;
import estacionamiento.EstacionamientoPuntual;
import registroDeCompra.RegistroDeCompraPuntual;

public class GestorEstacionamientoImpl implements GestorEstacionamiento {

	private SistemaCentral sistema;
	private List<Estacionamiento> estacionamientos;
	
	public GestorEstacionamientoImpl(SistemaCentral sistema) {
		this.sistema = sistema;
		this.estacionamientos = new ArrayList<Estacionamiento>();
	}

	void setEstacionamientos(List<Estacionamiento> estacionamientos) {
		this.estacionamientos = estacionamientos;	
	}	
	
	@Override
	public void registrarEstacionamiento(Estacionamiento estacionamiento) {
		this.estacionamientos.add(estacionamiento);
	}

	@Override
	public Boolean tieneEstacionamientoVigente(String patente, LocalDateTime momentoConsulta) {
		return this.getEstacionamientosActivos(momentoConsulta).stream()
				.anyMatch(estacionamiento -> estacionamiento.getPatente().equals(patente));
	}

	private List<Estacionamiento> getEstacionamientosActivos(LocalDateTime momentoConsulta) {
		return this.estacionamientos.stream()
				.filter(estacionamiento -> estacionamiento.estaVigente(momentoConsulta)).toList();
	}

	@Override
	public Float getCosto(Estacionamiento estacionamiento) {
		return estacionamiento.getDuracion() * this.sistema.getPrecioPorHora();
	}

	@Override
	public void generarEstacionamiento(RegistroDeCompraPuntual registroDeCompraPuntual) {
		LocalTime horaInicio = registroDeCompraPuntual.getHora();
		LocalTime horaFin = horaInicio.plusHours(registroDeCompraPuntual.getHorasCompradas());
		String patente = registroDeCompraPuntual.getPatente();
		EstacionamientoPuntual estacionamiento = new EstacionamientoPuntual(horaInicio, horaFin, patente,
				registroDeCompraPuntual);
		
		this.sistema.iniciarEstacionamiento(estacionamiento);
		
		registroDeCompraPuntual.notificarCompraExitosa();
	}

}
