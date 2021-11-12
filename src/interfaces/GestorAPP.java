package interfaces;

import app.APP;
import registroDeCompra.RegistroDeRecargaCelular;

public interface GestorAPP {

	Float getSaldo(APP app);

	Boolean tieneSaldoSuficiente(APP app);
	
	void recargarSaldo(RegistroDeRecargaCelular registroDeRecargaCelular);
	
	void iniciarEstacionamiento(String patente, APP app);
	
	void finalizarEstacionamiento(APP app);

}
