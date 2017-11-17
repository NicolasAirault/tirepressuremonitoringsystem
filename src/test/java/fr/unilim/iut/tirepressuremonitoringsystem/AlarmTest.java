package fr.unilim.iut.tirepressuremonitoringsystem;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class AlarmTest {

	Alarm alarm;
	
	
	@Test
	public void test_alarme_pression_basse() {
		Sensor sensor = mock(Sensor.class);
		when(sensor.popNextPressurePsiValue()).thenReturn(15.0);
		alarm = new Alarm(sensor);
		alarm.check();
		assertEquals(alarm.isAlarmOn(), true);
	}
	
	@Test
	public void test_alarme_pression_haute(){
		Sensor sensor = mock(Sensor.class);
		when(sensor.popNextPressurePsiValue()).thenReturn(50.0);
		alarm = new Alarm(sensor);
		alarm.check();
		assertTrue(alarm.isAlarmOn());
	}
	
	@Test
	public void test_pas_alarm_pression_juste(){
		Sensor sensor = mock(Sensor.class);
		when(sensor.popNextPressurePsiValue()).thenReturn(18.0);
		alarm = new Alarm(sensor);
		alarm.check();
		assertFalse(alarm.isAlarmOn());
	}
	
	@Test
	public void test_alarm_enclenche_constant(){
		Sensor sensor = mock(Sensor.class);
		when(sensor.popNextPressurePsiValue()).thenReturn(15.0).thenReturn(25.0);
		alarm = new Alarm(sensor);
		alarm.check();
		assertTrue(alarm.isAlarmOn());
		
	}

}
