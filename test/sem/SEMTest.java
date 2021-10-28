package sem;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SEMTest {

	private SEM sem;
	private LocalTime horaInicio;
	private LocalTime horaCierre;
	private Float precioPorHora;
	
	@BeforeEach
	void setUp() throws Exception {
	
		this.horaInicio = LocalTime.of(7, 0);
		this.horaCierre = LocalTime.of(20, 0);
		this.precioPorHora = 40f;
		this.sem = new SEM(this.horaInicio, this.horaCierre, this.precioPorHora);
		
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
