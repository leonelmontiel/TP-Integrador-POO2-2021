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
		EstadoAPP nuevoEstado = new ConEstacionamiento();
		// EVALUAR SI TIENE SALDO SUFICIENTE PARA LA PRIMERA HORA DE ESTACIONAMIENTO $40
		if (app.saldoEsMayorOIgualAPrecioPorHora()) {
			// env�a la patente para iniciar el estacionamiento y a su vez a la app misma para que el sistema los vincule
			app.iniciarEstacionamientoSeguro(patente);
			app.setEstado(nuevoEstado);
		} else {
			System.out.println("No tenes saldo suficiente para iniciar este estacionamiento.");
		}
		
	}

}
