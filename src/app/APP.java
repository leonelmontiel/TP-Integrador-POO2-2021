package app;

import sem.GestorAPP;

public class APP implements MovementSensor {

	private int numero;
	private GestorAPP sistema;
	private Pantalla pantalla;
	private EstadoAPP estado;
	private AsistenciaAlUsuario asistenciaAlUsuario;
	private ModoAPP modo;
	/**@implNote
	 * esta patente se inicializa en null porque el modo inicial de la app es manual, se configura al
	   cambiar de modo o setear una nueva patente
	 */
	private String patente;

	public APP(int numero, GestorAPP sistema, Pantalla pantalla) {
		this.numero = numero;
		this.sistema = sistema;
		this.pantalla = pantalla;
		this.estado = SinEstacionamiento.getInstance();
		this.asistenciaAlUsuario = AsistenciaAlUsuario.DESACTIVADA;
		this.modo = ModoAPP.MANUAL;
	}

	public int getNumero() {
		return this.numero;
	}
	
	public GestorAPP getSistema() {
		return this.sistema;
	}

	/**@implNote
	 * se delega la consulta de saldo al sistema por lo que se envia a si misma para consultar 
	 * en el registro del SEM cuanto saldo tiene disponible
	 */
	public Float getSaldo() {
		return this.sistema.getSaldo(this);
	}
	
	void setEstado(EstadoAPP nuevoEstado) {
		this.estado = nuevoEstado;		
	}
	
	public AsistenciaAlUsuario getAsistenciaAlUsuario() {
		return this.asistenciaAlUsuario;
	}
	
	public void activarAsistenciaAlUsuario() {
		this.asistenciaAlUsuario = AsistenciaAlUsuario.ACTIVADA;
	}
	
	public void desactivarAsistenciaAlUsuario() {
		this.asistenciaAlUsuario = AsistenciaAlUsuario.DESACTIVADA;
	}
	
	public ModoAPP getModo() {
		return this.modo;
	}
	
	public void activarModoAutomatico(String patente) {
		this.modo = ModoAPP.AUTOMATICO;
		this.setPatente(patente);
	}
	
	public void desactivarModoAutomatico() {
		this.modo = ModoAPP.MANUAL;
	}
		
	/** @implNote
	 * se deja publico el seter de patente para que pueda ser configurado por el usuario en caso de estar
	 * utilizando un vehiculo distinto del que configuro al activar el modo automatico 
	 */
	public void setPatente(String patente) {
		this.patente = patente;
	}
	
	public String getPatente() {
		return this.patente;
	}
	
	public void notificarAlUsuario(String mensaje) {
		this.pantalla.mostrar(mensaje);
	}
	
	public void iniciarEstacionamiento(String patente) {
		this.estado.iniciarEstacionamiento(this, patente);		
	}
	
	public void finalizarEstacionamiento() {
		this.estado.finalizarEstacionamiento(this);
	}

	Boolean tieneSaldoSuficiente() {
		return this.sistema.tieneSaldoSuficiente(this);
	}

	public Boolean tieneEstacionamiento() {
		return this.estado.tieneEstacionamiento();
	}

	@Override
	public void driving() {
		this.estado.alertaFinEstacionamiento(this);
		this.modo.ejecutarFinalizacion(this);
	}
	
	@Override
	public void walking() {
		this.estado.alertaInicioEstacionamiento(this);
		this.modo.ejecutarIniciacion(this);
	}

	void alertaInicioEstacionamiento() {
		this.asistenciaAlUsuario.alertaInicioEstacionamiento(this);
	}

	void alertaFinEstacionamiento() {
		this.asistenciaAlUsuario.alertaFinEstacionamiento(this);
	}
	
}
