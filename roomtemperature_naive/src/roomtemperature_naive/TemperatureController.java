package roomtemperature_naive;

import java.util.Timer;
import java.util.TimerTask;

public class TemperatureController {
	 
	private final static int OFF = 0;
	private final static int HEATER_ON = 1;
	private final static int HEATER_OFF = 2;
	private final static int AC_ON = 3;
	private final static int AC_OFF = 4;
	private final static int FAN_ON = 5;
	private final static int FAN_OFF = 6;
	private final static int HEATER_ENABLED = 7;
	private final static int AC_ENABLED=8;
	private final static int SIGNAL_TOO_COLD = 888;
	private final static int SIGNAL_TOO_HOT = 999;
	private final static int SIGNAL_IDEAL_TEMP = 9;
 
	private int state = OFF;
	private int subState = -1;
	private float minTemp = 10;
	private float maxTemp = 32;
	private float currentTemp;
	private float roomTemp = 22;
	private int mSignal;
	
	public int getSignal(){
		return mSignal;
	}
	
	public int getState(){
		return state;
	}
	
	public float getMinTemp(){
		return minTemp;
	}
	public float getMaxTemp(){
		return maxTemp;
	}
	public TemperatureController() {
	}
	
	public void setCurrenttemp(float temp){
		currentTemp = temp;
		System.out.println("Current temperature is "+ currentTemp);
		if(currentTemp < roomTemp){
			setSignal(SIGNAL_TOO_COLD);
		}else if(currentTemp > roomTemp){
			setSignal(SIGNAL_TOO_HOT);
		}else if(currentTemp == roomTemp){
			setSignal(SIGNAL_IDEAL_TEMP);
		}
	}
	
	public float getCurrentTemp(){
		return currentTemp;
	}
	public void setMode(int mode){
		
		switch(mode){
		case HEATER_ENABLED:
			setHeaterModeActive();
			break;
		case AC_ENABLED:
			setAcModeActive();
			break;
		case FAN_ON:
			switchFanOn();
			break;
		case OFF:
			doNothing();
			break;
		}
	}
	public void setRoomTemperature(float rTemp){
		roomTemp = rTemp;
	}
	
	public float getRoomtemp(){
		return roomTemp;
	}
	public int getSubState(){
		return subState;
	}
	
	private void setSignal(int signal) {
		mSignal = signal;
		if(OffTimer != null){
			OffTimer.cancel();
		}
		if(onTimer != null){
			onTimer.cancel();
		}	
		switch(signal){
		
		case SIGNAL_TOO_HOT:
			System.out.println("Signal received: SIGNAL_TOO_HOT");
			if(state == AC_ENABLED ){
					if(subState != AC_ON ){
						switchACOn();
					}
			}
			break;
		case SIGNAL_TOO_COLD:
			System.out.println("Signal received: SIGNAL_TOO_COLD");
			if(state == HEATER_ENABLED){
				if(subState != HEATER_ON ){
					switchHeaterOn();
				}
			}
			break;
		case SIGNAL_IDEAL_TEMP:
			System.out.println("Signal received: SIGNAL_IDEAL_TEMP");
			if(subState == HEATER_ON){
				switchHeaterOff();
			}else if(subState == AC_ON){
				switchAcOff();
			}
			break;
		}
		
	}


	
	private void setHeaterModeActive(){
		state = HEATER_ENABLED;
		System.out.println("Slider is moved to Heater. Heater mode is set");
		mWindow.updateSystemOutputWindow("Slider is moved to Heater. Heater mode is set");
		
	}
	
	private void doNothing(){
		state = OFF;
		if(onTimer != null){
			onTimer.cancel();
			onTimer = null;
		}
		if(OffTimer != null){
			OffTimer.cancel();
			OffTimer = null;
		}
		System.out.println("Slider moved to Off i.e. do nothing mode");
	}
	private void setAcModeActive(){
		state = AC_ENABLED;
		System.out.println("Slider is moved to AC. AC mode is set");
		mWindow.updateSystemOutputWindow("Slider is moved to AC. AC mode is set");
				
	}
	private void switchHeaterOn() {
		subState = HEATER_ON;
		System.out.println("Switching on the heater now. The room will start getting warm");
		mWindow.updateSystemOutputWindow("Switching on the heater now. The room will start getting warm");
	}

	private void switchHeaterOff() {
		subState = HEATER_OFF;
		System.out.println("The room is set to idealTemp. Switching the heater off");
		mWindow.updateSystemOutputWindow("The room is set to idealTemp. Switching the heater off");
	}
 
	private void switchACOn() {
		subState = AC_ON;
		System.out.println("Switching AC on .The room will cool down");
		mWindow.updateSystemOutputWindow("Switching AC on .The room will cool down");
	}

	private void switchAcOff(){
		subState = AC_OFF;
		System.out.println("The room is set to idealtemp.Switching of AC now");
		mWindow.updateSystemOutputWindow("The room is set to idealtemp.Switching of AC now");
	}
	
	private long delay =3*1000;
	private FanOnTimer fanOnTimerTask;
	private FanOffTimer fanOffTimerTask;
	private Timer onTimer,OffTimer;
	
	public void switchFanOn() {
		state = FAN_ON;
		System.out.println("Switching the Fan On . It will stop after 10 minutes");
		mWindow.updateSystemOutputWindow("Switching the Fan On . It will stop after 10 minutes");
		if(OffTimer != null)
			OffTimer.cancel();
		OffTimer = new Timer();
		fanOffTimerTask = new FanOffTimer();
		OffTimer.schedule(fanOffTimerTask, delay);
	}
 
	public void switchFanOff() {
		state = FAN_OFF;
		System.out.println("Switching the fan off. It will start after 10 minutes");
		mWindow.updateSystemOutputWindow("Switching the fan off. It will start after 10 minutes");
		if(onTimer != null)
			onTimer.cancel();
		onTimer = new Timer();
		fanOnTimerTask = new FanOnTimer();
		onTimer.schedule(fanOnTimerTask, delay);
	}
 
  private class FanOnTimer extends TimerTask{
	  
	@Override
	public void run() {
			switchFanOn();
		
	}
	  
  }
  
 private class FanOffTimer extends TimerTask{

	@Override
	public void run() {
		switchFanOff();
		
	}
	  
  }
private TemperatureControllerGui mWindow;
public void setHandle(TemperatureControllerGui window) {
	mWindow = window;	
}
	
}


