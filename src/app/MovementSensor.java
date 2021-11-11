package app;

public interface MovementSensor {
	
	/*
	 * Cada determinada cantidad de segundos las apps que implementen esta interfaz
	 * recibiran uno de estos dos mensajes por parte del telefono celular en que se encuentran
	 * instaladas. Un mismo mensaje puede (y suele) ser recibido de forma repetida.
	 */
	
	//indica que el desplazamiento se realiza a bordo de un veiculo
	public void driving();
	
	//indica que el desplazamiento se realiza caminando
	public void walking();
	
}
