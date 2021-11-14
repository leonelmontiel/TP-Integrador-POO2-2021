package app;

public class SinEstacionamiento extends EstadoAPP {

	public static EstadoAPP sinEstacionamiento;

	public static EstadoAPP getInstance() {
		if(sinEstacionamiento == null) {
			sinEstacionamiento = new SinEstacionamiento();
		}
		return sinEstacionamiento;
	}
	
	private SinEstacionamiento() {
		
	}
	
	@Override
	public Boolean tieneEstacionamiento() {
		return false;
	}

	@Override
	public void finalizarEstacionamiento(APP app) {
		// No hace nada porque no tiene estacionamiento	
	}

	@Override
	protected void iniciarEstacionamiento(APP app, String patente) {
		if (app.tieneSaldoSuficiente()) {
			EstadoAPP nuevoEstado = ConEstacionamiento.getInstance();
			app.getSistema().iniciarEstacionamiento(patente, app);
			app.setEstado(nuevoEstado);
		} else {
			//notifica al usuario que no dispone de saldo
			app.notificarAlUsuario("No tenes saldo suficiente para iniciar este estacionamiento.");
		}
		
	}

	@Override
	protected void alertaInicioEstacionamiento(APP app) {
		app.alertaInicioEstacionamiento();
	}

	@Override
	protected void alertaFinEstacionamiento(APP app) {
		//no debe dar aviso porque no tiene un estacionamiento activo
	}

}
