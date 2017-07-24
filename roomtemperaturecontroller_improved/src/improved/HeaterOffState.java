package improved;

public class HeaterOffState extends BaseState{

	
	@Override
	public void process() {
		String message = "The room is set to idealTemp. Switching the heater off";
		System.out.println(message);
		notifyObservers(message);
	}

	@Override
	public void notifySignalReceived(Signal sig) {
		process();
	}

	@Override
	public StateList getState() {
		return StateList.HEATER_OFF;
	}

}
