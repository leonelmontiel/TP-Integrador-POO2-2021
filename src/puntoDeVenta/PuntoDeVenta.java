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
	
	private void incrementarNroControl() {
		this.nroControlRegistro += 1;		
	}

	public void generarRecarga(APP app, Float monto) {
		this.incrementarNroControl(); 
		this.sistema.generarRecarga(this, nroControlRegistro, app, monto);
	}

	public void generarCompraPuntual(String patente, Integer horasCompradas) {
		this.incrementarNroControl(); 
			this.sistema.generarCompraPuntual(this, nroControlRegistro, patente,
				horasCompradas);
	}

	//este metodo tiene por fin emular la interaccion con el usuario del punto de venta
	public void notificarCompraExitosa() {
		this.pantalla.mostrar("Compra realizada con éxito");		
	}
	
	void setPantalla(Pantalla pantalla) {
		this.pantalla = pantalla;
	}

}
