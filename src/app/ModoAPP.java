package app;

public enum ModoAPP {
	MANUAL {
		@Override
		void ejecutarIniciacion(APP app) {
			//no realiza ninguna accion porque debe ser lanzada por el usuario
		}

		@Override
		void ejecutarFinalizacion(APP app) {
			//no realiza ninguna accion porque debe ser lanzada por el usuario
		}
	}, AUTOMATICO {
		
		private String INICIO_AUTOMATICO = "Se inicio un estacionamiento en forma automatica";
		private String FINALIZACION_AUTOMATICA = "Se finalizo el estacionamiento en forma automatica";
		
		@Override
		void ejecutarIniciacion(APP app) {
			app.iniciarEstacionamiento(app.getPatente());
			app.notificarAlUsuario(INICIO_AUTOMATICO);
		}

		@Override
		void ejecutarFinalizacion(APP app) {
			app.finalizarEstacionamiento();
			app.notificarAlUsuario(FINALIZACION_AUTOMATICA);
		}
	};

	abstract void ejecutarIniciacion(APP app);

	abstract void ejecutarFinalizacion(APP app);
	
}
