package app;

public class SinEstacionamiento extends EstadoAPP {

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
		// EVALUAR SI TIENE SALDO SUFICIENTE PARA LA PRIMERA HORA DE ESTACIONAMIENTO $40
		if (app.saldoEsMayorOIgualAPrecioPorHora()) {
			EstadoAPP nuevoEstado = new ConEstacionamiento();
			// env�a la patente para iniciar el estacionamiento y a su vez a la app misma para que 
			//el sistema los vincule
			app.getSistema().iniciarEstacionamiento(patente, app);
			app.setEstado(nuevoEstado);
		} else {
			//notifica al usuario que no dispone de saldo
			app.getPantalla().mostrar("No tenes saldo suficiente para iniciar este estacionamiento.");
		}
		
	}

	@Override
	protected void alertaInicioEstacionamiento(APP app) {
		app.getAsistenciaAlUsuario().alertaInicioEstacionamiento(app);
	}

	@Override
	protected void alertaFinEstacionamiento(APP app) {
		//no debe dar aviso porque no tiene un estacionamiento activo
	}

}
