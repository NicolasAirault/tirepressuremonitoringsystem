
package fr.unilim.iut.tirepressuremonitoringsystem;

public class Alarm
{
    private final double lowPressure;
    private final double highPressure;
    boolean alarmOn;
    Sensor sensor;

    public Alarm(Sensor sensor){
    	this.sensor = sensor;
    	this.alarmOn = false;
    	this.lowPressure = 17;
    	this.highPressure = 21;
    	
    }
    

    public void check()
    {
        double psiPressureValue = getPsiPressureValue();
        checkAlarm(psiPressureValue);
    }


	private double getPsiPressureValue() {
		return sensor.popNextPressurePsiValue();
		
	}

	private void checkAlarm(double psiPressureValue) {
		if (psiPressureValue < lowPressure || highPressure < psiPressureValue)
        {
            alarmOn = true;
        }
	}

    public boolean isAlarmOn()
    {
        return alarmOn; 
    }
}
