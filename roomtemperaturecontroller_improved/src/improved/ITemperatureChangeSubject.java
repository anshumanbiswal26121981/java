package improved;

public interface ITemperatureChangeSubject {

	public void registerObserver(ITemperatureChangeObserver o);
	public void removeObserver(ITemperatureChangeObserver o);
	public void notifyObservers(Signal signal);
}

