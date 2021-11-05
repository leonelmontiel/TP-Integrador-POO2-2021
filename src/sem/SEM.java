package sem;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import app.APP;
import estacionamiento.Estacionamiento;
import estacionamiento.EstacionamientoAPP;

public class SEM {

	private LocalTime horaInicio;
	private LocalTime horaCierre;
	private Float precioPorHora;
	private List<Estacionamiento> estacionamientos;
	private Map<APP, Float> usuariosAPP;

	public SEM(LocalTime horaInicio, LocalTime horaCierre, Float precioPorHora) {
		this.horaInicio = horaInicio;
		this.horaCierre = horaCierre;
		this.precioPorHora = precioPorHora;
		this.estacionamientos = new ArrayList<Estacionamiento>();
		this.usuariosAPP = new HashMap<APP, Float>();
	}
	
	public List<Estacionamiento> getEstacionamientos() {
		return this.estacionamientos;
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

	void setEstacionamientos(List<Estacionamiento> estacionamientos) {
		this.estacionamientos = estacionamientos;
	}

	public Estacionamiento iniciarEstacionamiento(String patente, APP app) {
		LocalTime horaInicio = LocalTime.now();
		//las instancias de estacionamiento estan estrictamente vinculadas a la inicializacion de las 
		//mismas por el sem (principio de dependency inversion)
		EstacionamientoAPP nuevoEstacionamiento = new EstacionamientoAPP(app, horaInicio, patente);
		
		this.registrarEstacionamiento(nuevoEstacionamiento);
		
		return nuevoEstacionamiento;
	}

	public void finalizarEstacionamiento(APP app) {
		this.estacionamientos.stream().filter(estacionamiento -> estacionamiento.getApp().equals(app))
		.filter(estacionamiento -> estacionamiento.estaVigente(LocalDateTime.now())).toList().get(0)
		.finalizar();
	}

	public void regitrarAPP(APP app) {
		this.usuariosAPP.put(app, 0f);
	}

	public Boolean tieneRegistradoApp(APP app) {
		return this.usuariosAPP.containsKey(app);
	}

	public Float getSaldo(APP app) {
		return this.usuariosAPP.get(app);
	}

	public void recargarSaldo(APP app, Float montoRecarga) {
		Float nuevoSaldo = this.getSaldo(app) + montoRecarga; 
		this.usuariosAPP.replace(app, nuevoSaldo);
	}

	public void registrarEstacionamiento(Estacionamiento estacionamiento) {
		this.estacionamientos.add(estacionamiento);
	}

	public Boolean tieneEstacionamientoVigente(String patente, LocalDateTime momentoConsulta) {
		return this.estacionamientos.stream()
				.filter(estacionamiento -> estacionamiento.getPatente().equals(patente))
				.anyMatch(estacionamiento -> estacionamiento.estaVigente(momentoConsulta));
	}

}
