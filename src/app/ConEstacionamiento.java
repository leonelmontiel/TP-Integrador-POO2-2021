package app;

public class ConEstacionamiento extends EstadoAPP {
	
	public static EstadoAPP conEstacionamiento;

	public static EstadoAPP getInstance() {
		if(conEstacionamiento == null) {
			conEstacionamiento = new ConEstacionamiento();
		}
		return conEstacionamiento;
	}

	@Override
	public Boolean tieneEstacionamiento() {
		return true;
	}

	@Override
	public void finalizarEstacionamiento(APP app) {
		EstadoAPP nuevoEstado = SinEstacionamiento.getInstance();
		app.getSistema().finalizarEstacionamiento(app);
		app.setEstado(nuevoEstado);
	}

	@Override
	protected void iniciarEstacionamiento(APP app, String patente) {
		// No hace anda porque ya tiene el estacionamiento iniciado		
	}

	@Override
	protected void alertaInicioEstacionamiento(APP app) {
		//no hace nada porque ya tiene un estacionamiento iniciado
	}

	@Override
	protected void alertaFinEstacionamiento(APP app) {
		app.alertaFinEstacionamiento();
	}

}
