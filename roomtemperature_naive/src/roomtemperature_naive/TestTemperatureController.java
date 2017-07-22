package roomtemperature_naive;

import static org.junit.Assert.*;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.Before;
import org.junit.Test;


public class TestTemperatureController {

	private TemperatureController tc = null;
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
 
	@Before
	public void testSetup(){
		tc = new TemperatureController();
		TemperatureControllerGui window = new TemperatureControllerGui();
		tc.setHandle(window);
	}
	
	@Test
	public void testTemperatureController() {
		assertNotNull(tc);
	}
	
	@Test
	public void testSetMode() {
		tc.setMode(0);
		assertSame(tc.getState(), OFF);
		tc.setMode(7);
		assertSame(tc.getState(),HEATER_ENABLED);
		tc.setMode(8);
		assertSame(tc.getState(),AC_ENABLED);
		tc.setMode(5);
		assertSame(tc.getState(),FAN_ON);
	}
	

	@Test
	public void testSetCurrenttemp() {
		tc.setMode(HEATER_ENABLED);
		tc.setRoomTemperature(20);
		validateTheSetCurrentTemp(10);
		validateTheSetCurrentTemp(20);
		tc.setMode(AC_ENABLED);
		assertTrue(tc.getState()== AC_ENABLED);
		tc.setRoomTemperature(20);
		validateTheSetCurrentTemp(35);
		validateTheSetCurrentTemp(20);
		
		
		
		
	}
	private void validateTheSetCurrentTemp(float temp) {
		tc.setCurrenttemp(temp);
		assertTrue(temp == tc.getCurrentTemp());
		validateStateAndSubState();
	}
	private void validateStateAndSubState() {
		if(tc.getCurrentTemp() < tc.getRoomtemp()){
			assertTrue(SIGNAL_TOO_COLD == tc.getSignal());
			assertTrue(tc.getState() == HEATER_ENABLED);
			if(tc.getSubState() != HEATER_ON){
				assertTrue(tc.getSubState()==HEATER_ON);
			}
		}else if(tc.getCurrentTemp() > tc.getRoomtemp()){
			assertTrue(SIGNAL_TOO_HOT == tc.getSignal());
			assertTrue(tc.getState() == AC_ENABLED);
			if(tc.getSubState() != AC_ON){
				assertTrue(tc.getSubState()==AC_ON);
			}
		}else if(tc.getCurrentTemp() == tc.getRoomtemp()){
			assertTrue(SIGNAL_IDEAL_TEMP == tc.getSignal());
			if(tc.getSubState() == AC_ON){
				assertTrue(tc.getSubState()==AC_OFF);
			}else if(tc.getSubState() ==  HEATER_ON){
				assertTrue(tc.getSubState() == HEATER_OFF);
			}
		}
	}

	

	private FanOnTimer fanOnTimerTask;
	private FanOffTimer fanOffTimerTask;
	private Timer onTimer,OffTimer;
	long delay = 10*1000;
	@Test
	public void testSwitchFanOn() {
		tc.setMode(5);// FAN ON
		validateFanIsSwitchedOn();
		try {
			Thread.sleep(40*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void validateFanIsSwitchedOn() {
		assertTrue(5==tc.getState());
		if(OffTimer != null)
			OffTimer.cancel();
		OffTimer = new Timer();
		fanOffTimerTask = new FanOffTimer();
		OffTimer.schedule(fanOffTimerTask, delay);
	}

	
	private class FanOnTimer extends TimerTask{
		  
		@Override
		public void run() {
			validateFanIsSwitchedOn();
		}
		  
	  }

	private void validateFanIsSwitchedOff() {
		assertTrue(FAN_OFF==tc.getState());
		if(onTimer != null)
			onTimer.cancel();
		onTimer = new Timer();
		fanOnTimerTask = new FanOnTimer();
		onTimer.schedule(fanOnTimerTask, delay);
		
	}
	 private class FanOffTimer extends TimerTask{

		@Override
		public void run() {
			validateFanIsSwitchedOff();
			
		}
		  
	  }
	
}
