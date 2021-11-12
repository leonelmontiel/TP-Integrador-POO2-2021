package puntoDeVenta;

import app.APP;
import app.Pantalla;
import interfaces.SistemaCentral;

public class PuntoDeVenta {

	private Integer nroControlRegistro;
	private SistemaCentral sistema;
	private Pantalla pantalla;

	public PuntoDeVenta(SistemaCentral sistema) {
		this.sistema = sistema;
		this.nroControlRegistro = 0;
	}

	public void generarRecarga(APP app, Float monto) {
		this.incrementarNroControl(); 
		this.sistema.generarRecarga(this, nroControlRegistro, app, monto);
	}

	private void incrementarNroControl() {
		this.nroControlRegistro += 1;		
	}

	public void generarCompraPuntual(String patente, Integer horasCompradas) {
		this.incrementarNroControl(); 
			this.sistema.generarCompraPuntual(this, nroControlRegistro, patente,
				horasCompradas);
	}

	public void notificarCompraExitosa() {
		this.pantalla.mostrar("Compra realizada con éxito");		
	}
	
	void setPantalla(Pantalla pantalla) {
		this.pantalla = pantalla;
	}

}
