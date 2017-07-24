package improved;

public class AcEnabledState extends BaseState {
	
	@Override
	public void process() {
		String message = "System is in AC Enabled state";
		System.out.println(message)	;	
		notifyObservers(message);
	}


	@Override
	public void notifySignalReceived(Signal sig) {
	}

	@Override
	public StateList getState() {
		return StateList.AC_ENABLED;
	}
	
}
