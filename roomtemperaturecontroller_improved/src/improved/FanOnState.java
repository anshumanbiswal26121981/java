package improved;

public class FanOnState extends BaseState{
	@Override
	public void process() {
		String message = "Switching the Fan On . It will stop after 10 minutes";
		System.out.println(message);
		notifyObservers(message);
	}

	@Override
	public StateList getState() {
		return StateList.FAN_ON;
	}

}
