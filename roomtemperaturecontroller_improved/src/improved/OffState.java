package improved;

public class OffState extends BaseState{

	
	@Override
	public void process() {
		String message = "I am now in Off state which is do nothing state";
		System.out.println(message);
		notifyObservers(message);
		
	}


	@Override
	public void notifySignalReceived(Signal sig) {
	}

	@Override
	public StateList getState() {
		return StateList.OFF;
	}

}
