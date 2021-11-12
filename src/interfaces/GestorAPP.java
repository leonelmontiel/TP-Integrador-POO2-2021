package interfaces;

import app.APP;
import registroDeCompra.RegistroDeRecargaCelular;

public interface GestorAPP {

	public Float getSaldo(APP app);

	public Boolean tieneSaldoSuficiente(APP app);

	public void recargarSaldo(RegistroDeRecargaCelular registroDeRecargaCelular);
	
	public void iniciarEstacionamiento(String patente, APP app);
	
	public void finalizarEstacionamiento(APP app);

}
