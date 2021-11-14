package app;

public abstract class EstadoAPP {
	
	public abstract Boolean tieneEstacionamiento();

	public abstract void finalizarEstacionamiento(APP app);

	protected abstract void iniciarEstacionamiento(APP app, String patente);

	protected abstract void alertaInicioEstacionamiento(APP app);

	protected abstract void alertaFinEstacionamiento(APP app);

}
