package improved;

import java.util.Timer;
import java.util.TimerTask;



public class TemperatureController implements ITemperatureChangeObserver{
	
	private  StateMachine m_fsm;
	
	private long delay =10*1000;
	private FanOnTimer fanOnTimerTask;
	private FanOffTimer fanOffTimerTask;
	private Timer onTimer,OffTimer;
	private boolean isFanOn = false;
	
	public TemperatureController( Thermostat thermostat){
		thermostat.registerObserver(this);
		m_fsm = TemperatureControllerStateMachine.getInstance();
	}
	
	@Override
	public void tempeChangeUpdate(Signal signal) {
		switch(signal){
		case SIGNAL_IDEAL_TEMPERATURE:
			System.out.println("Signal received: SIGNAL_IDEAL_TEMPERATURE");
			if(m_fsm.getCurrentState().getState() == StateList.HEATER_ON){
				try {
					m_fsm.nextState(EventActions.SWITCH_HEATER_OFF_WHEN_HEATER_ENABLED_TRANSITION).notifySignalReceived(signal);
				} catch (IllegalTransitionException e) {
					e.printStackTrace();
				}
			}else if(m_fsm.getCurrentState().getState() == StateList.AC_ON){
				try {
					m_fsm.nextState(EventActions.SWITCH_AC_OFF_WHEN_AC_ENABLED_TRANSITION).notifySignalReceived(signal);
				} catch (IllegalTransitionException e) {
					e.printStackTrace();
				}
			}
			
			break;
		case SIGNAL_TOO_COLD:
			System.out.println("Signal received: SIGNAL_TOO_COLD");
			if(m_fsm.getCurrentState().getState() == StateList.HEATER_ENABLED||m_fsm.getCurrentState().getState() == StateList.HEATER_OFF){
				try {
					m_fsm.nextState(EventActions.SWITCH_HEATER_ON_WHEN_HEATER_ENABLED_TRANSITION).notifySignalReceived(signal);
				} catch (IllegalTransitionException e) {
					e.printStackTrace();
				}
			}else if(m_fsm.getCurrentState().getState() == StateList.AC_ON){
				try {
					m_fsm.nextState(EventActions.SWITCH_AC_OFF_WHEN_AC_ENABLED_TRANSITION).notifySignalReceived(signal);
				} catch (IllegalTransitionException e) {
					e.printStackTrace();
				}
			}
			break;
		case SIGNAL_TOO_HOT:
			System.out.println("Signal received: SIGNAL_TOO_HOT");
			if(m_fsm.getCurrentState().getState() == StateList.AC_ENABLED||m_fsm.getCurrentState().getState() == StateList.AC_OFF){
				try {
					m_fsm.nextState(EventActions.SWITCH_AC_ON_WHEN_AC_ENABLED_TRANSITION).notifySignalReceived(signal);
				} catch (IllegalTransitionException e) {
					e.printStackTrace();
				}
			}else if(m_fsm.getCurrentState().getState() == StateList.HEATER_ON){
				try {
					m_fsm.nextState(EventActions.SWITCH_HEATER_OFF_WHEN_HEATER_ENABLED_TRANSITION).notifySignalReceived(signal);
				} catch (IllegalTransitionException e) {
					e.printStackTrace();
				}
			}
			break;
		default:
			break;
		}
		
	}
	
	public void setHeaterModeOn(){
		try {
			if(isFanOn){
				m_fsm.nextState(EventActions.SWITCH_FAN_OFF_TRANSITION).process();
				isFanOn = false;
				m_fsm.reset();
			}
			if(OffTimer != null){
				OffTimer.cancel();
				m_fsm.reset();
			}
			if(onTimer != null){
				onTimer.cancel();
				m_fsm.reset();
			}	
			m_fsm.nextState(EventActions.SET_HEATER_MODE_ACTIVE_TRANSITION).process();
			
		} catch (IllegalTransitionException e) {
			e.printStackTrace();
		}
		
	}
	

	public void switchFanOn(){
		try {
			m_fsm.nextState(EventActions.SWITCH_FAN_ON_TRANSITION).process();
			isFanOn = true;
			isFanOff = false;
			if(OffTimer != null)
				OffTimer.cancel();
			OffTimer = new Timer();
			fanOffTimerTask = new FanOffTimer();
			OffTimer.schedule(fanOffTimerTask, delay);
			
		} catch (IllegalTransitionException e) {
			e.printStackTrace();
		}
	}

	private boolean isFanOff = false;
	public void switchFanOff() throws IllegalTransitionException {
		m_fsm.nextState(EventActions.SWITCH_FAN_OFF_TRANSITION).process();
		isFanOn = false;
		isFanOff = true;
		if(onTimer != null)
			onTimer.cancel();
		onTimer = new Timer();
		fanOnTimerTask = new FanOnTimer();
		onTimer.schedule(fanOnTimerTask, delay);
		
	}
	
	public void setAcModeEnabled() {
		if(isFanOn){
			try {
				m_fsm.nextState(EventActions.SWITCH_FAN_OFF_TRANSITION).process();
				isFanOn = false;
				m_fsm.reset();
			} catch (IllegalTransitionException e) {
				e.printStackTrace();
			}
		}
		if(OffTimer != null){
			OffTimer.cancel();
			m_fsm.reset();
		}
		if(onTimer != null){
			onTimer.cancel();
			m_fsm.reset();
		}			
		try {
			m_fsm.nextState(EventActions.SET_AC_MODE_ACTIVE_TRANSITION).process();
		} catch (IllegalTransitionException e) {
			e.printStackTrace();
		}
		
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
			try {
				switchFanOff();
			} catch (IllegalTransitionException e) {
				e.printStackTrace();
			}
			
		}
		  
	  }

	public void setListener(TemperatureControllerGui window) {
		m_fsm.registerAllStateWithGUi(window);
		
	}
		
	public void setOffDoNothingMode(){
			m_fsm.reset();
			m_fsm.getCurrentState().process();
	}
	
}
