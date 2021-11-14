package sem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import app.APP;
import puntoDeVenta.PuntoDeVenta;
import registroDeCompra.RegistroDeCompra;
import registroDeCompra.RegistroDeCompraPuntual;
import registroDeCompra.RegistroDeRecargaCelular;

public class GestorRegistrosImpl implements GestorRegistros {

	private List<RegistroDeCompra> registros;
	private SistemaCentral sistema;
	
	public GestorRegistrosImpl(SistemaCentral sistema) {
		this.sistema = sistema;
		this.registros = new ArrayList<RegistroDeCompra>();
	}

	void setRegistros(List<RegistroDeCompra> registros) {
		this.registros = registros;
	}
	
	@Override
	public void almacenar(RegistroDeCompra registro) {
		this.registros.add(registro);
	}
	
	public Boolean tieneAlmacenadoRegistro(Integer nroControl) {
		return this.registros.stream().anyMatch(registro -> registro.getNroControl().equals(nroControl));
	}

	@Override
	public void generarCompraPuntual(PuntoDeVenta puntoDeVenta, Integer nroControlRegistro, String patente,
			Integer horasCompradas) {
		LocalDate hoy = LocalDate.now();
		LocalTime ahora = LocalTime.now();
		RegistroDeCompraPuntual registroCompraPuntual = new RegistroDeCompraPuntual(puntoDeVenta, 
				nroControlRegistro, hoy, ahora, patente, horasCompradas);

		this.almacenar(registroCompraPuntual);
		this.sistema.generarEstacionamientoPuntual(registroCompraPuntual);
	}

	@Override
	public void generarRecarga(PuntoDeVenta puntoDeVenta, Integer nroControlRegistro, APP app, Float monto) {
		LocalDate hoy = LocalDate.now();
		LocalTime ahora = LocalTime.now();
		RegistroDeRecargaCelular registroDeRecargaCelular = new RegistroDeRecargaCelular(puntoDeVenta, 
				nroControlRegistro, hoy, ahora, app, monto);
		
 		this.almacenar(registroDeRecargaCelular);	
 		this.sistema.generarRecarga(registroDeRecargaCelular);
	}

}
