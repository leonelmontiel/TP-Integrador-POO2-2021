package puntoDeVenta;

import app.APP;
import app.Pantalla;
import sem.GestorRegistros;

public class PuntoDeVenta {

	private Integer nroControlRegistro;
	private GestorRegistros sistema;
	private Pantalla pantalla;

	public PuntoDeVenta(GestorRegistros sistema) {
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
		this.pantalla.mostrar("Compra realizada con �xito");		
	}
	
	void setPantalla(Pantalla pantalla) {
		this.pantalla = pantalla;
	}

}
