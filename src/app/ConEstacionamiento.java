package app;

public class ConEstacionamiento extends EstadoAPP {

	@Override
	public Boolean tieneEstacionamiento() {
		return true;
	}

	@Override
	public void finalizarEstacionamiento(APP app) {
		EstadoAPP nuevoEstado = new SinEstacionamiento();
		app.finalizarEstacionamientoSeguro();
		app.setEstado(nuevoEstado);
	}

	@Override
	protected void iniciarEstacionamiento(APP app, String patente) {
		// No hace anda porque ya tiene el estacionamiento iniciado		
	}

}
