package sem;

import app.APP;
import puntoDeVenta.PuntoDeVenta;
import registroDeCompra.RegistroDeCompra;

public interface GestorRegistros {

	void almacenar(RegistroDeCompra registro);

	void generarCompraPuntual(PuntoDeVenta puntoDeVenta, Integer nroControlRegistro, String patente, Integer horasCompradas);

	void generarRecarga(PuntoDeVenta puntoDeVenta, Integer nroControlRegistro, APP app, Float monto);

}
