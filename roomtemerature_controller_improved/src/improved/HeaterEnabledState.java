package improved;

public class HeaterEnabledState extends BaseState  {
	
	
	@Override
	public void process() {
		String message = "System is in Heater Enabled state";
		System.out.println(message);
		notifyObservers(message);
	}

	
	public void notifySignalReceived(Signal sig){
	}
	
	@Override
	public StateList getState() {
		return StateList.HEATER_ENABLED;
	}	

}
