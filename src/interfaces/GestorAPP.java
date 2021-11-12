package interfaces;

import app.APP;
import registroDeCompra.RegistroDeRecargaCelular;

public interface GestorAPP {

	void recargarSaldo(RegistroDeRecargaCelular registroDeRecargaCelular);

	Float getSaldo(APP app);

	Float getPrecioPorHora();

	void finalizarEstacionamiento(APP app);

	void iniciarEstacionamiento(String patente, APP app);

}
