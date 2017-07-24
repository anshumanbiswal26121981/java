package improved;

public class AcOnState extends BaseState{

	
	@Override
	public void process() {
		String message = "Switching on the AC now. The room will start to cool down";
		System.out.println(message);
		notifyObservers(message);
	}

	
	@Override
	public void notifySignalReceived(Signal sig) {
		process();
	}

	@Override
	public StateList getState() {
		return StateList.AC_ON;
	}

}
