package fr.unilim.iut.tirepressuremonitoringsystem;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AlarmTest {

	@Test
	public void alarmeSeDeclenche_EnCasDeValeurTropBasse() {
		Alarm alarm = new Alarm(sensorThatProbes(0.0), new SafetyRange(17, 21));
		alarm.check();
		assertTrue(alarm.isAlarmOn());
	}

	@Test
	public void alarmeSeDeclenche_EnCasDeValeurTropForte() {
		Alarm alarm = new Alarm(sensorThatProbes(30.0), new SafetyRange(17, 21));
		alarm.check();
		assertTrue(alarm.isAlarmOn());
	}

	@Test
	public void alarmeNeSeDeclenchePas_SiValeurDansSeuilDeSecurite() {
		Alarm alarm = new Alarm(sensorThatProbes(20.0), new SafetyRange(17, 21));
		alarm.check();
		assertFalse(alarm.isAlarmOn());
	}

	@Test
	public void uneFoisDeclenchee_alarmeResteDeclenchee_QuelleQueSoitLaValeur() {
		Alarm alarm = new Alarm(sensorThatProbes(30.0, 20.0), new SafetyRange(17, 21));

		alarm.check();
		assertTrue(alarm.isAlarmOn());

		alarm.check();
		assertTrue(alarm.isAlarmOn());
	}

	private Sensor sensorThatProbes(double value) {
		Sensor sensor = mock(Sensor.class);
		when(sensor.probeValue()).thenReturn(value);
		return sensor;
	}

	private Sensor sensorThatProbes(double value1, double value2) {
		Sensor sensor = mock(Sensor.class);
		when(sensor.probeValue()).thenReturn(value1).thenReturn(value2);
		return sensor;
	}
}