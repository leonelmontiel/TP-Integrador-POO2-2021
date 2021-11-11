package sem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import app.APP;
import entidad.Entidad;
import estacionamiento.Estacionamiento;
import estacionamiento.EstacionamientoAPP;
import infraccion.Infraccion;
import inspector.Inspector;

public class SEM {
	// SE CONTEMPLA LA IMPLEMENTACIÓN DE 3 INTERFACES: IESTACIONAMIENTO, IAPP, IENTIDAD

	private LocalTime horaInicio;
	private LocalTime horaCierre;
	private Float precioPorHora;
	private List<Estacionamiento> estacionamientos;
	private Map<APP, Float> usuariosAPP;
	private List<Infraccion> infracciones;
	private List<Entidad> entidades;

	public SEM(LocalTime horaInicio, LocalTime horaCierre, Float precioPorHora) {
		this.horaInicio = horaInicio;
		this.horaCierre = horaCierre;
		this.precioPorHora = precioPorHora;
		this.estacionamientos = new ArrayList<Estacionamiento>();
		this.usuariosAPP = new HashMap<APP, Float>();
		this.entidades = new ArrayList<Entidad>();
		this.infracciones = new ArrayList<Infraccion>();
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
	
	void setUsuariosAPP(Map<APP, Float> usuariosAPP) {
		this.usuariosAPP = usuariosAPP;
	}

	public Estacionamiento iniciarEstacionamiento(String patente, APP app) {
		//las instancias de estacionamiento estan estrictamente vinculadas a la inicializacion de las 
		//mismas por el sem (principio de dependency inversion)
		LocalTime horaInicio = LocalTime.now();
		EstacionamientoAPP nuevoEstacionamiento = new EstacionamientoAPP(app, patente, horaInicio);
		String notificacion = "Estacionamiento iniciado a las " + nuevoEstacionamiento.getHoraInicio() +
				" valido hasta las " + this.getHoraMaxima(nuevoEstacionamiento) + " hs.";
		
		this.registrarEstacionamiento(nuevoEstacionamiento);
		app.notificarAlUsuario(notificacion);
		
		return nuevoEstacionamiento;
	}

	LocalTime getHoraMaxima(Estacionamiento estacionamiento) {
		Integer horasQuePuedeComprar = getHorasQuePuedeComprar(estacionamiento.getApp());
		LocalTime potencialHoraFin = estacionamiento.getHoraInicio().plusHours(horasQuePuedeComprar); 
		
		return (potencialHoraFin.isAfter(this.horaCierre)) ? this.horaCierre : potencialHoraFin;
	}

	private Integer getHorasQuePuedeComprar(APP app) {
		return (int) (this.getSaldo(app) / 
				this.precioPorHora);
	}
	
	public void finalizarEstacionamiento(APP app) {
		Estacionamiento estacionamientoAFinalizar = this.getEstacionamientosActivos(app).get(0);
		
		estacionamientoAFinalizar.finalizar();
		String notificacion = "Iniciado " + estacionamientoAFinalizar.getHoraInicio() + 
				" Finalizado " + estacionamientoAFinalizar.getHoraFin() + " con una duracion de " +
				estacionamientoAFinalizar.getDuracion() + " y un costo de " + 
				this.calcularCosto(estacionamientoAFinalizar);
		
		estacionamientoAFinalizar.getApp().notificarAlUsuario(notificacion);
		
		this.notificarEstacionamientoFinalizado(this, estacionamientoAFinalizar);
	}

	private List<Estacionamiento> getEstacionamientosActivos(APP app) {
		return this.estacionamientos.stream()
				.filter(estacionamiento -> estacionamiento.estaVigente(LocalDateTime.now()) && 
						estacionamiento.getApp().equals(app)).toList();
	}

	private Float calcularCosto(Estacionamiento estacionamiento) {
		return estacionamiento.getDuracion() * this.precioPorHora;
	}

	private void notificarEstacionamientoFinalizado(SEM sem, Estacionamiento estacionamiento) {
		this.entidades.stream().forEach(entidad -> entidad.actualizarEstacionamientoFinalizado(this, estacionamiento));		
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
		notificarEstacionamientoIniciado(estacionamiento);
	}

	private void notificarEstacionamientoIniciado(Estacionamiento estacionamiento) {
		this.entidades.stream().forEach(entidad -> entidad.actualizarEstacionamientoIniciado(this, estacionamiento));
	}

	public Boolean tieneEstacionamientoVigente(String patente, LocalDateTime momentoConsulta) {
		return this.estacionamientos.stream()
				.anyMatch(estacionamiento -> estacionamiento.getPatente().equals(patente) &&
						estacionamiento.estaVigente(momentoConsulta));
	}

	void setInfracciones(List<Infraccion> infracciones) {
		this.infracciones = infracciones;
	}

	public Infraccion altaInfraccion(String patente, Inspector inspector) {
		LocalDate fecha = LocalDate.now();
		LocalTime hora = LocalTime.now();
		Infraccion infraccion = new Infraccion(patente, inspector, fecha, hora, inspector.getZona());
		
		this.infracciones.add(infraccion);
		return infraccion;
	}

	public void finalizarTodosLosEstacionamientos() {
		this.estacionamientos.stream().forEach(Estacionamiento::finalizar);
	}

	void setEntidades(List<Entidad> listaEntidades) {
		this.entidades = listaEntidades;
	}

	public void suscribir(Entidad entidad) {
		if (!estaSuscripto(entidad)) {
			this.entidades.add(entidad);
		}		
	}

	Boolean estaSuscripto(Entidad entidad) {
		return this.entidades.contains(entidad);
	}

	public void desuscribir(Entidad entidad) {
		this.entidades.remove(entidad);		
	}

}
