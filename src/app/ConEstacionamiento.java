package app;

public class ConEstacionamiento extends EstadoAPP {

	@Override
	public Boolean tieneEstacionamiento() {
		return true;
	}

	@Override
	public void finalizarEstacionamiento(APP app) {
		EstadoAPP nuevoEstado = new SinEstacionamiento();
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
