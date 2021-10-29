package sem;

import app.APP;

public class SEM {

	public void iniciarEstacionamiento(String patente, APP aPP) {
		// asocia la patente con la aplicación y registra el estacionamiento
	}

	public void finalizarEstacionamiento(APP aPP) {
		// usa a la app dada por parámetro para asociarla con la patente y finalizar el estacionamiento registrado (en un map<Patente, APP>)	
	}

	public Float getSaldoDe(APP aPP) {
		// devuelve el saldo registrado asociado a la aplicación dada por parámetro (en un map<App, monto>)
		return null;
	}

	public Float getPrecioXHora() {
		return null;
	}

}
