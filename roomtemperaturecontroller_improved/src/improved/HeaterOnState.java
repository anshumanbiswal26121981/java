package improved;

public class HeaterOnState extends BaseState{

	@Override
	public void process() {
		String message = "Switching on the heater now. The room will start getting warm";
		System.out.println(message);
		notifyObservers(message);
	}


	@Override
	public void notifySignalReceived(Signal sig) {
		  process();
	}

	@Override
	public StateList getState() {
		return StateList.HEATER_ON;
	}

}
