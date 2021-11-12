package puntoDeVenta;

import java.time.LocalTime;

import java.time.LocalDate;

import app.APP;
import app.Pantalla;
import registroDeCompra.RegistroDeCompra;
import registroDeCompra.RegistroDeCompraPuntual;
import registroDeCompra.RegistroDeRecargaCelular;
import sem.SEM;

public class PuntoDeVenta {

	private Integer nroControlRegistro;
	private SEM sistema;
	private Pantalla pantalla;

	public PuntoDeVenta(SEM sem) {
		this.sistema = sem;
		this.nroControlRegistro = 0;
	}

	public RegistroDeCompra generarRecarga(APP app, Float monto) {
		LocalDate hoy = LocalDate.now();
		LocalTime ahora = LocalTime.now();
		this.incrementarNroControl();
		RegistroDeRecargaCelular registro = new RegistroDeRecargaCelular(this, this.nroControlRegistro, hoy, ahora, app, monto);
		this.sistema.almacenar(registro);
		return registro;
	}

	private void incrementarNroControl() {
		this.nroControlRegistro += 1;		
	}

	public RegistroDeCompra generarCompraPuntual(String patente, Integer horasCompradas) {
		LocalDate hoy = LocalDate.now();
		LocalTime ahora = LocalTime.now();
		this.incrementarNroControl();
		RegistroDeCompraPuntual registro = new RegistroDeCompraPuntual(this, this.nroControlRegistro, hoy, ahora, patente, horasCompradas);
		this.sistema.almacenar(registro);
		return registro;
	}

	public void notificarCompraExitosa() {
		this.pantalla.mostrar("Compra realizada con éxito");		
	}
	
	void setPantalla(Pantalla pantalla) {
		this.pantalla = pantalla;
	}

}
