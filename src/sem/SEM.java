package sem;

import java.time.LocalTime;

import app.APP;
import estacionamiento.Estacionamiento;

public class SEM {

	public void iniciarEstacionamiento(String patente, APP aPP) {
		// asocia la patente con la aplicaci�n y registra el estacionamiento
	}

	public void finalizarEstacionamiento(APP aPP) {
		// usa a la app dada por par�metro para asociarla con la patente y finalizar el estacionamiento registrado (en un map<Patente, APP>)	
	}

	public Float getSaldoDe(APP aPP) {
		// devuelve el saldo registrado asociado a la aplicaci�n dada por par�metro (en un map<App, monto>)
		return null;
	}

	public Float getPrecioXHora() {
		return null;
	}

	public LocalTime getHoraMaximaDe(Estacionamiento estacionamiento) {
		// retorna la hora m�xima del estacionamiento dado por par�metro
		return null;
	}

}
