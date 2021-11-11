package zona;

import java.util.ArrayList;
import java.util.List;

import inspector.Inspector;
import puntoDeVenta.PuntoDeVenta;

public class Zona {

	private Inspector inspector;
	private List<PuntoDeVenta> puntosDeVenta;

	public Zona(Inspector inspector) {
		this.inspector = inspector;
		this.puntosDeVenta = new ArrayList<PuntoDeVenta>();
	}
	
	public Inspector getInspector() {
		return this.inspector;
	}

	void setPuntosDeVenta(List<PuntoDeVenta> puntosDeVenta) {
		this.puntosDeVenta = puntosDeVenta;		
	}

	public List<PuntoDeVenta> getPuntosDeVenta() {
		return this.puntosDeVenta;
	}

	public void registrar(PuntoDeVenta puntoDeVenta) {
		if (!this.tieneRegistrado(puntoDeVenta)) {
			this.puntosDeVenta.add(puntoDeVenta);
		}				
	}

	public void desvincular(PuntoDeVenta puntoDeVenta) {
		if (this.tieneRegistrado(puntoDeVenta)) {
			this.puntosDeVenta.remove(puntoDeVenta);
		}			
	}

	private Boolean tieneRegistrado(PuntoDeVenta puntoDeVenta) {
		return this.puntosDeVenta.contains(puntoDeVenta);
	}

}
