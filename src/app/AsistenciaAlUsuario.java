package app;

public enum AsistenciaAlUsuario {
	
	DESACTIVADA{

		@Override
		void alertaInicioEstacionamiento(APP app) {
			// no realiza ninguna accion 			
		}

		@Override
		void alertaFinEstacionamiento(APP app) {
			// no realiza ninguna accion
		}
		
	}, ACTIVADA{
		
		private String ALERTA_INICIO = "Alerta debe iniciar estacionamiento";
		private String ALERTA_FIN = "Alerta debe finalizar estacionamiento";
		
		@Override
		void alertaInicioEstacionamiento(APP app) {
			app.notificarAlUsuario(ALERTA_INICIO);
		}

		@Override
		void alertaFinEstacionamiento(APP app) {
			app.notificarAlUsuario(ALERTA_FIN);
		}
	};

	
	abstract void alertaInicioEstacionamiento(APP app);

	abstract void alertaFinEstacionamiento(APP app);

}
