package improved;

public class AcOffState extends BaseState{

	@Override
	public void process() {
		String message = "The room met the ideal temperature. Switching off the AC now";
		System.out.println(message);
		notifyObservers(message);
	}

	
	@Override
	public void notifySignalReceived(Signal sig) {
		process();
	}

	@Override
	public StateList getState() {
		return StateList.AC_OFF;
	}

}
