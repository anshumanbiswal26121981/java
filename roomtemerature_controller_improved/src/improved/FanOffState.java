package improved;

public class FanOffState extends BaseState{

	@Override
	public void process() {
		String message = "Switching the Fan Off . It will start after 10 minutes";
		System.out.println(message);
		notifyObservers(message);
	}

	@Override
	public StateList getState() {
		return StateList.FAN_OFF;
	}

}
